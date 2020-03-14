package ua.nure.usik.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {
    private Object[] container;
    private int length;
    private static final int SIZE_CONT = 100;

    public ArrayImpl() {
        container = new Object[SIZE_CONT];
        length = 0;
    }

    @Override
    public void add(Object element) {
        if (container.length <= length) {
            Object[] tmp = container;
            container = new Object[tmp.length * 2];
            System.arraycopy(tmp, 0, container, 0, tmp.length);
        }
        container[length] = element;
        ++length;
    }

    @Override
    public void set(int index, Object element) {
        if (container[index] != null){
            container[index] = element;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Object get(int index) {
        if (container[index] != null) {
            return container[index];
        }
        throw new IllegalArgumentException();
    }

    @Override
    public int indexOf(Object element) {
        for (int i = 0; i < container.length; ++i) {
            if (container[i] == element) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void remove(int index) {
        if (container[index] != null) {
            int numMoved = length - index - 1;
            if (numMoved > 0) {
                System.arraycopy(container, index + 1, container, index, numMoved);
            }
            container[--length] = null;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void clear() {
        container = new Object[SIZE_CONT];
        length = 0;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append('[');
        for (int i = 0; i < length; i++) {
            Object o = container[i];
            if (o != null) {
                str.append(o);
                if (i != length - 1) {
                    str.append(", ");
                }
            }
        }
        str.append(']');
        return str.toString();
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    class IteratorImpl implements Iterator<Object> {
        private int cursor;
        private int lastRet = -1;

        @Override
        public void remove() {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            ArrayImpl.this.remove(lastRet);
            cursor = lastRet;
            lastRet = -1;
        }

        @Override
        public boolean hasNext() {
            return cursor != length;
        }

        @Override
        public Object next() {
            int i = cursor;
            if (i >= length) {
                throw new NoSuchElementException();
            }
            Object[] elementData = container;
            cursor = i + 1;
            lastRet = i;
            return elementData[lastRet];
        }
    }

    public static void main(String[] s) {
        Array arrayOne = new ArrayImpl();
        arrayOne.add("A");
        arrayOne.add("B");
        arrayOne.add("C");

        System.out.println(arrayOne);
        System.out.println(arrayOne.size());

        arrayOne.clear();
        System.out.println(arrayOne);
        System.out.println(arrayOne.size());

        arrayOne.add("A");
        arrayOne.add("B");
        arrayOne.add("C");


        System.out.println(arrayOne.indexOf("A"));
        System.out.println(arrayOne.indexOf("B"));
        System.out.println(arrayOne.indexOf("C"));
        System.out.println(arrayOne.indexOf("D"));

        arrayOne.remove(2);
        System.out.println(arrayOne);

        arrayOne.remove(0);
        System.out.println(arrayOne);

        arrayOne.remove(0);
        System.out.println(arrayOne);

        Array arrayTwo = new ArrayImpl();
        arrayTwo.add("A");
        arrayTwo.add("B");
        arrayTwo.add("C");

        for (Object element : arrayTwo) {
            System.out.print(element);
        }

        Iterator it = arrayTwo.iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
        }
        System.out.println();
        it = arrayTwo.iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
        }

        it = arrayTwo.iterator();
        System.out.println(it.next());
        it.remove();
        System.out.println(it.next());
        it.remove();
        System.out.println(it.next());
        it.remove();
        System.out.println(arrayTwo);
    }
}
