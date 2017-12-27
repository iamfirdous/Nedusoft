package com.nexusinfo.nedusoft.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nexusinfo.nedusoft.R;

/**
 * Created by firdous on 12/27/2017.
 */

public class Util {

    public static void showCustomToast(Context context, String message) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View toastLayout = inflater.inflate(R.layout.toast_custom, null);
        TextView tv = toastLayout.findViewById(R.id.textView_toast_custom);
        tv.setText(message);
        for(int i = 0; i < 2; i++) {
            Toast toast = new Toast(context);
            toast.setGravity(Gravity.CENTER, 0, -120);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(toastLayout);
            toast.show();
        }
    }
}
