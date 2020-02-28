package com.example.p1cashv3.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.p1cashv3.R;
import com.example.p1cashv3.models.Machine;

import java.util.ArrayList;
import java.util.List;

public class MachineAdapter extends ArrayAdapter<Machine> {
    private Context context;
    private List<Machine> listMachine;
    private LayoutInflater inflater;

    public MachineAdapter(Context context, int ressources, ArrayList<Machine> listMachine) {
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
    public View getView(int position, View view,ViewGroup parent) {
        view = inflater.inflate(R.layout.activity_management_machine, null);

        //Log.i("ERROR GETITEM", getItem(position));
        final Machine ThisMachine = getItem(position);

        String nomMachine = "Machine " + ThisMachine.get_id();
        //Log.i("ERROR CAST?", nomMachine);

        final double tauxMachine = ((double) ThisMachine.get_thisCapacity() / (double) ThisMachine.get_maxCapacity()) * 100;
        final String showTauxMachine = tauxMachine + " %";

        // get the TextView for item name and item description
        TextView tvNomMachine = (TextView) view.findViewById(R.id.TVMachine);
        final TextView tvTauxMachine = (TextView) view.findViewById(R.id.TVMachineVal);
        final ProgressBar pbMachine = (ProgressBar) view.findViewById(R.id.PBMachineVal);
        Button btnUp = (Button) view.findViewById(R.id.buttonAdd);
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



                    String textTaux = ((double) capacity / maxCapacity) * 100 + " %";
                    double tauxIntMachine = ((double) capacity / maxCapacity) * 100;

                    if(tauxIntMachine==100){
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notification trop-plein");
                        builder.setSmallIcon(R.drawable.ic_warning_orange_24dp);
                        builder.setContentTitle("full");
                        builder.setContentText("machine " + ThisMachine.get_id() + " is full");
                        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

                        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
                        notificationManagerCompat.notify(ThisMachine.get_id(),builder.build());
                        Log.d("MACHINE TROP PLEIN","------------------------------------------------------");

                    }
                    else if(tauxIntMachine>80){
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notification trop-plein");
                        builder.setSmallIcon(R.drawable.ic_warning_red_24dp);
                        builder.setContentTitle("almost full");
                        builder.setContentText("machine " + ThisMachine.get_id() + " is almost full");
                        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

                        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
                        notificationManagerCompat.notify(ThisMachine.get_id(),builder.build());
                        Log.d("MACHINE TROP PLEIN","------------------------------------------------------");

                    }
                    ThisMachine.set_thisCapacity(capacity);
                    tvTauxMachine.setText(textTaux);
                    pbMachine.setProgress((int) tauxIntMachine);

                    //Log.i("cap",new Integer(capacity).toString());
                    //Log.i("max",new Integer(maxCapacity).toString());
                    //Log.i("taux NOW", new Double(tauxIntMachine).toString());

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
