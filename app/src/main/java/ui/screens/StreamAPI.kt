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
import androidx.compose.runtime.mutableStateMapOf
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
import com.example.javaapplication.streamAPI.ui.StreamApiViewModel
import ui.shared.MoveButton

@Composable
fun StreamAPI(viewModel: StreamApiViewModel = viewModel()) {
    val cardStates = remember { mutableStateMapOf<String, Boolean>() }

    fun initializeCardState(description: String) {
        if (!cardStates.contains(description)) {
            cardStates[description] = false
        }
    }

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

        val methods = listOf(
            "Найти среднее значение чисел" to { averageInputText.text.split(",").mapNotNull { it.trim().toIntOrNull() } },
            "Преобразовать строки в верхний регистр" to { transformInputText.text.split(",").map { it.trim() } },
            "Найти уникальные квадраты чисел" to { uniqueSquaresInputText.text.split(",").mapNotNull { it.trim().toIntOrNull() } },
            "Получить последний элемент" to { lastElementInputText.text.split(",").map { it.trim() } },
            "Сумма чётных чисел" to { evenNumbersInputText.text.split(",").mapNotNull { it.trim().toIntOrNull() }.toIntArray() },
            "Преобразовать строки в Map" to { stringsToMapInputText.text.split(",").map { it.trim() } }
        )

        methods.forEach { (description, inputParser) ->
            initializeCardState(description)
            MethodCard(
                description = description,
                isActive = cardStates[description] ?: false,
                onClick = {
                    val inputData = inputParser()
                    when (description) {
                        "Найти среднее значение чисел" -> averageOutputText = viewModel.handleAverage(inputData as List<Int>)
                        "Преобразовать строки в верхний регистр" -> transformOutputText = viewModel.handleTransformStrings(inputData as List<String>)
                        "Найти уникальные квадраты чисел" -> uniqueSquaresOutputText = viewModel.handleUniqueSquares(inputData as List<Int>)
                        "Получить последний элемент" -> lastElementOutputText = viewModel.handleGetLastElement(inputData as List<String>)
                        "Сумма чётных чисел" -> evenNumbersOutputText = viewModel.handleSumOfEvenNumbers(inputData as IntArray)
                        "Преобразовать строки в Map" -> stringsToMapOutputText = viewModel.handleStringsToMap(inputData as List<String>)
                    }
                    cardStates[description] = true
                },
                inputPlaceholder = "Введите данные через запятую",
                outputText = when (description) {
                    "Найти среднее значение чисел" -> averageOutputText
                    "Преобразовать строки в верхний регистр" -> transformOutputText
                    "Найти уникальные квадраты чисел" -> uniqueSquaresOutputText
                    "Получить последний элемент" -> lastElementOutputText
                    "Сумма чётных чисел" -> evenNumbersOutputText
                    "Преобразовать строки в Map" -> stringsToMapOutputText
                    else -> ""
                },
                inputText = when (description) {
                    "Найти среднее значение чисел" -> averageInputText
                    "Преобразовать строки в верхний регистр" -> transformInputText
                    "Найти уникальные квадраты чисел" -> uniqueSquaresInputText
                    "Получить последний элемент" -> lastElementInputText
                    "Сумма чётных чисел" -> evenNumbersInputText
                    "Преобразовать строки в Map" -> stringsToMapInputText
                    else -> TextFieldValue("")
                },
                onInputChange = { newValue ->
                    when (description) {
                        "Найти среднее значение чисел" -> averageInputText = newValue
                        "Преобразовать строки в верхний регистр" -> transformInputText = newValue
                        "Найти уникальные квадраты чисел" -> uniqueSquaresInputText = newValue
                        "Получить последний элемент" -> lastElementInputText = newValue
                        "Сумма чётных чисел" -> evenNumbersInputText = newValue
                        "Преобразовать строки в Map" -> stringsToMapInputText = newValue
                    }
                }
            )
        }
    }
}

@Composable
fun MethodCard(
    description: String,
    onClick: () -> Unit,
    isActive: Boolean = false,
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

        MoveButton(onClick = onClick, isActive = isActive, text = "Применить")
    }
}
