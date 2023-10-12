package Strings;

import java.util.Scanner;

public class DigitOrNot {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a character:");
        int n=sc.nextInt();
        if(n>0 && n<=9) {
            System.out.println("Digit");
        }else{
            System.out.println("Not Digit");
        }
    }
}
