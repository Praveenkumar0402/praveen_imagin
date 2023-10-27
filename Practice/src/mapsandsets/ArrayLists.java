package mapsandsets;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayLists {
    public static void main(String args[]) {
        ArrayList<String> list = new ArrayList<String>();//Creating arraylist
        list.add("Ravi");//Adding object in arraylist
        list.add("Vijay");
        list.add("Ravi");
        list.add("Ajay");
//Traversing list through Iterator
        Iterator itr = list.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
        System.out.println("==========");
        for(String r:list){
            System.out.println(r);
        }
    }
}