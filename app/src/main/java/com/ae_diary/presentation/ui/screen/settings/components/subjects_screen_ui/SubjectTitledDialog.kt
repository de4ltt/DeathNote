package com.ae_diary.presentation.ui.screen.settings.components.subjects_screen_ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ae_diary.R
import com.ae_diary.presentation.model.event.SubjectUIEvent
import com.ae_diary.presentation.model.state.SubjectDialogState
import com.ae_diary.presentation.ui.common.bottom_sheet.BottomBarTextField
import com.ae_diary.presentation.ui.common.bottom_sheet.BottomBarWithTextFields
import com.ae_diary.presentation.ui.theme.DarkRed
import com.ae_diary.presentation.ui.theme.DarkYellow
import com.ae_diary.presentation.ui.theme.settings.DeathNoteTheme
import com.ae_diary.presentation.util.Validator
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectTitledDialog(
    state: SubjectDialogState,
    onEvent: (SubjectUIEvent) -> Unit
) {

    state.apply {

        if (isShown)
            BottomBarWithTextFields(
                title = title,
                onAcceptRequest = {
                    onEvent(SubjectUIEvent.UpsertSubject(subject))
                    onEvent(SubjectUIEvent.ChangeDialogState(false))
                },
                onDismissRequest = { onEvent(SubjectUIEvent.ChangeDialogState(false)) },
                isActive = Validator.run {
                    validateSubjectName(subject.name)
                },
                content = {

                    BottomBarTextField(
                        title = R.string.subject_name,
                        icon = {
                            Crossfade(targetState = subject.type) {
                                Box(
                                    modifier = Modifier.width(40.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = stringResource(
                                            id = if (it == "lk") R.string.lk
                                            else R.string.pr
                                        ).uppercase(),
                                        modifier = Modifier
                                            .clickable(
                                                interactionSource = remember { MutableInteractionSource() },
                                                indication = null,
                                                onClick = {
                                                    onEvent(
                                                        SubjectUIEvent.ChangeSubjectType(
                                                            if (subject.type == "lk") "pr" else "lk"
                                                        )
                                                    )
                                                }
                                            ),
                                        color = if (it == "lk") DarkRed else DarkYellow,
                                        style = DeathNoteTheme.typography.textFieldTitle
                                    )
                                }
                            }

                        },
                        onValueChange = {
                            onEvent(SubjectUIEvent.ChangeSubjectName(it.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.getDefault()
                                ) else it.toString()
                            }))
                        },
                        value = subject.name,
                        isCentered = false,
                        innerTitle = R.string.enter_callname
                    )

                }
            )

    }
}