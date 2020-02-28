package be.corentingoo.p1cashv9.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

import be.corentingoo.p1cashv9.R;
import be.corentingoo.p1cashv9.fragments.MachineCashFragment;
import be.corentingoo.p1cashv9.fragments.MachineListFragment;
import be.corentingoo.p1cashv9.models.Machine;


public class MainActivity extends AppCompatActivity implements MachineListFragment.ICallback, MachineCashFragment.ICallback{


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
    public void goToMachineCashFragment(Machine machine) {
        cashFragment =  MachineCashFragment.getInstance(machine);
        cashFragment.setCallback(this);
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