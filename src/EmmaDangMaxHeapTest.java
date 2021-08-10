import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class EmmaDangMaxHeapTest {
    private MaxHeap<String> heap;
    private static final int TIME_OUT = 200;
    private static final int INITIAL_CAPACITY = 13;

    @Before
    public void setUp() {
        heap = new MaxHeap<>();
    }

    @Test(timeout = TIME_OUT)
    public void testInitialization() {
        assertEquals(0, heap.size());
        Comparable[] testArray = new String[INITIAL_CAPACITY];
        assertArrayEquals(testArray, heap.getBackingArray());
    }

    @Test(timeout = TIME_OUT)
    public void testConstructor1() {
        // pass in an empty ArrayList
        ArrayList<String> list = new ArrayList<>();
        heap = new MaxHeap<>(list);
        assertEquals(0, heap.size());
        Comparable[] testArray = new String[1];
        assertArrayEquals(testArray, heap.getBackingArray());
    }

    @Test(timeout = TIME_OUT)
    public void testConstructor2() {
        // pass in a 1-element ArrayList
        ArrayList<String> list = new ArrayList<>();
        list.add("01a");
        heap = new MaxHeap<>(list);
        assertEquals(1, heap.size());
        Comparable[] testArray = new String[2 * 1 + 1];
        testArray[1] = "01a";
        assertArrayEquals(testArray, heap.getBackingArray());
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testConstructor3() {
        // pass in a null data
        heap = new MaxHeap<>(null);
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testConstructor4() {
        // pass in an arrayList which contains a null element
        ArrayList<String> list = new ArrayList<>();
        list.add("01a");
        list.add(null);
        list.add("02a");
        heap = new MaxHeap<>(list);
    }

    @Test(timeout = TIME_OUT)
    public void testConstructor5() {
        // pass in a 5-element arraylist
        ArrayList<String> list = new ArrayList<>();
        list.add("33a");
        list.add("66a");
        list.add("08a");
        list.add("89a");
        list.add("34a");
        heap = new MaxHeap<>(list);
        String[] testArray = new String[2 * 5 + 1];
        testArray[1] = "89a";
        testArray[2] = "66a";
        testArray[3] = "08a";
        testArray[4] = "33a";
        testArray[5] = "34a";
        assertEquals(5, heap.size());
        assertArrayEquals(testArray, heap.getBackingArray());
    }

    @Test(timeout = TIME_OUT)
    public void testAdd1() {
        // add to an empty heap
        heap.add("01a");
        assertEquals(1, heap.size());
        String[] testArray = new String[INITIAL_CAPACITY];
        testArray[1] = "01a";
        assertArrayEquals(testArray, heap.getBackingArray());
    }

    @Test(timeout = TIME_OUT)
    public void testAdd2() {
        // add to a non-empty heap
        testConstructor5();
        heap.add("73a");
        String[] testArray = new String[2 * 5 + 1];
        testArray[1] = "89a";
        testArray[2] = "66a";
        testArray[3] = "73a";
        testArray[4] = "33a";
        testArray[5] = "34a";
        testArray[6] = "08a";
        assertEquals(6, heap.size());
        assertArrayEquals(testArray, heap.getBackingArray());
    }

    @Test(timeout = TIME_OUT)
    public void testAdd3() {
        // add 14 elements. Test resize
        heap.add("44a");
        heap.add("15a");
        heap.add("01a");
        heap.add("40a");
        heap.add("47a");
        heap.add("45a");
        heap.add("72a");
        heap.add("03a");
        heap.add("19a");
        heap.add("07a");
        heap.add("13a");
        heap.add("92a");
        heap.add("68a");
        heap.add("89a");
        assertEquals(14, heap.size());
        String[] testArray = new String[26];
        testArray[1] = "92a";
        testArray[2] = "44a";
        testArray[3] = "89a";
        testArray[4] = "19a";
        testArray[5] = "40a";
        testArray[6] = "68a";
        testArray[7] = "72a";
        testArray[8] = "03a";
        testArray[9] = "15a";
        testArray[10] = "07a";
        testArray[11] = "13a";
        testArray[12] = "01a";
        testArray[13] = "47a";
        testArray[14] = "45a";
        assertArrayEquals(testArray, heap.getBackingArray());
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testAdd4() {
        // add a null element. Expect exception
        heap.add(null);
    }

    @Test(timeout = TIME_OUT, expected = NoSuchElementException.class)
    public void testRemove1() {
        // remove from an empty heap raise exception
        heap.remove();
    }

    @Test(timeout = TIME_OUT)
    public void testRemove2() {
        // remove from a 1-element heap
        testAdd1();
        assertEquals("01a", heap.remove());
        assertEquals(0, heap.size());
    }

    @Test(timeout = TIME_OUT)
    public void testRemove3() {
        // remove 1-element from a heap with 5 elements
        testConstructor5();
        assertEquals("89a", heap.remove());
        String[] testArray = new String[2 * 5 + 1];
        testArray[1] = "66a";
        testArray[2] = "34a";
        testArray[3] = "08a";
        testArray[4] = "33a";
        assertEquals(4, heap.size());
        assertArrayEquals(testArray, heap.getBackingArray());
    }

    @Test(timeout = TIME_OUT)
    public void testRemove4() {
        // remove until half empty
        testConstructor5();
        assertEquals("89a", heap.remove());
        assertEquals("66a", heap.remove());
        assertEquals("34a", heap.remove());
        assertEquals(2, heap.size());
        String[] testArray = new String[5 * 2 + 1];
        testArray[1] = "33a";
        testArray[2] = "08a";
        assertArrayEquals(testArray, heap.getBackingArray());
    }

    @Test(timeout = TIME_OUT)
    public void testRemove5() {
        // remove until empty
        testAdd3();
        for (int i = 0; i < 14; i++) {
            heap.remove();
        }
        String[] testArray = new String[13 * 2];
        assertArrayEquals(testArray, heap.getBackingArray());
        assertEquals(0, heap.size());
    }

    @Test(timeout = TIME_OUT, expected = NoSuchElementException.class)
    public void testGetMax1() {
        // GetMax from an empty heap
        heap.getMax();
    }

    @Test(timeout = TIME_OUT)
    public void testGetMax2() {
        // GetMax from a 1-element heap
        heap.add("19a");
        assertEquals("19a", heap.getMax());
        assertEquals(1, heap.size());
    }

    @Test(timeout = TIME_OUT)
    public void testGetMax3() {
        // GetMax from a non-empty heap
        testConstructor5();
        assertEquals("89a", heap.getMax());
    }

    @Test(timeout = TIME_OUT)
    public void testIsEmpty1() {
        // test an empty heap
        assertTrue(heap.isEmpty());
    }

    @Test(timeout = TIME_OUT)
    public void testIsEmpty2() {
        // test a non-empty heap
        testConstructor5();
        assertFalse(heap.isEmpty());
    }

    @Test(timeout = TIME_OUT)
    public void testClear1() {
        // test clear() on an empty heap
        heap.clear();
        assertEquals(0, heap.size());
        String[] testArray = new String[INITIAL_CAPACITY];
        assertArrayEquals(testArray, heap.getBackingArray());
    }

    @Test(timeout = TIME_OUT)
    public void testClear2() {
        // test clear on a non-empty heap
        testConstructor5();
        heap.clear();
        assertEquals(0, heap.size());
        String[] testArray = new String[INITIAL_CAPACITY];
        assertArrayEquals(testArray, heap.getBackingArray());
    }
}
