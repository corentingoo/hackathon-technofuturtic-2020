package be.corentingoo.p1cashv6.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import java.util.ArrayList;

import be.corentingoo.p1cashv6.R;
import be.corentingoo.p1cashv6.adapters.MachineAdapter;
import be.corentingoo.p1cashv6.fragments.MachineDetailsFragment;
import be.corentingoo.p1cashv6.fragments.MachineListFragment;
import be.corentingoo.p1cashv6.models.Cash;
import be.corentingoo.p1cashv6.models.Machine;
import be.corentingoo.p1cashv6.models.UrlToSend;
import be.corentingoo.p1cashv6.utils.GetCashData;
import be.corentingoo.p1cashv6.utils.GetMachineData;


public class MainActivity extends AppCompatActivity implements
        GetMachineData.CallBackMachine,
        MachineListFragment.ICallback{
    private ListView LVMachine;
    private ListView LVCash;
    private Context context=this;
    private String urlMachine = "http://10.0.2.2:5000/machine";
    private String urlCash = "http://10.0.2.2:5000/cash/machine/";
    private UrlToSend urlToMachine = new UrlToSend(urlMachine,context);
    private UrlToSend urlToCash = new UrlToSend(urlCash,context);
    private ArrayList<Machine> machineList ;
    private ArrayList<Cash> cashList;
    MachineListFragment listFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listFragment = MachineListFragment.getInstance();
        listFragment.setCallback(this);
        FragmentManager manager = getSupportFragmentManager();
        manager
            .beginTransaction()
            .add(R.id.main_fl_fragment, listFragment)
            .commitNow();
//        LVMachine = (ListView) findViewById(R.id.LVMachine);
//        GetMachineData getMachineData =new GetMachineData();
//        getMachineData.setCallBackMachine(this);
//        getMachineData.execute(urlToMachine);
    }

    @Override
    public void setCallBack(ArrayList<Machine> ml, Context context) {

        machineList=ml;
        for(int i=0 ;i<machineList.size();i++){
            GetCashData getCashData =new GetCashData();
            final int finalI = i;
            getCashData.setCallBackCash(new GetCashData.CallBackCash() {
                @Override
                public void setCallBack(ArrayList<Cash> lc, Context context) {
                    cashList=lc;
                    for(Cash c:lc) {
                        machineList.get(finalI).addCash(c);
                    }
                    for(int j=0;j<machineList.get(finalI).ListCash().size();j++) {
                        Log.d("machineList recup", "id:"+Integer.toString(machineList.get(finalI).ListCash().get(j).IdMachine())+"type:"+Integer.toString(machineList.get(finalI).ListCash().get(j).IdTypeCash())+"current capacity:"+Integer.toString(machineList.get(finalI).ListCash().get(j).CurrentCapacity()));
                    }
                    MachineAdapter Ma = new MachineAdapter(context,R.layout.view_machine,machineList);
                    LVMachine.setAdapter(Ma);
                }
            });
            UrlToSend urlToCash=new UrlToSend(urlCash+machineList.get(i).Id(),context);
            getCashData.execute(urlToCash);
        }
    }

    @Override
    public void changeFragment(String machine) {
        MachineDetailsFragment fragment = new MachineDetailsFragment(machine);
        FragmentManager manager = getSupportFragmentManager();
        manager
                .beginTransaction()
                .replace(R.id.main_fl_fragment, fragment)
                .addToBackStack(null)
                .commit();
        }
}

