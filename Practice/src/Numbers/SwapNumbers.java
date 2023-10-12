package Numbers;

public class SwapNumbers {
    public static void main(String[] args){
        int a=10;
        int b=20;
        System.out.println("Before "+a);
        System.out.println("Before "+b);
        a=a+b;
        b=a-b;
        a=a-b;
        System.out.println("After "+a);
        System.out.println("After "+b);
    }
}
