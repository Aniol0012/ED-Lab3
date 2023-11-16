package heaps;

import java.util.ArrayList;
import java.util.Collections;
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
            int priorityDiff = this.priority.compareTo(other.priority);
            if (priorityDiff != 0) {
                return priorityDiff;
            }
            // Less timeStamp means that it was added before, so its priority is bigger
            return Long.compare(other.timeStamp, this.timeStamp);
        }
    }

    public HeapQueue() {
        this.triplets = new ArrayList<>();
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (triplets.get(index).compareTo(triplets.get(parentIndex)) > 0) {
                Collections.swap(triplets, index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void heapifyDown(int index) {
        int size = triplets.size();
        while (true) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int largest = index;

            if (leftChild < size && triplets.get(leftChild).compareTo(triplets.get(largest)) > 0) {
                largest = leftChild;
            }

            if (rightChild < size && triplets.get(rightChild).compareTo(triplets.get(largest)) > 0) {
                largest = rightChild;
            }

            if (largest != index) {
                Collections.swap(triplets, index, largest);
                index = largest;
            } else {
                break;
            }
        }
    }

    // TODO: Use .set() for ArrayList to make cost logarithmic
    @Override
    public void add(P priority, V value) {
        Triplet<P, V> triplet = new Triplet<>(priority, nextTimeStamp++, value);
        triplets.add(triplet);
        heapifyUp(triplets.size() - 1);
    }

    @Override
    public V remove() {
        if (triplets.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        V result = triplets.get(0).value;
        Collections.swap(triplets, 0, triplets.size() - 1);
        triplets.remove(triplets.size() - 1);
        heapifyDown(0);
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
}