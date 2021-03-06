package ua.nure.usik.practice6.part6;


import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Part62 {

    public static void main(String[] s) {
        Scanner in = Part6.in;

        TreeMap<Part61.WordWithArrow, Integer> words = new TreeMap<>();
        String word;

        int i = 0;
        while (in.hasNext()) {
            word = in.next().trim();
            words.put(new Part61.WordWithArrow(word), ++i);
        }

        for (Map.Entry<Part61.WordWithArrow, Integer> entry : words.entrySet()) {
            entry.getKey().setFrequency(entry.getKey().getContent().length());
        }

        LinkedList<Part61.WordWithArrow> result = new LinkedList<>();
        for (int j = 0; j < 3; j++) {
            result.add(Part61.findMax(words));
        }
        result.sort((o1, o2) -> o2.getFrequency() - o1.getFrequency());

        for (Part61.WordWithArrow w : result) {
            System.out.println(w);
        }
    }

}
