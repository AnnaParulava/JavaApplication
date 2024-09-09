package com.example.javaapplication.StreamAPI;

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
        Map<Integer, Long> frequencyMap = numbers.stream()
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()));

        return frequencyMap.entrySet().stream()
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
                        s -> s.charAt(0),       // Первый символ - ключ
                        s -> s.substring(1)      // Остальные символы - значение
                ));
    }

    public static void main(String[] args) {
        // Пример работы методов
    }
}
