package be.corentingoo.p1cashv10.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

import be.corentingoo.p1cashv10.R;
import be.corentingoo.p1cashv10.fragments.MachineCashFragment;
import be.corentingoo.p1cashv10.fragments.MachineListFragment;
import be.corentingoo.p1cashv10.models.Cash;
import be.corentingoo.p1cashv10.models.Machine;


public class MainActivity extends AppCompatActivity implements MachineListFragment.ICallback, MachineCashFragment.ICallback{


    private ArrayList<Machine> machineList =new ArrayList<>();
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
    public void goToMachineCashFragment(Machine machine) {
        Log.d("machineobj",Integer.toString(machine.Id()));
        cashFragment =  MachineCashFragment.getInstance(machine);
        cashFragment.setCallback(this);
        MachineCashFragment fragment = new MachineCashFragment(machine);
        FragmentManager manager = getSupportFragmentManager();
        manager
                .beginTransaction()
                .replace(R.id.main_fl_fragment, cashFragment)
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