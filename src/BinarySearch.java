import java.lang.reflect.Array;
import java.util.Arrays;

public class BinarySearch {
    public static final int NUMBER_OF_ARRAYS  = 10;
    public static final int SIZE_OF_ARRAYS  = 10;

    public static int binarySearch(int[] arr, int key) {
        int lo = 0;
        int hi = arr.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key > arr[mid])
                lo = mid + 1;
            else if (key < arr[mid])
                hi = mid - 1;
            else
                return mid;
        }

        return -1;
    }

    public static void main(String[] args) {
        MyRandom random = new MyRandom();
        for (int i = 0; i < NUMBER_OF_ARRAYS; i++) {
            int[] arr = random.randomIntArr(SIZE_OF_ARRAYS, 0, 15);
            Arrays.sort(arr);
            int randomKey = random.nextInt(0, 15);
            System.out.println("Array = " + Arrays.toString(arr));
            System.out.println("Finding key " + randomKey + "\nBinarySearch result is: "
                    + binarySearch(arr, randomKey));
            System.out.println();
        }
    }
}
