package com.nexusinfo.nedusoft.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.nexusinfo.nedusoft.R;
import com.nexusinfo.nedusoft.models.StudentDetailsModel;
import com.nexusinfo.nedusoft.ui.activities.StudentDetailsActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeeDetailsFragment extends Fragment {


    public FeeDetailsFragment() {
        // Required empty public constructor
    }

    private TextView tvFeeDesc, tvTotal, tvPaid, tvBalance;
    private TableRow tableRow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//         Inflate the layout for this fragment
        getActivity().setTitle(R.string.title_fee_details_fragment);
        View view = inflater.inflate(R.layout.fragment_fee_details, container, false);

        TableLayout tl = view.findViewById(R.id.tableLayout_fee_details);

        ArrayList<StudentDetailsModel.Row> rows = StudentDetailsActivity.getStudentFeeDetails();

        for (StudentDetailsModel.Row row : rows) {
            tableRow = (TableRow) inflater.inflate(R.layout.rowitem_fee_details_table, container, false);
            tvFeeDesc = tableRow.findViewById(R.id.textView_fee_description);
            tvTotal = tableRow.findViewById(R.id.textView_total_fee);
            tvPaid = tableRow. findViewById(R.id.textView_paid_fee);
            tvBalance = tableRow. findViewById(R.id.textView_balance_fee);

            tvFeeDesc.setText(row.getFeeDesc());
            tvTotal.setText("" + row.getTotal());
            tvPaid.setText("" + row.getPaid());
            tvBalance.setText("" + row.getBalance());

            tl.addView(tableRow);
        }

        return view;
    }

}
