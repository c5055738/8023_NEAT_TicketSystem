import java.util.LinkedList;

public class SortedLinkedList<E extends Comparable<E>> extends LinkedList<E> {
    public void insertSorted (E element){
        if (this.isEmpty()){
            //not to sure to use this or not: this.size() == 0
            this.add(element);
            return;
        }

        for (int i = 0; i < this.size(); i++){
            if (element.compareTo(this.get(i)) < 0){
                this.add(i, element);
                return;
            }
        }
        this.add(element);
    }
}
