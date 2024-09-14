package ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.javaapplication.annotation.ui.AnnotationViewModel
import ui.shared.MoveButton

@Composable
fun AnnotationScreen(viewModel: AnnotationViewModel = viewModel()) {

    val outputText = viewModel.annotatedMethods
    var showAnnotatedString by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Вызвать из другого класса все аннотированные защищенные и приватные методы " +
                        "столько раз, сколько указано в параметре аннотации.",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (showAnnotatedString) {
                Text(
                    text = outputText,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        MoveButton(
            onClick = { showAnnotatedString = true },
            isActive = showAnnotatedString,
            text = "Выполнить методы"
        )
    }
}



