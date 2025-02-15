package com.ae_diary.activity.util

import com.ae_diary.presentation.model.util.DayOfWeek
import com.ae_diary.presentation.model.util.WeekType
import com.ae_diary.presentation.util.TimeFormatter.dateFormatter
import java.time.LocalDate

fun curWeekType(date: String, semesterTime: Triple<String, String, WeekType>): WeekType {
    var curDate = LocalDate.parse(date, dateFormatter)
    val semStartTime = LocalDate.parse(semesterTime.first, dateFormatter)
    var semFirstWeekType = semesterTime.third

    while (curDate != semStartTime) {
        curDate = curDate.plusDays(1)

        if (curDate.dayOfWeek.value == DayOfWeek.MONDAY.code)
            semFirstWeekType = if (semFirstWeekType == WeekType.ODD) WeekType.EVEN else WeekType.ODD
    }

    return semFirstWeekType
}