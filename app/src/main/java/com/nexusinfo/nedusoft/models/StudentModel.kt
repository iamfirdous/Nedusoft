package com.nexusinfo.nedusoft.models

import java.util.Date

/**
 * Created by firdous on 11/23/2017.
 */

class StudentModel {
    var studentID: Int = 0
    //[Required(ErrorMessage = "Registration No. is required")]
    var admissionNo: String? = null
    //[Required(ErrorMessage = "Year is required")]
    var yearID: Int = 0
    var combinationID: Int = 0
    var applicationID: Int = 0
    var rollNo: String? = null
    var courseID: Int = 0
    //[Required(ErrorMessage = "Student name is required")]
    var firstName: String? = null
    var middleName: String? = null
    var lastName: String? = null
    var placeofBirth: String? = null
    //[Required(ErrorMessage = "Date of birth is required")]
    var dob: String? = null
    var caste: String? = null
    var subCaste: String? = null
    var adressIdPresent: Int = 0
    var adressIdPermanent: Int = 0
    var motherTounge: String? = null
    var categoryID: String? = null
    var mediumofInstruction: String? = null
    var languageID_I: String? = null
    //[Required(ErrorMessage = "Nationality is required")]
    var nationality: Int = 0
    var religion: String? = null
    var quotaID: Int = 0
    var gender: String? = null
    var brcode: String? = null
    var standardAdmitted: String? = null
    var branchId: Int = 0
    //[Required(ErrorMessage = "Admission Sought is required")]
    var semesterId: Int = 0
    var status: String? = null
    var examPassed: Int = 0
    var extraCurriculam: String? = null
    var lastInstitute: String? = null
    var noofAttempts: String? = null
    var universityRoll: String? = null
    var yearofPassing: String? = null
    var sportsNational: String? = null
    var sportsState: String? = null
    var income: String? = null
    var studentMobile: String? = null
    var passport: String? = null
    var universityID: Int = 0
    var hostelFeeid: Int = 0
    var transportFeeid: Int = 0

    var cmpid: String? = null
    var languageID_II: String? = null
    var languageID_III: String? = null
    var waiver: String? = null
    var scholarship: String? = null
    var sectionID: Int = 0
    var sports: String? = null
    var otherActivity: String? = null
    var gradeScored: String? = null
    var tcno: String? = null
    var uid: String? = null
    var diceNo: String? = null
    var remarks: String? = null
    var sportsLevel: String? = null
    var maxMarks: String? = null
    var obtainedMarks: Int = 0
    var percentage: Double = 0.toDouble()
    var hospitalDetailsId: Int = 0
    var familyID: Int = 0
    var addressId: Int = 0
    var photoID: Int = 0
    var feeId: Int = 0
    //[Required(ErrorMessage = "Registration Date is required")]
    var appDate: Date? = null
    //[Required(ErrorMessage = "Child Id is required")]
    var childId: Int = 0
    //[Required(ErrorMessage = "Father Id is required")]
    var fatherId: Int = 0
    var schoolTransport: String? = null
    var parentsNo: String? = null
    var emerNo1: String? = null
    var emerNo2: String? = null
    var emerNo3: String? = null
    var msg: String? = null
    //[Display(Name = "Year")]
    var lastYear: Int = 0

    //===================================//
    //[Display(Name = "Syllabus")]
    var branchName: String? = null
    //[Display(Name = "Admission Sought For")]
    var semesterName: String? = null
    //[Display(Name = "Year")]
    var yearName: String? = null
    //[Display(Name = "Registration No.")]
    var admissionNumber: String? = null
    //[Display(Name = "In Case of Emergency.")]
    var inCase: String? = null
    var applicationStatus: String? = null
    var fathersName: String? = null
    var combination: String? = null

    var routeID: Int = 0
    var lastSyllabus: Int = 0
}

