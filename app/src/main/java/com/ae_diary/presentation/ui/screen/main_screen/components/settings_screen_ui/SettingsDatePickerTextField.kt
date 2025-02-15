package com.ae_diary.presentation.ui.screen.main_screen.components.settings_screen_ui

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ae_diary.presentation.ui.theme.settings.DeathNoteTheme

@Composable
fun SettingsDatePickerTextField(
    @StringRes title: Int,
    value: String,
    onClick: () -> Unit
) {
    Column {

        Text(
            text = stringResource(id = title),
            style = DeathNoteTheme.typography.textFieldTitle,
            color = DeathNoteTheme.colors.inverse
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(
                    shape = DeathNoteTheme.shapes.rounded12
                )
                .background(
                    color = DeathNoteTheme.colors.baseBackground
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onClick
                ),
            verticalAlignment = Alignment.CenterVertically,
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 15.dp),
                    contentAlignment = Alignment.Center,
                    content = {
                        Text(
                            text = value,
                            style = TextStyle(
                                color = DeathNoteTheme.colors.inverse,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                )
            }
        )
    }
}
