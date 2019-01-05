package sort;

import tools.AnalyzeSort;

public class QuickSort {
    public static void quickSort(Comparable[] arr) {
        quickHelp(arr, 0, arr.length - 1);
    }


    public static void quickHelp(Comparable[] arr, int lo, int hi) {
        if (lo >= hi)
            return;
        int j = partition(arr, lo, hi);
        quickHelp(arr, lo, j - 1);
        quickHelp(arr, j + 1, hi);
    }


    public static int partition(Comparable[] arr, int lo, int hi) {
        Comparable pivot = arr[hi];
        int i = lo - 1;
        for (int j = lo; j < hi; j++) {
            if (pivot.compareTo(arr[j]) >= 0) {
                i++;
                Comparable t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
        }
        Comparable temp = arr[i + 1];
        arr[i + 1] = arr[hi];
        arr[hi] = temp;
        return i + 1;
    }

    //question
    public static void main(String[] args) {
        AnalyzeSort analyzeSort = new AnalyzeSort(QuickSort::quickSort);
        analyzeSort.printTest();
        analyzeSort.testTimeArSize = 10_000_00;
        analyzeSort.printTimeTest();
    }
}
