package be.corentingoo.p1cashv6.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import be.corentingoo.p1cashv6.R;
import be.corentingoo.p1cashv6.adapters.CashAdapter;
import be.corentingoo.p1cashv6.models.Cash;
import be.corentingoo.p1cashv6.models.Machine;
import be.corentingoo.p1cashv6.models.UrlToSend;
import be.corentingoo.p1cashv6.utils.GetCashData;

public class MachineDetailsFragment extends Fragment implements GetCashData.CallBackCash {

    private String m;

    private ListView lv_Details;

    public MachineDetailsFragment(String machine) {
        this.m=machine;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cash_list, container, false);
        lv_Details = v.findViewById(R.id.fr_lv_Details);

        GetCashData gmd = new GetCashData();
        gmd.setCallBackCash(this);
        gmd.execute(new UrlToSend("http://10.0.2.2:5000/cash/machine/"+m, getContext()));

        return v;
    }

    @Override
    public void setCallBack(ArrayList<Cash> cl, Context context) {
        CashAdapter adapter = new CashAdapter(getContext(), R.layout.view_cash, cl);

        lv_Details.setAdapter(adapter);

    }

}
