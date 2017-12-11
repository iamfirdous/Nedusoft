package com.nexusinfo.nedusoft.ui.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nexusinfo.nedusoft.LocalDBHelper;
import com.nexusinfo.nedusoft.MainActivity;
import com.nexusinfo.nedusoft.R;
import com.nexusinfo.nedusoft.models.StudentDetailsModel;
import com.nexusinfo.nedusoft.ui.fragments.AttendanceFragment;
import com.nexusinfo.nedusoft.ui.fragments.DocumentFragment;
import com.nexusinfo.nedusoft.ui.fragments.FamilyFragment;
import com.nexusinfo.nedusoft.ui.fragments.FeeDetailsFragment;
import com.nexusinfo.nedusoft.ui.fragments.HospitalFragment;
import com.nexusinfo.nedusoft.ui.fragments.MarksFragment;
import com.nexusinfo.nedusoft.ui.fragments.PersonalFragment;
import com.nexusinfo.nedusoft.viewmodels.StudentDetailsViewModel;

import java.util.ArrayList;
import java.util.List;

public class StudentDetailsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager mManager;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigationView;

    private View header;
    private TextView tvStudentName, tvRollNo;

    public static StudentDetailsViewModel viewModel;
    public static StudentDetailsModel model;
    public static ArrayList<String> studentPersonalDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        viewModel =  ViewModelProviders.of(this).get(StudentDetailsViewModel.class);

        FetchData task = new FetchData();
        task.execute();

        if(task.isCancelled()){
            Toast.makeText(this, "Some error occurred, Check your internet connection.", Toast.LENGTH_LONG).show();
            //TODO: Try without finish()
            finish();
            return;
        }

    }

    public void initializeUI () {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mManager = getSupportFragmentManager();
        mDrawerLayout = findViewById(R.id.drawer_layout);

        mManager.beginTransaction().replace(R.id.content_main, new PersonalFragment()).commit();

        if (!mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }

        mToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        studentPersonalDetails = getStudentPersonalDetails();

        header = mNavigationView.getHeaderView(0);
        tvStudentName = header.findViewById(R.id.textView_student_name_drawer);
        tvRollNo = header.findViewById(R.id.textView_student_roll_no_drawer);

        tvStudentName.setText(getStudentFullName(studentPersonalDetails));
        tvRollNo.setText(studentPersonalDetails.get(8));
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up buttonSubmit, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_logout:
                LocalDBHelper.getInstance(this).deleteData();
                Intent logout = new Intent(this, MainActivity.class);
                startActivity(logout);
                finish();
                break;

            case R.id.action_settings:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.nav_personal:
                mManager.beginTransaction().replace(R.id.content_main, new PersonalFragment()).commit();
                break;
            case R.id.nav_family:
                mManager.beginTransaction().replace(R.id.content_main, new FamilyFragment()).commit();
                break;
            case R.id.nav_hospital:
                mManager.beginTransaction().replace(R.id.content_main, new HospitalFragment()).commit();
                break;
            case R.id.nav_fee_master:
                mManager.beginTransaction().replace(R.id.content_main, new FeeDetailsFragment()).commit();
                break;
            case R.id.nav_attendance:
                mManager.beginTransaction().replace(R.id.content_main, new AttendanceFragment()).commit();
                break;
            case R.id.nav_document:
                mManager.beginTransaction().replace(R.id.content_main, new DocumentFragment()).commit();
                break;
            case R.id.nav_marks:
                mManager.beginTransaction().replace(R.id.content_main, new MarksFragment()).commit();
                break;
//            case R.id.nav_share:
//
//                break;
//            case R.id.nav_send:
//
//                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class FetchData extends AsyncTask<String, String, String> {

        LinearLayout layout = findViewById(R.id.linlaHeaderProgress);

        @Override
        protected void onPreExecute() {
            layout.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                if(model == null)
                    viewModel.setStudent(StudentDetailsActivity.this);
            }
            catch (Exception e){
                Log.e("Exception", e.toString());
                publishProgress("Exception");
                cancel(true);
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            if(values[0].equals("Exception")){
                layout.setVisibility(View.GONE);
                Toast.makeText(StudentDetailsActivity.this, "Some error occurred, Check your internet connection.", Toast.LENGTH_LONG).show();
                //TODO: Try without finish()
                finish();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            model = viewModel.getStudent();
            layout.setVisibility(View.GONE);
            initializeUI();
        }
    }

    public static ArrayList<String> getStudentPersonalDetails() {
        return viewModel.getStudentPersonalDetails(model);
    }

    public static ArrayList<String> getStudentFamilyDetails() {
        return viewModel.getStudentFamilyDetails(model);
    }

    public static ArrayList<String> getStudentHospitalDetails() {
        return viewModel.getStudentHospitalDetails(model);
    }

    public static ArrayList<StudentDetailsModel.Row> getStudentFeeDetails() {
        return model.getFeeDetails();
    }

    public static int[] getStudentAttendance() {
        int[] a = {model.getTotalClass(), model.getTotalPresents()};
        return a;
    }

    public static String getStudentFullName(List<String> contents) {
        String name;

        if(contents.get(13) == null && contents.get(14) == null) {
            name = contents.get(12);
        }
        else if(contents.get(14) == null) {
            name = contents.get(12) + " " + contents.get(13);
        }
        else if(contents.get(13) == null) {
            name = contents.get(12) + " " + contents.get(14);
        }
        else {
            name = contents.get(12) + " " + contents.get(13) + " " + contents.get(14);
        }

        return name;
    }
}
