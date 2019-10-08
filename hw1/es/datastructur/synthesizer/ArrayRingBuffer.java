package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T>  implements BoundedQueue<T>{
    /* Index for the next dequeue or peek.
    Integer instance variable first that stores the index of the least recently (oldest) inserted item
    */
    private int first;

    /* Index for the next enqueue.
    Integer instance variable last that stores the index one beyond the most recently inserted item.
    */
    private int last;

    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    @Override
    public int capacity(){// return size fo the buffer
        return rb.length;
    }

    @Override
    public int fillCount(){// return number of items currently in the buffer
        return fillCount;
    }

    public Iterator<T> iterator(){
        return new ArrayRingBufferIterator(this);
    }

    private class ArrayRingBufferIterator implements Iterator<T>{
        int count;
        int current;
        public ArrayRingBufferIterator(ArrayRingBuffer obj){
            count = 0;
            current = first;
        }

        public boolean hasNext(){
            return count < fillCount();
        }

        public T next(){
            T item = rb[current];
            current = (current + 1) % capacity();
            count++;
            return item;
        }

    }
    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        rb = (T[]) new Object[capacity];
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
    }



    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if(isFull()){
            throw new RuntimeException("Ring buffer is full!");
        }

        rb[last] = x;
        fillCount += 1;
        last = (last + 1) % capacity();
        //System.out.println("The buffer is:" + this.toString());
    }


    /**
     * Delete and return oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }

        T x = rb[first];
        rb[first] = null;
        fillCount -= 1;
        first = (first + 1) % capacity();
        return x;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        if(isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    @Override
    public String toString(){
        String str = "";
        for(int i = 0; i < fillCount; i++){
            int index = (first + i) % capacity();
            if(i == fillCount - 1){
                str = str + rb[index].toString();
            }

            else
                str = str + rb[index].toString() + " ";
        }
        return str;
    }

    @Override
    public boolean equals(Object other){
        if(this == other){
            return true;
        }

        if(other == null){
            return false;
        }

        if(this.getClass() != other.getClass()){
            return false;
        }

        ArrayRingBuffer<T> o = (ArrayRingBuffer<T>) other;
        if(o.fillCount() != this.fillCount()){
            return false;
        }

        Iterator<T> thisIter = this.iterator();
        Iterator<T> otherIter = o.iterator();
        while(thisIter.hasNext()){
            if(thisIter.next() != otherIter.next()){
                return false;
            }
        }
        return true;
    }
    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
}
    // TODO: Remove all comments that say TODO when you're done.
