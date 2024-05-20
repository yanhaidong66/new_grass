package org.example;

import java.util.TreeMap;

public class Main{
    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(1, "One");
        treeMap.put(2, "Two");
        treeMap.put(3, "Three");

        // 获取值
        System.out.println("Key 2: " + treeMap.get(2));
        System.out.println(treeMap);//{1=One, 2=Two, 3=Three}

        // 检查键和值
        System.out.println("Contains key 2: " + treeMap.containsKey(2));
        System.out.println("Contains value 'One': " + treeMap.containsValue("One"));

        // 获取键的范围
        System.out.println("First key: " + treeMap.firstKey());//First key: 1
        System.out.println("Last key: " + treeMap.lastKey());//Last key: 3

        // 子映射
        System.out.println("SubMap (2 to 3): " + treeMap.subMap(1, 3));//{1=One, 2=Two, 3=Three}

        // 清空映射
        treeMap.clear();
        System.out.println("Is empty: " + treeMap.isEmpty());//true
    }
}