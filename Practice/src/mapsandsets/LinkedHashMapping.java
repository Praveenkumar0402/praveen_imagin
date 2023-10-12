package mapsandsets;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapping {
    public static void main(String[] args) {
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(1, "Praveen");
        map.put(2, "Ajay");
        map.put(3, "Rajesh");
        map.put(1, "Vishnu");
        map.put(5,"Praveen");
        System.out.println(map);
        for (Map.Entry m : map.entrySet()) {
            System.out.println(m.getKey() + " " + m.getValue());
        }

        System.out.println("After adding the map");
        map.put(4,"Praveen");
        for(Map.Entry m1:map.entrySet()){
            System.out.println(m1.getKey()+" "+m1.getValue());
        }

        System.out.println("After replacing the value");
        map.replace(4,"Praveen","Chaitanya");
        for(Map.Entry m2: map.entrySet()){
            System.out.println(m2.getKey()+" "+m2.getValue());
        }

        System.out.println("Removing the key and values");
        map.remove(5);
        System.out.println(map);
    }
}
