package com.rudraksha.school.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BrowseGallery
import androidx.compose.material.icons.outlined.NoiseControlOff
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rudraksha.school.models.firebase.FirebaseTeacher
import com.rudraksha.school.ui.components.SchoolButton
import com.rudraksha.school.ui.components.SchoolCard
import com.rudraksha.school.ui.components.SchoolCheckBox
import com.rudraksha.school.ui.components.SchoolIcon
import com.rudraksha.school.ui.components.SchoolText
import com.rudraksha.school.ui.components.SchoolTextField
import com.rudraksha.school.ui.utils.SchoolTopBar
import com.rudraksha.school.ui.utils.validateTeacherDetailsInput
import com.rudraksha.school.viewmodel.SchoolViewModel

@Composable
fun AddTeacherScreen(
    modifier: Modifier = Modifier,
    onNavIconClick: () -> Unit = {},
    onSubmitClick: (String, String, String, String, Boolean, String) -> Unit = { _, _, _, _, _, _ -> },
) {
//    val uiState by remember { mutableStateOf(UiStateAddTeacher()) }

    var id by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isClassTeacher by remember { mutableStateOf(false) }
    var standard by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            SchoolTopBar(
                title = "Add Teacher",
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
                .fillMaxSize()
        ) {
            SchoolCard(
                content = {
                    SchoolIcon(
                        imageVector = Icons.Outlined.BrowseGallery,
                        modifier = Modifier.size(100.dp)
                    )
                    SchoolText(
                        text = "Add Image",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                },
                onCardClick = {
                }
            )

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
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                SchoolText(
                    text = "Is class teacher?",
                    style = MaterialTheme.typography.bodyLarge,
                )
                SchoolCheckBox(
                    checkedState = isClassTeacher,
                    onCheckedChange = {
                        isClassTeacher = it
                    }
                )
            }

            if (isClassTeacher) {
                SchoolTextField(
                    label = { SchoolText(text = "Grade Level") },
                    value = standard,
                    onValueChange = {
                        standard = it
                    },
                    leadingIcon = {
                        SchoolIcon(imageVector = Icons.Outlined.PersonOutline)
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    )
                )
            }

            SchoolTextField(
                label = { SchoolText(text = "Description") },
                value = description,
                onValueChange = {
                    description = it
                },
                leadingIcon = {
                    SchoolIcon(imageVector = Icons.Outlined.NoiseControlOff)
                },
                singleLine = false,
                maxLines = 5
            )

            Spacer(modifier = Modifier.height(16.dp))

            SchoolButton(
                content = {
                    SchoolText(
                        text = "Submit",
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                onClick = {
                    onSubmitClick( id, name, imageUrl, description, isClassTeacher, standard)
                }
            )
        }
    }
}
/*sealed class UiState(
    val id: String = "",
    val name: String = "",
    val imageUrl: String = "",
    val isClassTeacher: Boolean = false,
    val standard: String = "",
    val description: String = "",
    val isError: Boolean = false,
    val errorMessage: String = ""
)*/

/*data class UiStateAddTeacher(
    var id: MutableState<String> = StateFlow(""),
    var name: MutableState<String> = mutableStateOf(""),
    var imageUrl: MutableState<String> = mutableStateOf(""),
    var isClassTeacher: Boolean = false,
    var standard: MutableState<String> = mutableStateOf(""),
    var description: MutableState<String> = mutableStateOf(""),
    var isError: MutableState<Boolean> = mutableStateOf(false),
    var errorMessage: MutableState<String> = mutableStateOf(""),
)*/

@Preview
@Composable
fun AddTeacherScreenPreview() {
    AddTeacherScreen()
}
