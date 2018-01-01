package com.nexusinfo.nedusoft.ui.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nexusinfo.nedusoft.LocalDatabaseHelper;
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

import static com.nexusinfo.nedusoft.utils.Util.showCustomToast;

public class StudentDetailsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager mManager;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigationView;

    private View header;
    private TextView tvStudentName, tvRollNo;
    private ImageView ivStudentPhoto;

    public static StudentDetailsViewModel viewModel;
    public static StudentDetailsModel model;
    public static ArrayList<String> studentPersonalDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        viewModel = ViewModelProviders.of(this).get(StudentDetailsViewModel.class);

        FetchData task = new FetchData();
        task.execute();

        if(task.isCancelled()){
            showCustomToast(this, "Some error occurred, Check your internet connection.",1);
            //TODO: Try without finish()
            finish();
            return;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.setStudent(model);
    }

    @Override
    protected void onResume() {
        super.onResume();
        model = viewModel.getStudent();
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

        tvStudentName.setText(StudentDetailsViewModel.getFullName(model));
        tvRollNo.setText(studentPersonalDetails.get(2));

        byte[] photoData = model.getPhotoData();

        if(photoData != null) {
            Bitmap bmp = BitmapFactory.decodeByteArray(photoData, 0, photoData.length);
            ivStudentPhoto = header.findViewById(R.id.imageView_student_photo_drawer);
            ivStudentPhoto.setImageBitmap(bmp);
        }

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
                LocalDatabaseHelper.getInstance(this).deleteData();
                Intent logout = new Intent(this, MainActivity.class);
                startActivity(logout);
                finish();
                break;

            case R.id.action_refresh:
                Intent refresh = new Intent(this, MainActivity.class);
                startActivity(refresh);
                finish();
                break;

            case R.id.action_change_password:
                Intent changePassword = new Intent(this, ChangePasswordActivity.class);
                startActivity(changePassword);
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
            case R.id.nav_lesson_updates:
                Intent lessonUpdateIntent = new Intent(StudentDetailsActivity.this, LessonUpdatesActivity.class);
                lessonUpdateIntent.putExtra("SectionID", model.getSectionID());
                startActivity(lessonUpdateIntent);
                break;
            case R.id.nav_feedback:
                Intent feedbackIntent = new Intent(StudentDetailsActivity.this, FeedbackActivity.class);
                startActivity(feedbackIntent);
                break;
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
                if(viewModel.getStudent() == null)
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
                showCustomToast(StudentDetailsActivity.this, "Some error occurred, Check your internet connection.",1);
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

    public static ArrayList<StudentDetailsModel.FeeRow> getStudentFeeDetails() {
        return model.getFeeDetails();
    }

    public static StudentDetailsModel getStudent() {
        return model;
    }

    public static int[] getStudentAttendance() {
        int[] a = {model.getTotalClass(), model.getTotalPresents()};
        return a;
    }

    public static Object[] getStudentMarksDetails() {
        Object[] o = {model.getExamNames(), model.getMarksDetails()};
        return o;
    }

}
