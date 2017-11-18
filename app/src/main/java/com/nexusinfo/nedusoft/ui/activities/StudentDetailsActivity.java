package com.nexusinfo.nedusoft.ui.activities;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.nexusinfo.nedusoft.R;
import com.nexusinfo.nedusoft.ui.fragments.AttendanceFragment;
import com.nexusinfo.nedusoft.ui.fragments.DocumentFragment;
import com.nexusinfo.nedusoft.ui.fragments.FamilyFragment;
import com.nexusinfo.nedusoft.ui.fragments.FeeMasterFragment;
import com.nexusinfo.nedusoft.ui.fragments.HospitalFragment;
import com.nexusinfo.nedusoft.ui.fragments.MarksFragment;
import com.nexusinfo.nedusoft.ui.fragments.PersonalFragment;

public class StudentDetailsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        mManager = getSupportFragmentManager();
        mManager.beginTransaction().replace(R.id.content_student_details, new PersonalFragment()).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
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
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
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
                mManager.beginTransaction().replace(R.id.content_student_details, new PersonalFragment()).commit();
                break;
            case R.id.nav_family:
                mManager.beginTransaction().replace(R.id.content_student_details, new FamilyFragment()).commit();
                break;
            case R.id.nav_hospital:
                mManager.beginTransaction().replace(R.id.content_student_details, new HospitalFragment()).commit();
                break;
            case R.id.nav_fee_master:
                mManager.beginTransaction().replace(R.id.content_student_details, new FeeMasterFragment()).commit();
                break;
            case R.id.nav_attendance:
                mManager.beginTransaction().replace(R.id.content_student_details, new AttendanceFragment()).commit();
                break;
            case R.id.nav_document:
                mManager.beginTransaction().replace(R.id.content_student_details, new DocumentFragment()).commit();
                break;
            case R.id.nav_marks:
                mManager.beginTransaction().replace(R.id.content_student_details, new MarksFragment()).commit();
                break;
//            case R.id.nav_share:
//
//                break;
//            case R.id.nav_send:
//
//                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
