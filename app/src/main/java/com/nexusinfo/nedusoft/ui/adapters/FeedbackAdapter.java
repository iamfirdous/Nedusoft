package com.nexusinfo.nedusoft.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nexusinfo.nedusoft.R;
import com.nexusinfo.nedusoft.models.FeedbackModel;

import java.util.ArrayList;

/**
 * Created by firdous on 12/26/2017.
 */

public class FeedbackAdapter extends ArrayAdapter<FeedbackModel> {

    private LayoutInflater inflater;
    private ArrayList<FeedbackModel> list;

    public FeedbackAdapter(@NonNull Context context, ArrayList<FeedbackModel> list) {
        super(context, R.layout.listitem_feedback, list);

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder = null;

        if(convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.listitem_feedback, parent, false);

            viewHolder.tvTitle = convertView.findViewById(R.id.textView_feedback_title);
            viewHolder.tvDesc = convertView.findViewById(R.id.textView_feedback_description);
            viewHolder.ivFeedback = convertView.findViewById(R.id.imageView_feedback);

            convertView.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) convertView.getTag();

        FeedbackModel model = list.get(position);

        viewHolder.tvTitle.setText(model.getTitle());
        viewHolder.tvDesc.setText(model.getDesc());
        viewHolder.ivFeedback.setImageResource(model.getImage());

        return convertView;
    }

    class ViewHolder {
        public TextView tvTitle, tvDesc;
        public ImageView ivFeedback;
    }
}
