package Numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Streams_IntermediateOperations {
    public static void main(String[] args) {
        //Starting letter names converting to uppercase
        List<String> names = Arrays.asList("Praveen", "Vishnu", "Dinesh", "Ajay");
        List<String> filteredNames = names.stream()
                .filter(name -> name.startsWith("P"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(filteredNames);

        /*Intermediate Operations*/
        //Numbers divisible values
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> ans = list.stream()
                .filter(value -> value % 2 != 0)
                .collect(Collectors.toList());
        System.out.println(ans);

        //Numbers Multiplied
        List<Integer> lists = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> answer = list.stream()
                .map(value -> value * 10)
                .collect(Collectors.toList());
        answer.add(100);
        System.out.println(answer);

        //Ascending and Descending Order
        List<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.print("Ascending Order:");
        list2.stream().sorted().forEach(System.out::print);
        System.out.print("\nDescending Order:");
        list2.stream().sorted(Comparator.reverseOrder())
                .forEach(System.out::print);

        //Distinct
        List<Integer> list3 = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 4, 3));
        List<Integer> answer3 = list3.stream()
                .distinct().toList();//Here using only toList
//        answer3.add(5);//when we are using toList this line will not work
        System.out.println("\n" + answer3);

        //Peek(first non-modified elements are printing)
        List<Integer> list4 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> answer4 = list4.stream()
                .filter(value -> value % 2 == 0)
                .peek(value -> System.out.println(value))
                .map(value -> value * 10)
                .collect(Collectors.toList());
        System.out.println(answer4);

        //Limit of values
        List<Integer> list5 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> answer5 = list5.stream()
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(answer5);

        //Skip the first number of values
        List<Integer> list6 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> answer6 = list6.stream()
                .skip(3)
                .collect(Collectors.toList());
        System.out.println(answer6);

    }

}
