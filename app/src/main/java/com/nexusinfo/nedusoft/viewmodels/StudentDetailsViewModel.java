package com.nexusinfo.nedusoft.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import com.nexusinfo.nedusoft.LocalDBHelper;
import com.nexusinfo.nedusoft.connection.DatabaseConnection;
import com.nexusinfo.nedusoft.models.StudentDetailsModel;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by firdous on 12/1/2017.
 */

public class StudentDetailsViewModel extends ViewModel {

    private StudentDetailsModel studentDetailsModel;

    public StudentDetailsModel getStudent(Context context) {

        studentDetailsModel = new StudentDetailsModel();
        Field fields[] = StudentDetailsModel.class.getDeclaredFields();
        StringBuffer buffer = new StringBuffer();

        for(Field f : fields){
            buffer.append("studentDetailsModel.set"+f.getName().replaceFirst(f.getName().substring(0, 1), f.getName().substring(0, 1).toUpperCase())+"();\n");
        }
        System.out.println(buffer.toString());

        try{
            String userID = LocalDBHelper.getInstance(context).getUser().getUserID();
            DatabaseConnection databaseConnection = new DatabaseConnection(context);
            Connection conn = databaseConnection.getConnection();

            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM " + DatabaseConnection.VIEW_STUDENT_DETAILS_FOR_REPORT + " WHERE " + DatabaseConnection.COL_ROLLNO + " = '" + userID + "'";
            Log.e("Query: ", query);
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                studentDetailsModel.setACross(rs.getString(DatabaseConnection.COL_CROSS));
                studentDetailsModel.setAddressId(rs.getInt(DatabaseConnection.COL_ADDRESSID));
                studentDetailsModel.setAddressLine1(rs.getString(DatabaseConnection.COL_ADDRESSLINE1));
                studentDetailsModel.setAddressLine2(rs.getString(DatabaseConnection.COL_ADDRESSLINE2));
                studentDetailsModel.setAddressTypeId(rs.getInt(DatabaseConnection.COL_ADDRESSTYPEID));
                studentDetailsModel.setAdmissionNo(rs.getString(DatabaseConnection.COL_ADMISSIONNO));
                studentDetailsModel.setAdressIdPermanent(rs.getInt(DatabaseConnection.COL_ADRESSIDPERMANENT));
                studentDetailsModel.setAdressIdPresent(rs.getInt(DatabaseConnection.COL_ADRESSIDPRESENT));
//                studentDetailsModel.setAppDate();
                studentDetailsModel.setApplicationID(rs.getInt(DatabaseConnection.COL_APPLICATIONID));
//                studentDetailsModel.setApplicationStatus();
                studentDetailsModel.setBranchId(rs.getInt(DatabaseConnection.COL_BRANCHID));
                studentDetailsModel.setBranchName(rs.getString(DatabaseConnection.COL_BRANCHNAME));
                studentDetailsModel.setBrcode(rs.getString(DatabaseConnection.COL_BRCODE));
                studentDetailsModel.setCaste(rs.getString(DatabaseConnection.COL_CASTE));
                studentDetailsModel.setCategory(rs.getString(DatabaseConnection.COL_TYPEDESC));
                studentDetailsModel.setCategoryID(rs.getString(DatabaseConnection.COL_CATEGORYID));
//                studentDetailsModel.setChildId();
                studentDetailsModel.setCity(rs.getString(DatabaseConnection.COL_CITY));
                studentDetailsModel.setCmpid(rs.getString(DatabaseConnection.COL_CMPID));
                studentDetailsModel.setCombination(rs.getString(DatabaseConnection.COL_COMBINATION));
                studentDetailsModel.setCombinationID(rs.getInt(DatabaseConnection.COL_COMBINATIONID));
//                studentDetailsModel.setCountry();
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
//                studentDetailsModel.setExampassedName();
                studentDetailsModel.setExt(rs.getString(DatabaseConnection.COL_EXT));
                studentDetailsModel.setExtension(rs.getString(DatabaseConnection.COL_EXTENSION));
                studentDetailsModel.setExtraCurriculam(rs.getString(DatabaseConnection.COL_EXTRACURRICULAM));
                studentDetailsModel.setFamilyID(rs.getInt(DatabaseConnection.COL_FAMILYID));
                studentDetailsModel.setFatherAnnualIncome(rs.getString(DatabaseConnection.COL_FATHERANNUALINCOME));
                studentDetailsModel.setFatherCmpAddress(rs.getString(DatabaseConnection.COL_FATHERCMPADDRESS));
                studentDetailsModel.setFatherCmpName(rs.getString(DatabaseConnection.COL_FATHERCMPNAME));
                studentDetailsModel.setFatherCmpNo(rs.getString(DatabaseConnection.COL_FATHEROFFICENO));
                studentDetailsModel.setFatherEmail(rs.getString(DatabaseConnection.COL_FATHEREMAIL));
//                studentDetailsModel.setFatherId();
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
                studentDetailsModel.setILanguage(rs.getString(DatabaseConnection.COL_LANGUAGE_I));
                studentDetailsModel.setIILanguage(rs.getString(DatabaseConnection.COL_LANGUAGEII));
                studentDetailsModel.setIIILanguage(rs.getString(DatabaseConnection.COL_LANGAUGEIII));
//                studentDetailsModel.setInCase();
                studentDetailsModel.setIncome(rs.getString(DatabaseConnection.COL_INCOME));
                studentDetailsModel.setLanguageID_I(rs.getString(DatabaseConnection.COL_LANGUAGEID_I));
                studentDetailsModel.setLanguageID_II(rs.getString(DatabaseConnection.COL_LANGUAGEID_II));
                studentDetailsModel.setLanguageID_III(rs.getString(DatabaseConnection.COL_LANGUAGEID_III));
                studentDetailsModel.setLastInstitute(rs.getString(DatabaseConnection.COL_LASTINSTITUTE));
                studentDetailsModel.setLastName(rs.getString(DatabaseConnection.COL_LASTNAME));
                studentDetailsModel.setLastSyllabus(rs.getInt(DatabaseConnection.COL_LASTINST_BRANCHID));
                studentDetailsModel.setLastYear(rs.getInt(DatabaseConnection.COL_LASTINST_YEARID));
                studentDetailsModel.setManualIncome(rs.getString(DatabaseConnection.COL_MANUALINCOME));
                studentDetailsModel.setMaxMarks(rs.getString(DatabaseConnection.COL_MAXMARKS));
                studentDetailsModel.setMedicalInsurance(rs.getString(DatabaseConnection.COL_MEDICALINSURANCE));
                studentDetailsModel.setMediumofInst(rs.getString(DatabaseConnection.COL_MEDIUMOFINSTRUCTION));
                studentDetailsModel.setMediumofInstruction(rs.getString(DatabaseConnection.COL_MEDIUMOFINSTRUCTION));
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
//TODO                studentDetailsModel.setMsg();
                studentDetailsModel.setNationality(rs.getInt(DatabaseConnection.COL_NATIONALITY));
//TODO                studentDetailsModel.setNationalityName(rs.getString(DatabaseConnection.COL_NATIONALITY));
                studentDetailsModel.setNoofAttempts(rs.getString(DatabaseConnection.COL_NOOFATTEMPTS));
                studentDetailsModel.setObtainedMarks(rs.getInt(DatabaseConnection.COL_OBTAINEDMARKS));
                studentDetailsModel.setOtherActivity(rs.getString(DatabaseConnection.COL_OTHERACTIVITY));
//TODO                studentDetailsModel.setParentsNo(rs.getString(DatabaseConnection.COL_));
                studentDetailsModel.setPassport(rs.getString(DatabaseConnection.COL_PASSPORT));
//TODO                studentDetailsModel.setPercent(rs.getFloat(DatabaseConnection.COL_PERCENTAGE));
                studentDetailsModel.setPercentage(rs.getDouble(DatabaseConnection.COL_PERCENTAGE));
                studentDetailsModel.setPhotoID(rs.getInt(DatabaseConnection.COL_PHOTOID));
                studentDetailsModel.setPlaceofBirth(rs.getString(DatabaseConnection.COL_PLACEOFBIRTH));
                studentDetailsModel.setPolicy(rs.getString(DatabaseConnection.COL_POLICY));
                studentDetailsModel.setPostalCode(rs.getString(DatabaseConnection.COL_POSTALCODE));
//TODO                studentDetailsModel.setQuota(rs.getString(DatabaseConnection.COL_QUOTA));
                studentDetailsModel.setQuotaID(rs.getInt(DatabaseConnection.COL_QUOTAID));
//TODO                studentDetailsModel.setQuotaName(rs.getString(DatabaseConnection.COL_QUOTANAME));
                studentDetailsModel.setReligion(rs.getString(DatabaseConnection.COL_RELIGION));
                studentDetailsModel.setRemarks(rs.getString(DatabaseConnection.COL_REMARKS));
                studentDetailsModel.setResidentContact(rs.getString(DatabaseConnection.COL_RESIDENTCONTACT));
                studentDetailsModel.setRoad(rs.getString(DatabaseConnection.COL_ROAD));
                studentDetailsModel.setRollNo(rs.getString(DatabaseConnection.COL_ROLLNO));
                studentDetailsModel.setRouteID(rs.getInt(DatabaseConnection.COL_ROUTEID));
                studentDetailsModel.setScholarship(rs.getString(DatabaseConnection.COL_SCHOLARSHIP));
                studentDetailsModel.setSchoolTransport(rs.getString(DatabaseConnection.COL_SCHOOLTRANSPORT));
                studentDetailsModel.setSection(rs.getString(DatabaseConnection.COL_SECTIONNAME));  //TODO <----------- Have to ask about this
                studentDetailsModel.setSectionID(rs.getInt(DatabaseConnection.COL_SECTIONID));
//TODO                studentDetailsModel.setSemester(rs.getString(DatabaseConnection.COL_SEMESTER));
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
//TODO                studentDetailsModel.setTotalClass(rs.getInt(DatabaseConnection.COL_TOTALCLASS));
//TODO                studentDetailsModel.setTotalPresents(rs.getInt(DatabaseConnection.COL_TOTALPRESENTS));
                studentDetailsModel.setTransportFeeid(rs.getInt(DatabaseConnection.COL_TRANSPORTFEEID));
                studentDetailsModel.setUid(rs.getString(DatabaseConnection.COL_UID));
                studentDetailsModel.setUniversityID(rs.getInt(DatabaseConnection.COL_UNIVERSITYID));
                studentDetailsModel.setUniversityName(rs.getString(DatabaseConnection.COL_UNIVERSITYNAME));
                studentDetailsModel.setUniversityRoll(rs.getString(DatabaseConnection.COL_UNIVERSITYROLL));
                studentDetailsModel.setWaiver(rs.getString(DatabaseConnection.COL_WAIVER));
                studentDetailsModel.setYearID(rs.getInt(DatabaseConnection.COL_YEARID));
                studentDetailsModel.setYearName(rs.getString(DatabaseConnection.COL_YEARNAME));
                studentDetailsModel.setYearofPassing(rs.getString(DatabaseConnection.COL_YEAROFPASSING));
//TODO                studentDetailsModel.setYop(rs.getString(DatabaseConnection.COL_YOP));
            }
        }
        catch (Exception e){
            Log.e("Exception", e.toString());
        }


