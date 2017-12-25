package com.nexusinfo.nedusoft.ui.activities;

import android.Manifest;
import android.app.DialogFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
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

public class LessonUpdatesActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback{

    private EditText etFromDate, etToDate;
    private Button buttonFetch;
    private ListView listViewlessons;
    private LinearLayout loading, noLessons;
    public static View mLayout;

    private Snackbar snackbar;

    private DialogFragment newFragment = new DatePickerFragment();

    private static final String FROM_DATE = "FromDate", TO_DATE = "ToDate";
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    private String from, to;
    private int sectionId;

    private LessonUpdatesViewModel viewModel;
    private LessonUpdatesModel model;

    private static final int PERMISSION_REQUEST_WRITE = 2;

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
        mLayout = findViewById(R.id.relativeLayout_lesson_update);

        snackbar = Snackbar.make(mLayout, "", Snackbar.LENGTH_INDEFINITE);

        request();

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == PERMISSION_REQUEST_WRITE) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                snackbar.setAction("DISMISS", view -> {
                    snackbar.dismiss();
                });
                snackbar.setText("Write permission was granted");
                snackbar.show();
            }
            else {
                snackbar.setAction("DISMISS", view -> {
                    snackbar.dismiss();
                });
                snackbar.setText("Write permission request was denied. You cannot download the attachments.");
                snackbar.show();
            }
        }
    }

    private void request() {
        // BEGIN_INCLUDE(startCamera)
        // Check if the Camera permission has been granted
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            // Permission is already available, start camera preview
            snackbar.setAction("DISMISS", view -> {
                snackbar.dismiss();
            });
            snackbar.setText("Write permission is available. You can download the attachments.");
            snackbar.show();
        } else {
            // Permission is missing and must be requested.
            requestWritePermission();
        }
        // END_INCLUDE(startCamera)
    }

    public void requestWritePermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            snackbar.setAction("GRANT\nPERMISSION", view -> {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_WRITE);
            });
            snackbar.setText("Write permission is required for downloading the attachments");
            snackbar.show();
        }
        else {
            snackbar.setAction("DISMISS", view -> {
                snackbar.dismiss();
            });
            snackbar.setText("Permission is not available. Requesting write permission.");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_WRITE);
        }
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

    public static LessonUpdatesActivity getInstance() {
        return new LessonUpdatesActivity();
    }
}
