package com.nexusinfo.nedusoft.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nexusinfo.nedusoft.R;
import com.nexusinfo.nedusoft.ui.activities.StudentDetailsActivity;

import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttendanceFragment extends Fragment {


    public AttendanceFragment() {
        // Required empty public constructor
    }

    private TextView tvTaken, tvAttended, tvPercentage;
    private DecimalFormat df = new DecimalFormat("###.##");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.title_attendance_fragment);

        View view = inflater.inflate(R.layout.fragment_attendance, container, false);

        int[] a = StudentDetailsActivity.getStudentAttendance();
        int taken = a[0], attended = a[1];
        float p = ((float)attended/(float)taken)*100;

        tvTaken = view.findViewById(R.id.textView_class_taken_content);
        tvAttended = view.findViewById(R.id.textView_class_attended_content);
        tvPercentage = view.findViewById(R.id.textView_attendance_percentage_content);

        tvTaken.setText("" + taken);
        tvAttended.setText("" + attended);
        tvPercentage.setText("" + df.format(p) + "%");

        return view;
    }

}
