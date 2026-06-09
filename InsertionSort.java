import java.util.Arrays;

// Java program for implementation of Insertion Sort
public class InsertionSort {
    /*Function to sort array using insertion sort*/
    void sort(int[] arr)
    {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
            System.out.print("\nBefore Insertion: i = "+i+" key = "+key+" j = "+j+" Array: ");
            System.out.println(Arrays.toString(arr));

			// loop to find the correct position of key
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
                System.out.print("\tkey = "+key+" j = "+j+" Array: ");
                System.out.println(Arrays.toString(arr));
            }
            arr[j + 1] = key;
            System.out.print("After insertion: i = "+i+" key = "+key+" j = "+j+" Array: ");
            System.out.println(Arrays.toString(arr));
        }
    }


    // Driver method
    public static void main(String[] args)
    {
        int[] arr = { 64, 34, 25, 12, 22, 11, 90 };
        InsertionSort ob = new InsertionSort();
        System.out.print("Array before sort: ");
        System.out.println(Arrays.toString(arr));
        ob.sort(arr);
        System.out.print("\nArray after sort: ");
        System.out.println(Arrays.toString(arr));
    }
}