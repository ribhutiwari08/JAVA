import java.util.Arrays;

public class secondLargest {
    public static void main(String[] args) {
        int[] arr = {10, 20, 5, 7, 15};
        System.out.println("Original Array: " + Arrays.toString(arr));
        
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int num : arr) {
            if (num > largest) {
                secondLargest = largest;
                largest = num;
            } else if (num > secondLargest && num != largest) {
                secondLargest = num;
            }
        }

        System.out.println("Second Largest Element: " + secondLargest);
    }
}
