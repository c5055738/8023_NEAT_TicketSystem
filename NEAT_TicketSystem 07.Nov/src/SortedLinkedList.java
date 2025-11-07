import java.util.LinkedList;

public class SortedLinkedList<E extends Comparable<E>> extends LinkedList<E> {
    public void insertSorted (E elements){
        if (this.isEmpty()) {
            this.add(elements);
            return;
        }

        for (int i = 0; i < this.size(); i++){
            if (elements.compareTo(this.get(i)) < 0){
                this.add(i, elements);
                return;
            }
        }
        this.add(elements);
    }
}


//import java.util.LinkedList; = tell Java I want to use this built-in class
//E = my items, can store any type (it's generic)
//<E extends Comparable<E>> = it knows how to compare itself and sorting works.
//extends LinkedList<E> = my class is customized ver of LinkedList and I inherit all features.
//if "this" empty = SortedLinkedList
//this.add(element) is inherited "LinkedList" method which is adds the element to the end of the list.
//return = after placing the element, stop here.
//a for loop =  for (int i = 0; i < this.size(); i++) = go through the list from the first element to the last
//if (elements.compareTo(this.get(i)) < 0), this.add(i, elements); = if the new element smaller than the one at position i then insert before the element.
//return = done inserting, stop here.
//this.add(elements); = if it's larger than all existing elements, add it to the end