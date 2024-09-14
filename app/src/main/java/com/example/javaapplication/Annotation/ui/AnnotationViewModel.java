package com.example.javaapplication.Annotation.ui;

import static com.example.javaapplication.Annotation.MethodInvoker.invokeAnnotatedMethods;

import androidx.lifecycle.ViewModel;

import com.example.javaapplication.Annotation.MethodClass;

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
