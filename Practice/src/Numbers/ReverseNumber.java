package Numbers;

import java.util.Scanner;

public class ReverseNumber {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number:");
        int n=sc.nextInt();
        int s=0;
        while(n>0){
            int r=n%10;
            s=s*10+r;
            n=n/10;
        }
        System.out.println(s);
    }
}
