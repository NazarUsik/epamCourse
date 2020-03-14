package ua.nure.usik.practice3;

public class Part5 {
    private static final String LINE = " --> ";
    private static final int NUM = 100;
    private static final int DEC = 90;
    private static final int HES = 40;
    private static final int DES = 20;


    public static String decimal2Roman(int x) {
        if (x < 1 || x > NUM) {
            return "Invalid Roman Number Value";
        }
        StringBuilder s = new StringBuilder();

        while (x >= NUM) {
            s.append("C");
            x -= NUM;
        }
        while (x >= DEC) {
            s.append("XC");
            x -= DEC;
        }
        while (x >= NUM/2) {
            s.append("L");
            x -= NUM/2;
        }
        while (x >= HES) {
            s.append("XL");
            x -= HES;
        }
        while (x >= NUM/10) {
            s.append("X");
            x -= NUM/10;
        }
        while (x >= DEC/10) {
            s.append("IX");
            x -= DEC/10;
        }
        while (x >= 5) {
            s.append("V");
            x -= 5;
        }
        while (x >= 4) {
            s.append("IV");
            x -= 4;
        }
        while (x >= 1) {
            s.append("I");
            x -= 1;
        }
        return s.toString();
    }

    public static int roman2Decimal(String s) {
        int intToRes = 0;

        int i = 0;
        while (i < s.length()) {
            int s1 = romChar(s.charAt(i));
            if (i + 1 < s.length()) {
                int s2 = romChar(s.charAt(i + 1));
                if (s1 >= s2) {
                    intToRes = intToRes + s1;
                } else {
                    intToRes = intToRes + s2 - s1;
                    i++;
                }
            } else {
                intToRes = intToRes + s1;
                i++;
            }
            i++;
        }

        return intToRes;
    }

    static int romChar(char r) {
        if (r == 'I') {
            return 1;
        }
        if (r == 'V') {
            return NUM / DES;
        }
        if (r == 'X') {
            return NUM / 10;
        }
        if (r == 'L') {
            return NUM / 2;
        }
        return NUM;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= NUM; i++) {
            System.out.println(i + LINE + decimal2Roman(i)
                    + LINE + roman2Decimal(decimal2Roman(i)));
        }
    }
}
