package com.example.numbersapp.ui.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.numbersapp.R
import com.example.numbersapp.ui.theme.CustomTheme
import com.example.numbersapp.ui.theme.bodyTextStyle
import com.example.numbersapp.ui.theme.current
import com.example.numbersapp.ui.theme.subtitleStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialogWarning(onDismissRequest: () -> Unit, onConfirm: () -> Unit) {
    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(CustomTheme.colors.container)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.title_dialog_warning),
                style = subtitleStyle(),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = stringResource(R.string.body_dialog_warning),
                style = bodyTextStyle(),
                textAlign = TextAlign.Justify
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(
                    onClick = onDismissRequest,
                    border = BorderStroke(2.dp, current),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CustomTheme.colors.container,
                        contentColor = CustomTheme.colors.content
                    ),
                    modifier = Modifier.padding(horizontal = 8.dp).weight(1f)
                ) {
                    Text(
                        text = stringResource(R.string.cancel),
                        style = bodyTextStyle()
                    )
                }
                TextButton(
                    onClick = onConfirm,
                    border = BorderStroke(2.dp, current),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = current,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.padding(horizontal = 8.dp).weight(1f)
                ) {
                    Text(
                        text = stringResource(R.string.delete),
                        style = bodyTextStyle(),
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewAlertDialogWarning() {
    AlertDialogWarning({}, {})
}