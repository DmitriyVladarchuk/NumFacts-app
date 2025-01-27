package com.example.numbersapp.ui.views

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.numbersapp.domain.models.TypeFact

@Composable
fun ContainerTypeFact(currentTypeFact: TypeFact = TypeFact.MATH, changeTypeFact: (selected: TypeFact) -> Unit) {
    DynamicItemContainer(
        items = TypeFact.entries.toList(),
        selectedItem = currentTypeFact,
        modifier = Modifier.padding(start = 20.dp, top = 16.dp, end = 20.dp, bottom = 20.dp),
        onItemSelected = { selected ->
            changeTypeFact(selected)
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
}

@Preview
@Composable
private fun PreviewContainerTypeFact() {
    ContainerTypeFact() { }
}