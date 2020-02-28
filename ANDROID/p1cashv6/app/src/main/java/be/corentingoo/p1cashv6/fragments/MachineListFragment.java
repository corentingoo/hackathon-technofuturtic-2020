package be.corentingoo.p1cashv6.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import be.corentingoo.p1cashv6.R;
import be.corentingoo.p1cashv6.models.Machine;
import be.corentingoo.p1cashv6.models.UrlToSend;
import be.corentingoo.p1cashv6.utils.GetMachineData;


public class MachineListFragment extends Fragment implements GetMachineData.CallBackMachine {
    private static MachineListFragment instance = null;
    private ICallback callback;

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

        GetMachineData gmd = new GetMachineData();
        gmd.setCallBackMachine(this);
        gmd.execute(new UrlToSend("http://10.0.2.2:5000/machine", getContext()));

        return v;
    }

    @Override
    public void setCallBack(ArrayList<Machine> ml, Context context) {
        List<String> machines = new ArrayList<>();

        for(Machine m : ml) {
            machines.add("Machine "+ m.Id());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, machines);

        lv_Machine.setAdapter(adapter);
        lv_Machine.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view;
                Log.d("ITEM_CLICK", tv.getText().toString());
                callback.changeFragment(tv.getText().toString().replace("Machine ", ""));
            }
        });
    }

    public interface ICallback {
        void changeFragment(String machine);
    }
}
