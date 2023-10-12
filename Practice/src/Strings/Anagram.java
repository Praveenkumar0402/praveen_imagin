package Strings;

import java.util.Arrays;
import java.util.Scanner;

public class Anagram {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the String:");
        String s1=sc.nextLine();
        String s2=sc.nextLine();

        char[] ch1=s1.toLowerCase().toCharArray();
        char[] ch2=s2.toLowerCase().toCharArray();
        Arrays.sort(ch1);
        Arrays.sort(ch2);

        boolean status=Arrays.equals(ch1,ch2);
        if(status) {
            System.out.println("Anagram");
        }else{
            System.out.println("Not Anagram");
        }
    }
}
