package com.rudraksha.school.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    var isFabPressed by remember { mutableStateOf(false) }
    var newGradeLevel by remember { mutableStateOf("") }
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
                        isFabPressed = !isFabPressed
                        if (isFabPressed && newGradeLevel.isNotEmpty()) {
                            standardList.add(newGradeLevel) // Add new grade level
                        }
                    }
                ) {
                    SchoolIcon(
                        imageVector = if (isFabPressed) Icons.Outlined.Save else Icons.Outlined.Add,
                        modifier = Modifier.size(32.dp),
                    )
                }
            }
        ) { innerPadding ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = modifier
                    .padding(innerPadding)
                    .padding(start = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
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
                            gradeLevel = classList[index],
                            onCardClick = onCardClick,
                        )
                    }
                }

                // Add the editable card at the end of the list if FAB is pressed
                items(1) {
                    if (isFabPressed) {
                        SchoolCard(
                            modifier = Modifier.size(100.dp),
                            onCardClick = {},
                            content = {
                                SchoolTextField(
                                    value = newGradeLevel,
                                    modifier = Modifier.padding(16.dp),
                                    onValueChange = {
                                        newGradeLevel = it
                                    },
                                    placeholder = {
                                        SchoolText(
                                            text = "New",
                                            style = MaterialTheme.typography.headlineMedium
                                        )
                                    },
                                    singleLine = true,
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ClassCard(
    gradeLevel: String,
    onCardClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var scale by remember { mutableFloatStateOf(1f) }
    var isClicked by remember { mutableStateOf(false) }

    // Animation for scaling the card to full screen on click
    LaunchedEffect(isClicked) {
        scale = if (isClicked) {
            5f // Adjust this to make it fill the screen
        } else { // Return to original size
            1f
        }
    }
    // UI for displaying a single class card
    SchoolCard(
        modifier = modifier
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .clickable {
                isClicked = true
            }
//            .shadow(
//                elevation = 4.dp,
//                shape = circleShapeMedium
//            )
        ,
        onCardClick = {
            onCardClick(gradeLevel)
        },
        content = {
            SchoolBox(
                modifier = modifier
                    .background(color = MaterialTheme.colorScheme.secondaryContainer)
                    .fillMaxSize(),
            ) {
                SchoolText(
                    text = gradeLevel,
                    modifier = Modifier.padding(16.dp),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    )
}

@Preview
@Composable
fun ClassesPreview() {
    ClassesScreen()
}
