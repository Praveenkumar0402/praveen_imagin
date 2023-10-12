package mapsandsets;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
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

        Map<String,Long> hm=new HashMap<>();
        hm=hs.stream().collect(Collectors.toMap(Function.identity() ,HashSet::copyOf,LinkedHashMap::new,Collectors.counting()));
        System.out.println(hm);

        for(Map.Entry h1:hm.entrySet()){
            System.out.println(h1.getKey()+" "+h1.getValue());
        }

    }
}
