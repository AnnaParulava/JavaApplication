package ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ui.screens.HeroScreen
import ui.screens.TranslatorScreen
import ui.screens.AnnotationScreen
import androidx.navigation.NavHostController
import com.example.javaapplication.R
import com.example.javaapplication.util.ROUTES.ANNOTATION
import com.example.javaapplication.util.ROUTES.HERO
import com.example.javaapplication.util.ROUTES.STREAM_API
import com.example.javaapplication.util.ROUTES.TRANSLATOR
import ui.screens.StreamAPI


@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues
) {

    NavHost(
        navController = navController,
        startDestination = HERO,
        modifier = Modifier.padding(paddingValues = padding)
    ) {
        composable(HERO) {
            HeroScreen()
        }

        composable(ANNOTATION) {
            AnnotationScreen()
        }

        composable(TRANSLATOR) {
            TranslatorScreen()
        }

        composable(STREAM_API) {
            StreamAPI()
        }
    }
}