        return studentDetailsModel;
    }

    public ArrayList<String> getStudentPersonalDetails(Context context){
        ArrayList<String> personalDetails = new ArrayList<>();
        StudentDetailsModel m = getStudent(context);

        personalDetails.add("For header");
        personalDetails.add(m.getYearName());
        personalDetails.add(m.getUniversityName());
        personalDetails.add(m.getCourseName());
        personalDetails.add(m.getBranchName());    //TODO <----------- Have to ask about this
        personalDetails.add(m.getCombination());   //TODO <----------- Have to ask about this
//TODO        personalDetails.add(m.getClass());
        personalDetails.add(m.getSection());
        personalDetails.add(m.getRollNo());
        personalDetails.add(m.getCategory());     //TODO <----------- Have to ask about this
        personalDetails.add(m.getAdmissionNo());
        personalDetails.add(m.getQuota());
        personalDetails.add(m.getFirstName());
        personalDetails.add(m.getMiddleName());
        personalDetails.add(m.getLastName());
        personalDetails.add(m.getDob());
        personalDetails.add(m.getPlaceofBirth());
        personalDetails.add(m.getGender());
        personalDetails.add(m.getReligion());
        personalDetails.add(m.getCaste());
        personalDetails.add(m.getSubCaste());
        personalDetails.add(m.getNationalityName());
        personalDetails.add(m.getMotherTounge());
        personalDetails.add(m.get);<item>Category</item>
        <item>Medium of\nInstruction</item>
        <item>UID</item>
        <item>Last Institute</item>
        <item>Language - I</item>
        <item>Language - II</item>
        <item>Language - III</item>
        <item>ExamPassed</item>
        <item>Year of\nPassing</item>
        <item>No. of\nAttempts</item>
        <item>Max Marks</item>
        <item>Obtained Marks</item>
        <item>Percentage</item>
        <item>Grade Scored</item>
        <item>TC No.</item>
        <item>Dice No.</item>
        <item>Sports National</item>
        <item>Sports State</item>
        <item>Sports Level</item>
        <item>Waiver</item>
        <item>Passport</item>
        <item>Scholarship</item>
        <item>Remarks</item>
        <item>Other Activity</item>
        <item>Student Mobile</item>
        <item>Country</item>
        <item>City</item>
        <item>Pin Code</item>
        <item>Address Line-1</item>
        <item>Address Line-2</item>
        <item>Status</item>
    }

}
