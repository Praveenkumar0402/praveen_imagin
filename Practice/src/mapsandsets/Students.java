package mapsandsets;

import java.util.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Students {
    private int age;
    private String name;
    private Character Gender;

    public Students(int age, String name, Character gender) {
        this.age = age;
        this.name = name;
        Gender = gender;
    }

    public Character getGender() {
        return Gender;
    }

    public void setGender(Character gender) {
        Gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Students{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", Gender=" + Gender +
                '}';
    }

    public static void main(String[] args) {
        List<Students> studentsList = new ArrayList<>();
        studentsList.add(new Students(23, "Sridhar", 'm'));
        studentsList.add(new Students(24, "Dinesh", 'm'));
        studentsList.add(new Students(25, "praveen", 'f'));
        studentsList.add(new Students(25, "praveen", 'm'));


        Map<String,List<Students>> map=studentsList.stream().collect(Collectors.groupingBy(students -> students.name));
        System.out.println(map);

        System.out.println("====================================================================================================================================");

        Map<Integer, List<Students>> maps = studentsList.stream().collect(Collectors.groupingBy(Students::getAge));
        System.out.println(maps);

        System.out.println("====================================================================================================================================");

        Map<Character, List<Students>> m = studentsList.stream().collect(Collectors.groupingBy(Students::getGender));
        System.out.println(m);

        System.out.println("====================================================================================================================================");

        studentsList.stream().filter(n -> n.age > 23).forEach(System.out::println);

        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4));

        Integer n = numbers.stream().filter(j -> j % 2 == 0).mapToInt(Integer::intValue).sum();
        System.out.println(n);
        numbers.stream().map(integer -> (integer % 2 == 0) ? integer : integer * 2).forEach(System.out::println);

        List<String> names = new ArrayList<>(Arrays.asList("Sridhar", "Nookesh", "Dinesh"));
        names.stream().map(name -> name.toUpperCase()).forEach(System.out::println);

        names.stream().forEach(name -> System.out.println(name.length()));

        List<String> strings = studentsList.stream().map(i -> i.getName().toUpperCase()).collect(Collectors.toList());
        System.out.println(strings);

    }
}
