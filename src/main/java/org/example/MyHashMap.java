package org.example;

public class MyHashMap<K, V> {
    static final int DEFAULT_CAPACITY = 1 << 4;
    static final int MAXIMUM_CAPACITY = 1 << 30;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private int threshold; // capacity * load factor
    private int capacity;
    private int elementsQuantity;
    Node<K, V>[] table;
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
    }

    public void put(K key, V value){
        int hash = hash(key);
        int index = hash & capacity - 1;
        Node<K, V> newNode = new Node<>(hash, key, value);

        if(elementsQuantity >= capacity){
            resize();
        }

        if(table[index] == null){
            table[index] = newNode;
            elementsQuantity++;
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
                    elementsQuantity++;
                }
            }
        }
    }

    final void resize(){
        Node<K, V>[] oldTable = this.table;
        capacity = capacity();
        Node<K, V>[] newTable = (Node<K, V>[]) new Node[capacity];
        table = newTable;

        if(oldTable != null){
            for(Node<K, V> node: oldTable){
                if(node != null){
                    Node<K, V> currentNode = node;

                    while(currentNode != null){
                        put(node.key, node.value);
                        currentNode = currentNode.next;
                    }
                }
            }
            threshold = (int) (capacity * DEFAULT_LOAD_FACTOR);
        }
    }
    final int capacity(){
        return (this.table == null) ? capacity = DEFAULT_CAPACITY : capacity << 1;
    }
    public MyHashMap(){
        resize();
        threshold = (int) (capacity * DEFAULT_LOAD_FACTOR);
        elementsQuantity = 0;
    }

    /*-------------------------- STATIC METHODS --------------------------*/
    static int hash(Object key){
        int hash;
        return (key == null) ? 0 : (hash = key.hashCode()) ^ (hash >>> 16);
    }
}
