package com.ae_diary.presentation.ui.screen.settings.components.timetable_screen_ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ae_diary.R
import com.ae_diary.presentation.model.Subject
import com.ae_diary.presentation.model.event.TimetableUIEvent
import com.ae_diary.presentation.model.state.TimetableUIState
import com.ae_diary.presentation.ui.theme.settings.DeathNoteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectSelectMenu(
    onEvent: (TimetableUIEvent) -> Unit,
    state: TimetableUIState,
    allSubjects: List<Subject>,
    paddingValues: PaddingValues = PaddingValues(
        start = 25.dp,
        end = 25.dp,
        bottom = 25.dp
    )
) {

    if (state.bottomSheetSubjectPickerState) {
        BackHandler {
            onEvent(TimetableUIEvent.ChangeBottomSheetSubjectPickerState)
        }

        ModalBottomSheet(
            onDismissRequest = { onEvent(TimetableUIEvent.ChangeBottomSheetSubjectPickerState) },
            dragHandle = {}
        ) {
            Column(
                modifier = Modifier
                    .clip(
                        shape = DeathNoteTheme.shapes.rounded20_top
                    )
                    .background(color = DeathNoteTheme.colors.baseBackground)
                    .padding(paddingValues)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ) {

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 50.dp
                        ),
                    textAlign = TextAlign.Center,
                    text = stringResource(id = R.string.choose_subject),
                    style = DeathNoteTheme.typography.topBar,
                    color = DeathNoteTheme.colors.inverse
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(bottom = 10.dp)
                ) {
                    items(allSubjects) { subject ->
                        SubjectMenuBar(
                            subject = subject,
                            onSelect = {
                                onEvent(TimetableUIEvent.ChangeBottomSheetSubject(it))
                                onEvent(TimetableUIEvent.ChangeBottomSheetSubjectPickerState)
                            }
                        )
                    }
                }
            }
        }
    }
}