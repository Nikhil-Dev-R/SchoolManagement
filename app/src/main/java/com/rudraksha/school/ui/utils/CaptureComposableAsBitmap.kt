package com.rudraksha.school.ui.utils

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView

fun captureComposableAsBitmap(context: Context, content: @Composable () -> Unit): Bitmap {
    // Ensure the context is valid
    if (context !is Activity) {
        throw IllegalArgumentException("Context must be an instance of Activity")
    }

    // Create a bitmap with the desired size
    val width = 1080 // Set your desired width
    val height = 1920 // Set your desired height
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

    // Create a Canvas to draw the bitmap
    val canvas = Canvas(bitmap)

    // Create a ComposeView to render the Composable
    val composeView = ComposeView(context).apply {
        setContent {
            content()
        }
    }

    // Measure and layout the ComposeView
    composeView.measure(
        View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
        View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY)
    )
    composeView.layout(0, 0, width, height)

    // Draw the ComposeView onto the canvas
    composeView.draw(canvas)

    return bitmap
}
