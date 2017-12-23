package com.nexusinfo.nedusoft.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nexusinfo.nedusoft.R;
import com.nexusinfo.nedusoft.models.LessonUpdatesModel;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by firdous on 12/23/2017.
 */

public class LessonUpdatesAdapter extends ArrayAdapter<LessonUpdatesModel.Lesson> {

    private List<LessonUpdatesModel.Lesson> lessons;
    private LayoutInflater inflater;

    public LessonUpdatesAdapter(@NonNull Context context, List<LessonUpdatesModel.Lesson> lessons) {
        super(context, R.layout.listitem_lesson_updates, lessons);

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.lessons = lessons;
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

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String date = sdf.format(lesson.getDate());

        viewHolder.tvTopic.setText(lesson.getTopic());
        viewHolder.tvNotes.setText(lesson.getNotes());
        viewHolder.tvSubject.setText(lesson.getSubject());
        viewHolder.tvFaculty.setText(lesson.getFacultyName());
        viewHolder.tvDate.setText(date);

        if(lesson.isFileAvailable()) {
            viewHolder.ivAttachment.setVisibility(View.VISIBLE);

            viewHolder.tvFileName.setText(lesson.getFileName());

            viewHolder.buttonDownload.setOnClickListener(view -> {
                Toast.makeText(getContext(), "Downloading file for " + lesson.getTopic() + ", " + lesson.getSubject(), Toast.LENGTH_LONG).show();
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
}
