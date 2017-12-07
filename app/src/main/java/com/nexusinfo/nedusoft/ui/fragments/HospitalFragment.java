package com.nexusinfo.nedusoft.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.nexusinfo.nedusoft.R;
import com.nexusinfo.nedusoft.ui.activities.StudentDetailsActivity;
import com.nexusinfo.nedusoft.ui.adapters.HospitalAdapter;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 */
public class HospitalFragment extends Fragment {


    public HospitalFragment() {
        // Required empty public constructor
    }

    private View view;
    private ListView listViewHospital;

    private ArrayList<String> labels, contents;
    private HospitalAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.title_hospital_fragment);
        view = inflater.inflate(R.layout.fragment_hospital, container, false);
        labels = new ArrayList<>();
        contents  = StudentDetailsActivity.getStudentHospitalDetails();

        listViewHospital = view.findViewById(R.id.listView_hospital);
        adapter = new HospitalAdapter(getContext(), labels, contents);

        Collections.addAll(labels, getResources().getStringArray(R.array.labels_hospital));

        listViewHospital.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }

}
