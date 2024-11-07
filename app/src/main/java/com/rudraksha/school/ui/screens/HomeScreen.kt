package com.rudraksha.school.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rudraksha.school.R
import com.rudraksha.school.ui.components.SchoolCard
import com.rudraksha.school.ui.components.SchoolImage
import com.rudraksha.school.ui.components.SchoolText
import com.rudraksha.school.ui.theme.circleShapeMedium
import com.rudraksha.school.ui.utils.DrawerContent
import com.rudraksha.school.ui.utils.SchoolTopBar
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    name: String,
    email: String,
    signOut: () -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            AnimatedVisibility(
                visible = drawerState.isOpen,
                enter = fadeIn() + slideInHorizontally(),
                exit = slideOutHorizontally() + fadeOut(),
            ) {
                DrawerContent(
                    onCloseDrawer = {
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    modifier = Modifier,
                    name = name,
                    email = email,
                    signOut = signOut,
                    navController = navController
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                SchoolTopBar(
                    title = "Home",
                    navigationIcon = Icons.Outlined.Menu,
                    onNavIconClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    },
                )
            },
            modifier = Modifier,
            containerColor = MaterialTheme.colorScheme.background
        ) { innerPadding ->
//            val p = innerPadding
//            Content()
            HomeScreenContent(
                modifier = Modifier
                    .padding(innerPadding),
            )
        }
    }
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    Box(
//        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            SchoolImage(
                imageUrl = "https://static.vecteezy.com/system/resources/previews/025/782/464/non_2x/school-logo-free-vector.jpg",
//            imageUrl = "https://img.freepik.com/premium-vector/education-school-logo-design_586739-4428.jpg?w=2000",
                contentDescription = "Watermark",
                modifier = modifier
                    .size(250.dp)
                    .clip(shape = RoundedCornerShape(125.dp))
                    .zIndex(0F)
                    .alpha(0.25f),
                contentScale = ContentScale.None
            )
        }

        Column(
            modifier = modifier
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            // School Image
            SchoolCard(
                modifier = Modifier
                    .wrapContentSize()
                    .aspectRatio(16 / 9f)
                    .align(Alignment.CenterHorizontally)
                    .shadow(
                        elevation = 4.dp,
                        shape = circleShapeMedium
                    ),
                content = {
                    SchoolImage(
                        imageUrl = "https://images.squarespace-cdn.com/content/v1/5e9737180d92ba206d5b0777/1588962068093-2GT81W9M1TPARNP4F39G/CMS+Campus.jpg",
                        contentDescription = "College Image",
                        modifier = Modifier
                            .wrapContentSize(),
                    )
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // School Details
            SchoolText(
                text = "Welcome to Nature Loving School",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(8.dp))

            SchoolText(
                text = stringResource(id = R.string.school_desc_200),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(32.dp))

            SchoolText(
                text = "Created by Rudraksha",
                fontFamily = FontFamily.Cursive,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        name = "nik",
        email = "fg@h.c",
        {},
        navController = rememberNavController()
    )
}
