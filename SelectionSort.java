// Java program for implementation of Selection Sort
import java.io.*;
import java.util.Arrays;

public class SelectionSort
{
    void sort(int[] arr)
    {
        int n = arr.length;


        for (int i = 0; i < n-1; i++)
        {

            // find the minimum element in unsorted array group
            int min_idx = i;
            System.out.println("\ni = "+i+" min_idx = "+min_idx);
            for (int j = i+1; j < n; j++) {
                System.out.println("\tj=" + j);
                if (arr[j] < arr[min_idx])
                    min_idx = j;
            }
            // Swap the found minimum element with the first element in the unsorted array group making it sorted

            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
            System.out.print("i = "+i+" min_idx = "+min_idx+" array: ");
            System.out.println(Arrays.toString(arr));
        }
    }


    // Driver code to test above
    public static void main(String[] args)
    {
        SelectionSort ob = new SelectionSort();

        int[] arr = { 64, 34, 25, 12, 22, 11, 90 };

        System.out.print("Array before sort: ");
        System.out.println(Arrays.toString(arr));

        ob.sort(arr);

        System.out.print("\nArray after sort: ");
        System.out.println(Arrays.toString(arr));
    }
}


