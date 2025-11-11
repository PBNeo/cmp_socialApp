package com.neosoft.designsystem.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.neosoft.designsystem.utils.AppColors
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp

@Composable

fun AppSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    borderColor: Color = AppColors.primary,
    contentColor: Color = AppColors.primary,
    backgroundColor: Color = Color.White
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, borderColor),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = contentColor,
            containerColor = backgroundColor
        )
    ) {
        Text(text)
    }
}
