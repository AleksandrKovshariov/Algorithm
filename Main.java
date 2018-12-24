import java.util.*;
import java.lang.*;

//The algorithms I've learnt

public class Main{


	public static int Evklid(int p, int q){
		if(q == 0)
			return q;
		int r = p % q;
		return Evklid(q, r);
	}

	
	public static int binarySearch(int[] arr, int key){
		int lo = 0;
		int hi = arr.length - 1;

		while(lo <= hi){
			int mid = lo + (hi - lo)/2;
			if(key > arr[mid])
				lo = mid + 1;
			else if(key < arr[mid])
				hi = mid - 1;
			else
				return mid;
		}

		return -1;
	}

	public static void selectionSort(Comparable [] arr){
		int min;
		for(int i = 0; i < arr.length; i++){
			min = i;
			for(int j = i + 1; j < arr.length; j++){
				if(arr[min].compareTo(arr[j]) > 0)
					min = j;
			}
			Comparable t = arr[i];
			arr[i] = arr[min];
			arr[min] = t;
		}
	}

	public static void insertionSort(Comparable[] arr){

		for(int i = 1; i < arr.length; i++){

			for(int j = i; j > 0 && arr[j].compareTo(arr[j - 1]) < 0; j--){
				Comparable t = arr[j];
				arr[j] = arr[j - 1];
				arr[j - 1] = t;
			}
		}
	}

	public static void improvedInsertionSort(Comparable[] arr){
		for(int i = 1; i < arr.length; i++){
			Comparable key = arr[i];
			int j = i - 1;
			while(j >= 0 && arr[j].compareTo(key) > 0){
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = key;
		}
	}
	public static void shellSort(Comparable[] arr){
		int N = arr.length;
		int h = 1;
		while(h < N/3)
			h = h * 3 + 1;

		while(h >= 1){
			for(int i = h; i < N; i++){
				for(int j = i; j >= h && arr[j].compareTo(arr[j - h]) < 0; j-=h){
					Comparable t = arr[j];
					arr[j] = arr[j - h];
					arr[j - h] = t;
				}

			}
		h /= 3;
		}
	}

	private static Comparable[] aux;
	private static int counter = 0;
	public static void mergeSort(Comparable[] arr){
		aux = new Comparable[arr.length];
		msort(arr, 0, arr.length - 1);
	}

	public static void msort(Comparable[] arr, int lo, int hi){

		//insertion sort for small arrays
		if(hi - lo < 7){
			for(int i = lo; i <= hi; i++)
				for(int j = i; j > lo && arr[j].compareTo(arr[j - 1]) < 0; j--){
					Comparable t = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = t;
				}	
			return;
		}

		int mid = lo + (hi - lo)/2;
		msort(arr, lo, mid);
		msort(arr, mid + 1, hi);

		//oprimizatoin for already sorted parts
		if(arr[mid].compareTo(arr[mid + 1]) <= 0)
			return;

		abstractMerge(arr, lo, mid, hi);
	}

	public static void abstractMerge(Comparable[] arr, int lo, int mid, int hi){
		int i = lo, j = mid + 1;
		System.out.println("abstractMerge");
		for(int k = lo; k <= hi; k++){
			aux[k] = arr[k];
		}

		for(int k = lo; k <= hi; k++){
			if(i > mid)
				arr[k] = aux[j++];
			else if(j > hi)
				arr[k] = aux[i++];
			else if(aux[i].compareTo(aux[j]) > 0)
				arr[k] = aux[j++];
			else
				arr[k] = aux[i++];
		}
	}

	public static void main(String[] args){
		Integer[] arr = new Integer[100];
		for(int i = 0; i< arr.length; i++)
			arr[i] = i;
		mergeSort(arr);
		for(int i = 0; i < arr.length; i++)
			System.out.println(arr[i]);

	}
}

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
			public void remove(){

			}
			@Override
			public boolean hasNext(){
				return i.next != null;

			}

		}
}

class LinkedQueue<T> implements Iterable<T>{
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

	//BE carefull if you want to check this way: return N==0. You should change dequeue method!
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
			public void remove(){

			}
			@Override
			public boolean hasNext(){
				return first.next != null;

			}
		}
}

class Stack<T> implements Iterable<T>{

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
			public void remove(){

			}
			@Override
			public boolean hasNext(){
				return i > 0;

			}
		}
}

