package sort;

import tools.AnalyzeSort;

public class ShellSort{

    public static void shellSort(Comparable[] arr) {
        int N = arr.length;
        int h = 1;
        while (h < N / 3)
            h = h * 3 + 1;

        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && arr[j].compareTo(arr[j - h]) < 0; j -= h) {
                    Comparable t = arr[j];
                    arr[j] = arr[j - h];
                    arr[j - h] = t;
                }

            }
            h /= 3;
        }
    }

    public static void main(String[] args) {
        AnalyzeSort analyzeSort = new AnalyzeSort(ShellSort::shellSort);
        analyzeSort.printTest();
        analyzeSort.printTimeTest();
    }
}
