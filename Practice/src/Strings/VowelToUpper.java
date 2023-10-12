package Strings;

import java.util.ArrayList;
import java.util.Scanner;

public class VowelToUpper {
    public static void main(String[] args){
        String s="Welcome";
        char[] ch=s.toCharArray();
        for(int i=0;i<s.length();i++){
            if(ch[i]=='a'||ch[i]=='e'||ch[i]=='i'||ch[i]=='o'||ch[i]=='u'||ch[i]=='A'||ch[i]=='E'||ch[i]=='I'||ch[i]=='O'||ch[i]=='U') {
                s=s.toUpperCase();
            }
        }
        System.out.println(s);
    }
}
