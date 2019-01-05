package sort;

import tools.AnalyzeSort;

import java.util.Arrays;

public class MergeSort{

    private static final int SIZE_INSERTION = 7;

    private static Comparable[] aux;
    public static void mergeSort(Comparable[] arr) {
        aux = new Comparable[arr.length];
        msort(arr, 0, arr.length - 1);
    }

    public static void mergeIter(Comparable[] arr) {
        aux = new Comparable[arr.length];
        int N = arr.length;
        for (int i = 1; i < N; i = i + i) {
            for (int j = 0; j < N - i; j += i + i) {
                abstractMerge(arr, j, j + i - 1, Math.min(j + i + i - 1, N - 1));
            }
        }
    }

    public static void msort(Comparable[] arr, int lo, int hi) {

        //insertion sort for small arrays
        if (hi - lo < SIZE_INSERTION) {
            for (int i = lo; i <= hi; i++)
                for (int j = i; j > lo && arr[j].compareTo(arr[j - 1]) < 0; j--) {
                    Comparable t = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = t;
                }
            return;
        }
        int mid = lo + (hi - lo) / 2;
        msort(arr, lo, mid);
        msort(arr, mid + 1, hi);

        if (arr[mid].compareTo(arr[mid + 1]) <= 0)
            return;


        abstractMerge(arr, lo, mid, hi);
    }


    public static void abstractMerge(Comparable[] arr, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;

        for(int k = lo; k <= hi; k++)
            aux[k] = arr[k];

        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                arr[k] = aux[j++];
            else if (j > hi)
                arr[k] = aux[i++];
            else if (aux[i].compareTo(aux[j]) > 0) {
                arr[k] = aux[j++];
            } else
                arr[k] = aux[i++];
        }


    }


    public static void main(String[] args) {
        AnalyzeSort analyzeSort = new AnalyzeSort(MergeSort::mergeSort);
        analyzeSort.printTest();
        analyzeSort.printTimeTest();

        analyzeSort.setSort(Arrays::sort);
        analyzeSort.printTimeTest("System array sort(Arrays.sort()): ");
    }
}
