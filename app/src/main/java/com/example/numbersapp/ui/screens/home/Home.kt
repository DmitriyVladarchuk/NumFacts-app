package com.example.numbersapp.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.numbersapp.R
import com.example.numbersapp.Utils.Utils
import com.example.numbersapp.domain.models.Fact
import com.example.numbersapp.domain.models.FactSource
import com.example.numbersapp.domain.models.TypeFact
import com.example.numbersapp.ui.theme.CustomTheme
import com.example.numbersapp.ui.theme.bodyTextStyle
import com.example.numbersapp.ui.theme.captionStyle
import com.example.numbersapp.ui.theme.current
import com.example.numbersapp.ui.theme.subtitleStyle
import com.example.numbersapp.ui.theme.textFieldStyle
import com.example.numbersapp.ui.theme.yellow
import com.example.numbersapp.ui.views.ContainerTypeFact
import com.example.numbersapp.ui.views.DynamicItemContainer
import com.example.numbersapp.ui.views.ItemTypeFact
import com.example.numbersapp.ui.views.TitleTextInfo


@Composable
fun Home(modifier: Modifier = Modifier, viewModel: HomeViewModel = hiltViewModel()) {
    Column(
        modifier = modifier
    ) {
        TitleTextInfo(stringResource(R.string.facts))

        DynamicItemContainer(
            items = FactSource.entries.toList(),
            selectedItem = viewModel.currentFactSource,
            modifier = Modifier.padding(start = 20.dp, top = 16.dp, end = 20.dp),
            onItemSelected = { selected ->
                viewModel.changeFactSource(selected)
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

        ContainerTypeFact(
            currentTypeFact = viewModel.currentTypeFact
        ) { selected ->
            viewModel.changeTypeFact(selected)
        }

        if (viewModel.currentFactSource == FactSource.USER &&
            viewModel.currentTypeFact != TypeFact.DATE) {
            InputUserNumberFact { number -> viewModel.loadUserFact(number) }

            if (viewModel.facts.isNotEmpty())
                viewModel.facts.last()?.let {
                    ItemFact(it) { viewModel.updateFactSavedStatus(it) }
                }
        }
        else if (viewModel.currentFactSource == FactSource.USER &&
            viewModel.currentTypeFact == TypeFact.DATE) {
            MonthAndNumberInput { month, year -> viewModel.loadUserFact(month, year) }

            if (viewModel.facts.isNotEmpty())
                viewModel.facts.last()?.let {
                    ItemFact(it) { viewModel.updateFactSavedStatus(it) }
                }
        } else {
            FactHorizontalPager(
                facts = viewModel.facts,
                loadFact = { viewModel.loadNextFact() },
                clickableItem = { fact: Fact ->  viewModel.updateFactSavedStatus(fact) }
            )
        }

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
private fun InputUserNumberFact(clickableSearch: (Int) -> Unit) {
    var textNumber by remember { mutableStateOf("") }

    OutlinedTextField(
        value = textNumber,
        onValueChange = { textNumber = it },
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(top = 8.dp),
        textStyle = textFieldStyle(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        shape = RoundedCornerShape(20.dp),
        maxLines = 1,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = CustomTheme.colors.background,
            disabledContainerColor = current,
            cursorColor = current,
            focusedBorderColor = current,
            unfocusedBorderColor = CustomTheme.colors.container
        ),
        trailingIcon = {
            TextButton(
                onClick = {
                    val number = textNumber.toIntOrNull() ?: 0
                    clickableSearch(number)
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(containerColor = current)
            ) {
                Text(
                    text = stringResource(id = R.string.search),
                    style = bodyTextStyle()
                )
            }
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MonthAndNumberInput(clickableSearch: (Int, Int) -> Unit) {
    val months = listOf(
        "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
        "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"
    )

    var expanded by remember { mutableStateOf(false) }
    var selectedMonth by remember { mutableStateOf(months[0]) }
    var textNumber by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(MenuAnchorType.PrimaryEditable, enabled = true),
                shape = RoundedCornerShape(20.dp),
                readOnly = true,
                value = selectedMonth,
                onValueChange = {},
                label = {
                    Text(
                        text = stringResource(R.string.month),
                        style = captionStyle(),
                    )
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = CustomTheme.colors.background,
                    unfocusedContainerColor = CustomTheme.colors.background,
                    focusedTextColor = CustomTheme.colors.content,
                    unfocusedTextColor = CustomTheme.colors.content,
                    focusedBorderColor = current,
                    unfocusedBorderColor = CustomTheme.colors.container,
                    disabledContainerColor = current,
                    cursorColor = current,
                )
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                containerColor = CustomTheme.colors.container
            ) {
                months.forEach { month ->
                    DropdownMenuItem(
                        text = { Text(text = month, style = bodyTextStyle()) },
                        onClick = {
                            selectedMonth = month
                            expanded = false
                        }
                    )
                }
            }
        }

        OutlinedTextField(
            value = textNumber,
            onValueChange = { textNumber = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(top = 8.dp),
            textStyle = textFieldStyle(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = RoundedCornerShape(20.dp),
            maxLines = 1,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = CustomTheme.colors.background,
                disabledContainerColor = current,
                cursorColor = current,
                focusedBorderColor = current,
                unfocusedBorderColor = CustomTheme.colors.container
            ),
            trailingIcon = {
                TextButton(
                    onClick = {
                        val number = textNumber.toIntOrNull() ?: 0
                        val month = months.indexOf(selectedMonth)
                        clickableSearch(month, number)
                    },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.fillMaxHeight(),
                    colors = ButtonDefaults.buttonColors(containerColor = current)
                ) {
                    Text(
                        text = stringResource(id = R.string.search),
                        style = bodyTextStyle()
                    )
                }
            }
        )
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
