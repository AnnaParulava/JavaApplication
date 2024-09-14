package com.example.javaapplication.streamAPI.ui;

import androidx.lifecycle.ViewModel;

import com.example.javaapplication.streamAPI.StreamExample;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

public class StreamApiViewModel extends ViewModel {


    public String handleAverage(List<Integer> numbers) {
        try {
            return "Average: " + StreamExample.getAverage(numbers);
        } catch (Exception e) {
            return e.getMessage() != null ? e.getMessage() : "Error";
        }
    }

    public String handleTransformStrings(List<String> strings) {
        return String.join(", ", StreamExample.transformStrings(strings));
    }

    public String handleUniqueSquares(List<Integer> numbers) {
        return StreamExample.getUniqueSquares(numbers).stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

    public String handleGetLastElement(List<String> elements) {
        try {
            return "Last element: " + StreamExample.getLastElement(elements);
        } catch (Exception e) {
            return e.getMessage() != null ? e.getMessage() : "Error";
        }
    }

    public String handleSumOfEvenNumbers(int[] numbers) {
        return "Sum of even numbers: " + StreamExample.sumOfEvenNumbers(numbers);
    }

    public String handleStringsToMap(List<String> strings) {
        Map<Character, String> resultMap = StreamExample.stringsToMap(strings);
        return resultMap.entrySet().stream()
                .map(entry -> entry.getKey() + " -> " + entry.getValue())
                .collect(Collectors.joining(", "));
    }
}


