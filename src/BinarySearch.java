import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class BinarySearch {

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
        for (int i = 0; i < 10; i++) {
            int[] arr = random.randomIntArr(20, 5, 11);
            System.out.println(Arrays.toString(arr));
        }
    }
}
