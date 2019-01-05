package dataStructures;

import tools.Convert;
import tools.MyRandom;

import java.util.Arrays;
import java.util.stream.IntStream;

public class BinarySearchST<K extends Comparable<K>, V>{
    private K[] keys;
    private V[] values;
    private int N = 0;

    public BinarySearchST(int size){
        keys = (K[])new Comparable[size];
        values = (V[])new Object[size];
    }

    public void put(K key, V value){
        int j = rank(key);
        if(j < N && key.compareTo(keys[j]) == 0){
            values[j] = value;
            return;
        }

        for(int i = N; i > j; i--){
            keys[i] = keys[i - 1];
            values[i] = values[i - 1];
        }

        keys[j] = key;
        values[j] = value;
        N++;

    }

    public K ceiling(K key){
        return keys[rank(key)];
    }
    public K floor(K key){
        int i = rank(key);
        if(i < N && keys[i].compareTo(key) == 0)
            return keys[i];

        return i > 0 ? keys[i-1] : null;
    }

    public V get(K key){
        int j = rank(key);
        if(j < N && key.compareTo(keys[j]) == 0)
            return values[j];
        return null;
    }

    public void delete(K key){
        put(key, null);
    }


    private int rank(K key){
        int lo = 0;
        int hi = N - 1;

        while(lo <= hi){
            int mid = lo + (hi - lo)/2;
            if(key.compareTo(keys[mid]) > 0)
                lo = mid + 1;
            else if(key.compareTo(keys[mid]) < 0)
                hi = mid - 1;
            else
                return mid;
        }
        return lo;
    }

    public static void main(String[] args) {
        MyRandom random = new MyRandom();
        Integer[] key  = Convert.primitiveArr(random.randomIntArr(10,30));
        Character[] value = Convert.toCharacter(random.randomIntArr(key.length,30));

        System.out.println("Key -> value: ");
        BinarySearchST<Integer,Character> dictionary = new BinarySearchST<>(key.length);
        IntStream.range(0, key.length).forEach(x -> System.out.println(key[x] + " -> " + value[x]));
        IntStream.range(0, key.length).forEach(x -> dictionary.put(key[x], value[x]));

        System.out.println("\nDictionary key -> value: ");
        IntStream.range(0, key.length).forEach(x -> System.out.println(key[x] + " -> " + dictionary.get(key[x])));

    }
}
