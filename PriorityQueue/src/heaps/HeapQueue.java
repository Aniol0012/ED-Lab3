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
        Triplet<P, V> triplet = new Triplet<>(priority, nextTimeStamp++, value);
        triplets.add(triplet);
        heapUp();
    }

    @Override
    public V remove() {
        V result = element();
        triplets.set(1, triplets.get(triplets.size() - 1));
        triplets.remove(triplets.size() - 1);
        heapDown();
        return result;
    }

    @Override
    public V element() {
        if (triplets.size() <= 1) {
            throw new NoSuchElementException("Heap is empty");
        }
        return triplets.get(1).value;
    }

    @Override
    public int size() {
        return triplets.size() - 1;
    }

    private void swap(int index1, int index2) {
        Triplet<P, V> temp = triplets.get(index1);
        triplets.set(index1, triplets.get(index2));
        triplets.set(index2, temp);
    }

    private void heapUp() {
        int index = triplets.size() - 1;
        Triplet<P, V> currentElement = triplets.get(index);

        while (index > 1) {
            int parentIndex = index / 2;
            Triplet<P, V> parentElement = triplets.get(parentIndex);

            if (currentElement.compareTo(parentElement) <= 0) {
                break;
            }
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    private void heapDown() {
        int index = 1;
        while (index < triplets.size()) {
            int largestChildIndex = getMaxChildIndex(index);
            if (largestChildIndex == index) break;
            swap(index, largestChildIndex);
            index = largestChildIndex;
        }
    }

    private int getMaxChildIndex(int parentIndex) {
        int leftChildIndex = leftIndex(parentIndex);
        int rightChildIndex = rightIndex(parentIndex);
        int largest = parentIndex;

        if (leftChildIndex < triplets.size() && triplets.get(leftChildIndex).compareTo(triplets.get(largest)) > 0) {
            largest = leftChildIndex;
        }

        if (rightChildIndex < triplets.size() && triplets.get(rightChildIndex).compareTo(triplets.get(largest)) > 0) {
            largest = rightChildIndex;
        }

        return largest;
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
        return 1 <= index && index < triplets.size();
    }


}