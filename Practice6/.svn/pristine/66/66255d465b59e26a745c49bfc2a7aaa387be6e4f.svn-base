package ua.nure.usik.practice6.part6;

import ua.nure.usik.practice6.part1.Word;

import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Collections;
import java.util.SortedMap;

public class Part61 {

    public static class WordWithArrow extends Word implements Comparable<WordWithArrow> {

        public WordWithArrow(String content) {
            super(content);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }

            if (this.getClass() != obj.getClass()) {
                return false;
            }

            Word word = (Word)obj;
            return this.getContent().equals(word.getContent());
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public String toString() {
            return super.getContent() + " ==> " + super.getFrequency();
        }

        @Override
        public int compareTo(WordWithArrow o) {
            return this.getContent().compareTo(o.getContent());
        }
    }

    public static void main(String[] s) {
        Scanner in = Part6.in;

        HashMap<WordWithArrow, Integer> words = new HashMap<>();
        String word;

        int i = 0;
        while (in.hasNext()) {
            word = in.next().trim();
            boolean check = true;
            for (Map.Entry<WordWithArrow, Integer> entry : words.entrySet()) {
                if (entry.getKey().getContent().compareTo(word) == 0) {
                    entry.getKey().increaseFrequency();
                    check = false;
                    break;
                }
            }
            if (check) {
                words.put(new WordWithArrow(word), ++i);
            }
        }

        TreeMap<WordWithArrow, Integer> tree = new TreeMap<>(Collections.reverseOrder());
        tree.putAll(words);

        LinkedList<WordWithArrow> result = new LinkedList<>();
        for (int j = 0; j < 3; j++) {
            result.add(findMax(tree));
        }
        result.sort(Collections.reverseOrder());

        for (WordWithArrow w : result) {
            System.out.println(w);
        }
    }

    public static WordWithArrow findMax(SortedMap<WordWithArrow, Integer> treeWords) {
        WordWithArrow max = new WordWithArrow("");
        for (Map.Entry<WordWithArrow, Integer> current : treeWords.entrySet()) {
            if (current.getKey().getFrequency() == max.getFrequency()) {
                int minValue = current.getValue();
                for (Map.Entry<WordWithArrow, Integer> maxEntry : treeWords.entrySet()) {
                    if (maxEntry.getValue() < minValue &&
                            maxEntry.getKey().getFrequency() == current.getKey().getFrequency()) {
                        minValue = maxEntry.getValue();
                    }
                }
                if (current.getValue() <= minValue) {
                    max = current.getKey();
                }
            } else if (current.getKey().getFrequency() > max.getFrequency()) {
                max = current.getKey();
            }
        }
        treeWords.remove(max);
        return max;
    }
}
