package com.nexusinfo.nedusoft.connection;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import com.nexusinfo.nedusoft.LocalDatabaseHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by firdous on 11/29/2017.
 */

public class DatabaseConnection extends BaseConnection {

    //Tables
    public static final String TABLE_MSTUDENT = "MStudent";
    public static final String VIEW_STUDENT_DETAILS_FOR_REPORT = "View_StudentDetailsForReport";
    public static final String TABLE_MMARKS = "MMarks";
    public static final String TABLE_MFEE_BAL_RCPT = "MFeeBalRcpt";
    public static final String TABLE_MQUOATA = "MQuota";

    //Columns for VIEW_STUDENT_DETAILS_FOR_REPORT
    public static final String COL_STUDENTID = "StudentID";
    public static final String COL_ADMISSIONNO = "AdmissionNo";
    public static final String COL_YEARID = "YearID";
    public static final String COL_COMBINATIONID = "CombinationID";
    public static final String COL_APPLICATIONID = "ApplicationID";
    public static final String COL_ROLLNO = "RollNo";
    public static final String COL_COURSEID = "CourseID";
    public static final String COL_FIRSTNAME = "FirstName";
    public static final String COL_MIDDLENAME = "MiddleName";
    public static final String COL_LASTNAME = "LastName";
    public static final String COL_PLACEOFBIRTH = "PlaceofBirth";
    public static final String COL_DOB = "DOB";
    public static final String COL_CASTE = "Caste";
    public static final String COL_SUBCASTE = "SubCaste";
    public static final String COL_ADRESSIDPRESENT = "AdressIdPresent";
    public static final String COL_ADRESSIDPERMANENT = "AdressIdPermanent";
    public static final String COL_MOTHERTOUNGE = "MotherTounge";
    public static final String COL_CATEGORYID = "CategoryID";
    public static final String COL_MEDIUMOFINSTRUCTION = "MediumofInstruction";
    public static final String COL_LANGUAGEID_I = "LanguageID_I";
    public static final String COL_NATIONALITY = "Nationality";
    public static final String COL_RELIGION = "Religion";
    public static final String COL_QUOTAID = "QuotaID";
    public static final String COL_GENDER = "Gender";
    public static final String COL_BRCODE = "brcode";
    public static final String COL_STANDARDADMITTED = "StandardAdmitted";
    public static final String COL_BRANCHID = "BranchId";
    public static final String COL_SEMESTERID = "SemesterId";
    public static final String COL_STATUS = "Status";
    public static final String COL_EXAMPASSED = "ExamPassed";
    public static final String COL_EXTRACURRICULAM = "ExtraCurriculam";
    public static final String COL_LASTINSTITUTE = "LastInstitute";
    public static final String COL_NOOFATTEMPTS = "NoofAttempts";
    public static final String COL_UNIVERSITYROLL = "UniversityRoll";
    public static final String COL_YEAROFPASSING = "YearofPassing";
    public static final String COL_SPORTSNATIONAL = "SportsNational";
    public static final String COL_SPORTSSTATE = "SportsState";
    public static final String COL_INCOME = "Income";
    public static final String COL_STUDENTMOBILE = "StudentMobile";
    public static final String COL_PASSPORT = "Passport";
    public static final String COL_UNIVERSITYID = "UniversityID";
    public static final String COL_HOSTELFEEID = "HostelFeeid";
    public static final String COL_TRANSPORTFEEID = "TransportFeeid";
    public static final String COL_CMPID = "cmpid";
    public static final String COL_LANGUAGEID_II = "LanguageID_II";
    public static final String COL_LANGUAGEID_III = "LanguageID_III";
    public static final String COL_WAIVER = "waiver";
    public static final String COL_SCHOLARSHIP = "Scholarship";
    public static final String COL_SECTIONID = "SectionID";
    public static final String COL_SPORTS = "Sports";
    public static final String COL_OTHERACTIVITY = "OtherActivity";
    public static final String COL_GRADESCORED = "GradeScored";
    public static final String COL_TCNO = "TCNO";
    public static final String COL_UID = "UID";
    public static final String COL_DICENO = "DiceNo";
    public static final String COL_REMARKS = "Remarks";
    public static final String COL_SPORTSLEVEL = "SportsLevel";
    public static final String COL_MAXMARKS = "MaxMarks";
    public static final String COL_OBTAINEDMARKS = "ObtainedMarks";
    public static final String COL_PERCENTAGE = "Percentage";
    public static final String COL_FEEID = "FeeId";
    public static final String COL_SUBSEMESTERID = "SubSemesterID";
    public static final String COL_PS_AFFILIATION = "PS_Affiliation";
    public static final String COL_PS_TYPE = "PS_Type";
    public static final String COL_PS_PINCODE = "PS_Pincode";
    public static final String COL_PS_DISTRICT = "PS_District";
    public static final String COL_PS_TALUK = "PS_Taluk";
    public static final String COL_PS_CITY = "PS_City";
    public static final String COL_PS_ADDRESS = "PS_Address";
    public static final String COL_AGE = "Age";
    public static final String COL_REASONAGEAPPR = "ReasonAgeAppr";
    public static final String COL_URBANORRURAL = "UrbanOrRural";
    public static final String COL_STUDENTCASTENO = "StudentCasteNo";
    public static final String COL_FATHERCASTENO = "FatherCasteNo";
    public static final String COL_MOTHERCASTENO = "MotherCasteNo";
    public static final String COL_MOTHERCASTE = "MotherCaste";
    public static final String COL_FATHERCASTE = "FatherCaste";
    public static final String COL_BPL = "BPL";
    public static final String COL_BPLCARDNO = "BPLCardNo";
    public static final String COL_BHAGIYALAKSHMINO = "BhagiyaLakshmiNo";
    public static final String COL_STUDENTEMAIL = "StudentEmail";
    public static final String COL_TCDATE = "TCDate";
    public static final String COL_ADDRESSID = "AddressId";
    public static final String COL_ADDRESSLINE1 = "AddressLine1";
    public static final String COL_ADDRESSLINE2 = "AddressLine2";
    public static final String COL_CITY = "City";
    public static final String COL_COUNTRYSTATEID = "CountryStateId";
    public static final String COL_POSTALCODE = "PostalCode";
    public static final String COL_ADDRESSTYPEID = "AddressTypeId";
    public static final String COL_DISTRICT = "District";
    public static final String COL_TALUK = "Taluk";
    public static final String COL_LOCALITY = "Locality";
    public static final String COL_MOTHERUID = "MotherUID";
    public static final String COL_FATHERUID = "FatherUID";
    public static final String COL_MOTHERLASTNAME = "MotherLastName";
    public static final String COL_MOTHERMIDDLENAME = "MotherMiddleName";
    public static final String COL_FATHERLASTNAME = "FatherLastName";
    public static final String COL_FATHERMIDDLENAME = "FatherMiddleName";
    public static final String COL_SIBLINGDOBII = "SiblingDOBII";
    public static final String COL_SIBLINGCLASSII = "SiblingClassII";
    public static final String COL_SIBLINGNAMEII = "SiblingNameII";
    public static final String COL_SIBLINGDOBI = "SiblingDOBI";
    public static final String COL_SIBLINGCLASSI = "SiblingClassI";
    public static final String COL_SIBLINGNAMEI = "SiblingNameI";
    public static final String COL_MOTHERCMPADDRESS = "MotherCmpAddress";
    public static final String COL_MOTHEREMAIL = "MotherEmail";
    public static final String COL_FATHEREMAIL = "FatherEmail";
    public static final String COL_MOTHERMOBILE = "MotherMobile";
    public static final String COL_FATHERMOBILE = "FatherMobile";
    public static final String COL_FATHERCMPADDRESS = "FatherCmpAddress";
    public static final String COL_MOTHERCMPNAME = "MotherCmpName";
    public static final String COL_FATHERCMPNAME = "FatherCmpName";
    public static final String COL_MANUALINCOME = "ManualIncome";
    public static final String COL_FATHERANNUALINCOME = "FatherAnnualIncome";
    public static final String COL_MOTHERQUALIFICATION = "MotherQualification";
    public static final String COL_FATHERQUALIFICATION = "FatherQualification";
    public static final String COL_MOTHERPROFESSION = "Motherprofession";
    public static final String COL_FATHERPROFESSION = "Fatherprofession";
    public static final String COL_MOTHERSNAME = "MothersName";
    public static final String COL_FATHERSNAME = "FathersName";
    public static final String COL_FAMILYID = "FamilyId";
    public static final String COL_HOSPITALDETAILSID = "HospitalDetailsID";
    public static final String COL_HOSPITALNAME = "Hospitalname";
    public static final String COL_MEDICALINSURANCE = "MedicalInsurance";
    public static final String COL_POLICY = "Policy";
    public static final String COL_FILENO = "Fileno";
    public static final String COL_DOCTORNAME = "Doctorname";
    public static final String COL_YEARNAME = "YearName";
    public static final String COL_COMBINATION = "Combination";
    public static final String COL_COURSENAME = "CourseName";
    public static final String COL_TYPEDESC = "TypeDesc";
    public static final String COL_LANGUAGE_I = "Language_I";
    public static final String COL_BRANCHNAME = "BranchName";
    public static final String COL_EXAMNAME = "ExamName";
    public static final String COL_UNIVERSITYNAME = "UniversityName";
    public static final String COL_HOSTELEFEE = "HosteleFee";
    public static final String COL_TRANSPORTFEE = "TransportFee";
    public static final String COL_LANGUAGEII = "LanguageII";
    public static final String COL_LANGAUGEIII = "LangaugeIII";
    public static final String COL_SECTIONNAME = "SectionName";
    public static final String COL_FEEAMOUNT = "FeeAmount";
    public static final String COL_SUBSEM = "SubSem";
    public static final String COL_PS_AFFILIATIONNAME = "PS_AffiliationName";
    public static final String COL_PS_TYPENAME = "PS_TypeName";
    public static final String COL_PHOTOID = "PhotoID";
    public static final String COL_DATA = "Data";
    public static final String COL_EXTENSION = "Extension";
    public static final String COL_ADMISSIONTYPEID = "AdmissionTypeID";
    public static final String COL_PASSWORD = "Password";
    public static final String COL_ROUTEID = "RouteID";
    public static final String COL_EMGNO1 = "EmgNo1";
    public static final String COL_EMGNO2 = "EmgNo2";
    public static final String COL_EMGNO3 = "EmgNo3";
    public static final String COL_LASTINST_YEARID = "LastInst_YearId";
    public static final String COL_LASTINST_BRANCHID = "LastInst_BranchId";
    public static final String COL_ROAD = "Road";
    public static final String COL_CROSS = "Cross";
    public static final String COL_RESIDENTCONTACT = "ResidentContact";
    public static final String COL_FATHEROFFICENO = "FatherOfficeNo";
    public static final String COL_CONTACTPERSON = "ContactPerson";
    public static final String COL_SCHOOLTRANSPORT = "SchoolTransport";
    public static final String COL_EXT = "Ext";


