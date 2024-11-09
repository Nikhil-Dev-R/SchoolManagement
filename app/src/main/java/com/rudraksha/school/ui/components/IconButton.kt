package com.rudraksha.school.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rudraksha.school.ui.theme.cutCornerShapeMedium
import com.rudraksha.school.ui.theme.primaryContainerLight

@Composable
fun SchoolButtonIconText(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = cutCornerShapeMedium,
    tint: Color = Color.Unspecified,
    fontFamily: FontFamily = FontFamily.Default,
    fontStyle: FontStyle = FontStyle.Normal,
    fontColor: Color = Color.Black,
    fontWeight: FontWeight = FontWeight.Bold,
    fontSize: TextUnit = 16.sp
) {
    SchoolButton(
        onClick = onClick,
        colors = ButtonDefaults
            .buttonColors(
                containerColor = primaryContainerLight,
            ),
        shape = shape,
        content = {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier,
            ) {
                SchoolIcon(
                    imageVector = icon,
                    modifier = modifier,
                    tint = tint,
                    contentDescription = ""
                )
                SchoolText(
                    text = text,
                    fontFamily = fontFamily,
                    fontStyle = fontStyle,
                    color = fontColor,
                    fontWeight = fontWeight,
                    fontSize = fontSize,
                    modifier = modifier
                        .padding(4.dp)
                )
            }
        }
    )
}

@Preview
@Composable
fun SchoolIconButtonPreview() {
    SchoolButtonIconText(
        text = "Sign In",
        icon = Icons.AutoMirrored.Outlined.ExitToApp,
        onClick = { /* Handle button click */ },
        fontFamily = FontFamily.SansSerif,
        fontStyle = FontStyle.Italic,
        fontColor = Color.Red,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
}