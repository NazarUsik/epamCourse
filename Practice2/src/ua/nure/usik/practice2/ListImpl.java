package ua.nure.usik.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {
    private Node firstEl;
    private Node lastEl;
    private int size;

    public ListImpl() {
        size = 0;
        lastEl = new Node(null, null, firstEl);
        firstEl = new Node(null, lastEl, null);
    }

    private static final class Node {
        private Object item;
        private Node next;
        private Node prev;

        private Node(Object item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;

        }

        public Object getItem() {
            return item;
        }

        public Node getNext() {
            return next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setItem(Object item) {
            this.item = item;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }

    @Override
    public void addFirst(Object element) {
        Node next = new Node(element, firstEl.next, firstEl);
        firstEl.next.setPrev(next);
        firstEl = new Node(null, next, null);
        ++size;
    }

    @Override
    public void addLast(Object element) {
        Node prev = lastEl;
        prev.setItem(element);
        lastEl = new Node(null, null, prev);
        prev.setNext(lastEl);
        ++size;
    }

    @Override
    public void removeFirst() {
        firstEl = firstEl.getNext();
        --size;
    }

    @Override
    public void removeLast() {
        lastEl.setPrev(lastEl);
        --size;
    }

    @Override
    public Object getFirst() {
        return firstEl.getNext().item;
    }

    @Override
    public Object getLast() {
        return lastEl.getPrev().item;
    }

    @Override
    public Object search(Object element) {
        Node target = firstEl.getNext();
        for (int i = 0; i < size; ++i) {
            if (target.getItem().equals(element)) {
                return target.getItem();
            } else {
                target = target.getNext();
            }
        }
        return null;
    }

    @Override
    public boolean remove(Object element) {
        if (element == null) {
            return false;
        } else if (element.equals(this.getFirst())) {
            firstEl = firstEl.next;
            --size;
            return true;
        } else if (element.equals(this.getLast())) {
            lastEl.prev = lastEl;
            size--;
            return true;
        }

        for (Node target = firstEl.getNext(); target != null; target = target.getNext()) {
            if (element.equals(target.getItem())) {
                Node prev = target.prev;
                Node next = target.next;
                prev.setNext(target.next);
                next.setPrev(prev);
                --size;
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        size = 0;
        lastEl = new Node(null, null, firstEl);
        firstEl = new Node(null, lastEl, null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append('[');
        Node next = firstEl.next;
        for (int i = 0; i < size; i++) {
            Object o = next.item;
            if (o != null) {
                str.append(o);
                if (i != size - 1) {
                    str.append(", ");
                }
            }
            next = next.getNext();
        }
        str.append(']');
        return str.toString();
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private Node lastRet;
        private Node next = firstEl.getNext();
        private int nextIndex ;

        @Override
        public void remove() {
            if (lastRet == null) {
                throw new IllegalStateException();
            }

            next = lastRet.getNext();
            --nextIndex;
            ListImpl.this.remove(lastRet.item);
            lastRet = null;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastRet = next;
            next = next.getNext();
            ++nextIndex;
            return lastRet.getItem();
        }

    }

    public static void main(String[] s) {
        List list = new ListImpl();
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");

        System.out.println(list);
        System.out.println(list.size());

        list.clear();
        System.out.println(list);
        System.out.println(list.size());

        list.addLast("A");
        list.addLast("B");
        list.addLast("C");

        System.out.println(list);
        System.out.print(list.getFirst());
        System.out.print(list.getLast());

        for (Object element : list) {
            System.out.print(element);
        }

        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
        }
        System.out.println();
        it = list.iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
        }

        it = list.iterator();
        System.out.println(it.next());
        it.remove();
        System.out.println(it.next());
        it.remove();
        System.out.println(it.next());
        it.remove();
        System.out.println(list);

        list = new ListImpl();
        list.addFirst("B");
        list.addLast("C");
        list.addFirst("A");

        System.out.println(list);

        list.removeLast();
        System.out.println(list);

        list.removeLast();
        System.out.println(list);

        list = new ListImpl();
        list.addFirst("B");
        list.addLast("C");
        list.addFirst("A");

        System.out.println(list);
        System.out.println(list.getFirst());
    }
}
