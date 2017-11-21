package com.nexusinfo.nedusoft.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nexusinfo.nedusoft.R;
import com.nexusinfo.nedusoft.ui.activities.StudentDetailsActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalFragment extends Fragment {


    public PersonalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.title_personal_fragment);

        View view = inflater.inflate(R.layout.fragment_personal, container, false);

        ListView listView = view.findViewById(R.id.listView_personal);
        String[] list = getResources().getStringArray(R.array.personal);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.listitem_personal, R.id.textView_label, list);
        listView.setAdapter(adapter);

        return view;
    }

}
