package com.example.numbersapp.ui.screens.like

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.numbersapp.R
import com.example.numbersapp.models.Fact
import com.example.numbersapp.models.TypeFact
import com.example.numbersapp.ui.theme.CustomTheme
import com.example.numbersapp.ui.theme.titleStyle
import com.example.numbersapp.ui.theme.yellow
import com.example.numbersapp.ui.views.AlertDialogWarning
import com.example.numbersapp.ui.views.ContainerTypeFact

@Composable
fun Favorite(viewModel: FavoriteViewModel = viewModel(), modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize().padding(top = 32.dp)
    ) {
        Text(
            text = "${stringResource(R.string.favorite)} ${stringResource(R.string.fact)}",
            style = titleStyle(),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        ContainerTypeFact(
            currentTypeFact = viewModel.currentTypeFact
        ) { selected ->
            viewModel.changeTypeFact(selected)
        }
        ListFavoriteFacts(
            favoriteFacts = viewModel.currentFavoriteFacts,
            changeTypeFact = { selected ->
                viewModel.changeTypeFact(selected)
            },
            deleteItem = { fact ->
                viewModel.deleteFact(fact)
            }
        )
    }
}

@Composable
private fun ListFavoriteFacts(favoriteFacts: List<Fact>, changeTypeFact: (selected: TypeFact) -> Unit, deleteItem: (fact: Fact) -> Unit) {
    val pagerState = rememberPagerState(pageCount = { TypeFact.entries.size })

    LaunchedEffect(pagerState.currentPage) {
        val type = TypeFact.entries[pagerState.currentPage]
        changeTypeFact(type)
    }

    HorizontalPager(
        state = pagerState,
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(start = 20.dp, top = 16.dp, end = 20.dp, bottom = 20.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp))
                .background(CustomTheme.colors.container)
        ) {
            itemsIndexed(favoriteFacts) { index, fact ->
                FavoriteItem(fact) { deleteItem(fact) }
                if (index < favoriteFacts.size - 1 )
                    HorizontalDivider(color = Color.Black, thickness = 1.dp)
            }
        }
    }
}

@Composable
private fun FavoriteItem(fact: Fact, deleteItem: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog)
        AlertDialogWarning(
            textInfo = "",
            onDismissRequest = { showDialog = false },
            onConfirm = {
                deleteItem()
                showDialog = false
            }
        )

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(CustomTheme.colors.container)
            .animateContentSize()
            .fillMaxWidth()
    ) {
        Text(
            text = fact.text,
            fontSize = 22.sp,
            maxLines = if (expanded) Int.MAX_VALUE else 3,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(1f)
                .clickable { expanded = !expanded }
        )

        Icon(
            imageVector = Icons.Sharp.Star,
            modifier = Modifier
                .padding(start = 8.dp)
                .size(32.dp)
                .align(Alignment.CenterVertically)
                .clickable { showDialog = true },
            tint = yellow,
            contentDescription = null
        )
    }
}
