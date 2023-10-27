package mapsandsets;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TreeSets {
    public static void main(String[] args){

        Set<String> ts=new TreeSet<>();
        ts.add("Ajay");
        ts.add("Praveen");
        ts.add("Vishnu");
        ts.add("Chaitanya");
        ts.add("Praveen");

        System.out.println(ts);

        TreeMap<String,Double> tm=new TreeMap<>();
//        tm=ts.stream().collect(Collectors.toCollection(Collectors.toCollection(TreeMap::new)));
//        System.out.println(tm);

//        for (Map.Entry<String, Double> entry : ts) {
//            tm.put(entry.getKey(), entry.getValue());
//        }
    }
}
