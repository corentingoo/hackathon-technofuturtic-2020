package be.corentingoo.p1cashv9.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import be.corentingoo.p1cashv9.R;
import be.corentingoo.p1cashv9.adapters.MachineAdapter;
import be.corentingoo.p1cashv9.models.Cash;
import be.corentingoo.p1cashv9.models.Machine;
import be.corentingoo.p1cashv9.utils.FragmentNotification;
import be.corentingoo.p1cashv9.utils.GetCashData;
import be.corentingoo.p1cashv9.utils.GetMachineData;


public class MachineListFragment extends FragmentNotification implements GetMachineData.CallBackMachine, GetCashData.CallBackCash {
    private static MachineListFragment instance = null;
    private ICallback callback;
    private int iMachine;
    private ArrayList<Machine> machines;
    private ListView lv_Machine;

    public static MachineListFragment getInstance() {
        return instance = (instance != null ? instance : new MachineListFragment());
    }

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
        if(machines==null){
            URL url = null;
            try {
                url = new URL("http://10.0.2.2:5000/machine");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            GetMachineData gmd = new GetMachineData();
            gmd.setCallBackMachine(this);
            gmd.execute(url);
        }
        else {
            MachineAdapter Ma = new MachineAdapter(getContext(), R.layout.view_machine, machines);

            lv_Machine.setAdapter(Ma);
            lv_Machine.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    callback.goToMachineCashFragment(machines.get(position));
                }
            });
        }
        return v;
    }

    @Override
    public void setCallBackMachine(ArrayList<Machine> ml) {
        Log.d("recep frag ml",ml.toString());
        machines=new ArrayList<>();
        for (Machine m : ml) {
            Log.d("recep frag m", Integer.toString(m.Id()));
            machines.add(m);
        }
        for (int i = 0; i < machines.size(); i++) {
                GetCashData getCashData = new GetCashData();
                iMachine = i;
                getCashData.setCallBackCash(this);
                URL url = null;
                try {
                    url = new URL("http://10.0.2.2:5000/cash/machine/" + machines.get(i).Id());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                getCashData.execute(url);
            }

    }

    @Override
    public void setCallBackCash(ArrayList<Cash> lc) {
        for (Cash c : lc) {
            Log.d("cashList recup", "id:" + Integer.toString(c.IdMachine()) + "type:" + Integer.toString(c.IdTypeCash()) + "current capacity:" + Integer.toString(c.CurrentCapacity()));
            double percentCash = ((double) c.CurrentCapacity() / c.MaxCapacity()) * 100;
            notifyCurrentCapacity((int) percentCash, c,getContext());
            machines.get(iMachine).addCash(c);
        }
        MachineAdapter Ma = new MachineAdapter(getContext(), R.layout.view_machine,machines);

        lv_Machine.setAdapter(Ma);
        lv_Machine.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callback.goToMachineCashFragment(machines.get(iMachine));
            }
        });
    }

    public interface ICallback {
        void goToMachineCashFragment(Machine machine);
    }

    @Override
    public void notifyCurrentCapacity(int percent, Cash current, Context context) {
        super.notifyCurrentCapacity(percent, current, context);
    }
}