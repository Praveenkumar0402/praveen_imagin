package Numbers;

import java.util.Scanner;

public class FibonacciUsingIteration {
    public static void main(String[] args){
        int n=10;
        int a=0,b=1,c;
        for(int i=0;i<n;i++){
            c=a+b;
            System.out.println(a);
            a=b;
            b=c;
        }
    }
}
