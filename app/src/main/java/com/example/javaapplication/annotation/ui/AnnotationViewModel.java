package com.example.javaapplication.annotation.ui;

import static com.example.javaapplication.annotation.MethodInvoker.invokeAnnotatedMethods;

import androidx.lifecycle.ViewModel;

import com.example.javaapplication.annotation.MethodClass;

public class AnnotationViewModel extends ViewModel {

    private final MethodClass mClass = new MethodClass();

    public String getAnnotatedMethods() {
        try {
            return invokeAnnotatedMethods(mClass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
