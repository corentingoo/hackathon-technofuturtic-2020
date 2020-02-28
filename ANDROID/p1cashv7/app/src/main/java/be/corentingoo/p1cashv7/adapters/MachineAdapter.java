package be.corentingoo.p1cashv7.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.ArrayList;
import java.util.List;

import be.corentingoo.p1cashv7.R;
import be.corentingoo.p1cashv7.models.Cash;
import be.corentingoo.p1cashv7.models.Machine;


public class MachineAdapter extends ArrayAdapter<Machine> {
    private List<Machine> listMachine;
    private LayoutInflater inflater;
    private ListView LVCash;

    public MachineAdapter(Context context, int ressources, ArrayList<Machine> listMachine) {
        super(context,ressources,listMachine);
        this.listMachine = listMachine;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listMachine.size();
    }

    @Override
    public Machine getItem(int position) {
        return listMachine.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view,ViewGroup parent) {
        view = inflater.inflate(R.layout.view_machine, null);
        //Log.i("ERROR GETITEM", getItem(position));
        Machine currentMachine = getItem(position);
        TextView TVMachine = (TextView) view.findViewById(R.id.TVMachine);
        String nomMachine = "Machine " + currentMachine.Id();
        TVMachine.setText(nomMachine);
        //Log.i("ERROR CAST?", nomMachine);
        for(Cash c : currentMachine.ListCash()){
            Log.d("HELLOOOOOOOOOOOOOOOOOO","id machine "+Integer.toString(c.IdMachine())+"current cap "+ Integer.toString(c.CurrentCapacity()));
        }
        //Log.d("hauteur liste",Integer.toString(calculateHeight(LVCash)));

        // returns the view for the current row
        return view;
    }
}
