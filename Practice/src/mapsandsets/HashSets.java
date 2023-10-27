package mapsandsets;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HashSets {
    public static void main(String[] args){
        HashSet<String> hs=new HashSet<>();
        hs.add("a");
        hs.add("b");
        hs.add("c");
        hs.add("d");
        System.out.println(hs);

        Map<String,Integer> hm=new HashMap<>();
        hm=hs.stream().collect(Collectors.toMap(Function.identity(),String::hashCode));
        System.out.println(hm);

        for(Map.Entry h1:hm.entrySet()){
            System.out.println(h1.getKey()+" "+h1.getValue());
        }
//        hs.stream().filter(n->n.).forEach(System.out::println);

    }
}
