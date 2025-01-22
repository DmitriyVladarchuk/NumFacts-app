package com.example.numbersapp.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.numbersapp.ui.theme.CustomTheme

@Composable
fun <T> DynamicItemContainer(
    items: List<T>,
    selectedItem: T,
    onItemSelected: (T) -> Unit,
    itemContent: @Composable (T, Boolean, Modifier, (T) -> Unit) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(start = 20.dp, top = 16.dp, end = 20.dp, bottom = 20.dp)
            .wrapContentHeight()
            .fillMaxWidth()
            .clip(CircleShape)
            .background(CustomTheme.colors.container),
    ) {
        items.forEach { item ->
            val isSelected = item == selectedItem
            itemContent(item, isSelected, Modifier.weight(1f)) { selectedItem ->
                onItemSelected(selectedItem)
            }
        }
    }
}