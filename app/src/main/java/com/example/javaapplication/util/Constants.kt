package com.example.javaapplication.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.javaapplication.R
import com.example.javaapplication.models.BottomNavItem

object Constants {
    @Composable
    fun getBottomNavItems(): List<BottomNavItem> {
        val context = LocalContext.current
        return listOf(
            BottomNavItem(
                ROUTES.HERO.toLabel(),
                painterResource(id = R.drawable.baseline_filter_1_24),
                ROUTES.HERO
            ),
            BottomNavItem(
                ROUTES.ANNOTATION.toLabel(),
                painterResource(id = R.drawable.baseline_filter_2_24),
                ROUTES.ANNOTATION
            ),
            BottomNavItem(
                ROUTES.TRANSLATOR.toLabel(),
                painterResource(id = R.drawable.baseline_filter_3_24),
                ROUTES.TRANSLATOR
            ),
            BottomNavItem(
                ROUTES.STREAM_API.toLabel().replace('_', ' '),
                painterResource(id = R.drawable.baseline_filter_4_24),
                ROUTES.STREAM_API
            )
        )
    }

    private fun String.toLabel(): String = this.lowercase().replaceFirstChar { it.uppercase() }
}

object ROUTES {
    const val HERO = "hero"
    const val ANNOTATION = "annotation"
    const val STREAM_API = "stream_api"
    const val TRANSLATOR = "translator"
}

