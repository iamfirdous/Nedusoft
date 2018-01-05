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

import java.util.List;

/**
 * Created by firdous on 12/6/2017.
 */

public class FamilyAdapter extends ArrayAdapter<String> {

    private List<String> labels, contents;
    private LayoutInflater inflater;

    public FamilyAdapter(@NonNull Context context, List<String> labels, List<String> contents) {
        super(context, R.layout.listitem_family, labels);

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.labels = labels;
        this.contents = contents;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder = null;

        if(convertView == null){
            viewHolder = new ViewHolder();

            convertView = inflater.inflate(R.layout.listitem_family, parent, false);
            viewHolder.imageViewHeader = convertView.findViewById(R.id.imageView_family_header);
            viewHolder.tvLabelHeader = convertView.findViewById(R.id.textView_family_header_label);
            viewHolder.tvLabel = convertView.findViewById(R.id.textView_label_family);
            viewHolder.tvContent = convertView.findViewById(R.id.textView_content_family);
            viewHolder.spaceTop = convertView.findViewById(R.id.space_family_top);
            viewHolder.spaceBottom = convertView.findViewById(R.id.space_family_bottom);

            convertView.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.imageViewHeader.setVisibility(View.GONE);
        viewHolder.tvLabelHeader.setVisibility(View.GONE);
        viewHolder.spaceTop.setVisibility(View.GONE);
        viewHolder.spaceBottom.setVisibility(View.GONE);

        viewHolder.tvLabel.setText(labels.get(position));

        if (contents.get(position) != null && !contents.get(position).equals(""))
            viewHolder.tvContent.setText(contents.get(position));
        else
            viewHolder.tvContent.setText("-");

        if(position == 0){
            viewHolder.imageViewHeader.setVisibility(View.VISIBLE);
            viewHolder.tvLabelHeader.setVisibility(View.VISIBLE);

            viewHolder.imageViewHeader.setImageResource(R.drawable.ic_father_face);
            viewHolder.tvLabelHeader.setText("Father's details");

            viewHolder.spaceTop.setVisibility(View.INVISIBLE);
        }

        if(position == 10){
            viewHolder.imageViewHeader.setVisibility(View.VISIBLE);
            viewHolder.tvLabelHeader.setVisibility(View.VISIBLE);

            viewHolder.imageViewHeader.setImageResource(R.drawable.ic_mother_face);
            viewHolder.tvLabelHeader.setText("Mother's details");

            viewHolder.spaceTop.setVisibility(View.INVISIBLE);
        }

        if(position == 20){
            viewHolder.imageViewHeader.setVisibility(View.VISIBLE);
            viewHolder.tvLabelHeader.setVisibility(View.VISIBLE);

            viewHolder.imageViewHeader.setImageResource(R.drawable.ic_siblings_face);
            viewHolder.tvLabelHeader.setText("Siblings");

            viewHolder.spaceTop.setVisibility(View.INVISIBLE);
        }

        if(position == labels.size() - 1)
            viewHolder.spaceBottom.setVisibility(View.INVISIBLE);

        return convertView;
    }

    public static class ViewHolder{
        //Header
        public ImageView imageViewHeader;
        public TextView tvLabelHeader;
        //Other
        public TextView tvLabel, tvContent;
        public Space spaceTop, spaceBottom;
    }
}
