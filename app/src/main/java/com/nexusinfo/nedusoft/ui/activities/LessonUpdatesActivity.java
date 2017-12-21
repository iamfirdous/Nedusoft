package com.nexusinfo.nedusoft.ui.activities;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.widget.EditText;

import com.nexusinfo.nedusoft.R;
import com.nexusinfo.nedusoft.ui.fragments.DatePickerFragment;

public class LessonUpdatesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_updates);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        EditText et = findViewById(R.id.editText_from_date);

        et.setInputType(InputType.TYPE_NULL);
        et.setOnClickListener(view -> {
            DialogFragment newFragment = new DatePickerFragment();
            newFragment.show(getFragmentManager(), "datePicker");
        });
    }
}
