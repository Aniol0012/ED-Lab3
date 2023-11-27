package heaps;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TripletTest {
    @Test
    void testSomeCompare() {
        var triplet1 = new HeapQueue.Triplet<Integer, Object>(1, 1L, new Object());
        var triplet2 = new HeapQueue.Triplet<Integer, Object>(2, 2L, new Object());
        Assertions.assertTrue(triplet2.compareTo(triplet1) > 0);
    }

    @Test
    void testPriorityCompare() {
        var triplet1 = new HeapQueue.Triplet<Integer, Object>(1, 1L, new Object());
        var triplet2 = new HeapQueue.Triplet<Integer, Object>(2, 2L, new Object());
        Assertions.assertTrue(triplet1.compareTo(triplet2) < 0);
    }

    @Test
    void testTimestampCompare() {
        var triplet1 = new HeapQueue.Triplet<Integer, Object>(1, 2L, new Object());
        var triplet2 = new HeapQueue.Triplet<Integer, Object>(1, 1L, new Object());
        Assertions.assertTrue(triplet1.compareTo(triplet2) < 0);
    }

    @Test
    void testEquality() {
        var triplet1 = new HeapQueue.Triplet<Integer, Object>(1, 1L, new Object());
        var triplet2 = new HeapQueue.Triplet<Integer, Object>(1, 1L, new Object());
        assertEquals(0, triplet1.compareTo(triplet2));
    }
}