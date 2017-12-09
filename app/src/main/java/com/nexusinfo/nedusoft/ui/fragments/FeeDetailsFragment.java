package com.nexusinfo.nedusoft.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.nexusinfo.nedusoft.R;
import com.nexusinfo.nedusoft.ui.activities.StudentDetailsActivity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeeDetailsFragment extends Fragment {


    public FeeDetailsFragment() {
        // Required empty public constructor
    }

    private TextView tvFeeDesc, tvTotal, tvPaid, tvBalance;
    private TableRow row;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//         Inflate the layout for this fragment
        getActivity().setTitle(R.string.title_fee_details_fragment);
        View view = inflater.inflate(R.layout.fragment_fee_details, container, false);

        TableLayout tl = view.findViewById(R.id.tableLayout_fee_details);

        ResultSet rs = StudentDetailsActivity.getStudent().getResultSetForFee();

        try {
            while (rs.next()){
                row = (TableRow) inflater.inflate(R.layout.rowitem_fee_details_table, container, false);
                tvFeeDesc = row.findViewById(R.id.textView_fee_description);
                tvTotal = row.findViewById(R.id.textView_total_fee);
                tvPaid = row. findViewById(R.id.textView_paid_fee);
                tvBalance = row. findViewById(R.id.textView_balance_fee);

                tvFeeDesc.setText(rs.getString("FeeDescription"));
                tvTotal.setText("" + rs.getFloat("total_Amt"));
                tvPaid.setText("" + rs.getFloat("Fpaidamt"));
                tvBalance.setText("" + (rs.getFloat("total_Amt") - rs.getFloat("Fpaidamt")));

                tl.addView(row);
            }

        } catch (SQLException e) {
            Log.e("Error", e.toString());
        }

        return view;
    }

}
