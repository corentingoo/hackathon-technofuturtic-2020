package be.corentingoo.p1cashv7.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import be.corentingoo.p1cashv7.R;
import be.corentingoo.p1cashv7.adapters.CashAdapter;
import be.corentingoo.p1cashv7.models.Cash;
import be.corentingoo.p1cashv7.utils.FragmentNotification;
import be.corentingoo.p1cashv7.utils.GetCashData;


public class MachineCashFragment extends FragmentNotification implements GetCashData.CallBackCash {

    private String m;
    private TextView tv_machine;
    private ListView lv_Details;

    public MachineCashFragment(String machine) {
        this.m=machine;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cash_list, container, false);

        lv_Details = v.findViewById(R.id.fr_lv_Details);
        tv_machine = v.findViewById(R.id.fr_tv_nom_machine);
        tv_machine.setText("Machine "+m);
        URL url=null;
        try {
            url=new URL("http://10.0.2.2:5000/cash/machine/"+m);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        GetCashData gcd = new GetCashData();
        gcd.setCallBackCash(this);
        gcd.execute(url);
        return v;
    }

    @Override
    public void setCallBackCash(ArrayList<Cash> cl) {
        CashAdapter adapter = new CashAdapter(getContext(), R.layout.view_cash, cl);

        lv_Details.setAdapter(adapter);

    }

}
