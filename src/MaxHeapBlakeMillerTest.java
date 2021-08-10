import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class MaxHeapBlakeMillerTest {
    private static final int TIMEOUT = 200;
    private MaxHeap<Integer> heap;

    @Before
    public void setUp() {
        heap = new MaxHeap<>();

        for (int i = 1; i <= 12; i++) {
            heap.add(i);
        }

        /*
                           12
                  10                  11
              7       9            6      5
            1   4   3   8        2
         */
    }

    @Test
    public void testBuildHeap() {
        MaxHeap<String> testHeap = new MaxHeap<>();
        ArrayList<String> toAdd = new ArrayList<>();
        toAdd.add("e");
        toAdd.add("c");
        toAdd.add("a");
        toAdd.add("b");
        toAdd.add("d");
        toAdd.add("g");
        toAdd.add("f");
        toAdd.add("h");
        toAdd.add("j");
        toAdd.add("i");
        toAdd.add("l");
        toAdd.add("k");
        toAdd.add("m");

        String[] expected = new String[27];
        expected[1] = "m";
        expected[2] = "l";
        expected[3] = "k";
        expected[4] = "j";
        expected[5] = "i";
        expected[6] = "g";
        expected[7] = "f";
        expected[8] = "h";
        expected[9] = "b";
        expected[10] = "c";
        expected[11] = "d";
        expected[12] = "a";
        expected[13] = "e";

        testHeap = new MaxHeap<>(toAdd);
        assertArrayEquals(expected, testHeap.getBackingArray());
        assertEquals(13, testHeap.size());
    }

    @Test
    public void testBuildHeap2() {
        MaxHeap<Double> testHeap = new MaxHeap<>();
        ArrayList<Double> toAdd = new ArrayList<>();

        toAdd.add(0.5D);
        toAdd.add(0.2D);
        toAdd.add(0.3D);

        Double[] expected = new Double[7];
        expected[1] = 0.5D;
        expected[2] = 0.2D;
        expected[3] = 0.3D;

        testHeap = new MaxHeap<>(toAdd);
        assertArrayEquals(expected, testHeap.getBackingArray());
        assertEquals(3, testHeap.size());
    }

    @Test
    public void testBuildHeap3() {
        MaxHeap<Integer> testHeap = new MaxHeap<>();
        ArrayList<Integer> toAdd = new ArrayList<>();

        toAdd.add(14);
        toAdd.add(15);
        toAdd.add(0);
        toAdd.add(1);
        toAdd.add(2);
        toAdd.add(9);
        toAdd.add(16);
        toAdd.add(4);
        toAdd.add(15);
        toAdd.add(14);
        toAdd.add(0);

        Integer[] expected = new Integer[23];
        expected[1] = 16;
        expected[2] = 15;
        expected[3] = 14;
        expected[4] = 15;
        expected[5] = 14;
        expected[6] = 9;
        expected[7] = 0;
        expected[8] = 4;
        expected[9] = 1;
        expected[10] = 2;
        expected[11] = 0;

        testHeap = new MaxHeap<>(toAdd);
        assertArrayEquals(expected, testHeap.getBackingArray());
        assertEquals(11, testHeap.size());
    }

    @Test
    public void addToHeap() {
        heap.add(13);

        Integer[] expected = new Integer[26];
        expected[1] = 13;
        expected[2] = 10;
        expected[3] = 12;
        expected[4] = 7;
        expected[5] = 9;
        expected[6] = 11;
        expected[7] = 5;
        expected[8] = 1;
        expected[9] = 4;
        expected[10] = 3;
        expected[11] = 8;
        expected[12] = 2;
        expected[13] = 6;

        assertArrayEquals(expected, heap.getBackingArray());
        assertEquals(13, heap.size());
    }

    @Test
    public void addToHeap2() {
        MaxHeap<String> testHeap = new MaxHeap<>();
        testHeap.add("a");

        String[] expected = new String[13];
        expected[1] = "a";

        assertArrayEquals(expected, testHeap.getBackingArray());
        assertEquals(1, testHeap.size());
    }

    @Test
    public void addToHeap3() {
        MaxHeap<Integer> testHeap = new MaxHeap<>();
        testHeap.add(3);
        testHeap.add(4);
        testHeap.add(2);
        testHeap.add(1);

        Integer[] expected = new Integer[13];
        expected[1] = 4;
        expected[2] = 3;
        expected[3] = 2;
        expected[4] = 1;

        assertArrayEquals(expected, testHeap.getBackingArray());
        assertEquals(4, testHeap.size());
    }

    @Test
    public void addToHeapDuplicates() {
        MaxHeap<Integer> testHeap = new MaxHeap<>();
        Integer dup = Integer.valueOf(15);

        testHeap.add(-2);
        testHeap.add(4);
        testHeap.add(3);
        testHeap.add(dup);
        testHeap.add(5);
        testHeap.add(dup);
    }

    @Test
    public void removeFromHeap() {
        heap.remove();

        Integer[] expected = new Integer[13];
        expected[1] = 11;
        expected[2] = 10;
        expected[3] = 6;
        expected[4] = 7;
        expected[5] = 9;
        expected[6] = 2;
        expected[7] = 5;
        expected[8] = 1;
        expected[9] = 4;
        expected[10] = 3;
        expected[11] = 8;

        assertArrayEquals(expected, heap.getBackingArray());

        heap.remove();

        expected = new Integer[13];
        expected[1] = 10;
        expected[2] = 9;
        expected[3] = 6;
        expected[4] = 7;
        expected[5] = 8;
        expected[6] = 2;
        expected[7] = 5;
        expected[8] = 1;
        expected[9] = 4;
        expected[10] = 3;

        assertArrayEquals(expected, heap.getBackingArray());

        heap.remove();

        expected = new Integer[13];
        expected[1] = 9;
        expected[2] = 8;
        expected[3] = 6;
        expected[4] = 7;
        expected[5] = 3;
        expected[6] = 2;
        expected[7] = 5;
        expected[8] = 1;
        expected[9] = 4;

        assertArrayEquals(expected, heap.getBackingArray());

        heap.remove();

        expected = new Integer[13];
        expected[1] = 8;
        expected[2] = 7;
        expected[3] = 6;
        expected[4] = 4;
        expected[5] = 3;
        expected[6] = 2;
        expected[7] = 5;
        expected[8] = 1;

        assertArrayEquals(expected, heap.getBackingArray());

        heap.remove();

        expected = new Integer[13];
        expected[1] = 7;
        expected[2] = 4;
        expected[3] = 6;
        expected[4] = 1;
        expected[5] = 3;
        expected[6] = 2;
        expected[7] = 5;

        assertArrayEquals(expected, heap.getBackingArray());

        assertEquals(7, heap.size());
    }

    @Test
    public void getMax() {
        assertEquals(Integer.valueOf(12), heap.getMax());
    }

    @Test
    public void getMax2() {
        MaxHeap<Integer> testHeap = new MaxHeap<>();
        for (int i = 0; i < 50; i++) {
            testHeap.add(i);
        }

        assertEquals(Integer.valueOf(49), testHeap.getMax());
    }

    @Test
    public void isEmpty() {
        assertFalse(heap.isEmpty());
    }

    @Test
    public void isEmpty2() {
        MaxHeap<String> testHeap = new MaxHeap<>();
        assertTrue(testHeap.isEmpty());
    }

    @Test
    public void isEmpty3() {
        MaxHeap<Integer> testHeap = new MaxHeap<>();

        for (int i = 0; i < 50; i++) {
            testHeap.add(i);
        }
        for (int i = 0; i < 50; i++) {
            testHeap.remove();
        }

        assertTrue(testHeap.isEmpty());
    }

    @Test
    public void clear() {
        MaxHeap<Integer> testHeap = new MaxHeap<>();

        for (int i = 0; i < 50; i++) {
            testHeap.add(i);
        }

        testHeap.clear();

        assertEquals(0, testHeap.size());
        assertArrayEquals(new Integer[13], testHeap.getBackingArray());
        assertTrue(testHeap.isEmpty());
    }

    @Test
    public void resizeTest() {
        ArrayList<Integer> toAdd = new ArrayList<>();
        toAdd.add(Integer.valueOf(1));

        MaxHeap<Integer> testHeap = new MaxHeap<>(toAdd);

        testHeap.add(2);
        testHeap.add(5);

        Integer[] expected = new Integer[6];
        expected[1] = 5;
        expected[2] = 1;
        expected[3] = 2;

        assertArrayEquals(expected, testHeap.getBackingArray());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddNullData() {
        heap.add(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void removeEmptyHeap() {
        MaxHeap<Integer> testHeap = new MaxHeap<>();
        testHeap.remove();
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void buildNullHeap() {
        ArrayList<Integer> toAdd = new ArrayList<>();
        toAdd.add(Integer.valueOf(1));
        toAdd.add(2);
        toAdd.add(5);
        toAdd.add(null);
        toAdd.add(4);
        toAdd.add(null);

        MaxHeap<Integer> testHeap = new MaxHeap<>(toAdd);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void getMaxNullHeap() {
        MaxHeap<Integer> testHeap = new MaxHeap<>();
        testHeap.getMax();
    }
}