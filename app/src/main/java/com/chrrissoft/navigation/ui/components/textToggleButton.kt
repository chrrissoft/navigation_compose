package com.chrrissoft.navigation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight.Companion.Black
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextToggleButton(
    text: String,
    checked: Boolean,
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit,
    enabled: Boolean = true,
) {
    val textWeight = if (checked) Black else Normal
    val textColor = if (checked) colorScheme.primaryContainer else colorScheme.primary
    val borderColor = if (checked) colorScheme.onPrimary else colorScheme.primary
    val back = if (checked) colorScheme.primary else colorScheme.onPrimary

    Row(
        horizontalArrangement = Center,
        verticalAlignment = CenterVertically,
        modifier = modifier
            .background(back)
            .padding(5.dp)
            .clip(shapes.extraLarge)
            .border(2.dp, SolidColor(borderColor), shapes.extraLarge)
            .clickable { onCheckedChange(!checked) },
    ) {
        Text(
            text = text, fontWeight = textWeight,
            color = textColor,
            fontSize = 18.sp,
        )
    }
}