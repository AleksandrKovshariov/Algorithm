package dataStructures;

import tools.Convert;
import tools.MyRandom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

class LinkedStack<T> implements Iterable<T>{

    Node first;
    private int N = 0;

    public boolean isEmpty(){
        return first == null;
    }

    public void push(T item){
        Node oldFirst = first;
        N++;

        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    public int size(){
        return N;
    }

    public T pop(){
        T item = first.item;
        first = first.next;
        N--;
        return item;
    }

    private class Node{
        Node next;
        T item;
    }

    @Override
    public Iterator<T> iterator(){
        return new ReverseIterator();
    }

    private class ReverseIterator implements Iterator<T>{
        private Node i = first;
        @Override
        public T next(){
            T item = i.item;
            i = i.next;
            return item;

        }
        @Override
        public boolean hasNext(){
            return i.next != null;

        }
    }

    public static void main(String[] args) {
        MyRandom random = new MyRandom();
        Integer[] arr  = Convert.primitiveArr(random.randomIntArr(20));
        LinkedStack<Integer> stack = new LinkedStack<>();

        System.out.println(Arrays.toString(arr));
        Arrays.stream(arr).forEach(stack::push);
        System.out.print("dataStructures.Stack: ");
        while (!stack.isEmpty()){
            System.out.print(stack.pop() + ", ");
        }
    }
}