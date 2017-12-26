package com.nexusinfo.nedusoft.models

import java.util.*

/**
 * Created by firdous on 12/22/2017.
 */
class LessonUpdatesModel {

    var lessons: ArrayList<Lesson>? = null

    class Lesson {
        var topicId: Int = 0
        var date: Date? = null
        var facultyName: String? = null
        var subject: String? = null
        var topic: String? = null
        var notes: String? = null
        var fileName: String? = null
        var isFileAvailable: Boolean? = null
    }
}