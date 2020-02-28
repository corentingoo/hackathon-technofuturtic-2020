package be.corentingoo.p1cashv9.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import be.corentingoo.p1cashv9.R;
import be.corentingoo.p1cashv9.adapters.CashAdapter;
import be.corentingoo.p1cashv9.models.Cash;
import be.corentingoo.p1cashv9.models.Machine;
import be.corentingoo.p1cashv9.utils.FragmentNotification;
import be.corentingoo.p1cashv9.utils.GetCashData;


public class MachineCashFragment extends FragmentNotification {

    private static MachineCashFragment instance = null;
    private Machine m;
    private TextView tv_machine;
    private ListView lv_Details;
    private ICallback callback;

    public void setCallback(ICallback callback) {
        this.callback = callback;
    }

    public static MachineCashFragment getInstance(Machine m){
        return instance =(instance !=null ? instance : new MachineCashFragment(m));
    }
    public MachineCashFragment(Machine machine) {
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
        CashAdapter adapter = new CashAdapter(getContext(), R.layout.view_cash, m.ListCash());
        lv_Details.setAdapter(adapter);
        return v;
    }

    public interface ICallback{
        void goToMachineListFragment();
    }
}