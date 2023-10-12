package Numbers;

import java.util.Scanner;

public class ArmstrongNumber {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number:");
        int n=sc.nextInt();
        int x=n;
        int s=0;
        while(n>0){
            int r=n%10;
            s=s+r*r*r;
            n=n/10;
        }
        if(s==x) {
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }
    }
}
