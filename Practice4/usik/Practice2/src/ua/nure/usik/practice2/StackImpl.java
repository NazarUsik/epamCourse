package ua.nure.usik.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class StackImpl implements Stack {
    private Object[] container;
    private int length;
    private static final int SIZE_CONT = 100;

    public StackImpl() {
        container = new Object[SIZE_CONT];
        length = 0;
    }

    @Override
    public void push(Object element) {
        if (container.length <= length) {
            Object[] tmp = container;
            container = new Object[tmp.length * 2];
            System.arraycopy(tmp, 0, container, 0, tmp.length);
        }
        container[length] = element;
        length++;
    }

    @Override
    public Object pop() {
        if (length >= 0) {
            Object o = container[length - 1];
            container[length - 1] = null;
            length--;
            return o;
        }
        throw new NoSuchElementException();
    }

    @Override
    public Object top() {
        if (length >= 0) {
            return container[length - 1];
        }
        throw new NoSuchElementException();
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

    public void remove(int index) {
        if (container[index] != null) {
            int numMoved = length - index - 1;
            if (numMoved > 0) {
                System.arraycopy(container, index + 1, container, index, numMoved);
            }
            container[--length] = null;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Iterator<Object> iterator() {
        return new Itr();
    }

    class Itr implements Iterator<Object> {
        private int cursor = length - 1;
        private int last = -1;

        @Override
        public void remove() {
            if (last < 0) {
                throw new IllegalStateException();
            }
            StackImpl.this.remove(last);
            cursor = last - 1;
            last = -1;
        }

        @Override
        public boolean hasNext() {
            return cursor < length && cursor >= 0;
        }

        @Override
        public Object next() {
            int i = cursor;
            if (i > length) {
                throw new NoSuchElementException();
            }
            cursor = i - 1;
            last = i;
            return container[last];
        }

    }

    public static void main(String[] s) {
        Stack stack = new StackImpl();
        stack.push("A");
        stack.push("B");
        stack.push("C");

        System.out.println(stack);
        System.out.println(stack.size());

        stack.clear();
        System.out.println(stack);
        System.out.println(stack.size());

        stack.push("A");
        stack.push("B");
        stack.push("C");

        System.out.println(stack);
        System.out.println(stack.size());

        System.out.print(stack.pop());
        System.out.print(stack.pop());
        System.out.print(stack.pop());

        stack = new StackImpl();
        stack.push("A");
        stack.push("B");
        stack.push("C");

        for (Object element : stack) {
            System.out.print(element);
        }

        Iterator it = stack.iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
        }
        System.out.println();
        it = stack.iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
        }

        it = stack.iterator();
        System.out.println(it.next());
        it.remove();
        System.out.println(it.next());
        it.remove();
        System.out.println(it.next());
        it.remove();
        System.out.println(stack);

        stack = new StackImpl();
        stack.push("A");
        stack.push("B");
        System.out.println(stack.top());
        System.out.println(stack.pop());
        System.out.println(stack.top());
        System.out.println(stack.pop());
    }
}
