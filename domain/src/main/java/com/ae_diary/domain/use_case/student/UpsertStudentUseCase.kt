package com.ae_diary.domain.use_case.student

import com.ae_diary.domain.model.StudentDomain
import com.ae_diary.domain.repository.StudentRepository
import javax.inject.Inject

class UpsertStudentUseCase @Inject constructor(
    private val studentRepository: StudentRepository
) {

    suspend operator fun invoke(student: StudentDomain) = studentRepository.upsertStudent(student)
}