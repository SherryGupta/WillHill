package com.iterator;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class IterateOverIterators<T> implements Iterator<T> {
    private final static Logger logger = Logger.getLogger(IterateOverIterators.class.getName());
    private Iterator<T>[] iterators=null;
    private Iterator mergedIterator;


    public IterateOverIterators(Iterator<T>... iterators) {
        this.iterators = iterators;
    }

    @Override
    public boolean hasNext() {
    return mergedIterator.hasNext();
    }

    @Override
    public T next() {
        return (T) mergedIterator.next();
    }

    @Override
    public void remove()  {
        try {
            throw new OperationNotAllowedException("This operation is not allowed");
        } catch (OperationNotAllowedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     * Its easy to create an Iterator of Iterator(Unsorted)  as we have to just change the implementation of overidden method hasnext(), next() and remove()
     * to look for all Iterators.
     * however sorted iteration is tricky. I tried to use SplitIterator and Stream of stream for each iterators but merging of streams was not working for me.
     *
     * Performace point of views my implementation is not great as I am merging all collection first. I used parallel stream to sort my list to improve the
     * performance.
     * To select a collection , i had a choice between arraylist and Treeset (As we have unique numbers). Arraylist sorting is expensive but data retrieval is o(1). For TreeSet sorting
     * is cheaper but then data retrieval is compartively expensive. It all depends what type of operation are more frequent in collection.
     */
    public Iterator<T> geIterator() {
        List<T> list = new ArrayList();

        for(Iterator<T> iterator: iterators) {
            iterator.forEachRemaining(list::add);
        }
        list = list.parallelStream().sorted().collect(Collectors.toList());
        logger.log(Level.FINE,"list size is"+list.size());
         return list.iterator();

        }

}
