package com.example.angatkinmirea.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.googlefonts.Font
import com.example.angatkinmirea.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

// Default Material 3 typography values
val baseline = Typography()

val AppTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.sciencegothic_bold)),
        fontWeight = FontWeight.Bold, fontSize = 24.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.sciencegothic_medium)),
        fontWeight = FontWeight.Medium, fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.sciencegothic_regular)),
        fontWeight = FontWeight.Normal, fontSize = 8.sp
    )
)

