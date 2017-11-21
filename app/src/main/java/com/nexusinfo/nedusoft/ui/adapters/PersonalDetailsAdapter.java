package com.nexusinfo.nedusoft.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nexusinfo.nedusoft.R;

import java.util.List;

/**
 * Created by lukhman on 11/21/2017.
 */

public class PersonalDetailsAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> labels, contents;

    public PersonalDetailsAdapter(@NonNull Context context, List<String> labels, List<String> contents) {
        super(context, R.layout.listitem_personal, labels);

        this.context = context;
        this.labels = labels;
        this.contents = contents;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listitem_personal, parent, false);
        }

        TextView tvLabel, tvContent;

        tvLabel = convertView.findViewById(R.id.textView_label);
        tvContent = convertView.findViewById(R.id.textView_content);

        tvLabel.setText(labels.get(position));

        return convertView;
    }
}
