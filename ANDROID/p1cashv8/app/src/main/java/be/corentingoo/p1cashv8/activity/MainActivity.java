package be.corentingoo.p1cashv8.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import be.corentingoo.p1cashv8.R;
import be.corentingoo.p1cashv8.fragments.MachineCashFragment;
import be.corentingoo.p1cashv8.fragments.MachineListFragment;
import be.corentingoo.p1cashv8.models.Cash;
import be.corentingoo.p1cashv8.models.Machine;
import be.corentingoo.p1cashv8.utils.GetCashData;
import be.corentingoo.p1cashv8.utils.GetMachineData;


public class MainActivity extends AppCompatActivity implements MachineListFragment.ICallback,MachineCashFragment.ICallback{


    private ArrayList<Machine> machineList ;
    MachineListFragment listFragment;
    MachineCashFragment cashFragment;


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
    }


    @Override
    public void goToMachineCashFragment(String machine) {
        MachineCashFragment fragmentcash = new MachineCashFragment(machine);
        fragmentcash.setCallback(this);
        FragmentManager manager = getSupportFragmentManager();
        manager
                .beginTransaction()
                .replace(R.id.main_fl_fragment, fragmentcash)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToMachineListFragment() {
        listFragment = MachineListFragment.getInstance();
        listFragment.setCallback(this);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.main_fl_fragment,listFragment).commitNow();
    }
}


/*
public class MainActivity extends AppCompatActivity implements MachineListFragment.ICallbackMachineList,
        MachineCashFragment.ICallbackCash,
        GetMachineData.CallBackMachine,
        GetCashData.CallBackCash {


    MachineListFragment listFragment;
    private ArrayList<Machine> machines;
    private int machineI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    @Override
    public void goToMachineCashFragment(String machine) {
        MachineCashFragment fragment = new MachineCashFragment(machine);
        FragmentManager manager = getSupportFragmentManager();
        manager
                .beginTransaction()
                .replace(R.id.main_fl_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToMachineListFragment() {
        MachineListFragment fragment = new MachineListFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager
                .beginTransaction()
                .replace(R.id.main_fl_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void setCallBackMachine(ArrayList<Machine> ml) {
        for (Machine m : ml) {
            machines.add(m);
        }
        for (int i = 0; i < ml.size(); i++) {
            GetCashData getCashData = new GetCashData();
            machineI = i;
            getCashData.setCallBackCash(this);
            URL url = null;
            try {
                url = new URL("http://10.0.2.2:5000/cash/machine/" + machines.get(machineI).Id());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            getCashData.execute(url);
        }
    }

    @Override
    public void setCallBackCash(ArrayList<Cash> lc) {
        for(Cash c : lc){
            machines.get(machineI).addCash(c);
        }
        listFragment = MachineListFragment.getInstance();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Machines",machines);
        listFragment.setArguments(bundle);
        FragmentManager manager = getSupportFragmentManager();
        manager
                .beginTransaction()
                .add(R.id.main_fl_fragment, listFragment)
                .commitNow();
    }
}
*/
