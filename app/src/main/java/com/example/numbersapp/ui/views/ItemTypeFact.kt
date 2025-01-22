package com.example.numbersapp.ui.views

import androidx.compose.animation.Animatable
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numbersapp.Utils.Utils
import com.example.numbersapp.models.TypeFact
import com.example.numbersapp.ui.theme.CustomTheme
import com.example.numbersapp.ui.theme.current

@Composable
fun ItemTypeFact(typeFact: TypeFact, isSelected: Boolean, modifier: Modifier = Modifier, changeType: () -> Unit) {
    val currentColor = current
    val containerColor = CustomTheme.colors.container

    val animateColor = remember { Animatable(containerColor) }

    LaunchedEffect(isSelected) {
        animateColor.animateTo(
            targetValue = if (isSelected) currentColor else containerColor,
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

@Preview
@Composable
private fun PreviewItemTypeFact() {
    ItemTypeFact(
        typeFact = TypeFact.MATH,
        isSelected = true,
    ) { }
}