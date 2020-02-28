package be.corentingoo.p1cashv6.adapters;

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


import java.util.ArrayList;
import java.util.List;

import be.corentingoo.p1cashv6.R;
import be.corentingoo.p1cashv6.models.Cash;
import be.corentingoo.p1cashv6.models.Machine;

public class CashAdapter extends ArrayAdapter<Cash> {
    private Context context;
    private Machine Machine;
    private List<Cash> listCash;
    private LayoutInflater inflater;

    public CashAdapter(Context context, int ressources, ArrayList<Cash> listCash) {
        super(context,ressources,listCash);
        this.context = context;
        this.listCash = listCash;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listCash.size();
    }

    @Override
    public Cash getItem(int position) {
        return listCash.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view,ViewGroup parent) {
        view = inflater.inflate(R.layout.view_cash, null);

        //Log.i("ERROR GETITEM", getItem(position));
        final Cash CurrentCash = getItem(position);

        final double percentCash=((double)CurrentCash.CurrentCapacity()/(double)CurrentCash.MaxCapacity()) * 100;
        // get the TextView for item name
        final TextView TVCash = (TextView) view.findViewById(R.id.TVCash);
        TextView TVTypeCash=(TextView) view.findViewById(R.id.TVTypeCash);
        final ProgressBar PBCash = (ProgressBar) view.findViewById(R.id.PBCash);
        Button btnAdd = (Button) view.findViewById(R.id.ButtonAddCash);
        Button btnEmptying = (Button) view.findViewById(R.id.ButtonEmptyingCash);
        TVTypeCash.setText(CurrentCash.Type_cash());
        TVCash.setText((int)percentCash +"%");
        PBCash.setProgress((int)percentCash);
        //Log.i("ERROR CAST?", nomMachine);
        double CurrentPercent = ((double) CurrentCash.CurrentCapacity() / CurrentCash.MaxCapacity()) * 100;
        notifyCurrentCapacity((int)CurrentPercent,CurrentCash);
        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(CurrentCash.IdTypeCash()<7 || CurrentCash.IdTypeCash()>11) {
                    int capacity = CurrentCash.CurrentCapacity();
                    int maxCapacity = CurrentCash.MaxCapacity();
                    if (capacity < maxCapacity) {
                        capacity += 50;
                        if (capacity > maxCapacity) {
                            capacity = maxCapacity;
                        }//si plus que le maximum redescendre au max pour eviter des problèmes


                        String textTaux = ((double) capacity / maxCapacity) * 100 + " %";
                        double percentCash = ((double) capacity / maxCapacity) * 100;
                        Log.d("vercf percent btn", Double.toString(percentCash));
                        Log.d("verif capacity", Integer.toString(capacity));
                        CurrentCash.setCurrentCapacity(capacity);
                        Log.d("verif CurrentCash", Integer.toString(CurrentCash.CurrentCapacity()));
                        TVCash.setText(percentCash + " %");
                        PBCash.setProgress((int) percentCash);
                        notifyCurrentCapacity((int)percentCash,CurrentCash);
                    }
                }
                else{
                    CurrentCash.setCurrentCapacity(CurrentCash.MaxCapacity());
                    TVCash.setText(100 + " %");
                    PBCash.setProgress(100);
                }
            }
        });
        btnEmptying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CurrentCash.IdTypeCash()<7 || CurrentCash.IdTypeCash()>11) {
                    CurrentCash.setCurrentCapacity(0);
                    TVCash.setText(0 + " %");
                    PBCash.setProgress(0);
                }
                else{
                    int capacity = CurrentCash.CurrentCapacity();
                    int maxCapacity = CurrentCash.MaxCapacity();
                    if (capacity > 0) {
                        capacity -= 50;
                        if (capacity < 0) {
                            capacity = 0;
                        }//si plus que le maximum redescendre au max pour eviter des problèmes


                        String textTaux = ((double) capacity / maxCapacity) * 100 + " %";
                        double percentCash = ((double) capacity / maxCapacity) * 100;

                        CurrentCash.setCurrentCapacity(capacity);
                        TVCash.setText(percentCash + " %");
                        PBCash.setProgress((int) percentCash);
                        notifyCurrentCapacity((int)percentCash,CurrentCash);
                    }
                }
            }
        });
        // returns the view for the current row
        return view;
    }
    private void notifyCurrentCapacity(int percent,Cash current){
        if(current.IdTypeCash()<7 || current.IdTypeCash()>11){
        //if(current.Id()==1){
            if(percent==100){
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notification trop-plein");
                builder.setSmallIcon(R.drawable.ic_icone_machine_full_24dp);
                builder.setContentTitle("full");
                builder.setContentText(current.Type_cash()+"from machine"+current.IdMachine()+" is full");
                builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
                notificationManagerCompat.notify(current.Id(),builder.build());
                Log.d("MACHINE TROP PLEIN","id "+current.Id()+"type id "+current.IdTypeCash()+" type "+current.Type_cash()+" machine "+current.IdMachine()+" current cap "+current.CurrentCapacity()+" maxcapacity " + current.MaxCapacity()+"NOTIF FULL------------------------------------------------------");
            }
            else if(percent>80){
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notification trop-plein");
                builder.setSmallIcon(R.drawable.ic_icone_machine_almost_full_24dp);
                builder.setContentTitle("almost full");
                builder.setContentText(current.Type_cash()+"from machine"+current.IdMachine()+" is almost full");
                builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
                notificationManagerCompat.notify(current.Id(),builder.build());
                Log.d("MACHINE TROP PLEIN","id "+current.Id()+"type id "+current.IdTypeCash()+" type "+current.Type_cash()+" machine "+current.IdMachine()+" current cap "+current.CurrentCapacity()+" maxcapacity " + current.MaxCapacity()+"NOTIF almost FULL------------------------------------------------------");
            }
        }
        else{
            if(percent==0){
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notification reserve");
                builder.setSmallIcon(R.drawable.ic_icone_machine_empty_24dp);
                builder.setContentTitle("empty");
                builder.setContentText(current.Type_cash()+"from machine"+current.IdMachine()+" is empty");
                builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
                notificationManagerCompat.notify(current.Id(),builder.build());
                Log.d("MACHINE EMPTY","type id "+current.IdTypeCash()+" type "+current.Type_cash()+" machine "+current.IdMachine()+" current cap "+current.CurrentCapacity()+"NOTIF EMPTY-------------------------------------------------");
            }
            else if(percent<20){
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notification reserve");
                builder.setSmallIcon(R.drawable.ic_icone_machine_almost_empty_24dp);
                builder.setContentTitle("almost empty");
                builder.setContentText(current.Type_cash()+"from machine"+current.IdMachine()+" is almost empty");
                builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
                notificationManagerCompat.notify(current.Id(),builder.build());
                Log.d("MACHINE EMPTY","type id "+current.IdTypeCash()+" type "+current.Type_cash()+" machine "+current.IdMachine()+" current cap "+current.CurrentCapacity()+"NOTIF ALMOST EMPTY------------------------------------------------------");
            }
        }

    }
}
