package ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.javaapplication.translator.ui.TranslationViewModel
import ui.shared.MoveButton

@Composable
fun TranslatorScreen(viewModel: TranslationViewModel = viewModel()) {
    val context = LocalContext.current

    val dictionaryContent by viewModel.dictionaryText.observeAsState("")
    val translatedText by viewModel.translatedText.observeAsState("")
    val originalText by viewModel.originalText.observeAsState("")
    var inputText by remember { mutableStateOf(TextFieldValue(originalText)) }
    var buttonTranslateEnabled by remember { mutableStateOf(false) }

    val pickFileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri: Uri? ->
            uri?.let {
                viewModel.processPickedFileUri(context, it)
            }
        }
    )
    val pickTranslationFileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri: Uri? ->
            uri?.let {
                viewModel.processPickedOriginalFileUri(context, it)
                inputText = TextFieldValue(originalText)
            }
        }
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item {
            Text(
                text = "Перевод текста",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(8.dp)
            )
        }

        item {
            MoveButton(
                modifier = Modifier.padding(top = 16.dp),
                onClick = { pickFileLauncher.launch(arrayOf("text/plain")) },
                isActive = dictionaryContent.isNotEmpty(),
                text = "Выбрать словарь"
            )
        }

        item {
            OutputBlock(
                title = "Содержимое словаря:",
                text = dictionaryContent,
            )
        }

        item {
            MoveButton(
                modifier = Modifier.padding(top = 16.dp),
                onClick = { pickTranslationFileLauncher.launch(arrayOf("text/plain")) },
                isActive = originalText.isNotEmpty(),
                text = "Выбрать текст"
            )
        }

        item {
            OutlinedTextField(
                shape = RoundedCornerShape(8.dp),
                value = inputText,
                onValueChange = { inputText = it },
                label = { Text("Введите текст для перевода") },
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
            )
        }

        item {
            MoveButton(
                modifier = Modifier.padding(top = 16.dp),
                onClick = {
                    viewModel.translateText(inputText.text)
                    buttonTranslateEnabled = true
                },
                isActive = buttonTranslateEnabled,
                text = "Перевести",
            )
        }

        if (translatedText.isNotEmpty()) {
            item {
                OutputBlock(
                    title = "Результат перевода:",
                    text = translatedText,
                )
            }
        }
    }
}

@Composable
fun OutputBlock(title: String, text: String) {
    Column {
        Text(
            text = title,
            modifier = Modifier.padding(top = 16.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = text,
            modifier = Modifier
                .padding(top = 8.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .padding(8.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewTranslationScreen() {
    TranslatorScreen()
}
