package com.nexusinfo.nedusoft.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.nexusinfo.nedusoft.R;
import com.nexusinfo.nedusoft.ui.activities.StudentDetailsActivity;
import com.nexusinfo.nedusoft.ui.adapters.PersonalAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalFragment extends Fragment {


    public PersonalFragment() {
        // Required empty public constructor
    }

    private List<String> labels, contents;
    private ListView listViewPersonal;
    private PersonalAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle(R.string.title_personal_fragment);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal, container, false);

        labels = new ArrayList<>();
        contents  = StudentDetailsActivity.getStudentPersonalDetails();

        listViewPersonal = view.findViewById(R.id.listView_personal);
        adapter = new PersonalAdapter(getContext(), labels, contents);

        Collections.addAll(labels, getResources().getStringArray(R.array.labels_personal));

        listViewPersonal.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }
}
