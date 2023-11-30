package heaps;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class HeapQueue<P extends Comparable<? super P>, V> implements PriorityQueue<P, V> {
    private final ArrayList<Triplet<P, V>> triplets;
    private long nextTimeStamp = 0L;

    static class Triplet<P extends Comparable<? super P>, V> implements Comparable<Triplet<P, V>> {
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
            int priority = this.priority.compareTo(other.priority);
            if (priority != 0) {
                return priority;
            }
            return Long.compare(other.timeStamp, this.timeStamp);
        }
    }

    public HeapQueue() {
        this.triplets = new ArrayList<>();
        this.triplets.add(null);
    }

    @Override
    public void add(P priority, V value) {
        if (priority == null || value == null) {
            throw new IllegalArgumentException("Priority and value must not be null");
        }
        Triplet<P, V> triplet = new Triplet<>(priority, nextTimeStamp++, value);
        triplets.add(triplet);
        heapUp();
    }

    @Override
    public V remove() {
        V prevRoot = element();
        int size = size();
        triplets.set(1, getTriplet(size));
        triplets.remove(size);
        heapDown();
        return prevRoot;
    }

    @Override
    public V element() {
        if (size() < 1) {
            throw new NoSuchElementException("Heap is empty");
        }
        return getTriplet(1).value;
    }

    @Override
    public int size() {
        return triplets.size() - 1;
    }

    private void swap(int index1, int index2) {
        Triplet<P, V> temp = getTriplet(index1);
        triplets.set(index1, getTriplet(index2));
        triplets.set(index2, temp);
    }

    private void heapUp() {
        int currentIndex = size();
        Triplet<P, V> currentElement = getTriplet(currentIndex);

        while (currentIndex > 1) {
            int parentIndex = parentIndex(currentIndex);
            Triplet<P, V> parentElement = getTriplet(parentIndex);

            if (currentElement.compareTo(parentElement) <= 0) {
                break;
            }
            swap(currentIndex, parentIndex);
            currentIndex = parentIndex;
        }
    }

    private void heapDown() {
        int currentIndex = 1;
        int largestChildIndex;

        while (exists(currentIndex)) {
            largestChildIndex = getIndexOfMaxPriorityChild(currentIndex);
            if (largestChildIndex == currentIndex) {
                break;
            }
            swap(currentIndex, largestChildIndex);
            currentIndex = largestChildIndex;
        }
    }

    private int getIndexOfMaxPriorityChild(int parentIndex) {
        int leftChildIndex = leftIndex(parentIndex);
        int rightChildIndex = rightIndex(parentIndex);
        int maxPriorityIndex = parentIndex;

        if (exists(leftChildIndex) && getTriplet(leftChildIndex).compareTo(getTriplet(maxPriorityIndex)) > 0) {
            maxPriorityIndex = leftChildIndex;
        }

        if (exists(rightChildIndex) && getTriplet(rightChildIndex).compareTo(getTriplet(maxPriorityIndex)) > 0) {
            maxPriorityIndex = rightChildIndex;
        }
        return maxPriorityIndex;
    }

    static int parentIndex(int i) {
        return i / 2;
    }

    static int leftIndex(int i) {
        return 2 * i;
    }

    static int rightIndex(int i) {
        return 2 * i + 1;
    }

    boolean exists(int index) {
        return 1 <= index && index <= size();
    }

    private Triplet<P, V> getTriplet(int index) {
        assert exists(index);
        return triplets.get(index);
    }
}