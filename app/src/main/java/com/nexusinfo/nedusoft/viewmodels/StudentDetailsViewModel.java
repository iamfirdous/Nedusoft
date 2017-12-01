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
            String userID = new LocalDBHelper(context).getUser().getUserID();
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
/*TODO:                studentDetailsModel.setFatherCmpAddress();
                studentDetailsModel.setFatherCmpName();
                studentDetailsModel.setFatherCmpNo();
                studentDetailsModel.setFatherEmail();
                studentDetailsModel.setFatherId();
                studentDetailsModel.setFatherLastName();
                studentDetailsModel.setFatherMiddleName();
                studentDetailsModel.setFatherMobile();
                studentDetailsModel.setFatherQualification();
                studentDetailsModel.setFatherprofession();
                studentDetailsModel.setFathersName();
                studentDetailsModel.setFeeId();
                studentDetailsModel.setFileno();
                studentDetailsModel.setFirstName();
                studentDetailsModel.setGender();
                studentDetailsModel.setGradeScored();
                studentDetailsModel.setHospitalDetailsId();
                studentDetailsModel.setHospitalname();
                studentDetailsModel.setHostelFeeid();
                studentDetailsModel.setILanguage();
                studentDetailsModel.setIiLanguage();
                studentDetailsModel.setIiiLanguage();
                studentDetailsModel.setInCase();
                studentDetailsModel.setIncome();
                studentDetailsModel.setLanguageID_I();
                studentDetailsModel.setLanguageID_II();
                studentDetailsModel.setLanguageID_III();
                studentDetailsModel.setLastInstitute();
                studentDetailsModel.setLastName();
                studentDetailsModel.setLastSyllabus();
                studentDetailsModel.setLastYear();
                studentDetailsModel.setManualIncome();
                studentDetailsModel.setMaxMarks();
                studentDetailsModel.setMedicalInsurance();
                studentDetailsModel.setMediumofInst();
                studentDetailsModel.setMediumofInstruction();
                studentDetailsModel.setMiddleName();
                studentDetailsModel.setMotherCmpAddress();
                studentDetailsModel.setMotherCmpName();
                studentDetailsModel.setMotherEmail();
                studentDetailsModel.setMotherLastName();
                studentDetailsModel.setMotherMiddleName();
                studentDetailsModel.setMotherMobile();
                studentDetailsModel.setMotherQualification();
                studentDetailsModel.setMotherTounge();
                studentDetailsModel.setMotherprofession();
                studentDetailsModel.setMothersName();
                studentDetailsModel.setMsg();
                studentDetailsModel.setNationality();
                studentDetailsModel.setNationalityName();
                studentDetailsModel.setNoofAttempts();
                studentDetailsModel.setObtainedMarks();
                studentDetailsModel.setOtherActivity();
                studentDetailsModel.setParentsNo();
                studentDetailsModel.setPassport();
                studentDetailsModel.setPercent();
                studentDetailsModel.setPercentage();
                studentDetailsModel.setPhotoID();
                studentDetailsModel.setPlaceofBirth();
                studentDetailsModel.setPolicy();
                studentDetailsModel.setPostalCode();
                studentDetailsModel.setQuota();
                studentDetailsModel.setQuotaID();
                studentDetailsModel.setQuotaName();
                studentDetailsModel.setReligion();
                studentDetailsModel.setRemarks();
                studentDetailsModel.setResidentContact();
                studentDetailsModel.setRoad();
                studentDetailsModel.setRollNo();
                studentDetailsModel.setRouteID();
                studentDetailsModel.setScholarship();
                studentDetailsModel.setSchoolTransport();
                studentDetailsModel.setSection();
                studentDetailsModel.setSectionID();
                studentDetailsModel.setSemester();
                studentDetailsModel.setSemesterId();
                studentDetailsModel.setSiblingClassI();
                studentDetailsModel.setSiblingClassII();
                studentDetailsModel.setSiblingDOBI();
                studentDetailsModel.setSiblingDOBII();
                studentDetailsModel.setSiblingNameI();
                studentDetailsModel.setSiblingNameII();
                studentDetailsModel.setSports();
                studentDetailsModel.setSportsLevel();
                studentDetailsModel.setSportsNational();
                studentDetailsModel.setSportsState();
                studentDetailsModel.setStandardAdmitted();
                studentDetailsModel.setStatus();
                studentDetailsModel.setStudentID();
                studentDetailsModel.setStudentMobile();
                studentDetailsModel.setSubCaste();
                studentDetailsModel.setTcno();
                studentDetailsModel.setTotalClass();
                studentDetailsModel.setTotalPresents();
                studentDetailsModel.setTransportFeeid();
                studentDetailsModel.setUid();
                studentDetailsModel.setUniversityID();
                studentDetailsModel.setUniversityName();
                studentDetailsModel.setUniversityRoll();
                studentDetailsModel.setWaiver();
                studentDetailsModel.setYearID();
                studentDetailsModel.setYearName();
                studentDetailsModel.setYearofPassing();
                studentDetailsModel.setYop();           ****/
            }
        }
        catch (Exception e){
            Log.e("Exception", e.toString());
        }


        return studentDetailsModel;
    }

}
