package com.nexusinfo.nedusoft.ui.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ListView;

import com.nexusinfo.nedusoft.LocalDatabaseHelper;
import com.nexusinfo.nedusoft.R;
import com.nexusinfo.nedusoft.ui.adapters.FeedbackAdapter;
import com.nexusinfo.nedusoft.viewmodels.FeedbackViewModel;

import static com.nexusinfo.nedusoft.utils.Util.showCustomToast;

public class FeedbackActivity extends AppCompatActivity {

    private ListView listView;
    private FeedbackAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        FeedbackViewModel viewModel = ViewModelProviders.of(this).get(FeedbackViewModel.class);

        listView = findViewById(R.id.listView_feedback);
        adapter = new FeedbackAdapter(this, viewModel.getFeedbacks());

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            int feedbackId = adapter.getItem(i).getFeedbackId();

            switch (feedbackId){
                case FeedbackViewModel.FEEDBACK_SCHOOL:
                    LocalDatabaseHelper db = LocalDatabaseHelper.getInstance(this);
                    String email = db.getUser().getSchoolEmail();

                    Log.e("Email", "" + email);

                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setData(Uri.parse("mailto:"));
                    emailIntent.setType("text/plain");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL  , new String[]{email});
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");

                    showCustomToast(this, "Please select an email client.\nFor eg., Gmail", 2, Gravity.CENTER, 0, -150);
                    startActivity(Intent.createChooser(emailIntent,"Please select an email client... For eg., Gmail"));
                    break;
                case FeedbackViewModel.FEEDBACK_NEDUSOFT:
                    Uri uri = Uri.parse("market://details?id=" + this.getPackageName());
                    Intent goToStore = new Intent(Intent.ACTION_VIEW, uri);
                    goToStore.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                            Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                            Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    try {
                        startActivity(goToStore);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
                    }
                    break;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                finish();
                break;
        }

        return true;
    }
}
