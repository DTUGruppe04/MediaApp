package com.example.mediaapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.mediaapp.R

val roboto = FontFamily(
        Font(R.font.roboto_regular, weight = FontWeight.Normal),
        Font(R.font.roboto_bold, weight = FontWeight.Bold),
        Font(R.font.roboto_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
        Font(R.font.roboto_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
        Font(R.font.roboto_black, weight = FontWeight.Black),
        Font(R.font.roboto_black_italic, weight = FontWeight.Black, style = FontStyle.Italic),
        Font(R.font.roboto_light, weight = FontWeight.Light),
        Font(R.font.roboto_light_italic, weight = FontWeight.Light, style = FontStyle.Italic),
        Font(R.font.roboto_medium, weight = FontWeight.Medium),
        Font(R.font.roboto_medium_italic, weight = FontWeight.Medium, style = FontStyle.Italic),
        Font(R.font.roboto_thin, weight = FontWeight.Thin),
        Font(R.font.roboto_thin_italic, weight = FontWeight.Thin, style = FontStyle.Italic),
)

// Set of Material typography styles to start with
val Typography = Typography(
        displayLarge = TextStyle(
                fontSize = 57.sp,
                lineHeight = 64.sp,
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF000000)),

        displayMedium = TextStyle(
                fontSize = 45.sp,
                lineHeight = 52.sp,
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF000000)),

        displaySmall = TextStyle(
                fontSize = 36.sp,
                lineHeight = 44.sp,
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF000000)),

        headlineLarge = TextStyle(
                fontSize = 32.sp,
                lineHeight = 40.sp,
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF000000)),

        headlineMedium = TextStyle(
                fontSize = 28.sp,
                lineHeight = 36.sp,
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF000000)),

        headlineSmall = TextStyle(
                fontSize = 24.sp,
                lineHeight = 32.sp,
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF000000)),

        titleLarge = TextStyle(
                fontSize = 22.sp,
                lineHeight = 28.sp,
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF000000)),

        titleMedium = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF000000)),

        titleSmall = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF000000)),

        labelLarge = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF000000)),

        labelMedium = TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF000000)),

        labelSmall = TextStyle(
                fontSize = 11.sp,
                lineHeight = 16.sp,
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF000000)),

        bodyLarge = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF000000)),

        bodyMedium = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF000000)),

        bodySmall = TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF000000)),
)