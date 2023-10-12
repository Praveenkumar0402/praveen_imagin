package Numbers;

public class FibonacciUsingRecursion {
    public static void fib(int n){
        int a=0,b=1,c;
        while(n>0){
            c=a+b;
            System.out.println(a);
            a=b;
            b=c;
            n--;
        }
    }
    public static void main(String[] args){
        fib(10);
        fib(5);
    }

}
