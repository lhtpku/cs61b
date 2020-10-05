package es.datastructur.synthesizer;
import java.util.Iterator;
import java.util.NoSuchElementException;
//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (fillCount >= capacity()) {
            throw new RuntimeException("Ring Buffer overflow");
        }
        rb[last] = x;
        last++;
        fillCount++;
        if (last > capacity() - 1) {
            last = 0;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        T tmp = rb[first];
        first++;
        fillCount--;
        if (first > capacity() - 1) {
            first = 0;
        }
        return tmp;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        return rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferInterator();
    }

    private class ArrayRingBufferInterator<T> implements Iterator<T> {
        private int count = fillCount;
        private int index = first;
        /**
         * Return whether it can be iterated
         */
        @Override
        public boolean hasNext() {
            return count != 0;
        }
        /**
         * Return the next item
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T tmp = (T) rb[index];
            index++;
            count--;
            if (index > rb.length - 1) {
                index = 0;
            }
            return tmp;
        }
    }


    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
    @Override
    public boolean equals(Object other) {
        if ((other == null) || (other.getClass() != this.getClass())) {
            return false;
        }
        ArrayRingBuffer tmp = (ArrayRingBuffer) other;
        if (this.fillCount() != tmp.fillCount()) {
            return false;
        }
        Iterator p = this.iterator();
        Iterator q = this.iterator();
        for (int i = 0; i < this.fillCount; i++) {
            if (p.next() != q.next()) {
                return false;
            }
        }
        return true;
    }
}
