package es.datastructur.synthesizer;
import java.lang.Math;
//Note: This file will not compile until you complete task 1 (BoundedQueue).
public class GuitarString{
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = 0.996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        // TODO: Create a buffer with capacity = SR / frequency. You'll need to
        //       cast the result of this division operation into an int. For
        //       better accuracy, use the Math.round() function before casting.
        //       Your buffer should be initially filled with zeros.
        int capacity = Math.round(SR / (float)frequency);
        buffer = new ArrayRingBuffer<Double>(capacity);
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // TODO: Dequeue everything in buffer, and replace with random numbers
        //       between -0.5 and 0.5. You can get such a number by using:
        //       double r = Math.random() - 0.5;
        //
        //       Make sure that your random numbers are different from each
        //       other.

        for(int i = 0; i < buffer.capacity(); i++){
            double r = Math.random() - 0.5;
            buffer.enqueue(r);
        }
    }

    public double getPeek(){
        return buffer.peek();
    }
    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        // TODO: Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       Do not call StdAudio.play().
        double front = buffer.dequeue();
        double second = buffer.peek();
        double last = DECAY * 0.5 * (front + second);
        buffer.enqueue(last);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        // TODO: Return the correct thing.
        if(buffer.isEmpty()){
            throw new NullPointerException("There is nothing here");
        }
        return buffer.peek();
    }

    @Override
    public String toString(){
        String str = buffer.toString();
        return str;
    }
}
    // TODO: Remove all comments that say TODO when you're done.
