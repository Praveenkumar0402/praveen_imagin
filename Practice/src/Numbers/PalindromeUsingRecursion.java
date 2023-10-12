package Numbers;

public class PalindromeUsingRecursion {
    public static void palindrome(int n){
        int s=0;
        int x=n;
        while(n>0){
            int r=n%10;
            s=s*10+r;
            n/=10;
        }
        if(x==s) {
            System.out.println("Palindrome");
        }else{
            System.out.println("Not Palindrome");
        }
    }
    public static void main(String[] args){
        palindrome(12321);
        palindrome(1224321);
        palindrome(98789);
    }
}
