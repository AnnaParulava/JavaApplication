package com.example.javaapplication.translator.ui;

import android.content.Context;
import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.javaapplication.translator.FileReadException;
import com.example.javaapplication.translator.InvalidFileFormatException;
import com.example.javaapplication.translator.Translator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class TranslationViewModel extends ViewModel {
    private final MutableLiveData<String> _translatedText = new MutableLiveData<>();
    private final MutableLiveData<String> _dictionaryText = new MutableLiveData<>();
    private final MutableLiveData<String> _originalText = new MutableLiveData<>();
    public final LiveData<String> translatedText = _translatedText;
    public final LiveData<String> originalText = _originalText;
    public final LiveData<String> dictionaryText = _dictionaryText;
    private final Translator translator = new Translator();

    public void processPickedFileUri(Context context, Uri uri) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(uri);
            if (inputStream != null) {
                File tempFile = createTempFileFromInputStream(inputStream, context);

                translator.loadDictionary(tempFile.getAbsolutePath());
                _dictionaryText.setValue(translator.getTextFromFile(tempFile.getAbsolutePath()));
            }
        } catch (IOException | InvalidFileFormatException | FileReadException e) {
            e.printStackTrace();
            _translatedText.setValue("Ошибка чтения файла: " + e.getMessage());
        }
    }

    public void processPickedOriginalFileUri(Context context, Uri uri) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(uri);
            if (inputStream != null) {
                File tempFile = createTempFileFromInputStream(inputStream, context);

                _originalText.setValue(translator.getTextFromFile(tempFile.getAbsolutePath()));
            }
        } catch (IOException | FileReadException e) {
            e.printStackTrace();
            _originalText.setValue("Ошибка чтения файла: " + e.getMessage());
        }
    }

    public void translateText(String inputText) {
        String result = translator.translate(inputText);
        _translatedText.setValue(result);
    }

    private File createTempFileFromInputStream(InputStream inputStream, Context context) throws IOException {
        File tempFile = File.createTempFile("tempDictionary", ".txt", context.getCacheDir());
        try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        }
        return tempFile;
    }
}
