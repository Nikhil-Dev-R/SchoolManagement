package com.rudraksha.school.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.drawscope.DrawScope.Companion.DefaultFilterQuality
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rudraksha.school.R

@Composable
fun SchoolImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = stringResource(R.string.image_content_description),
    placeholder: Painter? = painterResource(R.drawable.school_image),
    error: Painter? = painterResource(R.drawable.ic_launcher_background),
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.FillBounds,
    fallback: Painter? = error,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    filterQuality: FilterQuality = DefaultFilterQuality,
) {
//  Icon(imageVector = Icons.Outlined.Edit, contentDescription = "")
    AsyncImage(
        model = imageUrl,
        contentDescription = contentDescription,
        modifier = modifier
            .fillMaxSize()
        ,
        placeholder = placeholder,
        error = error,
        fallback = fallback,
        alignment = alignment,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = colorFilter,
        filterQuality = filterQuality
    )
}

@Preview(showBackground = true)
@Composable
fun SchoolImagePreview() {
    SchoolImage(
        imageUrl = "https://th.bing.com/th/id/OIP.5-ff1u_LA68PO1E7kroTAwHaNK?rs=1&pid=ImgDetMain",
    )
}