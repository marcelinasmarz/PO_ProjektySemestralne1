import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CustomList<T> extends AbstractList<T> {

    private static class Element<T> {
        T value;
        Element<T> next;

        public Element(T value) {
            this.value = value;
        }
    }

    private Element<T> first;
    private Element<T> last;
    // Zad.2
    private int size;

    public CustomList(){
        this.first= null;
        this.last = null;
        this.size = 0;
    }

    public void addLast(T value){
        Element<T> lastElement = new Element<>(value);
        if (this.first == null){
            this.first = this.last = lastElement;
        } else {
            this.last.next = lastElement;
            this.last = lastElement;
        }
        this.size++;
    }

    public T getLast(){
        if (this.last == null){
            throw new IllegalStateException("List is empty");
        }
        return this.last.value;
    }

    public void addFirst(T value){
        Element<T> newElement = new Element<>(value);
        if (this.first == null){
            this.first = this.last = newElement;
        } else {
            newElement.next = this.first;
            this.first = newElement;
        }
        this.size++;
    }

    public T getFirst(){
        if (this.first == null){
            throw new IllegalStateException("List is empty");
        }
        return this.first.value;
    }

    public T removeFirst(){
        if (this.first == null){
            throw new IllegalStateException("List is empty");
        }
        T value = this.first.value;
        this.first = this.first.next;
        if (this.first == null){
            this.last = null;
        }
        this.size--;
        return value;
    }

    public T removeLast(){
        if (this.last == null){
            throw new IllegalStateException("List is empty");
        }
        if (this.first == this.last){
            T value = this.first.value;
            this.first = this.last = null;
            return value;
        }

        Element<T> current = this.first;
        while (current.next != this.last){
            current = current.next;
        }
        T value = this.last.value;
        this.last = current;
        this.last.next = null;
        this.size--;
        return value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Element<T> lista = this.first;
        while (lista != null) {
            sb.append(lista.value);
            if (lista.next != null) {
                sb.append(", ");
            }
            lista = lista.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public boolean add(T t) {
        addLast(t);
        return true;
    }


    // Zad. 2

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        Element<T> current = this.first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    @Override
    public int size() {
        return size;
    }

    // Zad.3

    @Override
    public Iterator<T> iterator(){
        return new Iterator<T>() {

            private Element<T> current = first;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if(!hasNext()){
                    throw new NoSuchElementException("No more elements");
                }
                T value = this.current.value;
                this.current = this.current.next;
                return value;
            }
        };
    }

    @Override
    public Stream<T> stream() {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator(), 0), false);
    }


    public static <T> List<T> filter(List<T> list, Class<?> superClass) {
        List <T> result = new ArrayList<>();
        for(T item : list){
            if(item != null && item.getClass().equals(superClass)){
                result.add(item);
            }
        }
        return result;
    }



    public static <T extends Comparable<T>> boolean isInOpenRange(T value, T min, T max) {
        return value.compareTo(min) > 0 && value.compareTo(max) < 0;
    }

    public static <T extends Comparable<T>> int countInOpenRange(List<T> list, T min, T max) {
        int count = 0;
        for (T value : list) {
            if (isInOpenRange(value, min, max)) {
                count++;
            }
        }
        return count;
    }


}
