package ua.nure.usik.practice6.part5;

public class Tree<E extends Comparable<E>> {
    private Node<E> root;
    private int size;

    // добавляет элемент в контейнер
    // если в контейнере есть элемент равный по compareTo добавляемому,
    // то добавления не происходит и метод возвращает false
    // в противном случае элемент попадает в контейнер и метод возвращает true
    // первый добавляемый элемент становится корнем дерева
    // автобалансировки в дереве нет
    public boolean add(E element) {
        try {
            root = add(root, element);
        } catch (ArrayStoreException e) {
            return false;
        }
        size++;
        return true;
    }

    private Node<E> add(Node<E> current, E value) {
        if (current == null) {
            current = new Node<>(value);
        } else {
            if (value.compareTo(current.value) == 0) {
                throw new ArrayStoreException();
            } else if (value.compareTo(current.value) < 0) {
                current.leftChild = add(current.leftChild, value);
            } else {
                current.rightChild = add(current.rightChild, value);
            }
        }
        return current;
    }

    //проверка пустое ли дерево
    public boolean isEmpty() {
        return root == null;
    }

    // добавляет все элементы из массива в контейнер (вызов в цикле метода add)
    public void add(E[] elements) {
        for (E element : elements) {
            add(element);
        }
    }

    public int size() {
        return size;
    }

    // удаляет элемент из контейнера
    // если удаляемого элемента в контейнере нет, то возвращает false
    // в противном случае удаляет элемент и возвращает true
    // ВАЖНО! при удалении элемента дерево не должно потерять свойства бинарного дерева поиска
    public boolean remove(E element){
        if (root == null) {
            throw new IllegalArgumentException();
        }
        boolean retCheck = true;
        try {
            root = remove(root, element);
        } catch (IllegalArgumentException e) {
            retCheck = false;
        }
        if (retCheck) {
            size--;
        }
        return retCheck;
    }

    private Node<E> remove(Node<E> current, E value) {
        if (current == null) {
            throw new IllegalArgumentException();
        }

        if (value.compareTo(current.value) == 0) {
            if (current.leftChild == null && current.rightChild == null) {
                return null;
            }
            if (current.rightChild == null) {
                return current.leftChild;
            }
            if (current.leftChild == null) {
                return current.rightChild;
            }
            E smallestValue = findSmallestValue(current.rightChild);
            current.value = smallestValue;
            current.rightChild = remove(current.rightChild, smallestValue);
            return current;
        }
        if (value.compareTo(current.value) < 0) {
            current.leftChild = remove(current.leftChild, value);
        } else {
            current.rightChild = remove(current.rightChild, value);
        }
        return current;
    }

    private E findSmallestValue(Node<E> element) {
        return element.leftChild == null ? element.value : findSmallestValue(element.leftChild);
    }


    // распечатывает дерево, так чтобы было видно его древовидную структуру
    public void print() {
        if (root != null) {
            Object[] objects = output(root);
            for (Object o : objects) {
                System.out.println(o);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    private Object[] output(Node<E> current) {
        Object[] result = new Object[size];
        int index = 0;
        if (current.leftChild != null) {
            Object[] temp = output(current.leftChild);
            for (Object o : temp) {
                if (o != null) {
                    result[index++] = ("  " + o);
                }
            }
        }
        result[index++] = (current.value.toString());
        if (current.rightChild != null) {
            Object[] temp = output(current.rightChild);
            for (Object o : temp) {
                if (o != null) {
                    result[index++] = ("  " + o);
                }
            }
        }
        return result;
    }

    // вложенный класс, объекты этого класса составляют дерево
    private static class Node<E> {
        private E value;
        private Node<E> leftChild;
        private Node<E> rightChild;

        public Node(E value) {
            this.value = value;
            this.leftChild = null;
            this.rightChild = null;
        }
    }
}