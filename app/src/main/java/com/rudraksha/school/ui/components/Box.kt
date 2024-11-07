package com.rudraksha.school.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SchoolBox(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    propagateMinConstraints: Boolean = false,
    content: @Composable() (BoxScope.() -> Unit)
) {
    Box(
        modifier = modifier,
        contentAlignment = contentAlignment,
        propagateMinConstraints = propagateMinConstraints,
        content = content
    )
}

@Preview
@Composable
fun SchoolBoxPreview() {
    SchoolBox(
        modifier = Modifier
    ) {
        SchoolImage(imageUrl = "https://th.bing.com/th/id/OIP.x0BSUAdaMUNjF88kZoscyQHaGL?pid=ImgDet&w=171&h=152&c=7")
    }
}