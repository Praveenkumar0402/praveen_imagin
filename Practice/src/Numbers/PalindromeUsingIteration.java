package Numbers;

import java.util.Scanner;

public class PalindromeUsingIteration {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number:");
        int n=sc.nextInt();
        int x=n;
        int s=0;
        while(n>0){
            int r=n%10;
            s=s*10+r;
            n/=10;
        }
        if(s==x) {
            System.out.println("Palindrome");
        }else{
            System.out.println("Not Palindrome");
        }
    }
}
