package mapsandsets;

import java.util.Map;
import java.util.TreeMap;

public class TreeMapping {
    public static void main(String[] args){
        Map<Integer,String> tm=new TreeMap<>();
        tm.put(4,"Praveen");
        tm.put(2,"Vishnu");
        tm.put(3,"Ajay");
        System.out.println(tm);
    }
}
