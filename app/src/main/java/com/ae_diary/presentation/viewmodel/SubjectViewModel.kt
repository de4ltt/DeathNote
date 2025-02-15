package com.ae_diary.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ae_diary.domain.model.SubjectDomain
import com.ae_diary.domain.use_case.subject.util.SubjectUseCases
import com.ae_diary.presentation.mapper.toDomain
import com.ae_diary.presentation.mapper.toPresentation
import com.ae_diary.presentation.model.Subject
import com.ae_diary.presentation.model.event.SubjectUIEvent
import com.ae_diary.presentation.model.state.SubjectDialogState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubjectViewModel @Inject constructor(
    private val subjectUseCases: SubjectUseCases
) : ViewModel() {

    private val _allSubjects: MutableStateFlow<List<Subject>> = MutableStateFlow(emptyList())
    val allSubjects: StateFlow<List<Subject>> = _allSubjects.asStateFlow()

    private val _subjectDialogState: MutableStateFlow<SubjectDialogState> =
        MutableStateFlow(SubjectDialogState())
    val subjectDialogState: StateFlow<SubjectDialogState> = _subjectDialogState.asStateFlow()

    fun onEvent(event: SubjectUIEvent) {
        when (event) {
            is SubjectUIEvent.ChangeDialogContent ->
                viewModelScope.launch(Dispatchers.IO) {
                    _subjectDialogState.value = _subjectDialogState.value.copy()
                }

            is SubjectUIEvent.ChangeDialogTitle ->
                viewModelScope.launch(Dispatchers.IO) {
                    _subjectDialogState.value =
                        _subjectDialogState.value.copy(title = event.title)
                }

            is SubjectUIEvent.ChangeDialogState ->
                viewModelScope.launch(Dispatchers.IO) {
                    _subjectDialogState.value =
                        _subjectDialogState.value.copy(isShown = event.state)
                }

            is SubjectUIEvent.ChangeSubjectName ->
                viewModelScope.launch(Dispatchers.IO) {
                    _subjectDialogState.value =
                        _subjectDialogState.value.copy(
                            subject = subjectDialogState.value.subject.copy(name = event.name)
                        )
                }

            SubjectUIEvent.IdleSubject ->
                viewModelScope.launch(Dispatchers.IO) {
                    _subjectDialogState.value =
                        _subjectDialogState.value.copy(subject = Subject())
                }

            is SubjectUIEvent.ChangeSubjectType ->
                viewModelScope.launch(Dispatchers.IO) {
                    _subjectDialogState.value =
                        _subjectDialogState.value.copy(
                            subject = subjectDialogState.value.subject.copy(type = event.type)
                        )
                }

            is SubjectUIEvent.SelectSubject ->
                viewModelScope.launch(Dispatchers.IO) {
                    _subjectDialogState.value =
                        _subjectDialogState.value.copy(subject = event.subject)
                }

            is SubjectUIEvent.DeleteSubject -> deleteSubject(event.subject)

            is SubjectUIEvent.UpsertSubject -> upsertSubject(event.subject)
        }
    }

    fun getSubjectById(id: Int?): Subject? =
        if (id != null) _allSubjects.value.filter { it.id == id }[0]
        else null

    private fun deleteSubject(subject: Subject) =
        viewModelScope.launch(Dispatchers.IO) {
            subjectUseCases.DeleteSubjectUseCase(subject.toDomain())
        }

    private fun upsertSubject(subject: Subject) =
        viewModelScope.launch(Dispatchers.IO) {
            subjectUseCases.UpsertSubjectUseCase(subject.toDomain())
        }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            subjectUseCases.GetAllSubjectsUseCase().collect {
                _allSubjects.value = it.toPresentation(SubjectDomain::toPresentation)
            }
        }
    }
}