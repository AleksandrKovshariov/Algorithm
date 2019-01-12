package dataStructures;

import tools.Convert;
import tools.MyRandom;

import java.util.stream.IntStream;

public class ChainHashST<K,V> {
    private int M;
    public static final int SIZE = 997;
    private SequentialSearch<K,V>[] list;

    public ChainHashST(){
        this.M = SIZE;
        list = (SequentialSearch<K, V>[]) new SequentialSearch[SIZE];
        for (int i = 0; i < SIZE; i++) {
            list[i] = new SequentialSearch<>();
        }
    }

    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(K key, V value){
        int hash = hash(key);
        list[hash].put(key, value);
    }

    public V get(K key){
        int hash = hash(key);
        return list[hash].get(key);
    }

    public static void main(String[] args) {
        MyRandom random = new MyRandom();
        Integer[] key  = Convert.primitiveArr(random.randomIntArr(10,30));
        Character[] value = Convert.toCharacter(random.randomIntArr(key.length,30));

        System.out.println("Key -> value: ");
        ChainHashST<Integer,Character> dictionary = new ChainHashST<>();
        IntStream.range(0, key.length).forEach(x -> System.out.println(key[x] + " -> " + value[x]));
        IntStream.range(0, key.length).forEach(x -> dictionary.put(key[x], value[x]));

        System.out.println("\nDictionary key -> value: ");
        IntStream.range(0, key.length).forEach(x -> System.out.println(key[x] + " -> " + dictionary.get(key[x])));

    }
}
