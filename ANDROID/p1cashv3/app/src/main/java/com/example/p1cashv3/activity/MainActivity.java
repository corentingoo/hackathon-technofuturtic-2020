package com.example.p1cashv3.activity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.p1cashv3.adapters.MachineAdapter;
import com.example.p1cashv3.utils.DatabaseHelper;
import com.example.p1cashv3.R;
import com.example.p1cashv3.models.Machine;
import com.example.p1cashv3.utils.GetMachineData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView LV_Machine;
    private Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Machine> Machines = new ArrayList<Machine>();/*
        //implementation des données brutes
        Machines.add(new Machine(1, 50, 0));
        Machines.add(new Machine(2, 100, 5));
        Machines.add(new Machine(3, 150, 10));
        Machines.add(new Machine(4, 200, 20));*/
        GetMachineData getMachineData =new GetMachineData();
        getMachineData.setCallBackMachine(new GetMachineData.CallBackMachine() {
            @Override
            public void setCallBack(ArrayList<Machine> Machines,Context context) {
                /*verification retour
                for(int i = 0 ; i<Machines.size();i++){
                    String id = Integer.toString(Machines.get(i).get_id());
                    int max=Machines.get(i).get_maxCapacity();
                    int current=Machines.get(i).get_thisCapacity();
                    Log.d("RETOUR DATA","id :"+Integer.toString(Machines.get(i).get_id())+" max :"+Integer.toString(Machines.get(i).get_maxCapacity())+" this :"+Integer.toString(Machines.get(i).get_thisCapacity()));
                }
                 */
                MachineAdapter Ma = new MachineAdapter(context, R.layout.activity_management_machine, Machines);
                LV_Machine.setAdapter(Ma);
            }
        });
        LV_Machine = (ListView) findViewById(R.id.LVMachine);
        getMachineData.execute(this);

    }
}