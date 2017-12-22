package com.nexusinfo.nedusoft.ui.activities;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.widget.EditText;
import android.widget.ListView;

import com.nexusinfo.nedusoft.R;
import com.nexusinfo.nedusoft.ui.fragments.DatePickerFragment;

public class LessonUpdatesActivity extends AppCompatActivity {

    private EditText etFromDate, etToDate;
    private ListView listViewlessons;

    private DialogFragment newFragment = new DatePickerFragment();

    private static final String FROM_DATE = "FromDate", TO_DATE = "ToDate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_updates);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        etFromDate = findViewById(R.id.editText_from_date);
        etToDate = findViewById(R.id.editText_to_date);
        listViewlessons = findViewById(R.id.listView_lesson_updates);

        etFromDate.setInputType(InputType.TYPE_NULL);
        etToDate.setInputType(InputType.TYPE_NULL);

        etFromDate.setOnClickListener(view -> {
            newFragment.show(getFragmentManager(), FROM_DATE);
        });

        etToDate.setOnClickListener(view -> {
            newFragment.show(getFragmentManager(), TO_DATE);
        });
    }
}
