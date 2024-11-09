package com.rudraksha.school.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.NoiseControlOff
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rudraksha.school.models.firebase.Subject
import com.rudraksha.school.ui.components.SchoolButton
import com.rudraksha.school.ui.components.SchoolIcon
import com.rudraksha.school.ui.components.SchoolText
import com.rudraksha.school.ui.components.SchoolTextField
import com.rudraksha.school.ui.utils.SchoolTopBar

@Composable
fun AddStudentScreen(
    modifier: Modifier = Modifier,
    onNavIconClick: () -> Unit = {},
    standardList: List<String> = listOf("1", "2", "3"),
    subjectList: List<Subject> = listOf(
        Subject("S1", "Sub1"),
        Subject("S2", "Sub2"),
        Subject("S3", "Sub3")
    ),
    onSubmitClick: (String, String, String, String, Boolean, String) -> Unit = { _, _, _, _, _, _ -> },
) {
    var id by remember { mutableStateOf("") }
    var rollNo by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var standard by remember { mutableStateOf("") }
    var section by remember { mutableStateOf("") }
    var presentDays by remember { mutableIntStateOf(0) }
    val sectionList = listOf("A", "B", "C", "D", "E", "F")
    var expandedStandard by remember { mutableStateOf(false) }
    var expandedSection by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            SchoolTopBar(
                title = "Add Student",
                onNavIconClick = onNavIconClick
            )
        }
    ) { innerPadding ->
        // Content for the screen
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            SchoolTextField(
                label = { SchoolText(text = "ID") },
                value = id,
                onValueChange = {
                    id = it
                },
                leadingIcon = {
                    SchoolIcon(imageVector = Icons.Outlined.NoiseControlOff)
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                )
            )

            SchoolTextField(
                label = { SchoolText(text = "Roll No.") },
                value = rollNo,
                onValueChange = {
                    rollNo = it
                },
                leadingIcon = {
                    SchoolIcon(imageVector = Icons.Outlined.NoiseControlOff)
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                )
            )

            SchoolTextField(
                label = { SchoolText(text = "Name") },
                value = name,
                onValueChange = {
                    name = it
                },
                leadingIcon = {
                    SchoolIcon(imageVector = Icons.Outlined.PersonOutline)
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                )
            )
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        expandedStandard = !expandedStandard
                    }
            ) {
                SchoolText(
                    text = "Standard",
                    style = MaterialTheme.typography.bodyLarge
                )

                DropdownMenu(expanded = expandedStandard, onDismissRequest = { /*TODO*/ }) {
                    standardList.forEach {
                        DropdownMenuItem(
                            text = {
                                SchoolText(text = it)
                            },
                            onClick = {
                                standard = it
                            }
                        )
                    }
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            SchoolButton(
                content = {
                    SchoolText(
                        text = "Submit",
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                onClick = {
//                    onSubmitClick( id, name, imageUrl, description, isClassTeacher, standard)
                }
            )
        }
    }
}

@Preview
@Composable
fun StuPreview() {
    AddStudentScreen()
}
