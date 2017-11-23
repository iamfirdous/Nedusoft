package com.nexusinfo.nedusoft.models

import java.util.Date

/**
 * Created by lukhman on 11/23/2017.
 */

class MarksModel {
    var mId: Int = 0
    //[Display(Name = "Class Name	")]
    var classId: Int = 0
    //[Display(Name = "Subject")]
    var subjectid: Int = 0
    //[Display(Name = "Faculty	")]
    var facultyId: Int = 0

    //[Display(Name = "Exam ")]
    var examid: Int = 0
    var assesment: Int = 0
    var issueDate: Date? = null
    var studentid: Int = 0

    var actualmarks: Double = 0.toDouble()
    var marksObtained: Double = 0.toDouble()

    //[Display(Name = "Max Marks ")]
    //[Required(ErrorMessage = "Admission No. is required")]
    var maxmark: Int = 0

    //[Display(Name = "Pass Marks ")]
    var passmark: Double = 0.toDouble()
    var percentage: Double = 0.toDouble()
    var status: String? = null
    var classOrig: String? = null
    var brcode: String? = null
    var cmpid: String? = null
    var stats: String? = null
    //[Display(Name = "Year")]
    var yearId: Int = 0

    var rollNo: String? = null
    var firstName: String? = null
    var facultyName: String? = null


    //[Display(Name = "Month From")]
    var fromDate: Date? = null
    //[Display(Name = "To")]
    var toDate: Date? = null
    var examName: String? = null
    var subName: String? = null
}
