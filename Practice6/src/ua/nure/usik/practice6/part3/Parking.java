package ua.nure.usik.practice6.part3;

import java.util.Arrays;

public class Parking {
    private int[] array;

    public Parking(int n){
        array = new int[n];
        Arrays.fill(array, 0);
    }

    public boolean arrive(int k){
        if(check(k)){
           throw new IllegalArgumentException();
        }
        if(array[k] == 0){
            array[k] = 1;
            return true;
        } else {
            for (int i = k + 1; i < array.length; i++) {
                if(array[i] == 0){
                    array[i] = 1;
                    return true;
                }
            }

            for (int i = 0; i < k; i++) {
                if(array[i] == 0){
                    array[i] = 1;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean depart(int k){
        if(check(k)){
            throw new IllegalArgumentException();
        }
        if(array[k] == 1){
            array[k] = 0;
            return true;
        }
        return false;
    }

    public void print(){
        for(int i: array){
            System.out.print(i);
        }
        System.out.println();
    }

    private boolean check(int k){
        return k < 0 || k > array.length - 1;
    }
}
