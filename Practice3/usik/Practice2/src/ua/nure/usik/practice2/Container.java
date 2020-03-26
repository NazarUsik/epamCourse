package ua.nure.usik.practice2;

import java.util.Iterator;

public interface Container extends Iterable<Object> {

    // Removes all of the elements.
    void clear();

    // Returns the number of elements.
    int size();

    // Returns a string representation of this container.
    @Override
    String toString();

    // Returns an iterator over elements.
    // Iterator must implements the remove method.
    @Override
    Iterator<Object> iterator();

}