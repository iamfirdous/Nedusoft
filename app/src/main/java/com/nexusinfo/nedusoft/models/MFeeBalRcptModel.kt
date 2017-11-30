package com.nexusinfo.nedusoft.models

/**
 * Created by firdous on 11/23/2017.
 */

class MFeeBalRcptModel {
    var fBslno: Int = 0
    var fStudentId: Int = 0
    var feeRcvd: Long = 0
    var feeBFine: Double = 0.toDouble()
    var feeBBankCharge: Double = 0.toDouble()
    var feeBConcession: Double = 0.toDouble()
    var feeBal: Double = 0.toDouble()
    var feetype: String? = null
    var stdFee_ID: Int = 0
    var fYear_id: Int = 0

    //=====================================
    var semesterID: Int = 0
    var branchID: Int = 0
    var balanceList: List<MFeeBalRcptModel>? = null
    //[Display(Name = "Year")]
    var yearName: String? = null
    //[Display(Name = "Syllabus")]
    var branchName: String? = null
    //[Display(Name = "Combination")]
    var combination: String? = null
    var combinationID: Int = 0
    //[Display(Name = "Class")]
    var semester: String? = null

    var compose: String? = null
    var isIsselected: Boolean = false
    var studentName: String? = null
    var fatherMobile: String? = null
    var status: String? = null
}