    public static final String COL_ADMISSIONDATE = "AdmissionDate";
    //Columns for TABLE_MMARKS
     public static final String COL_MID = "MId";
    public static final String COL_CLASSID = "classId";
    public static final String COL_SUBJECTID = "subjectid";
    public static final String COL_FACULTYID = "facultyId";
    public static final String COL_EXAMID = "examid";
    public static final String COL_ASSESMENT = "Assesment";
    public static final String COL_ISSUEDATE = "IssueDate";
    public static final String COL_STUDENTID_MARKS = "studentid";
    public static final String COL_ACTUALMARKS = "actualmarks";
    public static final String COL_MARKSOBTAINED = "marksObtained";
    public static final String COL_MAXMARK = "maxmark";
    public static final String COL_PASSMARK = "passmark";
    public static final String COL_PERCENTAGE_MARKS = "percentage";
    public static final String COL_STATUS_MARKS = "status";
    public static final String COL_CLASSORIG = "ClassOrig";
    public static final String COL_BRCODE_MARKS = "brcode";
    public static final String COL_CMPID_MARKS = "cmpid";
    public static final String COL_STATS = "stats";

     public static final String COL_YEARID_MARKS = "YearId";
    //Columns for TABLE_MFEE_BAL_RCPT
     public static final String COL_FBSLNO = "FBslno";
    public static final String COL_FSTUDENTID = "FStudentId";
    public static final String COL_FEERCVD = "FeeRcvd";
    public static final String COL_FEEBFINE = "FeeBFine";
    public static final String COL_FEEBBANKCHARGE = "FeeBBankCharge";
    public static final String COL_FEEBCONCESSION = "FeeBConcession";
    public static final String COL_FEEBAL = "FeeBal";
    public static final String COL_FEETYPE = "Feetype";
    public static final String COL_STDFEE_ID = "StdFee_ID";

