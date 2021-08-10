import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Your implementation of a MaxHeap.
 *
 * @author Qingyuan Zhang
 * @version 1.0
 * @userid qzhang417
 * @GTID 903497782
 * <p>
 * Collaborators:
 * <p>
 * Resources:
 */
public class MaxHeap<T extends Comparable<? super T>> {

    /*
     * The initial capacity of the MaxHeap when created with the default
     * constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new MaxHeap.
     * <p>
     * The backing array should have an initial capacity of INITIAL_CAPACITY.
     */
    public MaxHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     * <p>
     * You must use the BuildHeap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     * As a reminder, this is the algorithm that involves building the heap
     * from the bottom up by repeated use of downHeap operations.
     * <p>
     * Before doing the algorithm, first copy over the data from the
     * ArrayList to the backingArray (leaving index 0 of the backingArray
     * empty). The data in the backingArray should be in the same order as it
     * appears in the passed in ArrayList before you start the BuildHeap
     * algorithm.
     * <p>
     * The backingArray should have capacity 2n + 1 where n is the
     * number of data in the passed in ArrayList (not INITIAL_CAPACITY).
     * Index 0 should remain empty, indices 1 to n should contain the data in
     * proper order, and the rest of the indices should be empty.
     *
     * @param data a list of data to initialize the heap with
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public MaxHeap(ArrayList<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        backingArray = (T[]) new Comparable[2 * data.size() + 1];
        for (int j = 0; j < data.size(); j++) {
            if (data.get(j) == null) {
                throw new IllegalArgumentException("Elements of heap cannot be null");
            }
            backingArray[j + 1] = data.get(j);
        }
        size = data.size();
        for (int i = size / 2; i >= 1; i--) {
            downHeap(i);
        }
    }

    /**
     * Adds the data to the heap.
     * <p>
     * If sufficient space is not available in the backing array (the backing
     * array is full except for index 0), resize it to double the current
     * length.
     *
     * @param data the data to add
     * @throws IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        if (size == backingArray.length - 1) {
            changeCapacity();
        }

        int myIndex = 0;
        T dummy = null;
        myIndex = size + 1;

        backingArray[myIndex] = data;
        size++;

        while (myIndex != 1) {
            if (backingArray[myIndex / 2].compareTo(backingArray[myIndex]) < 1) {
                dummy = backingArray[myIndex / 2];
                backingArray[myIndex / 2] = backingArray[myIndex];
                backingArray[myIndex] = dummy;
                myIndex = myIndex / 2;
            } else {
                break;
            }
        }
    }

    /**
     * Removes and returns the root of the heap.
     * <p>
     * Do not shrink the backing array.
     * <p>
     * Replace any unused spots in the array with null.
     *
     * @return the data that was removed
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T remove() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty");
        } else if (size == 1) {
            T dummy = backingArray[1];
            clear();
            return dummy;
        } else if (size == 2) {
            T dummy = backingArray[1];
            T dummy2 = backingArray[2];
            clear();
            backingArray[1] = dummy2;
            size = 1;
            return dummy;
        }
        T dummy = backingArray[1];
        T dummy2 = null;
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;
        downHeap(1);
        return dummy;
    }

    /**
     * Helper method that downheaps starting at a given index
     *
     * @param counter index that is being downheaped
     */
    private void downHeap(int counter) {
        T dummy = null;
        while (backingArray.length > 2 * (counter + 1)) {


            //Checking if it has two children
            if (backingArray[2 * counter + 1] != null) {

                //Right child greater
                if (backingArray[2 * counter + 1].compareTo(backingArray[2 * counter]) > 0) {
                    if (backingArray[2 * counter + 1].compareTo(backingArray[counter]) < 0) {
                        break;
                    }
                    dummy = backingArray[2 * counter + 1];
                    backingArray[2 * counter + 1] = backingArray[counter];
                    backingArray[counter] = dummy;
                    counter = counter * 2 + 1;
                    //Left child is greater
                } else {
                    if (backingArray[2 * counter].compareTo(backingArray[counter]) < 0) {
                        break;
                    }
                    dummy = backingArray[2 * counter];
                    backingArray[2 * counter] = backingArray[counter];
                    backingArray[counter] = dummy;
                    counter = counter * 2;
                }


                //Checking if it has a left child
            } else if (backingArray[2 * counter] != null) {
                if (backingArray[2 * counter].compareTo(backingArray[counter]) > 0) {
                    dummy = backingArray[2 * counter];
                    backingArray[2 * counter] = backingArray[counter];
                    backingArray[counter] = dummy;
                    counter = counter * 2;
                } else {
                    break;
                }

                //No children, end method
            } else {
                break;
            }
        }
    }

    /**
     * Returns the maximum element in the heap.
     *
     * @return the maximum element
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T getMax() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
        return backingArray[1];
    }

    /**
     * Returns whether or not the heap is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the heap.
     * <p>
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     */
    public void clear() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Returns the backing array of the heap.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Makes the ArrayList larger
     */
    private void changeCapacity() {
        T[] newArray = (T[]) new Comparable[backingArray.length * 2];
        for (int i = 0; i < backingArray.length; i++) {
            newArray[i] = backingArray[i];
        }
        this.backingArray = newArray;
    }

    public <T extends Comparable<? super T>> boolean is() {
        int i = 1;
        if (backingArray[i] == null) {
            return true;
        }
        return isBSTHelper(backingArray, i);
    }

    private <T extends Comparable<? super T>> boolean isBSTHelper(T[] backingArray, int index) {
        if (2* index + 1 > backingArray.length) {
            return true;
        }
        boolean left = (backingArray[index].compareTo(backingArray[2*index]) <= 0) && isBSTHelper(backingArray, 2*index);;
        boolean right = (2*index + 1 == backingArray.length) || (backingArray[index].compareTo(backingArray[2*index+1]) <= 0 && isBSTHelper(backingArray, 2*index + 1));
        return left && right;
    }
}