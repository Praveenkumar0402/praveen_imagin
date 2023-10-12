import java.util.Scanner;

public class LargestFactor {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the Number:");
        int n = input.nextInt();
        int LFactor = 0;
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                LFactor = i;
            }
        }
        System.out.println(LFactor);
        input.close();
    }
}
