package Strings;

import java.util.Scanner;

public class RemoveChar {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the string:");
        String s=sc.nextLine();
        System.out.println(s.replace("e",""));
    }
}
