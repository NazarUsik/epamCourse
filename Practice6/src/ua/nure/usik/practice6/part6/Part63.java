package ua.nure.usik.practice6.part6;

import ua.nure.usik.practice6.part1.Word;
import ua.nure.usik.practice6.part1.WordContainer;


import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.List;
import java.util.Collections;

public class Part63 {
    private static class WordContainerForDubl extends WordContainer<Word> {
        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();

            List<Word> words = getWords();
            int i = 0;
            for (Word w : words) {
                if (i >= 3) {
                    break;
                }
                if (w.getFrequency() > 1) {
                    stringBuilder.append(convert(w.getContent()).toUpperCase(Locale.getDefault())).append('\n');
                    i++;
                }
            }
            return stringBuilder.toString();
        }

        private static String convert(String s) {
            StringBuilder revers = new StringBuilder();
            char[] chars = s.toCharArray();
            ArrayList<Character> charsList = new ArrayList<>();

            for (char aChar : chars) {
                charsList.add(aChar);
            }

            Collections.reverse(charsList);

            for (char aChar : charsList) {
                revers.append(aChar);
            }

            return revers.toString();
        }

        @Override
        protected void sortToFrequency() {
            //doesn't required
        }
    }

    public static void main(String[] s) {
        Scanner in = Part6.in;
        WordContainerForDubl words = new WordContainerForDubl();
        String word;

        while (in.hasNext()) {
            word = in.next();
            words.add(new Word(word));
        }

        System.out.print(words);
    }
}
