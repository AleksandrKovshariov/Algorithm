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

	public static void quickSort(Comparable[] arr){
		quickHelp(arr, 0, arr.length - 1);
	}


	public static void quickHelp(Comparable[] arr, int lo, int hi){
		if(lo >= hi)
			return;
		int j = partition(arr, lo, hi);
		quickHelp(arr, lo, j - 1);
		quickHelp(arr, j + 1, hi);	

	}


	private static Comparable[] aux;
	private static int counter = 0;
	private static final int SIZE_INSERTION = 10;
	public static void mergeSort(Comparable[] arr){
		aux = new Comparable[arr.length];
		msort(arr, 0, arr.length - 1);
	}

	public static void msort(Comparable[] arr, int lo, int hi){

		//insertion sort for small arrays
		if(hi - lo < SIZE_INSERTION){
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
		int i = lo;
		int j = mid + 1;

		for(int k = 0; k <= hi; k++){
			if(i > mid)
				aux[k] = arr[j++];
			else if(j > hi)
				aux[k] = arr[i++];
			else if(arr[i].compareTo(arr[j]) > 0){
				aux[k] = arr[j++];
			}
			else
				aux[k] = arr[i++];
		}

		arr = aux;

	}


	public static void mergeIter(Comparable[] arr){
		aux = new Comparable[arr.length];
		int N = arr.length;
		for(int i = 1; i < N; i = i + i){
			for (int j = 0; j < N - i; j += i + i) {
				abstractMerge(arr, j, j + i - 1, Math.min(j + i + i - 1, N - 1));
			}
		}

	}

	public static int partition(Comparable[] arr, int lo, int hi){
		Comparable pivot = arr[hi];
		int i = lo - 1;
		for(int j = lo; j < hi; j++){
			if(pivot.compareTo(arr[j]) >= 0){
				i++;
				Comparable t = arr[j];
				arr[j] = arr[i];
				arr[i] = t;
			}
		}
		arr[hi] = arr[i+1]; 
        arr[i+1] = pivot; 
		return i + 1;
	} 	

	public static String reverseString(String str){
		char[] strChar = str.toCharArray();
        for (int i = 0, j = str.length() - 1; i < j; i++, j--){
            char t = strChar[i];
            strChar[i] = strChar[j];
            strChar[j] = t;
        }
        return new String(strChar);
	}

	public static void sink(Comparable[] arr, int k, int N){
		while(2*k <= N){
			int j = 2*k - 1;
			if(j < N && arr[j].compareTo(arr[j + 1]) < 0)
				j++;
			if(arr[k].compareTo(arr[j]) > 0)
				break;
			Comparable t = arr[j];
			arr[j] = arr[k];
			arr[k] = t;
			k = j;
			
		}
	}

	public static void binaryHeapSort(Comparable[] arr){
		int N = arr.length;
		for(int i = N / 2; i >= 1; i--)
			sink(arr, i, N);
		// while(N > 1){
		// 	Comparable t = arr[1];
		// 	arr[1] = arr[--N];
		// 	arr[N] = t;
		// 	sink(arr, 1, N);
		// }
	}

	public static void main(String[] args){

		Integer[] arr = new Integer[]{2,4,8,6,1};
		binaryHeapSort(arr);

		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + ", ");
		}

	}
}


class MaxPQ<T extends Comparable<T>>{
	private int N = 0;
	private T[] arr;

	public MaxPQ(int max){
		arr = (T[])new Comparable[max];
	}

	public void insert(T item){
		arr[++N] = item;
		swim(N);
	}

	public T delMax(){
		T val = arr[1];
		T t = arr[1];
		arr[1] = arr[N];
		arr[N] = t;

		arr[N] = null;
		N--;
		sink(1);

		return val;

	}	
		
	private void swim(int k){
		while(k > 1 && arr[k].compareTo(arr[k/2]) > 0){
			T t = arr[k];
			arr[k] = arr[k/2];
			arr[k/2] = t;
			k /= 2;
		}	

	}
	private void sink(int k){
		while(2*k <= N ){
			int j = k * 2;
			if(j < N && arr[j].compareTo(arr[j + 1]) < 0)
				j++;
			if(arr[k].compareTo(arr[j]) > 0)
				break;
			T t = arr[k];
			arr[k] = arr[j];
			arr[j] = t;
			k = j;
		}


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