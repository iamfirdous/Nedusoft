package com.nexusinfo.nedusoft.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.Space;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nexusinfo.nedusoft.R;
import com.nexusinfo.nedusoft.ui.activities.StudentDetailsActivity;

import java.util.List;

/**
 * Created by firdous on 11/21/2017.
 */

public class PersonalAdapter extends ArrayAdapter<String> {

    private static final int TYPE_PERSONAL_HEADER = 0;
    private static final int TYPE_PERSONAL = 1;

    private List<String> labels, contents;
    private LayoutInflater inflater;

    public PersonalAdapter(@NonNull Context context, List<String> labels, List<String> contents) {
        super(context, R.layout.listitem_personal, labels);

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.labels = labels;
        this.contents = contents;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return TYPE_PERSONAL_HEADER;
        }
        else {
            return TYPE_PERSONAL;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;

        int type = getItemViewType(position);

        if(convertView == null){
            viewHolder = new ViewHolder();
            switch (type){
                case TYPE_PERSONAL_HEADER:
                    convertView = inflater.inflate(R.layout.listitem_personal_header, parent, false);
                    viewHolder.ivPhoto = convertView.findViewById(R.id.imageView_student_photo_personal);
                    viewHolder.tvName = convertView.findViewById(R.id.textView_student_name_personal);
                    viewHolder.tvRollNo = convertView.findViewById(R.id.textView_roll_no_personal);
                    break;

                case TYPE_PERSONAL:
                    convertView = inflater.inflate(R.layout.listitem_personal, parent, false);
                    viewHolder.tvLabel = convertView.findViewById(R.id.textView_label_personal);
                    viewHolder.tvContent = convertView.findViewById(R.id.textView_content_personal);
                    viewHolder.space = convertView.findViewById(R.id.space_personal_bottom);
                    break;
            }
            convertView.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) convertView.getTag();


        switch (type) {
            case TYPE_PERSONAL_HEADER:
                viewHolder.tvName.setText(StudentDetailsActivity.getStudentFullName(contents));
                viewHolder.tvRollNo.setText(contents.get(8));
                break;

            case TYPE_PERSONAL:
                viewHolder.tvLabel.setText(labels.get(position));

                if (contents.get(position) != null && !contents.get(position).equals(""))
                    viewHolder.tvContent.setText(contents.get(position));
                else
                    viewHolder.tvContent.setText("-");

                viewHolder.space.setVisibility(View.GONE);

                if(position == labels.size() - 1)
                    viewHolder.space.setVisibility(View.INVISIBLE);

                break;
        }

        return convertView;
    }

    public static class ViewHolder{
        //Header
        public ImageView ivPhoto;
        public TextView tvName, tvRollNo;
        //Other
        public TextView tvLabel, tvContent;
        public Space space;
    }
}
