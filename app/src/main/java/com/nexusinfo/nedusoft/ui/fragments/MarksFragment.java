package com.nexusinfo.nedusoft.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.nexusinfo.nedusoft.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 */
public class MarksFragment extends Fragment {


    public MarksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.title_marks_fragment);
        View view = inflater.inflate(R.layout.fragment_marks, container, false);

        Spinner spinner = view.findViewById(R.id.spinner);
        ArrayList<String> list = new ArrayList<>(); list.add("Select exam".toUpperCase());
        Collections.addAll(list, getResources().getStringArray(R.array.labels_personal));
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, list);
//        listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(listAdapter);

        return view;
    }

}
