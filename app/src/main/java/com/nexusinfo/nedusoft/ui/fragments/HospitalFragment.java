package com.nexusinfo.nedusoft.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nexusinfo.nedusoft.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HospitalFragment extends Fragment {


    public HospitalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.title_hospital_fragment);
        return inflater.inflate(R.layout.fragment_hospital, container, false);
    }

}
