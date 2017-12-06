package com.nexusinfo.nedusoft.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.nexusinfo.nedusoft.R;
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.title_family_fragment);
        View view = inflater.inflate(R.layout.fragment_family, container, false);

        ListView listView = view.findViewById(R.id.listView_family);
        ArrayList<String> labels = new ArrayList<>();
        Collections.addAll(labels, getResources().getStringArray(R.array.labels_family));

        FamilyAdapter adapter = new FamilyAdapter(getContext(), labels, null);

        listView.setAdapter(adapter);

        return view;
    }

}
