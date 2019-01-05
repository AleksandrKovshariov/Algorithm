package sort;

import tools.AnalyzeSort;

public class SelectionSort{

    public static void selectionSort(Comparable[] arr) {
        int min;
        for (int i = 0; i < arr.length; i++) {
            min = i;
            for (int j = i + 1; j < arr.length; j++)
                if (arr[min].compareTo(arr[j]) > 0)
                    min = j;

            Comparable t = arr[i];
            arr[i] = arr[min];
            arr[min] = t;
        }
    }

    public static void main(String[] args) {
        AnalyzeSort analyzeSort = new AnalyzeSort(SelectionSort::selectionSort);
        analyzeSort.printTest();
        analyzeSort.printTimeTest();
    }


}
