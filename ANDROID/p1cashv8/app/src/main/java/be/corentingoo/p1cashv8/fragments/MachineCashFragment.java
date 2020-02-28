package be.corentingoo.p1cashv8.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import be.corentingoo.p1cashv8.R;
import be.corentingoo.p1cashv8.adapters.CashAdapter;
import be.corentingoo.p1cashv8.models.Cash;
import be.corentingoo.p1cashv8.utils.FragmentNotification;
import be.corentingoo.p1cashv8.utils.GetCashData;
public class MachineCashFragment extends FragmentNotification implements GetCashData.CallBackCash {

    private static MachineCashFragment instance = null;
    private String m;
    private TextView tv_machine;
    private ListView lv_Details;
    private ICallback callback;

    public MachineCashFragment() {
    }
    public void setCallback(ICallback callback) {
        this.callback = callback;
    }

    public static MachineCashFragment getInstance(){
        return instance =(instance !=null ? instance : new MachineCashFragment());
    }
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
        tv_machine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.goToMachineListFragment();
            }
        });
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

    public interface ICallback{
        void goToMachineListFragment();
    }
}

/*
public class MachineCashFragment extends FragmentNotification implements GetCashData.CallBackCash {
    private ICallbackCash callback;
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
        tv_machine.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                callback.goToMachineListFragment();
            }
        });
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

    public interface ICallbackCash {
        void goToMachineListFragment();
    }
    @Override
    public void setCallBackCash(ArrayList<Cash> cl) {
        CashAdapter adapter = new CashAdapter(getContext(), R.layout.view_cash, cl);

        lv_Details.setAdapter(adapter);

    }

}*/
