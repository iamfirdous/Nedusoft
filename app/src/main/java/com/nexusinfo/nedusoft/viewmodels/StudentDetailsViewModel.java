package com.nexusinfo.nedusoft.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.v4.util.ArraySet;

import com.nexusinfo.nedusoft.LocalDatabaseHelper;
import com.nexusinfo.nedusoft.connection.DatabaseConnection;
import com.nexusinfo.nedusoft.models.StudentDetailsModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by firdous on 12/1/2017.
 */

public class StudentDetailsViewModel extends ViewModel {

    private StudentDetailsModel studentDetailsModel;

    public void setStudent(Context context) throws Exception{

        studentDetailsModel = new StudentDetailsModel();
//        Field fields[] = StudentDetailsModel.class.getDeclaredFields();
//        StringBuffer buffer = new StringBuffer();

//        for(Field f : fields){
//            buffer.append("model.set"+f.getName().replaceFirst(f.getName().substring(0, 1), f.getName().substring(0, 1).toUpperCase())+"();\n");
//        }
//        System.out.println(buffer.toString());

            String userID = LocalDatabaseHelper.getInstance(context).getUser().getUserID();
            DatabaseConnection databaseConnection = new DatabaseConnection(context);
            Connection conn = databaseConnection.getConnection();

            Statement stmt2 = conn.createStatement();
            String query2 = "SELECT " + DatabaseConnection.COL_SEMESTER + " FROM " + DatabaseConnection.VIEW_STUDENT_DETAILS_FOR_REPORT + " " +
                                "LEFT JOIN MSemester ON View_StudentDetailsForReport.ExamPassed = MSemester.SemesterID " +
                                "WHERE " + DatabaseConnection.COL_ROLLNO + " = '" + userID + "'";
//            Log.e("Query2: ", query2);
            ResultSet rsForEP = stmt2.executeQuery(query2);

            Statement stmt1 = conn.createStatement();
            String query1 = "SELECT * FROM " + DatabaseConnection.VIEW_STUDENT_DETAILS_FOR_REPORT + " " +
                                "LEFT JOIN MQuota ON View_StudentDetailsForReport.QuotaID = MQuota.QuotaID " +
                                "LEFT JOIN MSemester ON View_StudentDetailsForReport.SemesterID = MSemester.SemesterID " +
                                "LEFT JOIN CSystemType ON View_StudentDetailsForReport.AdmissionTypeID = CSystemType.TypeID " +
                                "WHERE " + DatabaseConnection.COL_ROLLNO + " = '" + userID + "'";
//            Log.e("Query1: ", query1);
            ResultSet rs = stmt1.executeQuery(query1);

            Statement stmt3 = conn.createStatement();
            String query3 = "SELECT TypeId, TypeDesc FROM CSystemType WHERE ParentTypeId = 1038 OR ParentTypeId = 23 OR ParentTypeId = 1";
            ResultSet rsCSystemType = stmt3.executeQuery(query3);

            while (rs.next()){
                studentDetailsModel.setACross(rs.getString(DatabaseConnection.COL_CROSS));
                studentDetailsModel.setAddressId(rs.getInt(DatabaseConnection.COL_ADDRESSID));
                studentDetailsModel.setAddressLine1(rs.getString(DatabaseConnection.COL_ADDRESSLINE1));
                studentDetailsModel.setAddressLine2(rs.getString(DatabaseConnection.COL_ADDRESSLINE2));
                studentDetailsModel.setAddressTypeId(rs.getInt(DatabaseConnection.COL_ADDRESSTYPEID));
                studentDetailsModel.setAdmissionNo(rs.getString(DatabaseConnection.COL_ADMISSIONNO));
                studentDetailsModel.setAdressIdPermanent(rs.getInt(DatabaseConnection.COL_ADRESSIDPERMANENT));
                studentDetailsModel.setAdressIdPresent(rs.getInt(DatabaseConnection.COL_ADRESSIDPRESENT));
//                model.setAppDate();
                studentDetailsModel.setApplicationID(rs.getInt(DatabaseConnection.COL_APPLICATIONID));
//                model.setApplicationStatus();
                studentDetailsModel.setBranchId(rs.getInt(DatabaseConnection.COL_BRANCHID));
                studentDetailsModel.setBranchName(rs.getString(DatabaseConnection.COL_BRANCHNAME));
                studentDetailsModel.setBrcode(rs.getString(DatabaseConnection.COL_BRCODE));
                studentDetailsModel.setCaste(rs.getString(DatabaseConnection.COL_CASTE));
                studentDetailsModel.setCategory(rs.getString(DatabaseConnection.COL_TYPEDESC));
                studentDetailsModel.setCategoryID(rs.getString(DatabaseConnection.COL_CATEGORYID));
//                model.setChildId();
                studentDetailsModel.setCity(rs.getString(DatabaseConnection.COL_CITY));
                studentDetailsModel.setCmpid(rs.getString(DatabaseConnection.COL_CMPID));
                studentDetailsModel.setCombination(rs.getString(DatabaseConnection.COL_COMBINATION));
                studentDetailsModel.setCombinationID(rs.getInt(DatabaseConnection.COL_COMBINATIONID));
//                model.setCountry();
                studentDetailsModel.setCountryStateId(rs.getInt(DatabaseConnection.COL_COUNTRYSTATEID));
                studentDetailsModel.setCourseID(rs.getInt(DatabaseConnection.COL_COURSEID));
                studentDetailsModel.setCourseName(rs.getString(DatabaseConnection.COL_COURSENAME));
                studentDetailsModel.setData(rs.getBytes(DatabaseConnection.COL_DATA));
                studentDetailsModel.setDiceNo(rs.getString(DatabaseConnection.COL_DICENO));
                studentDetailsModel.setDob(rs.getString(DatabaseConnection.COL_DOB));
                studentDetailsModel.setDoctorname(rs.getString(DatabaseConnection.COL_DOCTORNAME));
                studentDetailsModel.setEmerNo1(rs.getString(DatabaseConnection.COL_EMGNO1));
                studentDetailsModel.setEmerNo2(rs.getString(DatabaseConnection.COL_EMGNO2));
                studentDetailsModel.setEmerNo3(rs.getString(DatabaseConnection.COL_EMGNO3));
                studentDetailsModel.setExamName(rs.getString(DatabaseConnection.COL_EXAMNAME));
                studentDetailsModel.setExamPassed(rs.getInt(DatabaseConnection.COL_EXAMPASSED));

                while (rsForEP.next()) {
                    studentDetailsModel.setExampassedName(rsForEP.getString(DatabaseConnection.COL_SEMESTER));
//                    if (studentDetailsModel.getExampassedName() != null)
//                        Log.e("Value", studentDetailsModel.getExampassedName());
//                    else
//                        Log.e("Value", "Null");
                }

                studentDetailsModel.setExt(rs.getString(DatabaseConnection.COL_EXT));
                studentDetailsModel.setExtension(rs.getString(DatabaseConnection.COL_EXTENSION));
                studentDetailsModel.setExtraCurriculam(rs.getString(DatabaseConnection.COL_EXTRACURRICULAM));
                studentDetailsModel.setFamilyID(rs.getInt(DatabaseConnection.COL_FAMILYID));
                studentDetailsModel.setFatherAnnualIncome(rs.getString(DatabaseConnection.COL_FATHERANNUALINCOME));
                studentDetailsModel.setFatherCmpAddress(rs.getString(DatabaseConnection.COL_FATHERCMPADDRESS));
                studentDetailsModel.setFatherCmpName(rs.getString(DatabaseConnection.COL_FATHERCMPNAME));
                studentDetailsModel.setFatherCmpNo(rs.getString(DatabaseConnection.COL_FATHEROFFICENO));
                studentDetailsModel.setFatherEmail(rs.getString(DatabaseConnection.COL_FATHEREMAIL));
//                model.setFatherId();
                studentDetailsModel.setFatherLastName(rs.getString(DatabaseConnection.COL_FATHERLASTNAME));
                studentDetailsModel.setFatherMiddleName(rs.getString(DatabaseConnection.COL_FATHERMIDDLENAME));
                studentDetailsModel.setFatherMobile(rs.getString(DatabaseConnection.COL_FATHERMOBILE));
                studentDetailsModel.setFatherQualification(rs.getString(DatabaseConnection.COL_FATHERQUALIFICATION));
                studentDetailsModel.setFatherprofession(rs.getString(DatabaseConnection.COL_FATHERPROFESSION));
                studentDetailsModel.setFathersName(rs.getString(DatabaseConnection.COL_FATHERSNAME));
                studentDetailsModel.setFeeId(rs.getInt(DatabaseConnection.COL_FEEID));
                studentDetailsModel.setFileno(rs.getString(DatabaseConnection.COL_FILENO));
                studentDetailsModel.setFirstName(rs.getString(DatabaseConnection.COL_FIRSTNAME));
                studentDetailsModel.setGender(rs.getString(DatabaseConnection.COL_GENDER));
                studentDetailsModel.setGradeScored(rs.getString(DatabaseConnection.COL_GRADESCORED));
                studentDetailsModel.setHospitalDetailsId(rs.getInt(DatabaseConnection.COL_HOSPITALDETAILSID));
                studentDetailsModel.setHospitalname(rs.getString(DatabaseConnection.COL_HOSPITALNAME));
                studentDetailsModel.setHostelFeeid(rs.getInt(DatabaseConnection.COL_HOSTELFEEID));

                while (rsCSystemType.next()){
                    int id = rsCSystemType.getInt("TypeId");
                    String desc = rsCSystemType.getString("TypeDesc");
                    if(rs.getInt("LanguageID_I") == id){
                        studentDetailsModel.setILanguage(desc);
                    }
                    if(rs.getInt("LanguageID_II") == id){
                        studentDetailsModel.setIILanguage(desc);
                    }
                    if(rs.getInt("LanguageID_III") == id){
                        studentDetailsModel.setIIILanguage(desc);
                    }
                    if(rs.getInt("MediumofInstruction") == id){
                        studentDetailsModel.setMediumofInstruction(desc);
                    }
                    if(rs.getInt("Nationality")  == id){
                        studentDetailsModel.setNationalityName(desc);
                    }
                    if(rs.getInt("CountryStateId") == id){
                        studentDetailsModel.setCountry(desc);
                    }
                }

//                studentDetailsModel.setILanguage(rs.getString(DatabaseConnection.COL_LANGUAGE_I));
//                studentDetailsModel.setIILanguage(rs.getString(DatabaseConnection.COL_LANGUAGEII));
//                studentDetailsModel.setIIILanguage(rs.getString(DatabaseConnection.COL_LANGAUGEIII));
//                model.setInCase();
                studentDetailsModel.setIncome(rs.getString(DatabaseConnection.COL_INCOME));
//                studentDetailsModel.setLanguageID_I(rs.getString(DatabaseConnection.COL_LANGUAGEID_I));
//                studentDetailsModel.setLanguageID_II(rs.getString(DatabaseConnection.COL_LANGUAGEID_II));
//                studentDetailsModel.setLanguageID_III(rs.getString(DatabaseConnection.COL_LANGUAGEID_III));
                studentDetailsModel.setLastInstitute(rs.getString(DatabaseConnection.COL_LASTINSTITUTE));
                studentDetailsModel.setLastName(rs.getString(DatabaseConnection.COL_LASTNAME));
                studentDetailsModel.setLastSyllabus(rs.getInt(DatabaseConnection.COL_LASTINST_BRANCHID));
                studentDetailsModel.setLastYear(rs.getInt(DatabaseConnection.COL_LASTINST_YEARID));
                studentDetailsModel.setManualIncome(rs.getString(DatabaseConnection.COL_MANUALINCOME));
                studentDetailsModel.setMaxMarks(rs.getString(DatabaseConnection.COL_MAXMARKS));
                studentDetailsModel.setMedicalInsurance(rs.getString(DatabaseConnection.COL_MEDICALINSURANCE));
//                studentDetailsModel.setMediumofInst(rs.getString(DatabaseConnection.COL_MEDIUMOFINSTRUCTION));
//                studentDetailsModel.setMediumofInstruction(rs.getString(DatabaseConnection.COL_MEDIUMOFINSTRUCTION));
                studentDetailsModel.setMiddleName(rs.getString(DatabaseConnection.COL_MIDDLENAME));
                studentDetailsModel.setMotherCmpAddress(rs.getString(DatabaseConnection.COL_MOTHERCMPADDRESS));
                studentDetailsModel.setMotherCmpName(rs.getString(DatabaseConnection.COL_MOTHERCMPNAME));
                studentDetailsModel.setMotherEmail(rs.getString(DatabaseConnection.COL_MOTHEREMAIL));
                studentDetailsModel.setMotherLastName(rs.getString(DatabaseConnection.COL_MOTHERLASTNAME));
                studentDetailsModel.setMotherMiddleName(rs.getString(DatabaseConnection.COL_MOTHERMIDDLENAME));
                studentDetailsModel.setMotherMobile(rs.getString(DatabaseConnection.COL_MOTHERMOBILE));
                studentDetailsModel.setMotherQualification(rs.getString(DatabaseConnection.COL_MOTHERQUALIFICATION));
                studentDetailsModel.setMotherTounge(rs.getString(DatabaseConnection.COL_MOTHERTOUNGE));
                studentDetailsModel.setMotherprofession(rs.getString(DatabaseConnection.COL_MOTHERPROFESSION));
                studentDetailsModel.setMothersName(rs.getString(DatabaseConnection.COL_MOTHERSNAME));
//                model.setMsg(rs.getString(DatabaseConnection.COL_FEEDBACK));

//                while (rsNat.next()) {
//                    if (rs.getInt("Nationality") == rsNat.getInt("TypeId")) {
//                        studentDetailsModel.setNationalityName(rsNat.getString("TypeDesc"));
//                    }
//                }

                studentDetailsModel.setNoofAttempts(rs.getString(DatabaseConnection.COL_NOOFATTEMPTS));
                studentDetailsModel.setObtainedMarks(rs.getInt(DatabaseConnection.COL_OBTAINEDMARKS));
                studentDetailsModel.setOtherActivity(rs.getString(DatabaseConnection.COL_OTHERACTIVITY));
//                model.setParentsNo(rs.getString(DatabaseConnection.COL_));
                studentDetailsModel.setPassport(rs.getString(DatabaseConnection.COL_PASSPORT));
//                model.setPercent(rs.getFloat(DatabaseConnection.COL_PERCENTAGE));
                studentDetailsModel.setPercentage(rs.getDouble(DatabaseConnection.COL_PERCENTAGE));
                studentDetailsModel.setPhotoID(rs.getInt(DatabaseConnection.COL_PHOTOID));

                if (studentDetailsModel.getPhotoID() != 0) {
                    Statement stmt4 = conn.createStatement();
                    String query4 = "SELECT Data FROM MStudentPhoto WHERE PhotoID = " + studentDetailsModel.getPhotoID();
                    ResultSet rsPhoto = stmt4.executeQuery(query4);

                    while(rsPhoto.next()) {
                        studentDetailsModel.setPhotoData(rsPhoto.getBytes("Data"));
                    }
                }

                studentDetailsModel.setPlaceofBirth(rs.getString(DatabaseConnection.COL_PLACEOFBIRTH));
                studentDetailsModel.setPolicy(rs.getString(DatabaseConnection.COL_POLICY));
                studentDetailsModel.setPostalCode(rs.getString(DatabaseConnection.COL_POSTALCODE));
//                model.setQuota(rs.getString(DatabaseConnection.COL_Q));
                studentDetailsModel.setQuotaID(rs.getInt(DatabaseConnection.COL_QUOTAID));
                studentDetailsModel.setQuotaName(rs.getString(DatabaseConnection.COL_QUOTANAME));
                studentDetailsModel.setReligion(rs.getString(DatabaseConnection.COL_RELIGION));
                studentDetailsModel.setRemarks(rs.getString(DatabaseConnection.COL_REMARKS));
                studentDetailsModel.setResidentContact(rs.getString(DatabaseConnection.COL_RESIDENTCONTACT));
                studentDetailsModel.setRoad(rs.getString(DatabaseConnection.COL_ROAD));
                studentDetailsModel.setRollNo(rs.getString(DatabaseConnection.COL_ROLLNO));
                studentDetailsModel.setRouteID(rs.getInt(DatabaseConnection.COL_ROUTEID));
                studentDetailsModel.setScholarship(rs.getString(DatabaseConnection.COL_SCHOLARSHIP));
                studentDetailsModel.setSchoolTransport(rs.getString(DatabaseConnection.COL_SCHOOLTRANSPORT));
                studentDetailsModel.setSection(rs.getString(DatabaseConnection.COL_SECTIONNAME));
                studentDetailsModel.setSectionID(rs.getInt(DatabaseConnection.COL_SECTIONID));
                studentDetailsModel.setSemester(rs.getString(DatabaseConnection.COL_SEMESTER));
                studentDetailsModel.setSemesterId(rs.getInt(DatabaseConnection.COL_SEMESTERID));
                studentDetailsModel.setSiblingClassI(rs.getString(DatabaseConnection.COL_SIBLINGCLASSI));
                studentDetailsModel.setSiblingClassII(rs.getString(DatabaseConnection.COL_SIBLINGCLASSII));
                studentDetailsModel.setSiblingDOBI(rs.getString(DatabaseConnection.COL_SIBLINGDOBI));
                studentDetailsModel.setSiblingDOBII(rs.getString(DatabaseConnection.COL_SIBLINGDOBII));
                studentDetailsModel.setSiblingNameI(rs.getString(DatabaseConnection.COL_SIBLINGNAMEI));
                studentDetailsModel.setSiblingNameII(rs.getString(DatabaseConnection.COL_SIBLINGNAMEII));
                studentDetailsModel.setSports(rs.getString(DatabaseConnection.COL_SPORTS));
                studentDetailsModel.setSportsLevel(rs.getString(DatabaseConnection.COL_SPORTSLEVEL));
                studentDetailsModel.setSportsNational(rs.getString(DatabaseConnection.COL_SPORTSNATIONAL));
                studentDetailsModel.setSportsState(rs.getString(DatabaseConnection.COL_SPORTSSTATE));
                studentDetailsModel.setStandardAdmitted(rs.getString(DatabaseConnection.COL_STANDARDADMITTED));
                studentDetailsModel.setStatus(rs.getString(DatabaseConnection.COL_STATUS));
                studentDetailsModel.setStudentID(rs.getInt(DatabaseConnection.COL_STUDENTID));
                studentDetailsModel.setStudentMobile(rs.getString(DatabaseConnection.COL_STUDENTMOBILE));
                studentDetailsModel.setSubCaste(rs.getString(DatabaseConnection.COL_SUBCASTE));
                studentDetailsModel.setTcno(rs.getString(DatabaseConnection.COL_TCNO));
//                model.setTotalClass(rs.getInt(DatabaseConnection.COL_TOTALCLASS));
//                model.setTotalPresents(rs.getInt(DatabaseConnection.COL_TOTALPRESENTS));
                studentDetailsModel.setTransportFeeid(rs.getInt(DatabaseConnection.COL_TRANSPORTFEEID));
                studentDetailsModel.setUid(rs.getString(DatabaseConnection.COL_UID));
                studentDetailsModel.setUniversityID(rs.getInt(DatabaseConnection.COL_UNIVERSITYID));
                studentDetailsModel.setUniversityName(rs.getString(DatabaseConnection.COL_UNIVERSITYNAME));
                studentDetailsModel.setUniversityRoll(rs.getString(DatabaseConnection.COL_UNIVERSITYROLL));
                studentDetailsModel.setWaiver(rs.getString(DatabaseConnection.COL_WAIVER));
                studentDetailsModel.setYearID(rs.getInt(DatabaseConnection.COL_YEARID));
                studentDetailsModel.setYearName(rs.getString(DatabaseConnection.COL_YEARNAME));
                studentDetailsModel.setYearofPassing(rs.getString(DatabaseConnection.COL_YEAROFPASSING));
//                model.setYop(rs.getString(DatabaseConnection.COL_YOP));

                studentDetailsModel.setAdmissionTypeID(rs.getInt(DatabaseConnection.COL_ADMISSIONTYPEID));
                studentDetailsModel.setAdmissionType(rs.getString(DatabaseConnection.COL_ADMISSIONTYPENAME));
            }

        PreparedStatement stmtForFee = conn.prepareStatement("EXEC SPFeeRptBalanceAmt_GD3 ?,?,?");
        stmtForFee.setEscapeProcessing(true);
        stmtForFee.setQueryTimeout(90);
        stmtForFee.setString(1, "" + studentDetailsModel.getStudentID());
        stmtForFee.setString(2, "" + studentDetailsModel.getFeeId());
        stmtForFee.setString(3, "" + studentDetailsModel.getYearID());

        ResultSet rsForFee = stmtForFee.executeQuery();

        ArrayList<StudentDetailsModel.FeeRow> feeRows = new ArrayList<>();

        while (rsForFee.next()) {
            StudentDetailsModel.FeeRow feeRow = new StudentDetailsModel.FeeRow();
            feeRow.setFeeDesc(rsForFee.getString("FeeDescription"));
            float total, paid;
            total = rsForFee.getFloat("total_Amt");
            paid = rsForFee.getFloat("Fpaidamt");
            feeRow.setTotal(total);
            feeRow.setPaid(paid);
            feeRow.setBalance(total - paid);

            feeRows.add(feeRow);
        }


        if(feeRows.size() != 0) {
            studentDetailsModel.setFeeDetails(feeRows);
        }
        else {
            Statement stmtForFeeNull = conn.createStatement();
            rsForFee = stmtForFeeNull.executeQuery("SELECT FeeDescription, total_Amt FROM View_FeeMasterDetails WHERE FeeID = " + studentDetailsModel.getFeeId());

            while (rsForFee.next()) {
                StudentDetailsModel.FeeRow feeRow = new StudentDetailsModel.FeeRow();
                feeRow.setFeeDesc(rsForFee.getString("FeeDescription"));
                float total = rsForFee.getFloat("total_Amt");
                feeRow.setTotal(total);
                feeRow.setPaid(0);
                feeRow.setBalance(total);

                feeRows.add(feeRow);
            }

            studentDetailsModel.setFeeDetails(feeRows);
        }

        Statement stmtForAttendance = conn.createStatement();
        ResultSet rsForAttendance = stmtForAttendance.executeQuery("SELECT StatusID FROM TStudentAttendance WHERE StudentID = " + studentDetailsModel.getStudentID() + " AND YearID = " + studentDetailsModel.getYearID());

        int totalClass = 0, totalPresents = 0;

        while (rsForAttendance.next()) {
            totalClass++;

            if (rsForAttendance.getInt("StatusID") == 1078) {
                totalPresents++;
            }
        }

        studentDetailsModel.setTotalClass(totalClass);
        studentDetailsModel.setTotalPresents(totalPresents);

        Statement stmtForMarks = conn.createStatement();
        ResultSet rsForMarks = stmtForMarks.executeQuery("SELECT ExamName, Subject, FirstName, MiddleName, LastName, passmark, marksObtained, maxmark, percentage, status, ExamName FROM View_MarksReport WHERE StudentID = " + studentDetailsModel.getStudentID() + " AND YearID = " + studentDetailsModel.getYearID());

        ArrayList<StudentDetailsModel.MarksRow> marksRows = new ArrayList<>();
        ArraySet<String> examNames = new ArraySet<>();

        while (rsForMarks.next()) {
            StudentDetailsModel.MarksRow marksRow = new StudentDetailsModel.MarksRow();

            marksRow.setExamName(rsForMarks.getString("ExamName"));
            marksRow.setSubjectName(rsForMarks.getString("Subject"));
            marksRow.setFacultyFirstName(rsForMarks.getString("FirstName"));
            marksRow.setFacultyMiddleName(rsForMarks.getString("MiddleName"));
            marksRow.setFacultyLastName(rsForMarks.getString("LastName"));
            marksRow.setPassingMarks(rsForMarks.getFloat("passmark"));
            marksRow.setObtainedMarks(rsForMarks.getFloat("marksObtained"));
            marksRow.setMaxMarks(rsForMarks.getInt("maxmark"));
            marksRow.setPercentage(rsForMarks.getFloat("percentage"));
            marksRow.setStatus(rsForMarks.getString("status").toUpperCase());
            examNames.add(rsForMarks.getString("ExamName"));

            marksRows.add(marksRow);
        }

        studentDetailsModel.setMarksDetails(marksRows);
        studentDetailsModel.setExamNames(examNames);

    }

