package com.nexusinfo.nedusoft.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nexusinfo.nedusoft.R;
import com.nexusinfo.nedusoft.ui.activities.StudentDetailsActivity;
import com.nexusinfo.nedusoft.ui.adapters.PersonalDetailsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalFragment extends Fragment {


    public PersonalFragment() {
        // Required empty public constructor
    }

    private List<String> list;
    private ListView listViewPersonal;
    private PersonalDetailsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.title_personal_fragment);

        View view = inflater.inflate(R.layout.fragment_personal, container, false);

        list = new ArrayList<>();
        listViewPersonal = view.findViewById(R.id.listView_personal);
        adapter = new PersonalDetailsAdapter(getContext(), list, null);

        for (String listItem : getResources().getStringArray(R.array.personal)){
            list.add(listItem);
        }

        listViewPersonal.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }

}
