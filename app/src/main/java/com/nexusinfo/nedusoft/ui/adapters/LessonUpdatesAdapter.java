package com.nexusinfo.nedusoft.ui.adapters;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nexusinfo.nedusoft.R;
import com.nexusinfo.nedusoft.connection.DatabaseConnection;
import com.nexusinfo.nedusoft.models.LessonUpdatesModel;
import com.nexusinfo.nedusoft.ui.activities.LessonUpdatesActivity;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static com.nexusinfo.nedusoft.utils.Util.dateToString;
import static com.nexusinfo.nedusoft.utils.Util.showCustomToast;

/**
 * Created by firdous on 12/23/2017.
 */

public class LessonUpdatesAdapter extends ArrayAdapter<LessonUpdatesModel.Lesson> {

    private List<LessonUpdatesModel.Lesson> lessons;
    private LayoutInflater inflater;
    private Snackbar snackbar;

    public LessonUpdatesAdapter(@NonNull Context context, List<LessonUpdatesModel.Lesson> lessons) {
        super(context, R.layout.listitem_lesson_updates, lessons);

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.lessons = lessons;
        snackbar = Snackbar.make(LessonUpdatesActivity.mLayout, "", Snackbar.LENGTH_INDEFINITE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder = null;

        if(convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.listitem_lesson_updates, parent, false);

            viewHolder.tvTopic = convertView.findViewById(R.id.textView_topic);
            viewHolder.tvNotes = convertView.findViewById(R.id.textView_notes);
            viewHolder.tvSubject = convertView.findViewById(R.id.textView_subject);
            viewHolder.tvFaculty = convertView.findViewById(R.id.textView_faculty);
            viewHolder.tvDate = convertView.findViewById(R.id.textView_date);
            viewHolder.tvFileName = convertView.findViewById(R.id.textView_filename);
            viewHolder.ivAttachment = convertView.findViewById(R.id.imageView_attachment1);
            viewHolder.buttonDownload = convertView.findViewById(R.id.button_download_lesson_update);

            convertView.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.ivAttachment.setVisibility(View.VISIBLE);

        LessonUpdatesModel.Lesson lesson = lessons.get(position);

        String date = dateToString(lesson.getDate());

        viewHolder.tvTopic.setText(lesson.getTopic());
        viewHolder.tvNotes.setText(lesson.getNotes());
        viewHolder.tvSubject.setText(lesson.getSubject());
        viewHolder.tvFaculty.setText(lesson.getFacultyName());
        viewHolder.tvDate.setText(date);

        if(lesson.isFileAvailable()) {
            viewHolder.ivAttachment.setVisibility(View.VISIBLE);
            viewHolder.buttonDownload.setVisibility(View.VISIBLE);

            viewHolder.tvFileName.setText(lesson.getFileName());

            viewHolder.buttonDownload.setOnClickListener(view -> {
                DowmloadTask task = new DowmloadTask();
                task.execute(lesson);
            });
        }
        else {
            viewHolder.ivAttachment.setVisibility(View.INVISIBLE);
            viewHolder.buttonDownload.setVisibility(View.INVISIBLE);
            viewHolder.tvFileName.setText("No attachment");
        }

        return convertView;
    }

    public static class ViewHolder {

        public TextView tvTopic, tvNotes, tvSubject, tvFaculty, tvDate, tvFileName;
        public ImageView ivAttachment;
        public Button buttonDownload;
    }

    class DowmloadTask extends AsyncTask<LessonUpdatesModel.Lesson, String, String> {

        private static final String PATH = "/storage/emulated/0/Download/";

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(LessonUpdatesModel.Lesson... lessons) {

            Context context = getContext();
            try {

                if(ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                    LessonUpdatesModel.Lesson lesson = lessons[0];

                    publishProgress("Downloading", lesson.getTopic(), lesson.getSubject());

                    DatabaseConnection databaseConnection = new DatabaseConnection(context);
                    Connection conn = databaseConnection.getConnection();

                    String query = "SELECT Data FROM View_LessonUpdate WHERE TopicId = " + lesson.getTopicId();
                    Log.e("QueryForData", query);

                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    byte[] data = null;

                    while (rs.next()) {
                        data = rs.getBytes("Data");
                    }

                    FileOutputStream fos = new FileOutputStream(PATH + lesson.getFileName());
                    fos.write(data);
                    fos.close();

                }
                else {
                    publishProgress("NoPermission");
                    cancel(true);
                }

            }
            catch (Exception e) {
                Log.e("Error", e.toString());
                publishProgress("Exception");
                cancel(true);
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            if(values[0].equals("Downloading"))
                showCustomToast(getContext(), "Downloading file for " + values[1] + ", " + values[2],1);

            if(values[0].equals("NoPermission")) {
                showCustomToast(getContext(), "Write permission isn't available.",1, Gravity.BOTTOM, 0, 230);
                snackbar.setAction("DISMISS", view -> {
                    snackbar.dismiss();
                });
                snackbar.setText("Goto Settings -> Apps -> Nedusoft -> Permissions to enable the Storage permissions");
                snackbar.show();
            }

            if(values[0].equals("Exception")) {
                showCustomToast(getContext(), "Some error occurred while downloading the file.",1);
            }
        }

        @Override
        protected void onPostExecute(String s) {
            showCustomToast(getContext(), "Download completed. Your attachment for the lesson is saved at \"" + PATH + "\"",2);
        }
    }
}
