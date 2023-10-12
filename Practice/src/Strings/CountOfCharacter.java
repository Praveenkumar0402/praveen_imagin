package Strings;

import java.util.Scanner;

public class CountOfCharacter {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the String:");
        String s=sc.nextLine();
        char c=sc.next().charAt(0);
        int count=0;
        char[] ch=new char[s.length()];
        for(int i=0;i<s.length();i++){
            ch[i]=s.charAt(i);
            if(ch[i]==c){
                count++;
            }
        }
        System.out.println(count);
    }
}
