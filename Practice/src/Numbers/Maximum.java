package Numbers;

import java.util.Scanner;

public class Maximum {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number:");
        int x=sc.nextInt();
        int y=sc.nextInt();
        int z=sc.nextInt();

        if(x>y && x>z) {
            System.out.println("X is max");
        }else if(y>z) {
            System.out.println("Y is max");
        }else{
            System.out.println("Z is max");
        }
    }
}
