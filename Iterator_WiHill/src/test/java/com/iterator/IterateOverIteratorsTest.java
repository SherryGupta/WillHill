package com.iterator;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class IterateOverIteratorsTest {

    @Test
    public void testConcatWithManyIterators() {
        List<Integer> list1 = Arrays.asList(1, 4, 6);
        List<Integer> list2 = Arrays.asList(3, 7);


        IterateOverIterators<Integer> its = new IterateOverIterators<>(
                list1.iterator(),
                list2.iterator());
        Iterator iterator = its.geIterator();

        assertEquals(Integer.valueOf(1), iterator.next());
        assertEquals(Integer.valueOf(3), iterator.next());
        assertEquals(Integer.valueOf(4), iterator.next());
        assertEquals(Integer.valueOf(6), iterator.next());
        assertEquals(Integer.valueOf(7), iterator.next());
    }

    @Test
    public void testConcatWithEmptyCollections() {
        List<Integer> list1 = Arrays.asList(1, 4, 6);
        List<Integer> list2 = Arrays.asList(3, 7);
        List<Integer> list3 = Arrays.asList();


        IterateOverIterators<Integer> its = new IterateOverIterators<>(
                list1.iterator(),
                list2.iterator(),
                list3.iterator());
        Iterator iterator = its.geIterator();

        assertEquals(Integer.valueOf(1), iterator.next());
        assertEquals(Integer.valueOf(3), iterator.next());
        assertEquals(Integer.valueOf(4), iterator.next());
        assertEquals(Integer.valueOf(6), iterator.next());
        assertEquals(Integer.valueOf(7), iterator.next());
    }

    @Test
    public void testConcatWithStringCollections() {
        List<String> list1 = Arrays.asList("hello1","hello4", "hello5");
        List<String> list2 = Arrays.asList("hello2","hello3");



        IterateOverIterators<String> its = new IterateOverIterators<>(
                list1.iterator(),
                list2.iterator());

        Iterator iterator = its.geIterator();

        assertEquals("hello1",iterator.next());
        assertEquals("hello2",iterator.next());
        assertEquals("hello3",iterator.next());
        assertEquals("hello4",iterator.next());
        assertEquals("hello5",iterator.next());
    }

}
