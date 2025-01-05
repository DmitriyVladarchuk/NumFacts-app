package com.example.numbersapp.ui.screens.bottomBar

import androidx.compose.animation.Animatable
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.numbersapp.R
import com.example.numbersapp.ui.screens.Routes
import com.example.numbersapp.ui.theme.container
import com.example.numbersapp.ui.theme.item
import com.example.numbersapp.ui.views.DynamicItemContainer

@Composable
fun BottomBar(navController: NavController) {
    val bottomBarItems = listOf(
        BottomBarItem(Routes.Home.route, R.string.home, Icons.Default.Home),
        BottomBarItem(Routes.Settings.route, R.string.setting, Icons.Default.Settings),
        BottomBarItem(Routes.Like.route, R.string.like, Icons.Default.FavoriteBorder)
    )

    // Состояние для текущего маршрута
    val currentRoute = remember { mutableStateOf(navController.currentDestination?.route ?: Routes.Home.route) }

    LaunchedEffect(navController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            currentRoute.value = destination.route ?: Routes.Home.route
        }
    }

    DynamicItemContainer(
        items = bottomBarItems,
        selectedItem = bottomBarItems.find { it.route == currentRoute.value } ?: bottomBarItems[0],
        onItemSelected = { selected ->
            navController.navigate(selected.route) {
                popUpTo(navController.graph.startDestinationId) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        },
        itemContent = { bottomBarItem, isSelected, modifier, onItemSelected ->
            BottomBarItem(
                bottomItem = bottomBarItem,
                isSelected = isSelected,
                modifier = modifier,
                changeType = { onItemSelected(bottomBarItem) }
            )
        }
    )
}

@Composable
fun BottomBarItem(bottomItem: BottomBarItem, isSelected: Boolean, modifier: Modifier, changeType: () -> Unit) {
    val animateColor = remember { Animatable(if (isSelected) item else container) }

    LaunchedEffect(isSelected) {
        animateColor.animateTo(
            targetValue = if (isSelected) item else container,
            animationSpec = tween(durationMillis = 700)
        )
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .clip(CircleShape)
            .background(
                animateColor.value
            )
            .clickable { changeType() }
            .padding(16.dp)
            .animateContentSize()
    ) {
        Icon(
            imageVector = bottomItem.icon,
            modifier = Modifier.size(32.dp),
            tint = if (isSelected) Color.White else Color.Black,
            contentDescription = ""
        )
        if (isSelected)
            Text(
                text = stringResource(bottomItem.titleId),
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(start = 8.dp).align(Alignment.CenterVertically)
            )
    }
}