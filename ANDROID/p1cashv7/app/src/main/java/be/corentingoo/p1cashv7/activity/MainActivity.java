package be.corentingoo.p1cashv7.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

import be.corentingoo.p1cashv7.R;
import be.corentingoo.p1cashv7.fragments.MachineCashFragment;
import be.corentingoo.p1cashv7.fragments.MachineListFragment;
import be.corentingoo.p1cashv7.models.Machine;


public class MainActivity extends AppCompatActivity implements MachineListFragment.ICallback{
    private String urlCash = "http://10.0.2.2:5000/cash/machine/";
    private ArrayList<Machine> machineList ;
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
    }

    @Override
    public void changeFragment(String machine) {
        MachineCashFragment fragment = new MachineCashFragment(machine);
        FragmentManager manager = getSupportFragmentManager();
        manager
                .beginTransaction()
                .replace(R.id.main_fl_fragment, fragment)
                .addToBackStack(null)
                .commit();
        }
}

