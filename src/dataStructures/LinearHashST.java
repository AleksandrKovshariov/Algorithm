package dataStructures;

import tools.Convert;
import tools.MyRandom;

import java.util.stream.IntStream;

public class LinearHashST<K,V> {
    private int N; //number of pair key-value
    private int M = 16;
    private K[] keys;
    private V[] values;

    public LinearHashST(){
        keys = (K[])new Object[M];
        values = (V[])new Object[M];
    }

    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(K key, V value){

        int i;
        for(i = hash(key); keys[i] != null; i = (i + 1) % M){
            if(keys[i].equals(key)) {
                values[i] = value;
                return;
            }
        }
        keys[i] = key;
        values[i] = value;
        N++;

    }

    public V get(K key){
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if(keys[i].equals(key))
                return values[i];
        }
        return null;
    }

    public static void main(String[] args) {
        MyRandom random = new MyRandom();
        Integer[] key  = Convert.primitiveArr(random.randomIntArr(10,30));
        Character[] value = Convert.toCharacter(random.randomIntArr(key.length,30));

        System.out.println("Key -> value: ");
        LinearHashST<Integer,Character> dictionary = new LinearHashST<>();
        IntStream.range(0, key.length).forEach(x -> System.out.println(key[x] + " -> " + value[x]));
        IntStream.range(0, key.length).forEach(x -> dictionary.put(key[x], value[x]));

        System.out.println("\nDictionary key -> value: ");
        IntStream.range(0, key.length).forEach(x -> System.out.println(key[x] + " -> " + dictionary.get(key[x])));

    }
}
