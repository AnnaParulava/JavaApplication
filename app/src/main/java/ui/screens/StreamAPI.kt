package ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.javaapplication.StreamAPI.ui.StreamApiViewModel
import ui.shared.MoveButton

@Composable
fun StreamAPI(viewModel: StreamApiViewModel = viewModel()) {
    var averageInputText by remember { mutableStateOf(TextFieldValue("")) }
    var averageOutputText by remember { mutableStateOf("") }

    var transformInputText by remember { mutableStateOf(TextFieldValue("")) }
    var transformOutputText by remember { mutableStateOf("") }

    var uniqueSquaresInputText by remember { mutableStateOf(TextFieldValue("")) }
    var uniqueSquaresOutputText by remember { mutableStateOf("") }

    var lastElementInputText by remember { mutableStateOf(TextFieldValue("")) }
    var lastElementOutputText by remember { mutableStateOf("") }

    var evenNumbersInputText by remember { mutableStateOf(TextFieldValue("")) }
    var evenNumbersOutputText by remember { mutableStateOf("") }

    var stringsToMapInputText by remember { mutableStateOf(TextFieldValue("")) }
    var stringsToMapOutputText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Используем StreamAPI",
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.padding(8.dp)
        )

        MethodCard(
            description = "Найти среднее значение чисел",
            onClick = {
                val numbers =
                    averageInputText.text.split(",").mapNotNull { it.trim().toIntOrNull() }
                averageOutputText = viewModel.handleAverage(numbers)
            },
            inputPlaceholder = "Введите числа через запятую",
            outputText = averageOutputText,
            inputText = averageInputText,
            onInputChange = { averageInputText = it }
        )

        MethodCard(
            description = "Преобразовать строки в верхний регистр",
            onClick = {
                val strings = transformInputText.text.split(",").map { it.trim() }
                transformOutputText = viewModel.handleTransformStrings(strings)
            },
            inputPlaceholder = "Введите строки через запятую",
            outputText = transformOutputText,
            inputText = transformInputText,
            onInputChange = { transformInputText = it }
        )

        MethodCard(
            description = "Найти уникальные квадраты чисел",
            onClick = {
                val numbers =
                    uniqueSquaresInputText.text.split(",").mapNotNull { it.trim().toIntOrNull() }
                uniqueSquaresOutputText = viewModel.handleUniqueSquares(numbers)
            },
            inputPlaceholder = "Введите числа через запятую",
            outputText = uniqueSquaresOutputText,
            inputText = uniqueSquaresInputText,
            onInputChange = { uniqueSquaresInputText = it }
        )

        MethodCard(
            description = "Получить последний элемент",
            onClick = {
                val elements = lastElementInputText.text.split(",").map { it.trim() }
                lastElementOutputText = viewModel.handleGetLastElement(elements)
            },
            inputPlaceholder = "Введите элементы через запятую",
            outputText = lastElementOutputText,
            inputText = lastElementInputText,
            onInputChange = { lastElementInputText = it }
        )

        MethodCard(
            description = "Сумма чётных чисел",
            onClick = {
                val numbers =
                    evenNumbersInputText.text.split(",").mapNotNull { it.trim().toIntOrNull() }
                        .toIntArray()
                evenNumbersOutputText = viewModel.handleSumOfEvenNumbers(numbers)
            },
            inputPlaceholder = "Введите числа через запятую",
            outputText = evenNumbersOutputText,
            inputText = evenNumbersInputText,
            onInputChange = { evenNumbersInputText = it }
        )

        MethodCard(
            description = "Преобразовать строки в Map",
            onClick = {
                val strings = stringsToMapInputText.text.split(",").map { it.trim() }
                stringsToMapOutputText = viewModel.handleStringsToMap(strings)
            },
            inputPlaceholder = "Введите строки через запятую",
            outputText = stringsToMapOutputText,
            inputText = stringsToMapInputText,
            onInputChange = { stringsToMapInputText = it }
        )
    }
}


@Composable
fun MethodCard(
    description: String,
    onClick: () -> Unit,
    inputPlaceholder: String,
    outputText: String,
    inputText: TextFieldValue,
    onInputChange: (TextFieldValue) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = description,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                    .padding(8.dp)
            ) {
                if (inputText.text.isEmpty()) {
                    Text(
                        text = inputPlaceholder,
                        color = Color.Gray,
                        fontSize = 16.sp
                    )
                }
                BasicTextField(
                    value = inputText,
                    onValueChange = onInputChange,
                    singleLine = true,
                    textStyle = TextStyle(fontSize = 16.sp),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = outputText, fontSize = 16.sp)

        Spacer(modifier = Modifier.height(8.dp))

        MoveButton(onClick = onClick, text = "Применить")
    }
}

