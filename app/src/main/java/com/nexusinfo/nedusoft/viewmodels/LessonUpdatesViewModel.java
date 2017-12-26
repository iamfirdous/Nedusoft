package com.nexusinfo.nedusoft.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import com.nexusinfo.nedusoft.connection.DatabaseConnection;
import com.nexusinfo.nedusoft.models.LessonUpdatesModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by firdous on 12/22/2017.
 */

public class LessonUpdatesViewModel extends ViewModel {

    private LessonUpdatesModel model;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    public void setLessonUpdates(Context context, int sectionId, Date fromDate, Date toDate) throws Exception{

        model = new LessonUpdatesModel();

        DatabaseConnection databaseConnection = new DatabaseConnection(context);
        Connection conn = databaseConnection.getConnection();

        String from = sdf.format(fromDate), to = sdf.format(toDate);

//        Log.e("From and To", from + " and " + to);

        String query = "SELECT * FROM View_LessonUpdate WHERE SectionID = " + sectionId + " AND CAST(LessonDate as DATE) BETWEEN '" + from + "' AND '" + to + "'";
        Log.e("Query", query);

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        ArrayList<LessonUpdatesModel.Lesson> lessons = new ArrayList<>();

        while (rs.next()) {
            LessonUpdatesModel.Lesson lesson = new LessonUpdatesModel.Lesson();

            lesson.setTopicId(rs.getInt("TopicId"));
            lesson.setDate(rs.getDate("LessonDate"));
            lesson.setFacultyName(getFullName(rs.getString("FirstName"), rs.getString("MiddleName"), rs.getString("LastName")));
            lesson.setSubject(rs.getString("Subject"));
            lesson.setTopic(rs.getString("Topic"));
            lesson.setNotes(rs.getString("Notes"));
            lesson.setFileName(rs.getString("Extension"));
            lesson.setFileAvailable(rs.getString("Extension") != null);

            lessons.add(lesson);
        }

        model.setLessons(lessons);
    }

    public void setLessonUpdates(LessonUpdatesModel model) {
        this.model = model;
    }

    public LessonUpdatesModel getLessonUpdates() {
        return model;
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
}
