package com.nexusinfo.nedusoft.viewmodels;

import android.arch.lifecycle.ViewModel;

import com.nexusinfo.nedusoft.R;
import com.nexusinfo.nedusoft.models.FeedbackModel;

import java.util.ArrayList;

/**
 * Created by firdous on 12/26/2017.
 */

public class FeedbackViewModel extends ViewModel {

    private ArrayList<FeedbackModel> list;

    public static final int FEEDBACK_SCHOOL = 1;
    public static final int FEEDBACK_NEDUSOFT = 2;

    public ArrayList<FeedbackModel> getFeedbacks() {
        list = new ArrayList<>();

        FeedbackModel model1 = new FeedbackModel();
        FeedbackModel model2 = new FeedbackModel();

        model1.setFeedbackId(FEEDBACK_SCHOOL);
        model1.setTitle("Give feedback to school");
        model1.setDesc("Tell us, what you think about the school and the faculties");
        model1.setImage(R.drawable.ic_school_black_24dp);

        model2.setFeedbackId(FEEDBACK_NEDUSOFT);
        model2.setTitle("Give feedback to Nedusoft");
        model2.setDesc("We need your help to improve our app and our service.");
        model2.setImage(R.mipmap.ic_nedusoft_new);

        list.add(model1);
        list.add(model2);

        return list;
    }
}
