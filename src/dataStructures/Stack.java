package dataStructures;

import tools.Convert;
import tools.MyRandom;

import java.util.Arrays;
import java.util.Iterator;

public class Stack<T> implements Iterable<T>{

    private T[] arr;
    private int N = 0;

    public Stack(){
        arr = (T[])new Object[10];
    }

    private void resizeArr(int max){
        T[] temp = (T[])new Object[max];
        for(int i = 0; i < N; i++)
            temp[i] = arr[i];
        arr = temp;
    }

    public boolean isFull(){
        return arr.length == N;
    }

    public T pop(){
        T val = arr[--N];
        arr[N] = null;
        if(arr.length / 4 >= N)
            resizeArr(arr.length / 2);

        return val;
    }

    public void push(T item){
        if(isFull())
            resizeArr(arr.length*2);
        arr[N++] = item;

    }

    public boolean isEmpty(){
        return N == 0;
    }

    @Override
    public Iterator<T> iterator(){
        return new ReverseIterator();
    }

    private class ReverseIterator implements Iterator<T>{

        private int i = N;
        @Override
        public T next(){
            return arr[--i];

        }
        @Override
        public boolean hasNext(){
            return i > 0;

        }
    }


    public static void main(String[] args) {
        MyRandom random = new MyRandom();
        Integer[] arr  = Convert.primitiveArr(random.randomIntArr(20));
        Stack<Integer> stack = new Stack<>();

        System.out.println(Arrays.toString(arr));
        Arrays.stream(arr).forEach(stack::push);
        System.out.print("dataStructures.Stack: ");
        while (!stack.isEmpty()){
            System.out.print(stack.pop() + ", ");
        }
    }
}