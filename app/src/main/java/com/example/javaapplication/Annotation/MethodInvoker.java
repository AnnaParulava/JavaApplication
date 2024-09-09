package com.example.javaapplication.Annotation;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Objects;

public class MethodInvoker {

    public static void invokeAnnotatedMethods(Object obj) throws Exception {
        // Получаем класс объекта
        Class<?> clazz = obj.getClass();

        // Получаем все методы класса
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            final boolean checkModifier = Modifier.isPrivate(method.getModifiers()) || Modifier.isProtected(method.getModifiers());
            // Проверяем, есть ли на методе аннотация @Repeat и он приватный или защищенный
            if (method.isAnnotationPresent(Repeat.class) && checkModifier) {
                Repeat repeat = method.getAnnotation(Repeat.class);

                if (repeat == null) {
                    throw new Exception("NPE Repeat was null");
                }

                int times = repeat.repeatValue();

                // Делаем приватные методы доступными для вызова
                method.setAccessible(true);

                // Вызываем метод нужное количество раз
                for (int i = 0; i < times; i++) {
                    // Генерируем параметры для метода в зависимости от его сигнатуры
                    Object[] params = generateParameters(method);
                    method.invoke(obj, params);
                }
            }
        }
    }

    // Метод для генерации параметров в зависимости от типа параметров метода
    private static Object[] generateParameters(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        Object[] params = new Object[parameterTypes.length];

        // Заполняем параметры значениями по умолчанию
        for (int i = 0; i < parameterTypes.length; i++) {
            params[i] = getDefaultValue(parameterTypes[i]);
        }
        return params;
    }

    // Метод для получения значения по умолчанию в зависимости от типа
    private static Object getDefaultValue(Class<?> type) {
        if (type.isArray()) {
            return Array.newInstance(Objects.requireNonNull(type.getComponentType()), 0);
        }

        return switch (type.getName()) {
            case "int" -> 0;
            case "boolean" -> false;
            case "double" -> 0.0;
            case "long" -> 0L;
            case "float" -> 0.0f;
            case "short" -> (short) 0;
            case "byte" -> (byte) 0;
            case "char" -> '\u0000';
            case "java.lang.String" -> "Default String";
            default -> null;
        };
    }

    public static void main(String[] args) throws Exception {
        MethodClass myClass = new MethodClass();
        invokeAnnotatedMethods(myClass);
    }
}
