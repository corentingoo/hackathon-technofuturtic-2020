package be.corentingoo.p1cashv10.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import be.corentingoo.p1cashv10.R;
import be.corentingoo.p1cashv10.adapters.CashAdapter;
import be.corentingoo.p1cashv10.models.Cash;
import be.corentingoo.p1cashv10.models.Machine;
import be.corentingoo.p1cashv10.utils.FragmentNotification;
import be.corentingoo.p1cashv10.utils.GetCashData;


public class MachineCashFragment extends FragmentNotification implements GetCashData.CallBackCash {

    private static MachineCashFragment instance = null;
    private Machine m;
    private TextView tv_machine;
    private ListView lv_Details;
    private ICallback callback;

    public void setCallback(ICallback callback) {
        this.callback = callback;
    }
    public static MachineCashFragment getInstance(Machine m){
        return instance = new MachineCashFragment(m);
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
        Log.d("objectmachine","-------------------------"+m.Id()) ;
        URL url=null;
        try {
            url=new URL("http://10.0.2.2:5000/cash/machine/"+m.Id());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        GetCashData gcd = new GetCashData();
        gcd.setCallBackCash(this);
        gcd.execute(url);
        return v;
    }

    @Override
    public void setCallBackCash(ArrayList<Cash> lc) {
        m.voidCash();
        for(Cash c : lc){
            m.addCash(c);
        }
        CashAdapter adapter = new CashAdapter(getContext(), R.layout.view_cash, lc);
        Log.d("liste de cash",lc.toString());
        lv_Details.setAdapter(adapter);
    }

    public interface ICallback{
        void goToMachineListFragment();
    }
}