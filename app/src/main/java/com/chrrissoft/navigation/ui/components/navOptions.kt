package com.chrrissoft.navigation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons.Rounded
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.material3.IconButtonDefaults.filledIconToggleButtonColors
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.unit.dp
import com.chrrissoft.navigation.NavOptions
import com.chrrissoft.navigation.PopUpTo
import com.chrrissoft.navigation.forEachIndexedReverse
import com.chrrissoft.navigation.route

//private const val TAG = "NavOptionsBuilder_UI"

private val iconColors
    @Composable get() = filledIconToggleButtonColors(
        containerColor = colorScheme.onPrimary,
        contentColor = colorScheme.primary,
        disabledContainerColor = colorScheme.onPrimary.copy(.5f),
        disabledContentColor = colorScheme.primary.copy(.5f),
        checkedContainerColor = colorScheme.primary,
        checkedContentColor = colorScheme.onPrimary,
    )

@Composable
fun NavOptionsBuilder(
    routesBackStack: List<String>,
    options: NavOptions,
    onNavOptionsChange: (NavOptions) -> Unit,
    modifier: Modifier = Modifier,
) {
    val popUpTo = remember {
        PopUpTo(routesBackStack.firstOrNull() ?: "")
    }

    val (showStack, onShowStackChange) = rememberSaveable {
        mutableStateOf(false)
    }

    if (showStack) {
        AlertDialog(
            onDismissRequest = {
                onShowStackChange(false)
            },
            confirmButton = {
                Button(onClick = { onShowStackChange(false) }) {
                    Text(text = "Okay", color = colorScheme.onPrimary, fontWeight = Medium)
                }
            },
            containerColor = colorScheme.primaryContainer,
            title = {
                Column(
                    horizontalAlignment = CenterHorizontally,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    OnPrimaryContainerMediumTitle(text = "Choose destination")
                    Spacer(Modifier.height(5.dp))
                    OnPrimaryContainerDivider()
                }
            },
            text = {
                Column(Modifier.verticalScroll(rememberScrollState())) {
                    routesBackStack.forEachIndexedReverse { index, entry ->
                        val selected = options.popUpTo!!.route==entry
                        val color = if (selected) colorScheme.primary
                        else colorScheme.secondary
                        val weight = if (selected) Bold else Normal
                        Row(
                            verticalAlignment = CenterVertically,
                            modifier = Modifier.clickable {
                                onNavOptionsChange(options.copy(popUpTo = popUpTo.copy(route = entry)))
                            }
                        ) {
                            RadioButton(selected = selected, onClick = {
                                onNavOptionsChange(options.copy(popUpTo = popUpTo.copy(route = entry)))
                            })
                            Spacer(Modifier.width(10.dp))
                            Text(
                                color = color,
                                fontWeight = weight,
                                text = entry.route.replace("screen", ""),
                            )
                        }
                    }
                }
            }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .clip(shapes.large)
            .fillMaxSize()
            .background(colorScheme.primaryContainer)
            .padding(8.dp),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        OnPrimaryContainerMediumTitle("Nav options")
        OnPrimaryContainerDivider()

        Row(
            horizontalArrangement = SpaceBetween,
            verticalAlignment = CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            FilledIconToggleButton(
                checked = options.launchSingleTop,
                colors = iconColors,
                onCheckedChange = {
                    onNavOptionsChange(options.copy(launchSingleTop = it))
                }
            ) { Icon(Rounded.SmartScreen, (null)) }
            FilledIconToggleButton(
                checked = options.restoreState,
                colors = iconColors,
                onCheckedChange = {
                    onNavOptionsChange(options.copy(restoreState = it))
                }
            ) { Icon(Rounded.SettingsBackupRestore, (null)) }
            FilledIconToggleButton(
                checked = options.popUpTo!=null,
                colors = iconColors,
                onCheckedChange = {
                    onNavOptionsChange(options.copy(popUpTo = if (it) popUpTo else null))
                }
            ) { Icon(Rounded.KeyboardArrowDown, (null)) }


            FilledIconToggleButton(
                checked = true,
                colors = iconColors,
                enabled = options.popUpTo!=null,
                onCheckedChange = { onShowStackChange(true) }
            ) { Icon(Rounded.Reorder, (null)) }
            FilledIconToggleButton(
                checked = if (options.popUpTo?.route!=routesBackStack.lastOrNull())
                    options.popUpTo?.inclusive ?: false else false,
                colors = iconColors,
                enabled = options.popUpTo!=null,
                onCheckedChange = {
                    if (options.popUpTo?.route!=routesBackStack.lastOrNull()) {
                        onNavOptionsChange(options.copy(popUpTo = options.popUpTo?.copy(inclusive = it)))
                    }
                }
            ) { Icon(Rounded.Grading, (null)) }
            FilledIconToggleButton(
                checked = options.popUpTo?.saveState ?: false,
                colors = iconColors,
                enabled = options.popUpTo!=null,
                onCheckedChange = {
                    onNavOptionsChange(options.copy(popUpTo = options.popUpTo?.copy(saveState = it)))
                }
            ) { Icon(Rounded.Save, (null)) }
        }

    }
}
