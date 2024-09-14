package com.example.javaapplication.streamAPI;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Arrays;

public class StreamExample {
    //    С использованием только Stream API реализовать следующие методы:

    //            ● метод, возвращающий среднее значение списка целых чисел;
    public static double getAverage(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElseThrow(() -> new IllegalArgumentException("List is empty"));
    }

    //          ● метод, приводящий все строки в списке в верхний регистр и
    //    добавляющий к ним префикс «_new_»;
    public static List<String> transformStrings(List<String> strings) {
        return strings.stream()
                .map(s -> "_new_" + s.toUpperCase())
                .collect(Collectors.toList());
    }

    //          ● метод, возвращающий список квадратов всех встречающихся
    //    только один раз элементов списка;
    public static List<Integer> getUniqueSquares(List<Integer> numbers) {
        return numbers.stream()
                // Группируем элементы и считаем количество их вхождений
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()))
                .entrySet().stream()
                // только те элементы, которые встречаются один раз
                .filter(entry -> entry.getValue() == 1)
                .map(entry -> entry.getKey() * entry.getKey())
                .collect(Collectors.toList());
    }


    //          ● метод, принимающий на вход коллекцию и возвращающий ее
    //    последний элемент или кидающий исключение, если коллекция
    //    пуста;
    public static <T> T getLastElement(Collection<T> collection) {
        return collection.stream()
                .reduce((first, second) -> second)
                .orElseThrow(() -> new IllegalArgumentException("Collection is empty"));
    }


    //          ● метод, принимающий на вход массив целых чисел, возвращающий
    //    сумму чётных чисел или 0, если чётных чисел нет;
    public static int sumOfEvenNumbers(int[] numbers) {
        return Arrays.stream(numbers)
                .filter(n -> n % 2 == 0)
                .sum();
    }

    //          ● метод, преобразовывающий все строки в списке в Map, где первый
    //    символ – ключ, оставшиеся – значение;
    public static Map<Character, String> stringsToMap(List<String> strings) {
        return strings.stream()
                .collect(Collectors.toMap(
                        s -> s.charAt(0),
                        s -> s.substring(1)
                ));
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        double average = getAverage(numbers);
        System.out.println("Average: " + average);

        List<String> strings = Arrays.asList("apple", "banana", "cherry");
        List<String> transformedStrings = transformStrings(strings);
        System.out.println("Transformed Strings: " + transformedStrings);

        List<Integer> numbersWithDuplicates = Arrays.asList(1, 2, 2, 3, 4, 4, 5, 2, 1, 7);
        List<Integer> uniqueSquares = getUniqueSquares(numbersWithDuplicates);
        System.out.println("Unique Squares: " + uniqueSquares);

        List<String> stringList = Arrays.asList("first", "second", "third");
        String lastElement = getLastElement(stringList);
        System.out.println("Last Element: " + lastElement);

        int[] evenNumbersArray = {1, 2, 3, 4, 5, 6};
        int sumEven = sumOfEvenNumbers(evenNumbersArray);
        System.out.println("Sum of Even Numbers: " + sumEven);

        List<String> stringListForMap = Arrays.asList("apple", "banana", "cherry");
        Map<Character, String> stringMap = stringsToMap(stringListForMap);
        System.out.println("String to Map: " + stringMap);
    }
}
