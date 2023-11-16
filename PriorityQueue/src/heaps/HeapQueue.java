package heaps;
import java.util.ArrayList;

public class HeapQueue<P extends Comparable<? super P>, V> implements PriorityQueue<P, V> {
    private final ArrayList<Triplet<P, V>> triplets;
    private long nextTimeStamp = 0L;
    static class Triplet<P extends Comparable<? super P>, V>
            implements Comparable<Triplet<P, V>> {
        private final P priority;
        private final long timeStamp;
        private final V value;
        Triplet(P priority, long timeStamp, V value) {
            this.value = value;
            this.priority = priority;
            this.timeStamp = timeStamp;
        }
        @Override
        public int compareTo(Triplet<P, V> other) {
            throw new UnsupportedOperationException("not done jefe");
        }
    }
    public HeapQueue() {
        this.triplets = new ArrayList<>();
    }
    @Override
    public void add(P priority, V value) {
        Triplet<P, V> triplet = new Triplet<>(priority, nextTimeStamp, value);
        triplets.add(triplet);
    }
    @Override
    public V remove() {
        throw new UnsupportedOperationException("not done jefe");
    }
    @Override
    public V element() {
        throw new UnsupportedOperationException("not done jefe");
    }
    @Override
    public int size() {
        throw new UnsupportedOperationException("not done jefe");
    }
    // Extra methods
}