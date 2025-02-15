package com.ae_diary.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.ae_diary.data.model.interfaces.DataEntity

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Subjects::class,
            parentColumns = [
                "id"
            ],
            childColumns = [
                "subjectId"
            ],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Timetables(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val date: String,
    val subjectId: Int,
    val startTime: String,
    val endTime: String,
    val weekType: String,
    val isDismissed: Boolean
) : DataEntity
