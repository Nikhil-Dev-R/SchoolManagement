package com.rudraksha.school.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ArrowForwardIos
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rudraksha.school.ui.components.SchoolButton
import com.rudraksha.school.ui.components.SchoolIcon
import com.rudraksha.school.ui.components.SchoolImage
import com.rudraksha.school.ui.components.SchoolText
import com.rudraksha.school.ui.screens.Screen
import com.rudraksha.school.ui.theme.roundedCorners

@Composable
fun DrawerContent(
    onCloseDrawer: () -> Unit,
    name: String,
    email: String,
    signOut: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .width(300.dp)
            .fillMaxHeight()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(top = 8.dp, end = 8.dp, bottom = 8.dp, start = 8.dp)
            .clickable { }
            .verticalScroll(scrollState),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            IconButton(
                onClick = onCloseDrawer
            ) {
                SchoolIcon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                )
            }

            SchoolText(
                text = "Back",
                style = MaterialTheme.typography.titleLarge
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            SchoolImage(
                imageUrl = "https://static.vecteezy.com/system/resources/previews/025/782/464/non_2x/school-logo-free-vector.jpg",
                contentDescription = "School Icon",
                modifier = modifier
                    .size(80.dp)
                    .clip(roundedCorners),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                SchoolText(
                    text = name.ifEmpty { "John" },
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    textAlign = TextAlign.Start,
                )

                Spacer(modifier = Modifier.height(4.dp))

                SchoolText(
                    text = email,
                    textAlign = TextAlign.Start
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Access Features
        DrawerItem(
            text = "Students",
            onClick = {
                onCloseDrawer()
                navController.navigate(Screen.Classes.route)
            },
            modifier = Modifier
        )

        Spacer(modifier = Modifier.height(16.dp))

        DrawerItem(
            text = "Teachers",
            onClick = {
                onCloseDrawer()
                navController.navigate(Screen.Teachers.route)
            },
            modifier = Modifier
        )

        Spacer(modifier = Modifier.height(16.dp))

        DrawerItem(
            text = "Gallery",
            onClick = {
                onCloseDrawer()
                navController.navigate(Screen.Gallery.route)
            },
            modifier = Modifier
        )

        Spacer(modifier = Modifier.height(16.dp))

        DrawerItem(
            imageVector = Icons.Outlined.Add,
            text = "Add User",
            onClick = {
                onCloseDrawer()
                navController.navigate(route = Screen.Register.route)
            },
            modifier = Modifier,
            placeIconInRight = false
        )

        Spacer(modifier = Modifier.height(16.dp))

        DrawerItem(
            imageVector = Icons.AutoMirrored.Outlined.ExitToApp,
            text = "Log Out",
            onClick = {
                signOut()
                onCloseDrawer()
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Home.route) {
                        inclusive = true
                    }
                }
            },
            modifier = Modifier,
            placeIconInRight = false
        )
    }
}

