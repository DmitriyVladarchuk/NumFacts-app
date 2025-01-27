package com.example.numbersapp.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.numbersapp.R
import com.example.numbersapp.Utils.Utils
import com.example.numbersapp.ui.screens.Routes
import com.example.numbersapp.ui.theme.CustomTheme
import com.example.numbersapp.ui.theme.bodyTextStyle
import com.example.numbersapp.ui.theme.captionStyle
import com.example.numbersapp.ui.theme.current
import com.example.numbersapp.ui.theme.subtitleStyle
import com.example.numbersapp.ui.theme.titleStyle
import com.example.numbersapp.ui.views.TitleTextInfo

@Composable
fun Settings(navController: NavController, viewModel: SettingsViewModel = hiltViewModel(), modifier: Modifier = Modifier) {

    val context = LocalContext.current

    Column(modifier = modifier) {

        TitleTextInfo(stringResource(R.string.setting))

        Card(
            colors = CardDefaults.cardColors(containerColor = CustomTheme.colors.container),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.theme),
                style = subtitleStyle(),
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
            )
            SwitchDarkModeItem(viewModel.isDArkModeEnabled) { viewModel.toggleDarkMode() }
        }

        Card(
            colors = CardDefaults.cardColors(containerColor = CustomTheme.colors.container),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 16.dp)
                .fillMaxWidth()
        ) {
            Feedback { Utils.sendEmail(context, context.getString(R.string.feedback_title_message), context.getString(R.string.feedback_email)) }
        }

        Card(
            colors = CardDefaults.cardColors(containerColor = CustomTheme.colors.container),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 16.dp)
                .fillMaxWidth()
        ) {
            Common(
                clickableAbout = { navController.navigate(Routes.AboutApp.route) },
                clickableGitHub = { Utils.openUrl(context, context.getString(R.string.github_repository)) }
            )
        }
    }
}

@Composable
private fun SwitchDarkModeItem(checked: Boolean, changeTheme: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .background(CustomTheme.colors.container)
    ) {
        Text(
            text = stringResource(R.string.dark_mode),
            style = bodyTextStyle(),
            modifier = Modifier.align(Alignment.CenterVertically)
        )

        Spacer(modifier = Modifier.weight(1f))

        Switch(
            checked = checked,
            onCheckedChange = { changeTheme() },
            colors = SwitchDefaults.colors(checkedTrackColor = current, uncheckedTrackColor = CustomTheme.colors.background)
        )
    }
}

@Composable
private fun Feedback(clickable: () -> Unit) {
    Column(
        modifier = Modifier.background(CustomTheme.colors.container).padding(horizontal = 20.dp, vertical = 8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.feedback),
            style = subtitleStyle(),
        )

        Column(
            modifier = Modifier
                .padding(top = 16.dp)
                .clickable { clickable() }
        ) {
            Text(
                text = stringResource(id = R.string.report_an_issue),
                style = bodyTextStyle(),
            )

            Text(
                text = stringResource(id = R.string.report_description),
                style = captionStyle(),
                modifier = Modifier.padding(top = 5.dp)
            )
        }
    }
}

@Composable
private fun Common(clickableAbout: () -> Unit, clickableGitHub: () -> Unit) {

    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.common),
            style = titleStyle(),
        )

        Column(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth()
                .clickable { clickableAbout() }
        ) {
            Text(
                text = stringResource(id = R.string.about_app),
                style = bodyTextStyle()
            )

            Text(
                text = stringResource(id = R.string.about_description),
                style = captionStyle(),
                modifier = Modifier.padding(top = 5.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { clickableGitHub() }
        ) {
            Text(
                text = stringResource(id = R.string.github),
                style = bodyTextStyle()
            )

            Text(
                text = stringResource(id = R.string.github_description),
                style = captionStyle(),
                modifier = Modifier.padding(top = 5.dp)
            )
        }
    }
}