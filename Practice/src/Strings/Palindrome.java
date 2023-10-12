package Strings;

public class Palindrome {
    public static void main(String[] args){
        String s="abcba";
        StringBuilder sb=new StringBuilder();
        sb.append(s);
        sb=sb.reverse();
        if(s.equals(sb.toString())) {
            System.out.println("Palindrome");
        }else{
            System.out.println("Not Palindrome");
        }

    }
}
