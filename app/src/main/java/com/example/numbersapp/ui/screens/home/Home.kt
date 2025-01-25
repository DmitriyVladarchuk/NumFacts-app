package com.example.numbersapp.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.numbersapp.R
import com.example.numbersapp.Utils.Utils
import com.example.numbersapp.domain.models.Fact
import com.example.numbersapp.ui.theme.CustomTheme
import com.example.numbersapp.ui.theme.bodyTextStyle
import com.example.numbersapp.ui.theme.current
import com.example.numbersapp.ui.theme.subtitleStyle
import com.example.numbersapp.ui.theme.titleStyle
import com.example.numbersapp.ui.theme.yellow
import com.example.numbersapp.ui.views.ContainerTypeFact


@Composable
fun Home(modifier: Modifier = Modifier, viewModel: HomeViewModel = viewModel()) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "${stringResource(R.string.random)} ${stringResource(R.string.fact)}",
            style = titleStyle(),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        ContainerTypeFact(
            currentTypeFact = viewModel.currentTypeFact
        ) { selected ->
            viewModel.changeTypeFact(selected)
        }

        FactHorizontalPager(
            facts = viewModel.facts,
            loadFact = { viewModel.loadNextFact() },
            clickableItem = { fact: Fact ->  viewModel.updateFactSavedStatus(fact) }
        )
    }
}

@Composable
private fun FactHorizontalPager(facts: List<Fact?>, loadFact: () -> Unit, clickableItem: (fact: Fact) -> Unit) {
    val pagerState = rememberPagerState(pageCount = { facts.size })

    if (facts.isEmpty()) {
        Box(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .background(
                    color = CustomTheme.colors.container,
                    shape = RoundedCornerShape(20.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = current)
        }
    } else {
        HorizontalPager(
            state = pagerState,
            beyondViewportPageCount = 2,
        ) { page ->
            if (page == facts.size - 1) {
                loadFact()
            }

            facts.getOrNull(page)?.let { fact ->
                ItemFact(fact) {
                    clickableItem(fact)
                }
            }
        }
    }
}


@Composable
private fun ItemFact(fact: Fact, clickableItem: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = CustomTheme.colors.container),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.7f)
            .clickable { clickableItem() }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(24.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${stringResource(Utils.getStringResourceIdForFactType(fact.type))} ${stringResource(R.string.fact)} ",
                    style = subtitleStyle(),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f)
                        .padding(start = 32.dp) // кастыль
                )

                Icon(
                    imageVector = Icons.Sharp.Star,
                    modifier = Modifier.size(32.dp),
                    tint = if(fact.isSaved) yellow else Color.Black,
                    contentDescription = ""
                )
            }


            Text(
                text = fact.text,
                style = bodyTextStyle(),
                modifier = Modifier.fillMaxSize().padding(top = 42.dp)
            )
        }
    }
}
