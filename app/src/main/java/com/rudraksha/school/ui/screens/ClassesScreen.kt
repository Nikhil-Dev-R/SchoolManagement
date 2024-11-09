package com.rudraksha.school.ui.screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntSizeAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.rudraksha.school.models.standardList
import com.rudraksha.school.ui.components.SchoolBox
import com.rudraksha.school.ui.components.SchoolCard
import com.rudraksha.school.ui.components.SchoolFab
import com.rudraksha.school.ui.components.SchoolIcon
import com.rudraksha.school.ui.components.SchoolText
import com.rudraksha.school.ui.components.SchoolTextField
import com.rudraksha.school.ui.theme.circleShapeMedium
import com.rudraksha.school.ui.utils.SchoolTopBar

@Composable
fun ClassesScreen(
    modifier: Modifier = Modifier,
    onNavIconClick: () -> Unit = {},
    onCardClick: (String) -> Unit = {},
    classList: MutableList<String> = standardList,
) {
    var showingSaveIcon by remember { mutableStateOf(false) }

    var newStandard by remember { mutableStateOf("") }

    var showAnimation by remember { mutableStateOf(false) }
    // Trigger the screen animation when it first appears
    LaunchedEffect(Unit) {
        showAnimation = true
    }

    // Screen transition: Slide in from the right when entering (screen comes from right to left)
    AnimatedVisibility(
        visible = showAnimation,
        enter = slideInHorizontally(
            animationSpec = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            initialOffsetX = { it } // Screen enters from the right (off-screen to right)
        ) + fadeIn(),
        exit = slideOutHorizontally(
            animationSpec = tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing
            ),
            targetOffsetX = { -it } // Screen exits to the left (off-screen to left)
        ) + fadeOut()
    ) {
        Scaffold(
            topBar = {
                SchoolTopBar(
                    title = "Classes",
                    modifier = Modifier,
                    onNavIconClick = onNavIconClick
                )
            },
            floatingActionButton = {
                SchoolFab(
                    onClick = {
                        if (showingSaveIcon && newStandard.isNotEmpty()) {
                            standardList.add(newStandard) // Add new standard
                            Log.d("ShowSaveIcon", "$newStandard added")
                        }

                        showingSaveIcon = !showingSaveIcon
                        Log.d("ShowSaveIcon", "$showingSaveIcon")
                    }
                ) {
                    SchoolIcon(
                        imageVector = if (showingSaveIcon) Icons.Outlined.Save else Icons.Outlined.Add,
                        modifier = Modifier.size(32.dp),
                    )
                }
            }
        ) { innerPadding ->
            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp),
                modifier = modifier
                    .padding(innerPadding)
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                items(classList.size) { index ->
                    // Animate each class card with a horizontal slide-in
                    AnimatedVisibility(
                        visible = true, // Always true so that animation applies even on scroll
                        enter = slideInHorizontally(
                            animationSpec = tween(
                                durationMillis = 300,
                                delayMillis = index * 50, // Stagger animation for a better effect
                                easing = FastOutSlowInEasing
                            ),
                            initialOffsetX = { it } // Each item slides in from the right
                        ) + fadeIn(),
                        exit = slideOutHorizontally(
                            animationSpec = tween(
                                durationMillis = 500,
                                easing = FastOutSlowInEasing
                            ),
                            targetOffsetX = { -it } // Screen exits to the left (off-screen to left)
                        ) + fadeOut()
                    ) {
                        ClassCard(
                            standard = classList[index],
                            onCardClick = onCardClick,
                        )
                    }
                }

                // Add the editable card at the end of the list when Add FAB is pressed
                item {
                    if (showingSaveIcon) {
                        SchoolCard(
                            modifier = Modifier.size(150.dp),
                            onCardClick = {},
                            content = {
                                SchoolBox(
                                    modifier = modifier
                                        .background(color = MaterialTheme.colorScheme.secondaryContainer)
                                        .fillMaxSize()
                                ) {
                                    SchoolTextField(
                                        value = newStandard,
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .size(100.dp),
                                        onValueChange = {
                                            newStandard = it
                                        },
                                        placeholder = {
                                            SchoolText(
                                                text = "New",
                                                style = MaterialTheme.typography.headlineMedium
                                            )
                                        },
                                        shape = circleShapeMedium,
                                        textStyle = MaterialTheme.typography.headlineMedium,
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ClassCard(
    standard: String,
    onCardClick: (String) -> Unit,
) {
//    val size = WindowSizeClass.current

    var isClicked by remember { mutableStateOf(false) }
    val animatedSize = animateIntSizeAsState(
        targetValue = if (isClicked) IntSize(500, 1000) else IntSize(150, 150),
        animationSpec = tween(
            durationMillis = 0,
            easing = FastOutSlowInEasing
        ), label = "SizeAnimation"
    ).value

    val animatedScale = animateFloatAsState(
        targetValue = if (isClicked) 2f else 1f,
        animationSpec = tween(
            durationMillis = 0,
            easing = FastOutSlowInEasing
        ), label = "ScaleAnimation"
    ).value
    val animatedElevation = animateDpAsState(
        targetValue = if (isClicked) 12.dp else 0.dp,
        animationSpec = tween(
            durationMillis = 500,
            easing = FastOutSlowInEasing
        ), label = "ElevationAnimation"
    ).value
    val animatedShape = animateFloatAsState(
        targetValue = if (isClicked) 16f else 0f,
        animationSpec = tween(
            durationMillis = 500,
            easing = FastOutSlowInEasing
        ), label = "ShapeAnimation"
    ).value
    val animatedColor = animateColorAsState(
        targetValue = if (isClicked) MaterialTheme.colorScheme.primaryContainer else DefaultShadowColor,
        animationSpec = tween(
            durationMillis = 0,
            easing = FastOutSlowInEasing
        ), label = "ColorAnimation"
    ).value

    // UI for displaying a single class card
    SchoolCard(
        modifier = Modifier
            .size(150.dp)
            .graphicsLayer(scaleX = animatedScale, scaleY = animatedScale)
            .combinedClickable(
                onClick = {
                    isClicked = true
                },
                onLongClick = {

                }
            )
            .shadow(
                elevation = animatedElevation,
                shape = RoundedCornerShape(animatedShape),
                spotColor = animatedColor
            )
            .zIndex(if (isClicked) 1f else 0f)
        ,
        onCardClick = {
            isClicked = true
            onCardClick(standard)
        },
        content = {
            SchoolBox(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.primary)
                    .fillMaxSize()
            ) {
                SchoolText(
                    text = standard,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    )
}

@Preview
@Composable
fun ClassCardPreview() {
    ClassCard(standard = "10th", onCardClick = {})
}

@Preview
@Composable
fun ClassesPreview() {
    ClassesScreen()
}
