package mapsandsets;

import java.util.LinkedHashSet;

public class LinkedHashSets {
    public static void main(String[] args){
        LinkedHashSet<String> lhs=new LinkedHashSet<>();
        lhs.add("A");
        lhs.add("B");
        lhs.add("C");
        lhs.add("D");

        System.out.println(lhs);
        for(String r:lhs){
            System.out.println(r);
        }
    }
}
