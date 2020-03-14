package ua.nure.usik.practice1;

public class Part7 {
    private static final String ARROW = " ==> ";
    private static final int CONST = 65;
    private static final int WORD = 26;


    public static String int2str(int number) {
        number--;

        return number < 0 ? "" : ( int2str(number / WORD) + (char) (CONST + number % WORD));
    }

    public static int str2int(String number) {
        for (int i = 0; i < WORD * WORD * WORD * WORD; ++i) {
            String tmp = int2str(i);
            if (number.equals(tmp)) {
                return i;
            }
        }

        return 0;
    }

    public static String rightColumn(String number) {
        return int2str(str2int(number) + 1);
    }

    public static void main(String[] args) {
        System.out.println("A" + ARROW + str2int("A") + ARROW + int2str(str2int("A")));
        System.out.println("B" + ARROW + str2int("B") + ARROW + int2str(str2int("B")));
        System.out.println("Z" + ARROW + str2int("Z") + ARROW + int2str(str2int("Z")));
        System.out.println("AA" + ARROW + str2int("AA") + ARROW + int2str(str2int("AA")));
        System.out.println("AZ" + ARROW + str2int("AZ") + ARROW + int2str(str2int("AZ")));
        System.out.println("BA" + ARROW + str2int("BA") + ARROW + int2str(str2int("BA")));
        System.out.println("ZZ" + ARROW + str2int("ZZ") + ARROW + int2str(str2int("ZZ")));
        System.out.println("AAA" + ARROW + str2int("AAA") + ARROW + int2str(str2int("AAA")));
    }
}
