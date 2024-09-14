package ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.javaapplication.Hero.ui.HeroViewModel
import com.example.javaapplication.Hero.ui.MoveState
import ui.shared.MoveButton

@Composable
fun HeroScreen(viewModel: HeroViewModel = viewModel()) {
    var state by remember { mutableStateOf<MoveState>(MoveState.none()) }

    Scaffold(
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = viewModel.chooseMoveStrategy(state),
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                    )

                    if (state != MoveState.none()) LoadingDotsAnimation()

                    Spacer(modifier = Modifier.weight(1f))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        MoveButton(
                            onClick = { state = MoveState.walking() },
                            isActive = state == MoveState.walking(),
                            text = "Идти пешком"
                        )

                        MoveButton(
                            onClick = { state = MoveState.horse() },
                            isActive = state == MoveState.horse(),
                            text = "Ехать на лошади"
                        )

                        MoveButton(
                            onClick = { state = MoveState.flying() },
                            isActive = state == MoveState.flying(),
                            text = "Лететь"
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun LoadingDotsAnimation(dotCount: Int = 5) {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val dotAlphaValues = List(dotCount) { index ->
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(300, easing = LinearEasing, delayMillis = index * 300),
                repeatMode = RepeatMode.Restart
            ), label = ""
        )
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp)
    ) {
        repeat(dotCount) { index ->
            Text(
                text = ".",
                fontSize = 50.sp,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .alpha(dotAlphaValues[index].value)
            )
        }
    }
}

@Composable
fun Move(move: String) {
    Scaffold(
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = move,
                        fontSize = 24.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    LoadingDotsAnimation()
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewLoadingScreen() {
    Move("Test")
}

