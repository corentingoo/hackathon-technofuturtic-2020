package be.corentingoo.p1cashv8.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import be.corentingoo.p1cashv8.R;
import be.corentingoo.p1cashv8.models.Cash;
import be.corentingoo.p1cashv8.utils.ArrayAdapterNotification;


public class CashAdapter extends ArrayAdapterNotification {

    private List<Cash> listCash;
    private LayoutInflater inflater;

    public CashAdapter(Context context, int ressources, ArrayList<Cash> listCash) {
        super(context,ressources,listCash);
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
        notifyCurrentCapacity((int)CurrentPercent,CurrentCash,getContext());
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
                        //Log.d("vercf percent btn", Double.toString(percentCash));
                        //Log.d("verif capacity", Integer.toString(capacity));
                        CurrentCash.setCurrentCapacity(capacity);
                        //Log.d("verif CurrentCash", Integer.toString(CurrentCash.CurrentCapacity()));
                        TVCash.setText(percentCash + " %");
                        PBCash.setProgress((int) percentCash);
                        notifyCurrentCapacity((int)percentCash,CurrentCash,getContext());
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
                        notifyCurrentCapacity((int)percentCash,CurrentCash,getContext());
                    }
                }
            }
        });
        // returns the view for the current row
        return view;
    }

    @Override
    public void notifyCurrentCapacity(int percent, Cash current, Context context) {
        super.notifyCurrentCapacity(percent, current, context);
    }
}
