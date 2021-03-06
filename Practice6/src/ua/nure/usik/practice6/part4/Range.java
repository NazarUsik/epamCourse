package ua.nure.usik.practice6.part4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer> {
    private int n;
    private int m;
    private boolean reverse;

    public Range(int n, int m) {
        this.n = n;
        this.m = m;
    }

    public Range(int n, int m, boolean reverse) {
        this(n, m);
        this.reverse = reverse;
    }

    @Override
    public Iterator<Integer> iterator() {
        if (reverse) {
            return new Iterator<Integer>() {
                private int cursor = m;

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }

                @Override
                public boolean hasNext() {
                    return cursor >= n;
                }

                @Override
                public Integer next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    return cursor--;
                }
            };
        }
        return new Iterator<Integer>() {
            private int cursor = n;

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean hasNext() {
                return cursor <= m;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return cursor++;
            }
        };
    }

}
