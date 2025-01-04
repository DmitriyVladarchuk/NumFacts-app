package com.example.numbersapp.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.numbersapp.models.Fact


@Composable
fun Main(modifier: Modifier = Modifier, viewModel: MainViewModel = viewModel()) {
    Column(
        modifier = modifier
    ) {

    }
}

@Composable
private fun HeaderHorizontalPager() {

}

@Composable
private fun ItemFact(fact: Fact) {

}