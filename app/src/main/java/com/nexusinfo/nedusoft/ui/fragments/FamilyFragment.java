package com.nexusinfo.nedusoft.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.nexusinfo.nedusoft.R;
import com.nexusinfo.nedusoft.ui.activities.StudentDetailsActivity;
import com.nexusinfo.nedusoft.ui.adapters.FamilyAdapter;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyFragment extends Fragment {


    public FamilyFragment() {
        // Required empty public constructor
    }

    private View view;
    private ListView listViewFamily;

    private ArrayList<String> labels, contents;
    private FamilyAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.title_family_fragment);
        view = inflater.inflate(R.layout.fragment_family, container, false);
        labels = new ArrayList<>();
        contents  = StudentDetailsActivity.getStudentFamilyDetails();

        listViewFamily = view.findViewById(R.id.listView_family);
        adapter = new FamilyAdapter(getContext(), labels, contents);

        Collections.addAll(labels, getResources().getStringArray(R.array.labels_family));

        listViewFamily.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }

}