    public void setStudent(StudentDetailsModel model) {
        studentDetailsModel = model;
    }

    public StudentDetailsModel getStudent() {
        return studentDetailsModel;
    }
    
    public static String getFullName(StudentDetailsModel m) {
        String fullName = "", first, middle, last;
        
        first = m.getFirstName();
        middle = m.getMiddleName();
        last = m.getLastName();

        if(middle == null && last == null) {
            fullName = first;
        }
        else if(last == null) {
            fullName = first + " " + middle;
        }
        else if(middle == null) {
            fullName = first + " " + last;
        }
        else {
            fullName = first + " " + middle + " " + last;
        }
        
        return fullName;
    }

    public ArrayList<String> getStudentPersonalDetails(StudentDetailsModel m){
        ArrayList<String> personalDetails = new ArrayList<>();

        personalDetails.add("For header");
//        personalDetails.add(m.getYearName());
//        personalDetails.add(m.getUniversityName());
//        personalDetails.add(m.getCourseName());
//        personalDetails.add(m.getBranchName());
//        personalDetails.add(m.getCombination());
//        personalDetails.add(m.getSemester());
        personalDetails.add(m.getSection());
        personalDetails.add(m.getRollNo());
//        personalDetails.add(m.getAdmissionType());
        personalDetails.add(m.getAdmissionNo());
        personalDetails.add(m.getQuotaName());
//        personalDetails.add(m.getFirstName());
//        personalDetails.add(m.getMiddleName());
//        personalDetails.add(m.getLastName());
        personalDetails.add(m.getDob());
        personalDetails.add(m.getPlaceofBirth());
        personalDetails.add(m.getGender());
        personalDetails.add(m.getReligion());
        personalDetails.add(m.getCaste());
        personalDetails.add(m.getSubCaste());
        personalDetails.add(m.getCategory());
        personalDetails.add(m.getNationalityName());
        personalDetails.add(m.getMotherTounge());
        personalDetails.add(m.getMediumofInstruction());
        personalDetails.add(m.getUid());
        personalDetails.add(m.getILanguage());
        personalDetails.add(m.getIILanguage());
        personalDetails.add(m.getIIILanguage());
//        personalDetails.add(m.getExampassedName());
//        personalDetails.add(m.getYearofPassing());
//        personalDetails.add(m.getNoofAttempts());
//        personalDetails.add(m.getMaxMarks());
//        personalDetails.add("" + m.getObtainedMarks());
//        personalDetails.add("" + m.getPercentage());
//        personalDetails.add(m.getGradeScored());
        personalDetails.add(m.getLastInstitute());
        personalDetails.add(m.getTcno());
        personalDetails.add(m.getDiceNo());
        personalDetails.add(m.getSportsNational());
        personalDetails.add(m.getSportsState());
        personalDetails.add(m.getSportsLevel());
        personalDetails.add(m.getWaiver());
        personalDetails.add(m.getPassport());
        personalDetails.add(m.getScholarship());
        personalDetails.add(m.getRemarks());
        personalDetails.add(m.getOtherActivity());
        personalDetails.add(m.getStudentMobile());
        personalDetails.add(m.getCountry());
        personalDetails.add(m.getCity());
        personalDetails.add(m.getPostalCode());
        personalDetails.add(m.getAddressLine1());
        personalDetails.add(m.getAddressLine2());
        personalDetails.add(m.getStatus());

        return  personalDetails;
    }

