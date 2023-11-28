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
    }

    @Override
    public void add(P priority, V value) {
        Triplet<P, V> triplet = new Triplet<>(priority, nextTimeStamp++, value);
        triplets.add(triplet);
        heapUp();
    }

    @Override
    public V remove() {
        if (triplets.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        V result = triplets.get(0).value;
        triplets.set(0, triplets.get(triplets.size() - 1));
        triplets.remove(triplets.size() - 1);
        heapDown();
        return result;
    }

    @Override
    public V element() {
        if (triplets.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return triplets.get(0).value;
    }

    @Override
    public int size() {
        return triplets.size();
    }

    private void swap(int index1, int index2) {
        Triplet<P, V> temp = triplets.get(index1);
        triplets.set(index1, triplets.get(index2));
        triplets.set(index2, temp);
    }

    private void heapUp() {
        int index = triplets.size() - 1;
        Triplet<P, V> currentElement = triplets.get(index);

        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            Triplet<P, V> parentElement = triplets.get(parentIndex);

            if (currentElement.compareTo(parentElement) <= 0) {
                break;
            }
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    private void heapDown() {
        int index = 0;
        int size = triplets.size();
        int largest = getMaxChildIndex(index, size);

        while (largest != index) {
            swap(index, largest);
            index = largest;
            largest = getMaxChildIndex(index, size);
        }
    }

    private int getMaxChildIndex(int index, int size) {
        int maxIndex = index;
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        if (leftChild < size && triplets.get(leftChild).compareTo(triplets.get(index)) > 0) {
            maxIndex = leftChild;
        }

        if (rightChild < size && triplets.get(rightChild).compareTo(triplets.get(index)) > 0) {
            maxIndex = rightChild;
        }
        return maxIndex;
    }
}