     public static final String COL_FYEAR_ID = "FYear_id";
    //Columns for TSTUDENT_ATTENDANCE
    public static final String COL_ATTENDANCEID = "AttendanceId";
    public static final String COL_ATTENDANCEDATE = "AttendanceDate";
    public static final String COL_STUDENTID_ATTENDANCE = "StudentId";
    public static final String COL_SECTIONID_ATTENDANCE = "SectionId";
    public static final String COL_SUBJECTID_ATTENDANCE = "SubjectId";
    public static final String COL_FACULTYID_ATTENDANCE = "FacultyId";
    public static final String COL_BRCODE_ATTENDANCE = "brcode";
    public static final String COL_CMPID_ATTENDANCE = "cmpid";
    public static final String COL_YEARID_ATTENDANCE = "YearId";
    public static final String COL_STATS_ATTENDANCE = "Stats";
    public static final String COL_SESSION = "Session";
    public static final String COL_UPDATEDBY = "UpdatedBy";
    public static final String COL_UPDATEDON = "UpdatedOn";
    public static final String COL_STATUSID = "StatusId";
    public static final String COL_REMARKSID = "RemarksId";

    //Other Table Columns
    public static final String COL_QUOTANAME = "QuotaName";
    public static final String COL_SEMESTER = "Semseter";
    public static final String COL_ADMISSIONTYPENAME = "TypeName";


    public DatabaseConnection(Context context) {
        DB = LocalDatabaseHelper.getInstance(context).getUser().getSchoolDBName();
    }

    public DatabaseConnection(String databaseName) {
        DB = databaseName;
    }

    @Override
    public Connection getConnection() {
        Connection conn = null;
        try{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName(DRIVER); //.newInstance();
            conn = DriverManager.getConnection(CONN_STRINGS[0] + IP_ADDRESS + CONN_STRINGS[1] + DB + CONN_STRINGS[2] + DB_USERNAME + CONN_STRINGS[3] + DB_PASSWORD + CONN_STRINGS[4]);
        }
        catch (SQLException e){
            Log.e("Exception", e.toString());
        }
        catch (ClassNotFoundException e){
            Log.e("Exception", e.toString());
        }
        catch (Exception e){
            Log.e("Exception", e.toString());
        }

        return conn;
    }

    @Override
    public String toString() {
        return super.toString() + " DB Name: " + DB;
    }
}
