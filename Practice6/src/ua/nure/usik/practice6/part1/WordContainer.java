package ua.nure.usik.practice6.part1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class WordContainer<E extends Word> {
    private static final String STOP = "stop";
    private List<E> words;

    public WordContainer() {
        words = new ArrayList<>();
    }

    public List<E> getWords() {
        return Collections.unmodifiableList(words);
    }

    public void add(E word) {
        for (E w : words) {
            if (w.equals(word)) {
                w.increaseFrequency();
                return;
            }
        }
        words.add(word);
        sortToFrequency();
    }

    @Override
    public String toString() {
        sortToFrequency();
        StringBuilder stringBuilder = new StringBuilder();

        for (E w : words) {

            stringBuilder.append(w.toString()).append('\n');
        }
        return stringBuilder.toString();
    }

    protected void sortToFrequency() {
        words.sort((o1, o2) -> ((o1.hashCode() == o2.hashCode()) ?
                (o1.getContent().compareTo(o2.getContent())) :
                (o2.hashCode() - o1.hashCode())));
    }

    public static void main(String[] s) {
        Scanner in = new Scanner(System.in);
        WordContainer<Word> words = new WordContainer<>();
        String word;

        while (in.hasNext()) {
            word = in.next();

            if (!word.equals(STOP)) {
                words.add(new Word(word));
            } else {
                break;
            }
        }


        //
        System.out.println(words);
    }
}
