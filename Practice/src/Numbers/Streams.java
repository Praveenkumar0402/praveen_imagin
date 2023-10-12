package Numbers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {
    public static void main(String[] args) {
        Stream.Builder<String> builder = Stream.builder();
        builder.add("Praveen")
                .add("Ajay")
                .add("Chaitanya");
        Stream<String> stream = builder.build();
        stream.forEach(System.out::println);

        //Count of Multiple Streams
        Stream<Integer> stream1 = Stream.of(1, 2, 3);
        System.out.println("Size of Stream:" + stream1.count());
        Stream<Integer> stream2 = Stream.of(4, 5, 6, 7, 8);
        System.out.println("Size of Stream:" + stream2.count());

        //Count of Array
        Integer[] array = new Integer[]{1, 2, 3, 4, 5};
        Stream<Integer> stream3 = Arrays.stream(array);
        System.out.println("Size:" + stream3.count());

        //concatenation
        Stream<Integer> stream4 = Stream.of(1, 2, 3, 9);
        Stream<Integer> stream5 = Stream.of(4, 5, 6, 7, 8);
        Stream<Integer> stream6 = Stream.concat(stream4, stream5);
        System.out.println("Concatenation Values");
        stream6.forEach(System.out::println);

        //Stream iterate
        List<Integer> values = Stream.iterate(1, n -> n * 2)
                .limit(5)
                .collect(Collectors.toList());
        System.out.println("Stream iteration values");
        values.forEach(System.out::println);
    }
}
