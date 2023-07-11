package com.chrrissoft.navigation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons.Rounded
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Reorder
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import com.chrrissoft.navigation.app.graphs.one.GraphOne
import com.chrrissoft.navigation.app.graphs.two.GraphTwo
import com.chrrissoft.navigation.forEachIndexedReverse
import com.chrrissoft.navigation.route

@OptIn(ExperimentalMaterial3Api::class)
private val appBarColors
    @Composable get() = centerAlignedTopAppBarColors(
        containerColor = colorScheme.primaryContainer,
        titleContentColor = colorScheme.primary,
    )

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenUI(
    title: String,
    backStack: List<NavBackStackEntry>,
    onBack: () -> Unit,
    receivedArgs: @Composable () -> Unit,
    argsSender: @Composable () -> Unit,
    navOptionsBuilder: @Composable () -> Unit,
    destinationChooser: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    receivedGraphArgs: (@Composable () -> Unit)? = null,
) {

    val (showReceivedGraphArgs, changeShowReceivedGraphArgs) = rememberSaveable {
        mutableStateOf(false)
    }
    val (showBackStack, changeShowBackStack) = rememberSaveable {
        mutableStateOf(false)
    }

    if (showReceivedGraphArgs && receivedGraphArgs!=null) {
        AlertDialog(
            onDismissRequest = { changeShowReceivedGraphArgs(false) },
            confirmButton = {
                Button(onClick = { changeShowReceivedGraphArgs(false) }) {
                    Text(text = "Okay", fontWeight = Medium, color = colorScheme.onPrimary)
                }
            },
            containerColor = colorScheme.primaryContainer,
            text = {
                Column(
                    verticalArrangement = Center,
                    horizontalAlignment = CenterHorizontally,
                ) {
                    receivedGraphArgs()
                }
            }
        )
    }

    if (showBackStack) {
        AlertDialog(
            onDismissRequest = { changeShowBackStack(false) },
            confirmButton = {
                Button(onClick = { changeShowBackStack(false) }) {
                    Text(text = "Okay", fontWeight = Medium, color = colorScheme.onPrimary)
                }
            },
            containerColor = colorScheme.primaryContainer,
            text = {
                Column(
                    verticalArrangement = Center,
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    OnPrimaryContainerMediumTitle(text = "Nav Back Stack")
                    OnPrimaryContainerDivider()

                    backStack.mapNotNull {
                        it.destination.route
                    }.reversed().forEachIndexedReverse { index, entry ->
                        Text(
                            text = entry
                                .route
                                .replace("screen", "")
                                .plus(" - $index"),
                            color = colorScheme.primary,
                            fontWeight = Medium
                        )
                        if (entry==GraphOne.route || entry==GraphTwo.route) {
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                }
            }
        )
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(title, fontWeight = Medium) },
                colors = appBarColors,
                navigationIcon = {
                    IconButton(onClick = { onBack() }, modifier = Modifier.padding(start = 10.dp)) {
                        Icon(
                            imageVector = Rounded.ArrowBack,
                            contentDescription = (null),
                            tint = colorScheme.primary,
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { changeShowBackStack(true) }) {
                        Icon(Rounded.Reorder, (null), tint = colorScheme.primary)
                    }
                    if (receivedGraphArgs!=null) {
                        IconButton(onClick = { changeShowReceivedGraphArgs(true) }) {
                            Icon(Rounded.Reorder, (null), tint = colorScheme.primary)
                        }
                    }
                }
            )
        }
    ) {
        Column(
            horizontalAlignment = CenterHorizontally,
            modifier = Modifier
                .padding(it)
                .background(colorScheme.onPrimary),
        ) {
            @Composable
            fun boxModifier(weight: Float) = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .weight(weight)
            Box(boxModifier(3f)) {
                receivedArgs()
            }
            Box(boxModifier(0.7f)) {
                argsSender()
            }
            Box(boxModifier(1.5f)) {
                navOptionsBuilder()
            }
            Box(boxModifier(3f)) {
                destinationChooser()
            }
        }
    }
}
