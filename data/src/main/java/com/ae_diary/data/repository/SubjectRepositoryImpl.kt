package com.ae_diary.data.repository

import com.ae_diary.data.mapper.toDomain
import com.ae_diary.data.mapper.toEntity
import com.ae_diary.data.model.Subjects
import com.ae_diary.data.repository.database.dao.SubjectsDAO
import com.ae_diary.domain.model.SubjectDomain
import com.ae_diary.domain.repository.SubjectRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubjectRepositoryImpl @Inject constructor(
    private val subjectDao: SubjectsDAO
): SubjectRepository {

    override suspend fun getAllSubjects(): Flow<List<SubjectDomain>> =
        subjectDao.getAllSubjects().toDomain(Subjects::toDomain)

    override suspend fun upsertSubject(subject: SubjectDomain) =
        subjectDao.upsertSubject(subject.toEntity())

    override suspend fun deleteSubject(subject: SubjectDomain) =
        subjectDao.deleteSubject(subject.toEntity())

}