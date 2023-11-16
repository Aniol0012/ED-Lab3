package heaps;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TripletTest {
    @Test
    void testSomeCompare() {
        var triplet1 = new HeapQueue.Triplet<Integer, Object>(1, 1L, new Object());
        var triplet2 = new HeapQueue.Triplet<Integer, Object>(2, 2L, new Object());
        assertTrue(triplet2.compareTo(triplet1) > 0);
    }
}