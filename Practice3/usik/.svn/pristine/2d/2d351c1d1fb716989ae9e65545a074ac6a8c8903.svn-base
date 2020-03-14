package ua.nure.usik.practice1;

public class Part6 {

    public static void main(String[] args) {
        try {
            int value = Integer.parseInt(args[0]);
            int[] prime = prime(value);
            for (int i = 0; i < prime.length; ++i) {
                System.out.print(prime[i] + " ");
            }
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Error");
        }
    }

    private static int[] prime(int value) {
        int[] primeArray = new int[value];
        int i = 0;
        int start = 2;

        while (i < value) {
            if (isPrime(start)) {
                primeArray[i] = start;

                ++i;
            }
            ++start;

        }

        return primeArray;
    }

    private static boolean isPrime(int value) {
        if (value == 2) {
            return true;
        }

        for (int i = 2; i * i < value + 1; i++) {
            if (value % i == 0) {
                return false;
            }
        }

        return true;
    }

}