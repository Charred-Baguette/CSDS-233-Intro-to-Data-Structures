import java.util.Arrays;

public class BubbleSort {


    void sort(int[] arr)
    {
        int n = arr.length;
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            System.out.print("\ni = "+i+" array: ");
            System.out.println(Arrays.toString(arr));
            for (j = 0; j < n - i - 1; j++) {
                System.out.println("\tj = "+j);
                if (arr[j] > arr[j + 1]) {

                    // Swap between the current element and the next element
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                    System.out.print("\tSwapping; array: ");
                    System.out.println(Arrays.toString(arr));
                }
            }

            // if no elements were swapped then break the loop
            if (swapped == false)
                break;
        }
    }


    // Driver program
    public static void main(String[] args)
    {
        BubbleSort ob = new BubbleSort();
        int[] arr = { 64, 34, 25, 12, 22, 11, 90 };

        System.out.print("Array before sort: ");
        System.out.println(Arrays.toString(arr));

        ob.sort(arr);
        
        System.out.print("\nArray after sort: ");
        System.out.println(Arrays.toString(arr));
    }
}