@Composable
fun DrawerItem(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    imageVector: ImageVector = Icons.AutoMirrored.Outlined.ArrowForwardIos,
    placeIconInRight: Boolean = true
) {
    SchoolButton(
        onClick = onClick,
        colors = ButtonDefaults
            .buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            ),
        modifier = modifier.fillMaxWidth(),
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = if(placeIconInRight) Arrangement.SpaceBetween else Arrangement.Start,
            ) {
                if (!placeIconInRight) {
                    SchoolIcon(
                        imageVector = imageVector,
                        modifier = modifier,
                        contentDescription = ""
                    )
                }

                SchoolText(
                    text = text,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                        .padding(4.dp)
                )

                if (placeIconInRight) {
                    SchoolIcon(
                        imageVector = imageVector,
                        modifier = modifier,
                        contentDescription = ""
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DrawerContentPreview() {
    DrawerContent(
        {},
        name = "Ab",
        email = "sch@gnsm.com",
        {},
        navController = rememberNavController()
    )
}

/*package com.rudraksha.school.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ArrowForwardIos
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rudraksha.school.ui.components.SchoolButton
import com.rudraksha.school.ui.components.SchoolIcon
import com.rudraksha.school.ui.components.SchoolImage
import com.rudraksha.school.ui.components.SchoolText
import com.rudraksha.school.ui.screens.Screen
import com.rudraksha.school.ui.theme.roundedCorners

@Composable
fun DrawerContent(
    onCloseDrawer: () -> Unit,
    name: String,
    email: String,
    signOut: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Scaffold(
        topBar = {
            SchoolTopBar(
                onNavIconClick = onCloseDrawer,
                title = "Back",
                modifier = Modifier.width(300.dp)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.clickable {}
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = modifier
                    .padding(innerPadding)
                    .width(300.dp)
                    .fillMaxHeight()
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(top = 16.dp, end = 8.dp, bottom = 8.dp, start = 8.dp),
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier,
                ) {
                    SchoolImage(
                        imageUrl = "https://static.vecteezy.com/system/resources/previews/025/782/464/non_2x/school-logo-free-vector.jpg",
                        contentDescription = "School Icon",
                        modifier = modifier
                            .size(80.dp)
                            .clip(roundedCorners),
                        contentScale = ContentScale.Crop,
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Column {
                        SchoolText(
                            text = name.ifEmpty { "John" },
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            textAlign = TextAlign.Center,
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        SchoolText(
                            text = email,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Access Features
                DrawerItem(
                    text = "Students",
                    onClick = {
                        onCloseDrawer()
                        navController.navigate(Screen.Classes.route)
                    },
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(16.dp))

                DrawerItem(
                    text = "Teachers",
                    onClick = {
                        onCloseDrawer()
                        navController.navigate(Screen.Teachers.route)
                    },
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(16.dp))

                DrawerItem(
                    text = "Gallery",
                    onClick = {
                        onCloseDrawer()
                        navController.navigate(Screen.Gallery.route)
                    },
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(16.dp))

                DrawerItem(
                    imageVector = Icons.Outlined.Add,
                    text = "Add User",
                    onClick = {
                        onCloseDrawer()
                        navController.navigate(route = Screen.Register.route)
                    },
                    modifier = Modifier,
                    placeIconInRight = false
                )

                Spacer(modifier = Modifier.height(16.dp))

                DrawerItem(
                    imageVector = Icons.AutoMirrored.Outlined.ExitToApp,
                    text = "Log Out",
                    onClick = {
                        signOut()
                        onCloseDrawer()
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Home.route) {
                                inclusive = true
                            }
                        }
                    },
                    modifier = Modifier,
                    placeIconInRight = false
                )
            }
        }
    }
}

@Composable
fun DrawerItem(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    imageVector: ImageVector = Icons.AutoMirrored.Outlined.ArrowForwardIos,
    placeIconInRight: Boolean = true
) {
    SchoolButton(
        onClick = onClick,
        colors = ButtonDefaults
            .buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            ),
        modifier = modifier.fillMaxWidth(),
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = if(placeIconInRight) Arrangement.SpaceBetween else Arrangement.Start,
            ) {
                if(!placeIconInRight) {
                    SchoolIcon(
                        imageVector = imageVector,
                        modifier = modifier,
                        contentDescription = ""
                    )
                }

                SchoolText(
                    text = text,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                        .padding(4.dp)
                )

                if(placeIconInRight) {
                    SchoolIcon(
                        imageVector = imageVector,
                        modifier = modifier,
                        contentDescription = ""
                    )
                }
            }
        }
    )
}*/

/*package com.rudraksha.school.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ArrowForwardIos
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rudraksha.school.ui.components.SchoolIcon
import com.rudraksha.school.ui.components.SchoolImage
import com.rudraksha.school.ui.components.SchoolText
import com.rudraksha.school.ui.screens.Screen
import com.rudraksha.school.ui.theme.roundedCorners

@Composable
fun DrawerContent(
    onCloseDrawer: () -> Unit,
    name: String,
    email: String,
    signOut: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    // Get the current route from the NavController
    val currentRoute = navController.currentDestination?.route

    Column(modifier = Modifier.clickable { }) {
        Spacer(modifier = Modifier.height(40.dp))
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = modifier
                .width(300.dp)
                .fillMaxHeight()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(top = 16.dp, end = 8.dp, bottom = 8.dp, start = 8.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier,
            ) {
                IconButton(onClick = onCloseDrawer) {
                    SchoolIcon(imageVector = Icons.AutoMirrored.Outlined.ArrowBack)
                }

                SchoolText(
                    text = "Back",
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
                SchoolImage(
                    imageUrl = "https://static.vecteezy.com/system/resources/previews/025/782/464/non_2x/school-logo-free-vector.jpg",
                    contentDescription = "School Icon",
                    modifier = modifier
                        .size(80.dp)
                        .clip(roundedCorners),
                    contentScale = ContentScale.Crop,
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    SchoolText(
                        text = name.ifEmpty { "John" },
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    SchoolText(
                        text = email,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Access Features
            NavigationDrawerItem(
                label = { SchoolText(text = "Students") },
                selected = currentRoute == Screen.Classes.route,  // Use route comparison
                onClick = {
                    onCloseDrawer()
                    navController.navigate(Screen.Classes.route)
                },
                modifier = Modifier.fillMaxWidth(),
                icon = { SchoolIcon(imageVector = Icons.Outlined.ArrowForwardIos) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            NavigationDrawerItem(
                label = { SchoolText(text = "Teachers") },
                selected = currentRoute == Screen.Teachers.route,  // Use route comparison
                onClick = {
                    onCloseDrawer()
                    navController.navigate(Screen.Teachers.route)
                },
                modifier = Modifier.fillMaxWidth(),
                icon = { SchoolIcon(imageVector = Icons.AutoMirrored.Outlined.ArrowForwardIos) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            NavigationDrawerItem(
                label = { SchoolText(text = "Gallery") },
                selected = currentRoute == Screen.Gallery.route,  // Use route comparison
                onClick = {
                    onCloseDrawer()
                    navController.navigate(Screen.Gallery.route)
                },
                modifier = Modifier.fillMaxWidth(),
                icon = { SchoolIcon(imageVector = Icons.AutoMirrored.Outlined.ArrowForwardIos) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            NavigationDrawerItem(
                label = { SchoolText(text = "Add User") },
                selected = currentRoute == Screen.Register.route,  // Use route comparison
                onClick = {
                    onCloseDrawer()
                    navController.navigate(Screen.Register.route)
                },
                modifier = Modifier.fillMaxWidth(),
                icon = { SchoolIcon(imageVector = Icons.Outlined.Add) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            NavigationDrawerItem(
                label = { SchoolText(text = "Log Out") },
                selected = false,  // Log Out item should never be selected
                onClick = {
                    signOut()
                    onCloseDrawer()
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                icon = { SchoolIcon(imageVector = Icons.AutoMirrored.Outlined.ExitToApp) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DrawerContentPreview() {
    DrawerContent(
        onCloseDrawer = {},
        name = "Ab",
        email = "sch@gnsm.com",
        signOut = {},
        navController = rememberNavController()
    )
}
*/
