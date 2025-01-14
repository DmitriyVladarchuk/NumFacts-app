package com.example.numbersapp.ui.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.numbersapp.models.TypeFact

@Composable
fun ContainerTypeFact(currentTypeFact: TypeFact = TypeFact.MATH, changeTypeFact: (selected: TypeFact) -> Unit) {
    DynamicItemContainer(
        items = TypeFact.entries.toList(),
        selectedItem = currentTypeFact,
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