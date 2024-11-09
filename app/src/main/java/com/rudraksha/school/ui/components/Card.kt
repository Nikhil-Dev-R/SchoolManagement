package com.rudraksha.school.ui.components

import androidx.compose.animation.Animatable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rudraksha.school.ui.theme.circleShapeMedium
import com.rudraksha.school.ui.theme.roundedCornerShapeMedium
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalConfiguration
import kotlinx.coroutines.launch

@Composable
fun SchoolCard(
    content: @Composable (ColumnScope.() -> Unit),
    modifier: Modifier = Modifier,
    onCardClick: (() -> Unit)? = null,
    width: Dp = 150.dp,
    height: Dp = 150.dp,
    shape: Shape = circleShapeMedium,
    colors: CardColors = CardDefaults.cardColors(),
    elevation: CardElevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
    border: BorderStroke? = BorderStroke(2.dp, MaterialTheme.colorScheme.onSurface),
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .size(width = width, height = height)
//            .shadow(
//                elevation = 8.dp,
//                shape = circleShapeMedium,
//                spotColor = MaterialTheme.colorScheme.onPrimaryContainer,
//                clip = false,
//            )
            .clickable {
                if (onCardClick != null) {
                    onCardClick()
                }
            }
//            .pointerInput(Unit) {
//                detectTapGestures(
//                    onPress = {
//                        // On press, elevate the card further
//                        elevation = 12.dp
//                        tryAwaitRelease() // Reset elevation on release
//                        elevation = 8.dp
//                    }
//                )
//            }
        ,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally, // Center horizontally
            verticalArrangement = Arrangement.Center // Center vertically
        ) {
            content() // Your card content here
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SchoolCardPreview() {
    SchoolCard(
//        imageUrl = "https://i.postimg.cc/2jZqd3xb/2609e729-9cb4-44c2-9361-f663d02ea415.jpg",
        content = {
            SchoolImage(
                imageUrl = "https://th.bing.com/th/id/OIP.5-ff1u_LA68PO1E7kroTAwHaNK?rs=1&pid=ImgDetMain",
            )
        },
        onCardClick = {},
    )
}

@Preview(showBackground = true)
@Composable
fun SchoolCardPreview2() {
    SchoolCard(
        content = {
            SchoolText(text = "App")
        }
    )
}

