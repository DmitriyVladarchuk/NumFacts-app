package com.example.numbersapp.ui.screens

import androidx.compose.animation.Animatable
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.numbersapp.R
import com.example.numbersapp.Utils.Utils
import com.example.numbersapp.models.Fact
import com.example.numbersapp.models.TypeFact
import com.example.numbersapp.ui.theme.container
import com.example.numbersapp.ui.theme.item
import com.example.numbersapp.ui.theme.yellow
import com.example.numbersapp.ui.views.DynamicItemContainer


@Composable
fun Home(modifier: Modifier = Modifier, viewModel: HomeViewModel = viewModel()) {
    Column(
        modifier = modifier.fillMaxSize().padding(top = 32.dp)
    ) {
        Text(
            text = "${stringResource(R.string.random)} ${stringResource(R.string.fact)}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        DynamicItemContainer(
            items = TypeFact.entries.toList(),
            selectedItem = viewModel.currentTypeFact,
            onItemSelected = { selected ->
                viewModel.changeTypeFact(selected)
            },
            itemContent = { typeFact, isSelected, modifier, onItemSelected ->
                ItemTypeFact(
                    typeFact = typeFact,
                    isSelected = isSelected,
                    modifier = modifier,
                    changeType = { onItemSelected(typeFact) }
                )
            }
        )
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

    HorizontalPager(
        state = pagerState,
        beyondViewportPageCount = 2,
    ) { page ->
        if (page == facts.size - 1) {
            loadFact()
        }

        facts.getOrNull(page)?.let { ItemFact(it) {
            clickableItem(it)
        } }
    }
}


@Composable
private fun ItemFact(fact: Fact, clickableItem: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = container),
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
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
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
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxSize().padding(top = 42.dp)
            )
        }
    }
}

@Composable
private fun ItemTypeFact(typeFact: TypeFact, isSelected: Boolean, modifier: Modifier, changeType: () -> Unit) {
    val animateColor = remember { Animatable(if (isSelected) item else container) }

    LaunchedEffect(isSelected) {
        animateColor.animateTo(
            targetValue = if (isSelected) item else container,
            animationSpec = tween(durationMillis = 700)
        )
    }

    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(
                animateColor.value
            )
            .clickable { changeType() }
            .padding(16.dp)
            .animateContentSize()
    ) {
        Text(
            text = stringResource(Utils.getStringResourceIdForFactType(typeFact)),
            color = if (isSelected) Color.White else Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
