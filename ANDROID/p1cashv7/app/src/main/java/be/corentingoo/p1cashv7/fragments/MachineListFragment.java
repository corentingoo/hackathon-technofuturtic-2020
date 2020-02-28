package be.corentingoo.p1cashv7.fragments;

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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import be.corentingoo.p1cashv7.R;
import be.corentingoo.p1cashv7.models.Cash;
import be.corentingoo.p1cashv7.models.Machine;
import be.corentingoo.p1cashv7.utils.FragmentNotification;
import be.corentingoo.p1cashv7.utils.GetCashData;
import be.corentingoo.p1cashv7.utils.GetMachineData;


public class MachineListFragment extends FragmentNotification implements GetMachineData.CallBackMachine, GetCashData.CallBackCash {
    private static MachineListFragment instance = null;
    private ICallback callback;
    private int iMachine;

    public static MachineListFragment getInstance() {
        return instance = (instance != null ? instance : new MachineListFragment());
    }

    private ListView lv_Machine;

    public MachineListFragment() {
        // Required empty public constructor
    }

    public void setCallback(ICallback callback) {
        this.callback = callback;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_machine_list, container, false);
        lv_Machine = v.findViewById(R.id.fr_lv_machine);
        URL url = null;
        try {
            url = new URL("http://10.0.2.2:5000/machine");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        GetMachineData gmd = new GetMachineData();
        gmd.setCallBackMachine(this);
        gmd.execute(url);

        return v;
    }

    @Override
    public void setCallBackMachine(ArrayList<Machine> ml) {
        List<String> machines = new ArrayList<>();

        for (Machine m : ml) {
            machines.add("Machine " + m.Id());
            for (int i = 0; i < ml.size(); i++) {
                GetCashData getCashData = new GetCashData();
                iMachine = i;
                getCashData.setCallBackCash(this);
                URL url = null;
                try {
                    url = new URL("http://10.0.2.2:5000/cash/machine/" + m.Id());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                getCashData.execute(url);
            }

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, machines);

        lv_Machine.setAdapter(adapter);
        lv_Machine.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view;
                callback.changeFragment(tv.getText().toString().replace("Machine ", ""));
            }
        });
    }

    @Override
    public void setCallBackCash(ArrayList<Cash> lc) {
        for (Cash c : lc) {
            Log.d("cashList recup", "id:" + Integer.toString(c.IdMachine()) + "type:" + Integer.toString(c.IdTypeCash()) + "current capacity:" + Integer.toString(c.CurrentCapacity()));
            double percentCash = ((double) c.CurrentCapacity() / c.MaxCapacity()) * 100;
            notifyCurrentCapacity((int) percentCash, c,getContext());
        }
    }

    public interface ICallback {
        void changeFragment(String machine);
    }

    @Override
    public void notifyCurrentCapacity(int percent, Cash current, Context context) {
        super.notifyCurrentCapacity(percent, current, context);
    }
}
