package com.nexusinfo.nedusoft.ui.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import com.nexusinfo.nedusoft.R;

import java.util.Calendar;

/**
 * Created by firdous on 12/21/2017.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), R.style.NedusoftTheme_DatePicker, this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
//        TextView tv1= (TextView) getActivity().findViewById(R.id.textview1);
//        tv1.setText("Year: "+view.getYear()+" Month: "+view.getMonth()+" Day: "+view.getDayOfMonth());
        EditText et = getActivity().findViewById(R.id.editText_from_date);
        et.setText(view.getYear()+"/"+view.getMonth()+"/"+view.getDayOfMonth());
    }
}
