package com.rudraksha.school.ui.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.rudraksha.school.ui.components.SchoolIcon
import com.rudraksha.school.ui.components.SchoolText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchoolTopBar(
    onNavIconClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String = "School Name",
    navigationIcon: ImageVector = Icons.AutoMirrored.Outlined.ArrowBack,
) {
    TopAppBar(
        title = {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
            ) {
                IconButton(
                    onClick = onNavIconClick
                ) {
                    SchoolIcon(
                        imageVector = navigationIcon,
                        contentDescription = "Menu",
                    )
                }

                SchoolText(
                    text = title,
                    modifier = modifier,
                    style = MaterialTheme.typography.headlineMedium,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    )
}

@Preview
@Composable
fun SchoolTopBarPreview() {
    SchoolTopBar(
        {},
        title = "Home",
    )
}