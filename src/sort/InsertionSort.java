package sort;

import tools.AnalyzeSort;

public class InsertionSort {

    public static <T extends Comparable<T>> void insertionSort(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            T key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].compareTo(key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        AnalyzeSort analyzeSort = new AnalyzeSort(InsertionSort::insertionSort);

        analyzeSort.testTimeArSize = 10000;
        analyzeSort.printTest();
        analyzeSort.printTimeTest();
    }
}
