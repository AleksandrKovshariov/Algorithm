package dataStructures;

import tools.Convert;
import tools.MyRandom;

import java.util.Arrays;
import java.util.Iterator;

public class LinkedQueue<T> implements Iterable<T>{
    private Node first;
    private Node last;
    private int N = 0;

    public void enqueue(T item){
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if(isEmpty())
            first = last;
        else
            oldLast.next = last;
        N++;
    }

    public T dequeue(){
        T item = first.item;
        first = first.next;
        if(isEmpty())
            last = null;
        N--;
        return item;
    }

    public int size(){
        return N;
    }

    //BE careful if you want to check this way: return N==0. You should change dequeue method!
    public boolean isEmpty(){
        return first == null;
    }

    private class Node{
        Node next;
        T item;
    }

    public Iterator<T> iterator(){
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<T>{

        private Node i = first;
        @Override
        public T next(){
            T item = first.item;
            first = first.next;
            return item;
        }
        @Override
        public boolean hasNext(){
            return first.next != null;
        }
    }

    public static void main(String[] args) {
        MyRandom random = new MyRandom();
        Integer[] arr  = Convert.primitiveArr(random.randomIntArr(20));
        LinkedQueue<Integer> queue = new LinkedQueue<>();

        System.out.println(Arrays.toString(arr));
        Arrays.stream(arr).forEach(queue::enqueue);
        System.out.print("dataStructures.Stack: ");
        while (!queue.isEmpty()){
            System.out.print(queue.dequeue() + ", ");
        }
    }
}