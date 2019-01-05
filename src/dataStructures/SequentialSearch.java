package dataStructures;

import tools.Convert;
import tools.MyRandom;

import java.util.Iterator;
import java.util.stream.IntStream;

public class SequentialSearch<K, V> implements Iterable<K>{

    private Node first;
    private int N = 0;


    public void put(K key, V value) {
        for (Node x = first; x != null; x = x.next) {
            if (x.key.equals(key)) {
                x.value = value;
                return;
            }
        }
        first = new Node(key, value, first);
        N++;
    }

    public V get(K key) {
        N--;
        for (Node x = first; x != null; x = x.next)
            if (x.key.equals(key))
                return x.value;

        return null;
    }

    public boolean contains(K key){
        return get(key) != null;
    }

    public void delete(K key) {
        if (first.key.equals(key)){
            first = first.next;
            return;
        }
        for (Node x = first; x.next != null; x = x.next) {
            if (x.next.key.equals(key)) {
                x.next = x.next.next;
                return;
            }
        }
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    private class Node {

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        K key;
        V value;
        Node next;
    }

    @Override
    public Iterator<K> iterator(){
        return new SequentialIterator();
    }

    private class SequentialIterator implements Iterator<K>{
        private Node x = first;
        @Override
        public boolean hasNext(){
            return x != null;
        }
        @Override
        public K next(){
            K nex = x.key;
            x = x.next;
            return nex;
        }
    }

    public static void main(String[] args) {
        MyRandom random = new MyRandom();
        Integer[] key  = Convert.primitiveArr(random.randomIntArr(10,30));
        Character[] value = Convert.toCharacter(random.randomIntArr(key.length,30));

        System.out.println("Key -> value: ");
        SequentialSearch<Integer,Character> dictionary = new SequentialSearch<>();
        IntStream.range(0, key.length).forEach(x -> System.out.println(key[x] + " -> " + value[x]));
        IntStream.range(0, key.length).forEach(x -> dictionary.put(key[x], value[x]));

        System.out.println("\nDictionary key -> value: ");
        IntStream.range(0, key.length).forEach(x -> System.out.println(key[x] + " -> " + dictionary.get(key[x])));

    }


}

