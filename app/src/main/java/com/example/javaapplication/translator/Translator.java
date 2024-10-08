package com.example.javaapplication.translator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Translator {

    // Словарь для хранения переводов
    private final Map<String, String> dictionary = new HashMap<>();

    // Метод для чтения словаря из файла
    public void loadDictionary(String fileName) throws InvalidFileFormatException, FileReadException {
        File file = new File(fileName);

        if (!file.exists()) {
            throw new FileReadException("File does not exist: " + fileName);
        }

        if (!file.canRead()) {
            throw new FileReadException("Cannot read file: " + fileName);
        }

        // Чтение файла
        putWordsToDictinaryFromFile(file);
    }

    private void putWordsToDictinaryFromFile(File file) throws InvalidFileFormatException, FileReadException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Разделение строки по символу "|"
                String[] parts = line.split("\\|");
                if (parts.length != 2) {
                    throw new InvalidFileFormatException("Invalid format in dictionary file: " + line);
                }
                String phrase = parts[0].trim().toLowerCase(); // регистр букв игнорируется
                String translation = parts[1].trim();
                dictionary.put(phrase, translation);
            }
        } catch (IOException e) {
            throw new FileReadException("Error reading file: " + file.getName());
        }
    }

    // Метод для получения текста из файла словаря
    public String getTextFromFile(String fileName) throws FileReadException {
        File file = new File(fileName);

        if (!file.exists()) {
            throw new FileReadException("File does not exist: " + fileName);
        }

        if (!file.canRead()) {
            throw new FileReadException("Cannot read file: " + fileName);
        }

        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new FileReadException("Error reading file: " + file.getName());
        }

        return contentBuilder.toString();
    }

    public String translate(String text) {
        // Преобразуем текст в нижний регистр и разбиваем на слова
        String[] words = text.toLowerCase().split("\\s+");
        StringBuilder translatedText = new StringBuilder();

        int i = 0;
        while (i < words.length) {
            String originalWord = words[i];
            String longestMatch = null;

            // Ищем наибольшее совпадение по длине фраз из словаря
            for (int length = words.length - i; length > 0; length--) {
                String phrase = String.join(" ", Arrays.copyOfRange(words, i, i + length));
                if (dictionary.containsKey(phrase)) {
                    longestMatch = phrase;
                    break;
                }
            }

            // Если найдена подходящая фраза
            if (longestMatch != null) {
                translatedText.append(dictionary.get(longestMatch)).append(" ");
                i += longestMatch.split("\\s+").length;
            } else {
                translatedText.append(originalWord).append(" ");
                i++;
            }
        }

        return translatedText.toString().trim();
    }

    public static void main(String[] args) {
        Translator translator = new Translator();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter file path:");
        String file = scanner.nextLine();
        try {
            translator.loadDictionary(file);
            String dictionaryText = translator.getTextFromFile(file); // Получение текста словаря
            System.out.println("Dictionary Content:");
            System.out.println(dictionaryText);
        } catch (InvalidFileFormatException | FileReadException e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }

        // Запрос текста для перевода
        System.out.println("Enter the text to translate:");
        String text = scanner.nextLine();

        // Выполнение перевода
        String translated = translator.translate(text);
        System.out.println("Translated text: " + translated);
    }
}

