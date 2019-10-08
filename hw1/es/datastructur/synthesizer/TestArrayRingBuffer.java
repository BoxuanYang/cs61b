package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer{
    @Test
    public void testEnqueue() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        arb.enqueue(10);
        assertSame(arb.fillCount(), 1);
        assertTrue("10".equals(arb.toString()));
        arb.enqueue(20);
        assertTrue(arb.toString().equals("10 20"));
        arb.enqueue(30);
        assertTrue(arb.toString().equals("10 20 30"));
        assertTrue(arb.peek().equals(10));
        assertTrue(arb.dequeue().equals(10));
        assertTrue(arb.toString().equals("20 30"));
    }
}
