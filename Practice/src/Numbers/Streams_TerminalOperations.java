package Numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams_TerminalOperations {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        list.stream().forEach(System.out::println);

        //forEachOrdered for Ordering of Alphabetical
        Stream.of("A", "B", "C")
                .parallel()
                .forEach(x -> System.out.println("for Each:" + x));
        Stream.of("A", "B", "C")
                .parallel()
                .forEachOrdered(x -> System.out.println("for EachOrdered:" + x));

        //Collector
        List<Integer> evenList = Stream.of(1, 2, 3, 4, 5)
                .filter(x -> x % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(evenList);

        //Count
        List<Integer> list1=new ArrayList<>(Arrays.asList(1,2,3,4,5));
        Long count=list1.stream().count();
        System.out.println("Count:"+count);

        //Reduce
        List<Integer> list2=new ArrayList<>(Arrays.asList(1,2,3,4,5));
        int answer= list2.stream().reduce(0,(value,sum)->sum+=value);
        System.out.println("Sum:"+answer);

        //Min and Max
        Stream<Integer> stream=Stream.of(1,2,3,4,5);
        int minimum=stream.min(Integer::compare).get();
        System.out.println("Minimum:"+minimum);

        stream=Stream.of(2,3,7,9,1);
        int maximum=stream.max(Integer::compare).get();
        System.out.println("Maximum:"+maximum);

        //min and max both outputs are same but using only one Array List here
        List<Integer> numbers=Arrays.asList(4,2,6,9,1);
        Optional<Integer> min=numbers.stream().min(Integer::compare);
        Optional<Integer> max=numbers.stream().max(Integer::compare);
        System.out.println("Minimum Number: "+min.orElse(0));
        System.out.println("Maximum Number: "+max.orElse(0));

        //findFirst
        List<Integer> list3=new ArrayList<>(Arrays.asList(1,2,3,5,6));
        Optional<Integer> answer3=list3.stream().findFirst();
        answer3.ifPresent(value-> System.out.println("First Value:"+value));
//        System.out.println("First value:"+answer3);

        //findAny
        List<Integer> list4=new ArrayList<>(Arrays.asList(1,2,3,4,5));
        Optional<Integer> answer4=list4.stream().findAny();
        answer4.ifPresent(value-> System.out.println("Any Value:"+value));

        //noneMatch
        boolean containsTwo = list.stream().noneMatch(value -> value == 2);
        boolean containsZero = list.stream().noneMatch(value -> value == 0);
        System.out.println("Is 2 present: " + containsTwo);
        System.out.println("Is 0 present: " + containsZero);
    }
}
