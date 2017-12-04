package com.nexusinfo.nedusoft.ui.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.nexusinfo.nedusoft.R;
import com.nexusinfo.nedusoft.ui.activities.StudentDetailsActivity;
import com.nexusinfo.nedusoft.ui.adapters.PersonalDetailsAdapter;
import com.nexusinfo.nedusoft.viewmodels.StudentDetailsViewModel;

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
    private PersonalDetailsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.title_personal_fragment);

        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        StudentDetailsViewModel viewModel = ViewModelProviders.of(this).get(StudentDetailsViewModel.class);

        labels = new ArrayList<>();
        contents  = ((StudentDetailsActivity)getActivity()).studentDetailsViewModel.getStudentPersonalDetails(getContext());

        listViewPersonal = view.findViewById(R.id.listView_personal);
        adapter = new PersonalDetailsAdapter(getContext(), labels, contents);

        Collections.addAll(labels, getResources().getStringArray(R.array.personal));

        listViewPersonal.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        return view;
    }
}
