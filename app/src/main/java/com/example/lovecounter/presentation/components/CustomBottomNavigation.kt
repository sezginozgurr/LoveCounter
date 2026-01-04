package com.example.lovecounter.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.lovecounter.R
import com.example.lovecounter.presentation.navigation.BottomNavItem
import com.example.lovecounter.presentation.theme.AppColor
import com.example.lovecounter.presentation.theme.SelectedItemColor

@Composable
fun CustomBottomNavigation(
    navController: NavController,
    onFabClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
    ) {
        // FAB
        FloatingActionButton(
            onClick = onFabClick,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-25).dp)
                .size(56.dp)
                .zIndex(1f),
            containerColor = Color.White,
            contentColor = Color.White
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_gift),
                contentDescription = "Ekle",
                tint = Color.Unspecified,
                modifier = Modifier.size(48.dp)
            )
        }

        // Bottom Navigation Bar
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color.White,
            shadowElevation = 8.dp,
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // İlk iki icon
                BottomNavItem.items.take(2).forEach { item ->
                    val currentRoute = navController.currentDestination
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(vertical = 2.dp)
                    ) {
                        IconButton(
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            modifier = Modifier.size(64.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = item.iconResId),
                                contentDescription = item.title,
                                modifier = Modifier.size(36.dp),
                                tint = if (currentRoute == item.route)
                                    AppColor
                                else
                                    Color.Gray
                            )
                        }
                        // Seçili item için sarı çizgi
                        if (currentRoute == item.route) {
                            Box(
                                modifier = Modifier
                                    .width(32.dp)
                                    .height(2.dp)
                                    .background(SelectedItemColor)
                            )
                        } else {
                            Spacer(modifier = Modifier.height(2.dp))
                        }
                    }
                }

                // FAB için boşluk
                Spacer(modifier = Modifier.width(72.dp))

                // Son iki icon
                BottomNavItem.items.takeLast(2).forEach { item ->
                    val currentRoute = navController.currentDestination
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        IconButton(
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            modifier = Modifier.size(64.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = item.iconResId),
                                contentDescription = item.title,
                                modifier = Modifier.size(36.dp),
                                tint = if (currentRoute == item.route)
                                    AppColor
                                else
                                    Color.Gray
                            )
                        }
                        // Seçili item için sarı çizgi
                        if (currentRoute == item.route) {
                            Box(
                                modifier = Modifier
                                    .width(32.dp)
                                    .height(2.dp)
                                    .background(SelectedItemColor)
                            )
                        } else {
                            Spacer(modifier = Modifier.height(2.dp))
                        }
                    }
                }
            }
        }
    }
} 