package org.example;

import java.util.Objects;

public class MyHashMap<K, V> implements MyMap<K, V>{
    static final int DEFAULT_CAPACITY = 1 << 4;
    static final int MAXIMUM_CAPACITY = 1 << 30;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private int threshold; // capacity * load factor
    private int capacity;
    private int size;
    private Node<K, V>[] table;
    static final class Node<K, V>{
        final int hashCode;
        final K key;
        V value;
        Node<K, V> next;

        public Node(int hashCode, K key, V value){
            this.hashCode = hashCode;
            this.key = key;
            this.value = value;
            this.next = null;
        }

        @Override
        public String toString() {
            if(key != null) {
                return "Hash-code: " + hashCode + "\n"
                        + "Key: " + key.toString() + "\n"
                        + "Value: " + value.toString();
            }
            return "Hash-code: " + hashCode + "\n"
                    + "Key: null\n"
                    + "Value: " + value.toString();
        }

        public Node<K, V> getNode(){
            return this;
        }

        public int getHashCode(){
            return hashCode;
        }

        public K getKey(){
            return key;
        }

        public V getValue(){
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Node
                    && this.hashCode == ((Node<?, ?>) obj).hashCode
                    && this.key == ((Node<?, ?>) obj).key;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }
    }
    @Override
    public void put(K key, V value){
        if(size >= MAXIMUM_CAPACITY) return;

        int hash = hash(key);
        int index = hash & capacity - 1;
        Node<K, V> newNode = new Node<>(hash, key, value);

        if(table[index] == null){
            table[index] = newNode;
            size++;
        } else {
            Node<K, V> currentNode = table[index];
            while(currentNode != null){
                if(currentNode.equals(newNode)){
                    currentNode.value = newNode.value;
                    break;
                } else if (currentNode.next != null){
                    currentNode = currentNode.next;
                } else {
                    currentNode.next = new Node<>(hash, key, value);
                    size++;
                }
            }
        }
        if(size >= this.threshold){
            resize();
        }
    }
    @Override
    public V get(K key){
        int hash = hash(key);
        int index = hash & capacity - 1;
        Node<K, V> node = table[index];

        if(node != null){
            Node<K, V> currentNode = node;
            while (currentNode != null){
                if(currentNode.key == key){
                    System.out.println(currentNode);
                    return currentNode.value;
                }
                currentNode = currentNode.next;
            }
        }
        System.out.println("Value not found");
        return null;
    }
    @Override
    public void remove(K key) {
        int hash = hash(key);
        int index = hash & capacity - 1;
        Node<K, V> node = table[index];

        if (node != null) {
            Node<K, V> currentNode = node;
            Node<K, V> previousNode = null;
            while (currentNode != null) {
                if (key == currentNode.key) {
                    if (currentNode == node) {
                        table[index] = currentNode.next;
                    } else {
                        previousNode.next = currentNode.next;
                    }
                    size--;
                    break;
                }
                previousNode = currentNode;
                currentNode = currentNode.next;
            }
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void removeAll() {
        table = null;
        size = 0;
        resize();
        threshold = (int) (capacity * DEFAULT_LOAD_FACTOR);
    }

    final void resize(){
        Node<K, V>[] oldTable = this.table;
        capacity = capacity();
        table = (Node<K, V>[]) new Node[capacity];

        if(oldTable != null){
            threshold = (int) (capacity * DEFAULT_LOAD_FACTOR);
            size = 0;
            for(Node<K, V> node: oldTable){
                if(node != null){
                    Node<K, V> currentNode = node;

                    while(currentNode != null){
                        put(currentNode.key, currentNode.value);
                        currentNode = currentNode.next;
                    }
                }
            }
        }
    }
    final int capacity(){
        return (this.table == null) ? capacity = DEFAULT_CAPACITY : capacity << 1;
    }
    public MyHashMap(){
        resize();
        threshold = (int) (capacity * DEFAULT_LOAD_FACTOR);
        size = 0;
    }
    public Node<K, V>[] getTable() {
        return table;
    }

    /*-------------------------- STATIC METHODS --------------------------*/
    static int hash(Object key){
        int hash;
        return (key == null) ? 0 : (hash = key.hashCode()) ^ (hash >>> 16);
    }
}
