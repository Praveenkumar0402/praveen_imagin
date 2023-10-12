package Numbers;
import java.util.Arrays;
import java.util.Scanner;

public class SecondLargestArray {
    public static int findSecondLargest(int[] arr, int n) {
        Arrays.sort(arr);
        int second_largest = 0;
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] != arr[n - 1]) {
                second_largest = arr[i];
                break;
            }
        }
        return second_largest;
    }
    public static void main(String[] args) {
        int[] arr = { 12, 35, 15, 10, 35, 1 };
        int n= arr.length;
        int answer = findSecondLargest(arr, n);
        System.out.println("The second largest element in the array is: " + answer);
    }
}
