package ua.nure.usik.practice6.part2;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

public class Part2 {
    private static final int N = 10000;
    private static final int K = 4;

    private static int[] indexToRemove(int size, int k) {
        int tempPos = k - 1;
        int iteration = size - 1;
        int[] index = new int[size];

        int i = 0;
        while (iteration > 0) {
            index[i++] = tempPos;
            tempPos += k - 1;
            if (tempPos > size - 2) {
                tempPos = tempPos % (size - 1);
            }
            iteration--;
            size--;
        }
        return index;
    }

    public static long removeByIndex(List<Integer> list, int k){
        long startTime = System.currentTimeMillis();
        int[] index = indexToRemove(list.size(), k);
        int l = 0;
        while (list.size() > 1) {
            list.remove(index[l++]);
        }
        return System.currentTimeMillis() - startTime;
    }

    public static long removeByIterator(List<Integer> list, int k) {
        long startTime = System.currentTimeMillis();
        Iterator<Integer> iterator = list.iterator();
        int size = list.size();

        while (size > 1) {
            for (int i = 0; i < k; i++) {
                if(!iterator.hasNext()){
                    iterator = list.iterator();
                }
                iterator.next();
            }
            iterator.remove();
            size--;
        }
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        ArrayList<Integer> peopleArray1 = new ArrayList<>();
        LinkedList<Integer> peopleList1 = new LinkedList<>();
        ArrayList<Integer> peopleArray2 = new ArrayList<>();
        LinkedList<Integer> peopleList2 = new LinkedList<>();

        int k = K;

        for (int i = 0; i < N; i++) {
            peopleArray1.add(i);
            peopleList1.add(i);
            peopleArray2.add(i);
            peopleList2.add(i);
        }

        System.out.println("ArrayList#Index:: " + removeByIndex(peopleArray1, k) + " ms");
        System.out.println("LinkedList#Index: " + removeByIndex(peopleList1, k) + " ms");
        System.out.println("ArrayList#Iterator:: " + removeByIterator(peopleArray2, k) + " ms");
        System.out.println("LinkedList#Iterator: " + removeByIterator(peopleList2, k) + " ms");
    }
}
