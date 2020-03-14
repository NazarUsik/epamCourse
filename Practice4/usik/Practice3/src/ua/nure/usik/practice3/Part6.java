package ua.nure.usik.practice3;

public class Part6 {
    private static final int NUM = 100;

    public static String convert(String input) {
        String[] strings = input.split("\n");
        String[][] words = new String[NUM][];
        int index = 0;
        for (String s : strings) {
            words[index++] = s.split("[ '\\-_.,]");

        }

        String[][] tmp = words;
        words = new String[index][];
        System.arraycopy(tmp, 0, words, 0, index);

        String[] wordsOne = convertArArToAr(words);
        String[] newWords = new String[NUM*10];

        index = 0;
        for (int i = 0; i < wordsOne.length; i++) {
            for (int j = 0; j < wordsOne.length; j++) {
                if(wordsOne[i].equals(wordsOne[j]) && i != j) {
                    newWords[index++] = "_" + wordsOne[i];
                    break;
                }
            }
        }

        String[] t = newWords;
        newWords = new String[index];
        System.arraycopy(t, 0, newWords, 0, index);

        for (index = 0; index < newWords.length; index++) {
            for (int i = 0; i < words.length; i++) {
                for (int j = 0; j < words[i].length; j++) {
                    if (newWords[index].equals("_" + words[i][j])) {
                        words[i][j] = newWords[index];
                    }
                }
            }
        }

        return convertArrToStr(words);
    }

    private static String[] convertArArToAr(String[][] words){
        int index = 0;
        for (String[] word : words) {
            for (int j = 0; j < word.length; j++) {
                index++;
            }
        }
        String[] wordsOne = new String[index];
        index = 0;

        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length; j++) {
                wordsOne[index++] = words[i][j];
            }
        }

        return  wordsOne;
    }

    private static String convertArrToStr(String[][] words){

        StringBuilder str = new StringBuilder();
        for (String[] s : words) {
            for (int i = 0; i < s.length; i++) {
                String s2 = s[i];
                if(i < s.length -1) {
                    str.append(s2).append(" ");
                } else {
                    str.append(s2);
                }
            }
            str.append('\n');
        }

        return str.toString();
    }


    public static void main(String[] s){
        System.out.println(convert(Part1.readFile("part6.txt")));
    }
}
