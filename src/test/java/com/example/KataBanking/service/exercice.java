package com.example.KataBanking.service;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class exercice {

    @Test
    public void test() {
        Predicate<Integer> isPair = integer -> integer % 2 == 0;
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> results = numbers.stream()
                .filter(isPair.negate())
                .collect(Collectors.toList());

        results.forEach(System.out::println);

    }

    @Test
    public void test2() {
        List<String> strings = List.of("hello", "world", "java", "programming");

        String result = strings.stream()
                .map(String::toUpperCase)
                .collect(Collectors.joining());

        System.out.println(result);

    }

    @Test
    public void test3() {
        List<Employee> employees = List.of(
                new Employee("John", 2500),
                new Employee("Mary", 3000),
                new Employee("David", 2000),
                new Employee("Linda", 3500)
        );

        double res = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0);


    }

    @Test
    public void test4() {
        List<String> strings = List.of("hello", "world", "java", "programming");

        String res = strings.stream()
                .max((o1, o2) -> o1.length() - o2.length())
                .orElse("");

        System.out.println(res);

    }

    @Test
    public void tes5() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Integer res = numbers.stream()
                .mapToInt(value -> value.intValue())
                .reduce(1, (a, b) -> a*b);

        System.out.println(res);
    }


}


class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
}