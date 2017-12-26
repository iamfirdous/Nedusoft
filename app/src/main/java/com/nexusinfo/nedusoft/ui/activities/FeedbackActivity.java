package com.nexusinfo.nedusoft.ui.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.nexusinfo.nedusoft.R;
import com.nexusinfo.nedusoft.ui.adapters.FeedbackAdapter;
import com.nexusinfo.nedusoft.viewmodels.FeedbackViewModel;

public class FeedbackActivity extends AppCompatActivity {

    private ListView listView;
    private FeedbackAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        FeedbackViewModel viewModel = ViewModelProviders.of(this).get(FeedbackViewModel.class);

        listView = findViewById(R.id.listView_feedback);
        adapter = new FeedbackAdapter(this, viewModel.getFeedbacks());

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {

        });
    }
}
