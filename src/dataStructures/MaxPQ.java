package dataStructures;

import tools.Convert;
import tools.MyRandom;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MaxPQ<T extends Comparable<T>> {
    private int N = 0;
    private T[] arr;

    public MaxPQ(int max) {
        arr = (T[]) new Comparable[max + 1];
    }

    public void insert(T item) {
        arr[++N] = item;
        swim(N);
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public T delMax() {
        T val = arr[1];
        T t = arr[1];
        arr[1] = arr[N];
        arr[N] = t;

        arr[N] = null;
        N--;
        sink(1);

        return val;

    }

    private void swim(int k) {
        while (k > 1 && arr[k].compareTo(arr[k / 2]) > 0) {
            T t = arr[k];
            arr[k] = arr[k / 2];
            arr[k / 2] = t;
            k /= 2;
        }

    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = k * 2;
            if (j < N && arr[j].compareTo(arr[j + 1]) < 0)
                j++;
            if (arr[k].compareTo(arr[j]) > 0)
                break;
            T t = arr[k];
            arr[k] = arr[j];
            arr[j] = t;
            k = j;
        }
    }

    public static void main(String[] args) {
        MyRandom random = new MyRandom();
        Integer[] arr  = Convert.primitiveArr(random.randomIntArr(10));
        System.out.println("Array = "  + Arrays.toString(arr));
        MaxPQ<Integer> priorQueue = new MaxPQ<>(arr.length);
        Arrays.stream(arr).forEach(priorQueue::insert);
        System.out.print("Queue = ");
        while (!priorQueue.isEmpty()){
            System.out.print(priorQueue.delMax() + ", ");
        }


    }
}
