package com.rudraksha.school.ui.theme

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val cutCornerShapeSmall = CutCornerShape(topEnd = 8.dp, bottomStart = 8.dp)
val cutCornerShapeMedium = CutCornerShape(topEnd = 16.dp, bottomStart = 16.dp)
val cutCornerShapeLarge = CutCornerShape(topEnd = 24.dp, bottomStart = 24.dp)
val cutCornerShapeExtraLarge = CutCornerShape(topEnd = 32.dp, bottomStart = 32.dp)

val roundedCornerShapeSmall = RoundedCornerShape(topEnd = 8.dp, bottomStart = 8.dp)
val roundedCornerShapeMedium = RoundedCornerShape(topEnd = 16.dp, bottomStart = 16.dp)
val roundedCornerShapeLarge = RoundedCornerShape(topEnd = 24.dp, bottomStart = 24.dp)
val roundedCornerShapeExtraLarge = RoundedCornerShape(topEnd = 32.dp, bottomStart = 32.dp)
val roundedCorners = RoundedCornerShape(40.dp)

val circleShapeSmall = RoundedCornerShape(8.dp)
val circleShapeMedium = RoundedCornerShape(16.dp)
val circleShapeLarge = RoundedCornerShape(24.dp)
val circleShapeExtraLarge = RoundedCornerShape(32.dp)

val cutCornerShapes = Shapes(
    small = cutCornerShapeSmall,
    medium = cutCornerShapeMedium,
    large = cutCornerShapeLarge,
    extraLarge = cutCornerShapeExtraLarge,
)

val roundedCornerShapes = Shapes(
    small = roundedCornerShapeSmall,
    medium = roundedCornerShapeMedium,
    large = roundedCornerShapeLarge,
    extraLarge = roundedCornerShapeExtraLarge,
)