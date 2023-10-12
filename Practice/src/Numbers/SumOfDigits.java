package Numbers;

import java.util.Scanner;

public class SumOfDigits {
    public static void sum(int n){
        int s=0;
        while(n>0){
            int r=n%10;
            s=s+r;
            n/=10;
        }
        System.out.println(s);
    }
    public static void main(String[] args){
        sum(12345);
        sum(987);
    }
}
