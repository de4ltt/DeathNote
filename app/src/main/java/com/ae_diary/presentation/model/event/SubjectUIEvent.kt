package com.ae_diary.presentation.model.event

import androidx.annotation.StringRes
import com.ae_diary.presentation.model.Subject

sealed class SubjectUIEvent {

    data class UpsertSubject(val subject: Subject): SubjectUIEvent()
    data class DeleteSubject(val subject: Subject): SubjectUIEvent()

    data object IdleSubject: SubjectUIEvent()
    data class SelectSubject(val subject: Subject): SubjectUIEvent()
    data class ChangeSubjectName(val name: String): SubjectUIEvent()
    data class ChangeSubjectType(val type: String): SubjectUIEvent()

    data class ChangeDialogState(val state: Boolean) : SubjectUIEvent()
    data class ChangeDialogTitle(@StringRes val title: Int): SubjectUIEvent()
    data class ChangeDialogContent(
        val subject: Subject,
        @StringRes val title: Int,
        val onAcceptRequest: () -> Unit,
        val onDismissRequest: () -> Unit,
        val refuseButtonTitle: Int? = null,
        val acceptButtonTitle: Int? = null
    ) : SubjectUIEvent()
}
