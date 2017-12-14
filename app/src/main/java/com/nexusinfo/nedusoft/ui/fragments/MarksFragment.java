package com.nexusinfo.nedusoft.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.nexusinfo.nedusoft.R;
import com.nexusinfo.nedusoft.models.StudentDetailsModel;
import com.nexusinfo.nedusoft.ui.activities.StudentDetailsActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MarksFragment extends Fragment {


    public MarksFragment() {
        // Required empty public constructor
    }

    private View view;

    private Spinner spinner;
    private TextView tvSubject, tvMaxMarks, tvObtainedMarks, tvPercentage, tvStatus;
    private TableLayout tl;
    private TableRow row;

    private ArrayList<String> examNamesList;
    private ArrayList<StudentDetailsModel.MarksRow> marksRows;

    private DecimalFormat df = new DecimalFormat("###.##");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.title_marks_fragment);
        view = inflater.inflate(R.layout.fragment_marks, container, false);

        tl = view.findViewById(R.id.tableLayout_marks_details);

        spinner = view.findViewById(R.id.spinner_exam_name);

        Object[] o = StudentDetailsActivity.getStudentMarksDetails();

        marksRows = (ArrayList<StudentDetailsModel.MarksRow>) o[1];

        ArraySet<String> examNames = (ArraySet<String>) o[0];
        examNamesList = new ArrayList<>();
        examNamesList.add("Select exam");
        examNamesList.addAll(examNames);

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, examNamesList);
        spinner.setAdapter(listAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view1, int i, long l) {

                while (tl.getChildCount() > 1)
                    tl.removeView(tl.getChildAt(tl.getChildCount() - 1));

                for(StudentDetailsModel.MarksRow marksRow: marksRows) {
                    if(marksRow.getExamName().equals(examNamesList.get(i))) {
                        tl.setVisibility(View.VISIBLE);

                        row = (TableRow) inflater.inflate(R.layout.rowitem_marks_table, container, false);
                        tvSubject = row.findViewById(R.id.textView_marks_subject);
                        tvMaxMarks = row.findViewById(R.id.textView_max_marks);
                        tvObtainedMarks = row.findViewById(R.id.textView_obtained_marks);
                        tvPercentage = row.findViewById(R.id.textView_marks_percentage);
                        tvStatus = row.findViewById(R.id.textView_marks_status);

                        tvSubject.setText("" + marksRow.getSubjectName());
                        tvMaxMarks.setText("" + marksRow.getMaxMarks());
                        tvObtainedMarks.setText("" + df.format(marksRow.getObtainedMarks()));
                        tvPercentage.setText("" + df.format(marksRow.getPercentage()));
                        tvStatus.setText("" + marksRow.getStatus());

                        tl.addView(row);
                    }
                    else
                        tl.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

}
