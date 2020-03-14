package ua.nure.usik.practice6.test;

import ua.nure.usik.practice6.part5.Tree;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {
    Tree<Integer> tree = new Tree<>();

    @org.junit.jupiter.api.Test
    void add() {
        assertTrue(tree.add(3));
        assertTrue(tree.add(1));
        assertTrue(tree.add(5));
        assertTrue(tree.add(0));
        assertTrue(tree.add(2));
        assertTrue(tree.add(4));
        assertTrue(tree.add(6));
        assertFalse(tree.add(2));
        tree.print();
        assertEquals(tree.size(), 7);
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
    }

    @org.junit.jupiter.api.Test
    void remove() {
        add();
        assertFalse(tree.remove(11));
        assertTrue(tree.remove(5));
        tree.print();
    }
}