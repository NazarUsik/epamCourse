package ua.nure.usik.practice3;

public class Part2 {
    public static String convert(String input) {
        String[] words = input.split("[ '\\-\n_.,]");

        StringBuilder str = new StringBuilder(findMin(words));
        str.append(findMax(words));

        return str.toString();
    }

    private static int[] minMaxLength(String[] words) {
        int[] minMax = new int[2];
        minMax[0] = words[0].length();
        minMax[1] = words[0].length();
        for (String word : words) {
            if (word.length() < minMax[0] && !word.isEmpty()) {
                minMax[0] = word.length();
            }
            if (word.length() > minMax[1]) {
                minMax[1] = word.length();
            }
        }

        return minMax;
    }

    private static String findMin(String[] words) {
        int minLength = minMaxLength(words)[0];

        String[] minWords = new String[words.length];

        int iMin = 0;
        for (String word : words) {
            if (word.length() == minLength) {
                minWords[iMin++] = word;
            }
        }

        String[] minWordArray = new String[iMin];
        System.arraycopy(minWords, 0, minWordArray, 0, iMin);

        for (int i = 0; i < minWordArray.length; i++) {
            for (int j = 0; j < minWordArray.length; j++) {
                if (minWordArray[i].equals(minWordArray[j]) && i != j) {
                    minWordArray[i] = null;
                    break;
                }
            }
        }

        StringBuilder str = new StringBuilder("Min: ");

        int j = 0;
        while (j < minWordArray.length) {
            String se = minWordArray[j];
            if (se != null && j < minWordArray.length - 1) {
                str.append(se).append(", ");
            } else if (se != null) {
                str.append(se).append('\n');
            }
            j++;
        }

        return str.toString();
    }

    private static String findMax(String[] words){
        int maxLength = minMaxLength(words)[1];

        String[] maxWords = new String[words.length];

        int iMax = 0;

        for (String word : words) {
            if (word.length() == maxLength) {
                maxWords[iMax++] = word;
            }
        }

        String[] maxWordsArray = new String[iMax];
        System.arraycopy(maxWords, 0, maxWordsArray, 0, iMax);


        int j = 0;
        while (j < maxWordsArray.length) {
            for (int k = 0; k < maxWordsArray.length; k++) {
                if (maxWordsArray[j].equals(maxWordsArray[k]) && j != k) {
                    maxWordsArray[j] = null;
                    break;
                }
            }
            j++;
        }


        StringBuilder str = new StringBuilder("Max: ");
        for (int i = 0; i < maxWordsArray.length; i++) {
            String se = maxWordsArray[i];
            if (se != null && i < maxWordsArray.length - 1) {
                str.append(se).append(", ");
            } else if (se != null) {
                str.append(se);
            }
        }

        return str.toString();
    }

    public static void main(String[] s) {
        System.out.println(convert(Part1.readFile("part2.txt")));
    }
}
