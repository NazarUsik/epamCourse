package ua.nure.usik.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {
    private Object[] container;
    private int size;
    private static final int SIZE_CONT = 100;

    public QueueImpl() {
        container = new Object[SIZE_CONT];
        size = 0;
    }

    @Override
    public void enqueue(Object element) {
        if (container.length <= size) {
            Object[] tmp = container;
            container = new Object[tmp.length * 2];
            System.arraycopy(tmp, 0, container, 0, tmp.length);
        }
        container[size] = element;
        ++size;
    }

    @Override
    public Object dequeue() {
        Object retObj = null;
        if (container[0] != null) {
            retObj = container[0];
            int numMoved = size - 1;
            if (numMoved > 0) {
                Object[] tmp = container;
                container = new Object[tmp.length - 1];
                System.arraycopy(tmp, 1, container, 0, numMoved);
            }
            --size;
        }
        return retObj;
    }

    @Override
    public Object top() {
        return container[0];
    }

    @Override
    public void clear() {
        container = new Object[SIZE_CONT];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append('[');
        for (int i = 0; i < size; i++) {
            Object o = container[i];
            if (o != null) {
                str.append(o);
                if (i != size - 1) {
                    str.append(", ");
                }
            }
        }
        str.append(']');
        return str.toString();
    }

    public void remove(int index) {
        if (container[index] != null) {
            int numMoved = size - index - 1;
            if (numMoved > 0) {
                System.arraycopy(container, index + 1, container, index, numMoved);
            }
            container[--size] = null;
        } else {
            throw new IllegalArgumentException();
        }
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
            QueueImpl.this.remove(lastRet);
            cursor = lastRet;
            lastRet = -1;
        }

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public Object next() {
            int i = cursor;
            if (i >= size) {
                throw new NoSuchElementException();
            }
            Object[] elementData = container;
            cursor = i + 1;
            lastRet = i;
            return elementData[lastRet];
        }
    }

    public static void main(String[] s){
        Queue queue = new QueueImpl();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        System.out.println(queue);
        System.out.println(queue.size());

        queue.clear();
        System.out.println(queue);
        System.out.println(queue.size());

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        System.out.println(queue);
        System.out.println(queue.size());

        System.out.print(queue.dequeue());
        System.out.print(queue.dequeue());
        System.out.print(queue.dequeue());
        System.out.println();
        System.out.print(queue);

        queue = new QueueImpl();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        for (Object element : queue) {
            System.out.print(element);
        }

        Iterator it = queue.iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
        }
        System.out.println();
        it = queue.iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
        }

        it = queue.iterator();
        System.out.println(it.next());
        it.remove();
        System.out.println(it.next());
        it.remove();
        System.out.println(it.next());
        it.remove();
        System.out.println(queue);

        queue = new QueueImpl();
        queue.enqueue("A");
        queue.enqueue("B");
        System.out.println(queue.top());
    }
}
