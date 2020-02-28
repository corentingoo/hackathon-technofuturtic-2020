package be.corentingoo.p1cashv10.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import be.corentingoo.p1cashv10.R;
import be.corentingoo.p1cashv10.models.Cash;
import be.corentingoo.p1cashv10.models.Machine;


public class MachineAdapter extends ArrayAdapter<Machine> {
    private List<Machine> listMachine;
    private LayoutInflater inflater;
    private ListView LVCash;
    private ImageView imgFull;
    private ImageView imgAlmostFull;
    private ImageView imgEmpty;
    private ImageView imgAlmostEmpty;
    private Machine currentMachine;

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
        currentMachine = getItem(position);
        TextView TVMachine = (TextView) view.findViewById(R.id.TVMachine);
        String nomMachine = "Machine " + currentMachine.Id();
        TVMachine.setText(nomMachine);
        imgFull = view.findViewById(R.id.lv_machine_icon_full);
        imgAlmostFull = view.findViewById(R.id.lv_machine_icon_almost_full);
        imgAlmostEmpty = view.findViewById(R.id.lv_machine_icon_almost_empty);
        imgEmpty = view.findViewById(R.id.lv_machine_icon_empty);
        //Log.i("ERROR CAST?", nomMachine);
        for(Cash c : currentMachine.ListCash()){
            int percent = CalcPercent(c.CurrentCapacity(),c.MaxCapacity());
            setVisibilityIcon(percent,c);
            //Log.d("HELLOOOOOOOOOOOOOOOOOO","id machine "+Integer.toString(c.IdMachine())+"current cap "+ Integer.toString(c.CurrentCapacity()));
        }
        //Log.d("hauteur liste",Integer.toString(calculateHeight(LVCash)));

        // returns the view for the current row
        return view;
    }
    public void setVisibilityIcon(int percent,Cash c){
        if (c.IdTypeCash() < 7 || c.IdTypeCash() > 11) {
            if (percent == 100) {
                imgFull.setVisibility(View.VISIBLE);
            } else if (percent > 80) {
                imgAlmostFull.setVisibility(View.VISIBLE);
            }
        } else {
            if (percent == 0) {
                imgEmpty.setVisibility(View.VISIBLE);
            } else if (percent < 20) {
                imgAlmostEmpty.setVisibility(View.VISIBLE);
            }
        }
    }
    public int CalcPercent(int dividende,int diviseur){
        return (int)Math.round((((double)dividende/diviseur) * 100)*100)/100;
    }
}
