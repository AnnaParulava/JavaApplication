package ui.shared

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ui.theme.Pink80

@Composable
fun MoveButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isActive: Boolean = true,
    text: String = String()
) {
    OutlinedButton(
        onClick = { onClick() },
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (isActive) Pink80 else Color.Transparent,
            contentColor = if (isActive) Color.Black else Color.Gray,
        ),
        border = if (isActive) BorderStroke(
            1.dp,
            Color.Transparent
        ) else BorderStroke(1.dp, Color.Gray),
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        Text(text)
    }
}
