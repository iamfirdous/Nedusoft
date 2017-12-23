package com.nexusinfo.nedusoft.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.nexusinfo.nedusoft.connection.DatabaseConnection;
import com.nexusinfo.nedusoft.models.LessonUpdatesModel;
import com.nexusinfo.nedusoft.models.StudentDetailsModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by firdous on 12/22/2017.
 */

public class LessonUpdatesViewModel extends ViewModel {

    private LessonUpdatesModel lessonUpdates;
    private Date fromDate, toDate;

    public void setLessonUpdates(Context context, String sectionId, Date fromDate, Date toDate) throws Exception{

        lessonUpdates = new LessonUpdatesModel();

        DatabaseConnection databaseConnection = new DatabaseConnection(context);
        Connection conn = databaseConnection.getConnection();

        String query = "SELECT * FROM View_LessonUpdate WHERE SectionID = " + sectionId;

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        ArrayList<LessonUpdatesModel.Lesson> lessonUpdates = new ArrayList<>();

        while (rs.next()) {
            LessonUpdatesModel.Lesson lesson = new LessonUpdatesModel.Lesson();

            lesson.setDate(rs.getDate("LessonDate"));
            lesson.setFacultyName(getFullName(rs.getString("FirstName"), rs.getString("MiddleName"), rs.getString("LastName")));
            lesson.setSubject(rs.getString("Subject"));
            lesson.setTopic(rs.getString("Topic"));
            lesson.setNotes(rs.getString("Notes"));
            lesson.setFileName(rs.getString("Extension"));
            lesson.setFileAvailable(rs.getBytes("Data") != null);

            lessonUpdates.add(lesson);
        }
    }

    public LessonUpdatesModel getLessonUpdates() {
        return lessonUpdates;
    }

    public static String getFullName(String first, String middle, String last) {
        String fullName = "";

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

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Date getToDate() {
        return toDate;
    }
}
