package com.example.numbersapp.ui.screens.like

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.numbersapp.ui.theme.container
import com.example.numbersapp.ui.views.ContainerTypeFact

@Composable
fun Favorite(viewModel: FavoriteViewModel = viewModel(), modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize().padding(top = 32.dp)
    ) {
        Text(
            text = "${stringResource(R.string.favorite)} ${stringResource(R.string.fact)}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        ContainerTypeFact(
            currentTypeFact = viewModel.currentTypeFact
        ) { selected ->
            viewModel.changeTypeFact(selected)
        }
        ListFavoriteFacts(viewModel.currentFavoriteFacts)
    }
}

@Composable
private fun ListFavoriteFacts(favoriteFacts: List<Fact>) {
    LazyColumn(
        modifier = Modifier
            .padding(start = 20.dp, top = 16.dp, end = 20.dp, bottom = 20.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(20.dp))
            .background(container)
    ) {
        itemsIndexed(favoriteFacts) { index, fact ->
            FavoriteItem(fact)
            if (index < favoriteFacts.size - 1 )
                HorizontalDivider(color = Color.Black, thickness = 1.dp)
        }
    }
}

@Composable
private fun FavoriteItem(fact: Fact) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 16.dp)
            .background(container)
            .clickable { expanded = !expanded }
            .animateContentSize()
    ) {
        Text(
            text = fact.text,
            fontSize = 22.sp,
            maxLines = if (expanded) Int.MAX_VALUE else 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}