    public ArrayList<String> getStudentFamilyDetails(StudentDetailsModel m){
        ArrayList<String> familyDetails = new ArrayList<>();

        familyDetails.add(m.getFathersName());
        familyDetails.add(m.getFatherMiddleName());
        familyDetails.add(m.getFatherLastName());
        familyDetails.add(m.getFatherQualification());
        familyDetails.add(m.getFatherprofession());
        familyDetails.add(m.getFatherAnnualIncome());
        familyDetails.add(m.getFatherCmpName());
        familyDetails.add(m.getFatherCmpAddress());
        familyDetails.add(m.getFatherMobile());
        familyDetails.add(m.getFatherEmail());
        familyDetails.add(m.getMothersName());
        familyDetails.add(m.getMotherMiddleName());
        familyDetails.add(m.getMotherLastName());
        familyDetails.add(m.getMotherQualification());
        familyDetails.add(m.getMotherprofession());
        familyDetails.add(m.getManualIncome());
        familyDetails.add(m.getMotherCmpName());
        familyDetails.add(m.getMotherCmpAddress());
        familyDetails.add(m.getMotherMobile());
        familyDetails.add(m.getMotherEmail());
        familyDetails.add(m.getSiblingNameI());
        familyDetails.add(m.getSiblingClassI());
        familyDetails.add(m.getSiblingDOBI());
        familyDetails.add(m.getSiblingNameII());
        familyDetails.add(m.getSiblingClassII());
        familyDetails.add(m.getSiblingDOBII());

        return familyDetails;
    }

    public ArrayList<String> getStudentHospitalDetails(StudentDetailsModel m) {
        ArrayList<String> hospitalDetails = new ArrayList<>();

        hospitalDetails.add(m.getHospitalname());
        hospitalDetails.add(m.getDoctorname());
        hospitalDetails.add(m.getMedicalInsurance());
        hospitalDetails.add(m.getPolicy());
        hospitalDetails.add(m.getFileno());

        return hospitalDetails;
    }

}
