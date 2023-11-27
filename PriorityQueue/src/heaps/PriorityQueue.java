package heaps;

public interface PriorityQueue<P extends Comparable<? super P>, V> {
    void add(P priority, V value);

    V remove();

    V element();

    int size();
}