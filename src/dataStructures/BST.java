package dataStructures;

import tools.Convert;
import tools.MyRandom;

import java.util.stream.IntStream;

public class BST<K extends Comparable<K>, V> {
    private Node root;
    private class Node {
        private K key;
        private V value;
        private Node left, right;
        private int N;

        public Node(K key, V value, int n) {
            this.key = key;
            this.value = value;
            this.N = n;
        }
    }

        public int size(){
            return size(root);
        }

        public int size(Node node){
            return node == null ? 0 : node.N;
        }

        public V get(K key){
            return get(root, key);
        }

        private V get(Node node, K key){
            if(node == null)
                return null;
            if(node.key.compareTo(key) > 0)
                return get(node.left, key);
            else if(node.key.compareTo(key) < 0)
                return get(node.right, key);
            else
                return node.value;
        }

        public void put(K key, V value){
            root = put(root, key, value);
        }

        private Node put(Node node, K key, V value){
            if(node == null)
                return new Node(key, value, 1);

            int cmp = key.compareTo(node.key);
            if(cmp > 0){
                node.right = put(node.right, key, value);
            }
            else if(cmp < 0){
                node.left  = put(node.left, key, value);
            }
            else{
                node.value = value;
            }
            node.N = size(node.left) + size(node.right) + 1;
            return node;
        }

    public static void main(String[] args) {
        MyRandom random = new MyRandom();
        Integer[] key  = Convert.primitiveArr(random.randomIntArr(10,30));
        Character[] value = Convert.toCharacter(random.randomIntArr(key.length,30));

        System.out.println("Key -> value: ");
        BST<Integer,Character> dictionary = new BST<>();
        IntStream.range(0, key.length).forEach(x -> System.out.println(key[x] + " -> " + value[x]));
        IntStream.range(0, key.length).forEach(x -> dictionary.put(key[x], value[x]));

        System.out.println("\nDictionary key -> value: ");
        IntStream.range(0, key.length).forEach(x -> System.out.println(key[x] + " -> " + dictionary.get(key[x])));

    }



}
