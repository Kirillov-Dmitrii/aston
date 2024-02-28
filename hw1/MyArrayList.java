import javax.swing.text.DefaultEditorKit;
import java.util.*;
import java.util.function.Consumer;

public class MyArrayList<E> implements Iterable {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENT_DATA = {};
    private Object[] elementData;
    private int size;

    public MyArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        if (capacity > 0) {
            this.elementData = new Object[capacity];
        } else if (capacity == 0) {
            this.elementData = EMPTY_ELEMENT_DATA;
        } else  {
            throw new IllegalArgumentException("Illegal Argument: " + capacity);
        }
    }

    public MyArrayList(Collection<? extends E> collection) {
        Object[] arr = collection.toArray();
        size = arr.length;

        if (size != 0) {
            if (collection.getClass() == MyArrayList.class) {
                this.elementData = arr;
            } else {
                this.elementData = Arrays.copyOf(arr, size, Object[].class);
            }
        } else {
            this.elementData = EMPTY_ELEMENT_DATA;
        }
    }


    private Object[] grow(Object[] elementData) {
        Object[] newElementData = Arrays.copyOf(elementData, (int) (elementData.length * 1.5), Object[].class);
        System.out.println("New length: " + newElementData.length);

        return newElementData;
    }

    public E add(E element) {
        if (size >= elementData.length) {
            elementData = grow(elementData);
        }
        elementData[size] = element;
        size++;

        System.out.println("Length: " + elementData.length);
        System.out.println("New size: " + size);
        System.out.println("------------------------------");

        return element;
    }

    public E add(int index, E element) {
        if (size < elementData.length) {
            for (int i = size; i > index; i--) {
                elementData[i] = elementData[i - 1];
            }
        } else {
            elementData  = grow(elementData);
        }

        elementData[index] = element;
        size++;

        System.out.println("New size: " + size);
        System.out.println("------------------------------");

        return element;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }

        size = 0;
    }

    public E get(int index) {
        Objects.checkIndex(index, size);

        return (E)elementData[index];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if (index >= 0 && index < size) {
            E deletedElement = (E) elementData[index];
            for (int i = index; i < size - 1; i++) {
                elementData[i] = elementData[i];
            }
            elementData[size - 1] = null;
            return deletedElement;
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public E remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(o)) {
                Object deletedElement = elementData[i];
                elementData[i] = null;
                return (E) deletedElement;
            }
        }
        return null;
    }

    public Object[] sort(Comparator<? super E> c) {
        //быстрая сортировка
        return elementData;
    }

    private Object[] quickSort() {
        return null;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator spliterator() {
        return Iterable.super.spliterator();
    }
}
