package ua.nure.usik.practice3;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class Part4 {
    private static final int HEX = 16;

    public static String toHexString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(HEX));

        while (hexString.length() < HEX*2) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    public static String hash(String input, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        return toHexString(digest.digest(input.getBytes(StandardCharsets.UTF_8))).toUpperCase(Locale.getDefault());
    }

    public static void main(String[] args){
        String er = "No such algorithm";
        String hash = "asdf";
        try {
            System.out.println(hash(hash, "MD5"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println(er);
        }

        try {
            System.out.println(hash(hash, "M5"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println(er);
        }

        try {
            System.out.println(hash(hash, "SHA-256"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println(er);
        }
    }

}
