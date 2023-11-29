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
                return -priority;
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
        if (triplets.size() <= 1) {
            throw new NoSuchElementException("Heap is empty");
        }
        V result = triplets.get(1).value;
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
        int size = triplets.size();
        int largest = getMaxChildIndex(index, size);

        while (largest != index && largest < size) {
            swap(index, largest);
            index = largest;
            largest = getMaxChildIndex(index, size);
        }
    }

    private int getMaxChildIndex(int parentIndex, int heapSize) {
        int leftChildIndex = 2 * parentIndex;
        int rightChildIndex = 2 * parentIndex + 1;

        boolean hasLeftChild = leftChildIndex < heapSize;
        boolean hasRightChild = rightChildIndex < heapSize;

        if (!hasLeftChild) {
            // No tiene hijos
            return parentIndex;
        } else if (!hasRightChild) {
            // Solo tiene hijo izquierdo
            return leftChildIndex;
        } else {
            // Tiene ambos hijos, devolver el de mayor valor
            Triplet<P, V> leftChild = triplets.get(leftChildIndex);
            Triplet<P, V> rightChild = triplets.get(rightChildIndex);

            return leftChild.compareTo(rightChild) > 0 ? leftChildIndex : rightChildIndex;
        }
    }
}