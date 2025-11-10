import java.util.LinkedList;

//Sorted linked list
public class SortedLinkedList<E extends Comparable<E>> extends LinkedList<E> {
    
    // Insert element in sorted list
    public void insertSorted(E elements) {
        if (this.isEmpty()) {
            this.add(elements);
            return;
        }

        // Find the correct position to insert
        for (int i = 0; i < this.size(); i++) {
            if (elements.compareTo(this.get(i)) < 0) {
                this.add(i, elements);
                return;
            }
        }
        // If bigger than all, add to end
        this.add(elements);
    }
}