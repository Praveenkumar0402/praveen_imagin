package mapsandsets;


import java.util.Map;

import java.util.HashMap;

public class HashMapping {
    public static void main(String[] args){
        Map<Integer,String> hm=new HashMap<>();
        hm.put(1,"Praveen");
        hm.put(3,"Prasanna");
        hm.put(4,"Praveen");
        hm.put(2,"Dinesh");
        for(Map.Entry g: hm.entrySet() ){
            System.out.println(g);
        }
    }
}
