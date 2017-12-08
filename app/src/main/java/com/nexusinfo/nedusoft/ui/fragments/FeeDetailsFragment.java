package com.nexusinfo.nedusoft.ui.fragments;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.nexusinfo.nedusoft.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeeDetailsFragment extends Fragment {


    public FeeDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//         Inflate the layout for this fragment
        getActivity().setTitle(R.string.title_fee_details_fragment);
        View view = inflater.inflate(R.layout.fragment_fee_details, container, false);

        TableLayout tableLayout = view.findViewById(R.id.tableLayout_fee_details);

        TableRow row = new TableRow(getContext());
        row.addView(getLabelTextView("Re-admission"));
        row.addView(getContentTextView("12345"));
        row.addView(getContentTextView("67543"));
        row.addView(getContentTextView("5647"));

        TableRow row1 = new TableRow(getContext());
        row1.addView(getLabelTextView("Tuition fee for\n3rd term-\n(Jan Feb Mar Apr)"));
        row1.addView(getContentTextView("12345"));
        row1.addView(getContentTextView("67543"));
        row1.addView(getContentTextView("5647"));

        tableLayout.addView(row, 2);
        tableLayout.addView(row1, 3);

        return view;
    }

    public TextView getLabelTextView(String text){
        TextView textView = new TextView(getContext());

        textView.setPadding(4,4,4,4);
        textView.setTextColor(getResources().getColor(R.color.colorTextBlack));
        textView.setTypeface(null, Typeface.BOLD);
//        textView.setGravity(Gravity.CENTER);
        textView.setText(text);

        return textView;
    }

    public TextView getContentTextView(String text){
        TextView textView = new TextView(getContext());

        textView.setPadding(12,12,12,12);
        textView.setTextColor(Color.parseColor("#444444"));
        textView.setTypeface(null, Typeface.NORMAL);
//        textView.setGravity(Gravity.CENTER);
        textView.setText(text);

        return textView;
    }

}
