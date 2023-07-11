package com.chrrissoft.navigation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chrrissoft.navigation.AbstractSerializableCustomArg

@Composable
fun ArgsReceiver(
    title: String,
    required0: Int, required1: Int,
    optional0: Int, optional1: Int,
    custom0: AbstractSerializableCustomArg,
    custom1: AbstractSerializableCustomArg,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(shapes.large)
            .background(colorScheme.primaryContainer)
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        OnPrimaryContainerMediumTitle(title)
        OnPrimaryContainerDivider()
        Spacer(modifier = Modifier.height(10.dp))

        Row {
            OnPrimaryContainerText(text = "required0: ${required0.toString().first()}", fontSize = 17.sp)
            Spacer(Modifier.width(3.dp))
            OnPrimaryContainerText(text = "required1: ${required1.toString().first()}", fontSize = 17.sp)
        }
        Row {
            OnPrimaryContainerText(text = "optional0: ${optional0.toString().first()}", fontSize = 17.sp)
            Spacer(Modifier.width(3.dp))
            OnPrimaryContainerText(text = "optional1: ${optional1.toString().first()}", fontSize = 17.sp)
        }

        Spacer(Modifier.height(10.dp))
        OnPrimaryContainerText(("custom0: ${custom0.data}"), fontSize = 17.sp)
        Spacer(Modifier.height(3.dp))
        OnPrimaryContainerText(("custom1: ${custom1.data}"), fontSize = 17.sp)
    }
}

@Composable
fun ArgsSender(
    sendOptional: Boolean,
    onSendOptionalsChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable { onSendOptionalsChange(!sendOptional) }
            .clip(shapes.extraLarge)
            .background(colorScheme.primaryContainer)
    ) {
        TextToggleButton(
            text = "Send optional arguments",
            checked = sendOptional,
            modifier.fillMaxSize(),
            {
                onSendOptionalsChange(!sendOptional)
            },
        )
    }
}
