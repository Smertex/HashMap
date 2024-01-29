package org.example;

import junit.framework.TestCase;
import org.junit.Test;

public class MyHashMapTest extends TestCase {
    @Test
    public void testPut(){
        MyHashMap<String, Integer> myHashMap = new MyHashMap<>();
        myHashMap.put(null, 4);
        myHashMap.put("KING", 23);
        myHashMap.put("BLAKE", 24);

        assertNull(myHashMap.getTable()[0].getKey(), null);
        assertEquals(myHashMap.getTable()[4].getKey(), "KING");
        assertEquals(myHashMap.getTable()[4].getNode().next.key, "BLAKE");
    }

    @Test
    public void testSearch(){
        MyMap<String, Integer> myHashMap = new MyHashMap<>();

        myHashMap.put("Abc", 12);
        myHashMap.put(null, 4);
        myHashMap.put("KING", 23);
        myHashMap.put("BLAKE", 24);
        myHashMap.put("Rake", 44);
        myHashMap.put("Zere", 215);
        myHashMap.put("Hu Tao", 228);
        myHashMap.put("Furina", 119);
        myHashMap.put("Red", 55);
        myHashMap.put("cdd", 21);
        myHashMap.put("ggggg", 58);
        myHashMap.put("gad", 54);
        myHashMap.put("led", 55);

        assertEquals((int) myHashMap.get("Furina"), 119);
        assertEquals((int) myHashMap.get("Hu Tao"), 228);
        assertEquals((int) myHashMap.get("gad"), 54);
    }

    public void testRemove(){
        MyHashMap<String, Integer> myHashMap = new MyHashMap<>();

        myHashMap.put("Abc", 12);
        myHashMap.put(null, 4);
        myHashMap.put("KING", 23);
        myHashMap.put("BLAKE", 24);
        myHashMap.put("Rake", 44);
        myHashMap.put("Zere", 215);
        myHashMap.put("Hu Tao", 228);
        myHashMap.put("Furina", 119);
        myHashMap.put("Red", 55);
        myHashMap.put("cdd", 21);
        myHashMap.put("ggggg", 58);

       //King -> Blake -> Zere

        myHashMap.remove(null);
        myHashMap.remove("BLAKE");
        assertEquals(myHashMap.getTable()[0].key, "Furina");
        assertEquals(myHashMap.getTable()[4].getNode().next.key, "Zere");
    }

    public void testRemoveAll(){
        MyMap<String, Integer> myHashMap = new MyHashMap<>();

        myHashMap.put("Abc", 12);
        myHashMap.put(null, 4);
        myHashMap.put("KING", 23);
        myHashMap.put("BLAKE", 24);
        myHashMap.put("Rake", 44);
        myHashMap.put("Zere", 215);
        myHashMap.put("Hu Tao", 228);
        myHashMap.put("Furina", 119);
        myHashMap.put("Red", 55);
        myHashMap.put("cdd", 21);
        myHashMap.put("ggggg", 58);

        myHashMap.removeAll();
        assertEquals(0, myHashMap.getSize());
    }
}