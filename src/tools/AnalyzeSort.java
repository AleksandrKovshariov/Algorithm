package tools;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * Very simple testing class for sort algorithm
 */
public class AnalyzeSort {
    private Consumer<Integer[]> sort;
    private MyRandom random = new MyRandom();

    public Consumer<Integer[]> getSort() {
        return sort;
    }

    public void setSort(Consumer<Integer[]> sort) {
        this.sort = sort;
    }

    public int numberOfArrays = 10;
    public int sizeOfArrays = 10;
    public int testTimeArSize = 10_000_000;

    public static<T extends Comparable<T>> double testTime(T[] array, Consumer<T[]> sortType){
        StopWatch stopWatch = new StopWatch();
        sortType.accept(array);
        return stopWatch.elapsedTime();
    }



    public AnalyzeSort(Consumer<Integer[]> sort){
        this.sort = sort;
    }

    public void printTimeTest(){
        printTimeTest("Time for testing array of " + testTimeArSize + " size: ");
    }

    public void printTimeTest(String str){
        Integer[] arr = Convert.primitiveArr(random.randomIntArr(testTimeArSize));

        System.out.println(str + AnalyzeSort.testTime(arr, sort));
    }

    public void printTest(){

        for (int i = 0; i < numberOfArrays; i++) {
            Integer[] arr = Convert.primitiveArr(random.randomIntArr(sizeOfArrays));
            System.out.println("Array = " + Arrays.toString(arr));
            sort.accept(arr);
            System.out.println("Sorted = " + Arrays.toString(arr)+ "\n");
        }
    }




}
