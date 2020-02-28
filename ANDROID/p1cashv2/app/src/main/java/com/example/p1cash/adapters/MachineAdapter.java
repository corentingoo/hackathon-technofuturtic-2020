package com.example.p1cash.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.p1cash.R;
import com.example.p1cash.models.Machine;
import java.util.ArrayList;
import java.util.List;

public class MachineAdapter extends ArrayAdapter<Machine> {
    private Context context;
    private List<Machine> listMachine;
    private LayoutInflater inflater;

    public MachineAdapter(Context context,int ressources, ArrayList<Machine> listMachine) {
        super(context,ressources,listMachine);
        this.context = context;
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
    public View getView(int position, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.activity_management_machine, null);

        //Log.i("ERROR GETITEM", getItem(position));
        final Machine ThisMachine = getItem(position);

        String nomMachine = "Machine " + ThisMachine.get_id();
        //Log.i("ERROR CAST?", nomMachine);

        final double tauxMachine = ((double) ThisMachine.get_thisCapacity() / (double) ThisMachine.get_maxCapacity()) * 100;
        final String showTauxMachine = tauxMachine + " %";

        // get the TextView for item name and item description
        TextView tvNomMachine = (TextView) view.findViewById(R.id.TVMachine);
        final TextView tvTauxMachine = (TextView) view.findViewById(R.id.TVTauxMachine);
        final ProgressBar pbMachine = (ProgressBar) view.findViewById(R.id.PBTauxMachine);
        Button btnUp = (Button) view.findViewById(R.id.buttonUp);
        Button btnDown = (Button) view.findViewById(R.id.buttonDown);

        //sets the text for item name and item description from the current item object
        tvNomMachine.setText(nomMachine);
        tvTauxMachine.setText(showTauxMachine);
        pbMachine.setProgress((int) tauxMachine);
        btnUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int capacity = ThisMachine.get_thisCapacity();
                int maxCapacity = ThisMachine.get_maxCapacity();
                if (capacity < maxCapacity) {
                    capacity += 10;
                    if (capacity > maxCapacity) {
                        capacity = maxCapacity;
                    }//si plus que le maximum redescendre au max pour eviter des problèmes
                    ThisMachine.set_thisCapacity(capacity);
                    String textTaux = ((double) capacity / maxCapacity) * 100 + " %";
                    tvTauxMachine.setText(textTaux);
                    double tauxIntMachine = ((double) capacity / maxCapacity) * 100;


                    //Log.i("cap",new Integer(capacity).toString());
                    //Log.i("max",new Integer(maxCapacity).toString());
                    //Log.i("taux NOW", new Double(tauxIntMachine).toString());
                    pbMachine.setProgress((int) tauxIntMachine);
                }
                //Log.i("CAPACITY NOW", new Integer(capacity).toString());
                //Log.i("ThisMachine CAPACITY", new Integer(ThisMachine.get_thisCapacity()).toString());
            }
        });
        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThisMachine.set_thisCapacity(0);
                tvTauxMachine.setText(0 + " %");
                pbMachine.setProgress(0);
            }
        });


        // returns the view for the current row
        return view;
    }
}
