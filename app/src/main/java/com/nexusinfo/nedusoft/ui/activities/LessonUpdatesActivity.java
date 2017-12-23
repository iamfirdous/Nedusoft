package com.nexusinfo.nedusoft.ui.activities;

import android.app.DialogFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.nexusinfo.nedusoft.R;
import com.nexusinfo.nedusoft.models.LessonUpdatesModel;
import com.nexusinfo.nedusoft.ui.adapters.LessonUpdatesAdapter;
import com.nexusinfo.nedusoft.ui.fragments.DatePickerFragment;
import com.nexusinfo.nedusoft.viewmodels.LessonUpdatesViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LessonUpdatesActivity extends AppCompatActivity {

    private EditText etFromDate, etToDate;
    private Button buttonFetch;
    private ListView listViewlessons;
    private LinearLayout loading, noLessons;

    private DialogFragment newFragment = new DatePickerFragment();

    private static final String FROM_DATE = "FromDate", TO_DATE = "ToDate";
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    private String from, to;
    private int sectionId;

    private LessonUpdatesViewModel viewModel;
    private LessonUpdatesModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_updates);

        viewModel = ViewModelProviders.of(this).get(LessonUpdatesViewModel.class);

        sectionId = getIntent().getExtras().getInt("SectionID");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        etFromDate = findViewById(R.id.editText_from_date);
        etToDate = findViewById(R.id.editText_to_date);
        buttonFetch = findViewById(R.id.button_fetch_lesson_updates);
        listViewlessons = findViewById(R.id.listView_lesson_updates);
        loading = findViewById(R.id.linearLayout_lessonUpdatesProgress);
        noLessons = findViewById(R.id.linearLayout_noLessonUpdates);

        etFromDate.setInputType(InputType.TYPE_NULL);
        etToDate.setInputType(InputType.TYPE_NULL);

        String today = sdf.format(new Date());
        etFromDate.setText(today);
        etToDate.setText(today);

        etFromDate.setOnClickListener(view -> {
            newFragment.show(getFragmentManager(), FROM_DATE);
        });

        etToDate.setOnClickListener(view -> {
            newFragment.show(getFragmentManager(), TO_DATE);
        });

        FetchLessons task = new FetchLessons();
        task.execute();

        buttonFetch.setOnClickListener(view -> {
            FetchLessons taskFromButton = new FetchLessons();
            taskFromButton.execute();
        });
    }

    class FetchLessons extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            listViewlessons.setVisibility(View.GONE);
            noLessons.setVisibility(View.GONE);
            loading.setVisibility(View.VISIBLE);

            from = etFromDate.getText().toString().trim();
            to = etToDate.getText().toString().trim();
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                viewModel.setLessonUpdates(LessonUpdatesActivity.this, sectionId, parseDate(from), parseDate(to));
            }
            catch (Exception e) {
                Log.e("Error", e.toString());
                publishProgress("Exception");
                cancel(true);
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            if(values[0].equals("Exception")){
                loading.setVisibility(View.GONE);
                listViewlessons.setVisibility(View.GONE);
                noLessons.setVisibility(View.GONE);
                Toast.makeText(LessonUpdatesActivity.this, "Some error occurred, Check your internet connection.", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            model = viewModel.getLessonUpdates();

            if (model != null) {
                ArrayList<LessonUpdatesModel.Lesson> lessons = model.getLessons();

                if (lessons.size() == 0) {
                    loading.setVisibility(View.GONE);
                    listViewlessons.setVisibility(View.GONE);
                    noLessons.setVisibility(View.VISIBLE);
                }
                else {
                    loading.setVisibility(View.GONE);
                    noLessons.setVisibility(View.GONE);
                    listViewlessons.setVisibility(View.VISIBLE);

                    LessonUpdatesAdapter adapter = new LessonUpdatesAdapter(LessonUpdatesActivity.this, lessons);
                    listViewlessons.setAdapter(adapter);
                }
            }
        }
    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("dd-MM-yyyy").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
