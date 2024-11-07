package com.rudraksha.school.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
                    if (isFabPressed) {
                        if (newGradeLevel.isNotEmpty()) {
                            standardList.add(newGradeLevel)
//                            newGradeLevel = ""
                        }
                    }
                }
            ) {
                if (isFabPressed) {
                    SchoolIcon(
                        imageVector = Icons.Outlined.Save,
                        modifier = Modifier.size(32.dp),
                    )
                } else {
                    SchoolIcon(
                        imageVector = Icons.Outlined.Add,
                        modifier = Modifier.size(32.dp)
                    )
                }
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
            items(classList.size) {
                ClassCard(
                    gradeLevel = classList[it],
                    onCardClick = onCardClick,
                )
            }
            items(1) {
                if (isFabPressed) {
                    // Show an editable card at the end of the list for adding a new class
                    // UI for displaying a single class card
                    SchoolCard(
                        modifier = Modifier
                            .size(100.dp),
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

@Composable
fun ClassCard(
    gradeLevel: String,
    onCardClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    // UI for displaying a single class card
    SchoolCard(
        modifier = modifier,
        onCardClick = { onCardClick(gradeLevel) },
        content = {
            // Display class name
            SchoolBox(
                modifier = modifier
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
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