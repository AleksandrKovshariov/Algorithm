package tools;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class MyRandom {
    private ThreadLocalRandom rnd = ThreadLocalRandom.current();

    /**
     * Generates int array with random value
     * Values in array are from [0 to 100)
     * @param size defines number of elements used in array
     */
    public int[] randomIntArr(int size){
        return  randomIntArr(size, 100);
    }

    /**
     * Generates int array with random value
     * Values in array are from 0 to {@code to}
     * @param size defines number of elements used in array
     * @param to defines excluded max random value used in array
     */
    public int[] randomIntArr(int size, int to){
        return  randomIntArr(size, 0, to);
    }

    /**
     * Generates int array with random value
     * Values in array are from {@code from} to {@code to}
     * @param size defines number of elements used in array
     * @param to defines excluded max random value used in array
     * @param from defines included max random value used in array
     */
    public int[] randomIntArr(int size, int from, int to){
        int[] randArr = new int[size];
        IntStream.range(0, randArr.length).forEach(x -> randArr[x] = rnd.nextInt(from, to));
        return randArr;
    }

    public int nextInt(int from, int to){
        return rnd.nextInt(from, to);
    }

    public int nextInt(int to){
        return rnd.nextInt(to);
    }

    public int nextInt(){
        return rnd.nextInt(0, 100);
    }
}
