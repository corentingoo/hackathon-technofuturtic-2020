package be.corentingoo.p1cashv6.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import be.corentingoo.p1cashv6.R;
import be.corentingoo.p1cashv6.models.Cash;
import be.corentingoo.p1cashv6.models.Machine;

public class MachineAdapter extends ArrayAdapter<Machine> {
    private Context context;
    private List<Machine> listMachine;
    private LayoutInflater inflater;
    private ListView LVCash;

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
        view = inflater.inflate(R.layout.view_machine, null);
        //Log.i("ERROR GETITEM", getItem(position));
        Machine currentMachine = getItem(position);
        //String Url = "http://10.0.2.2:5000/cash/machine/"+currentMachine.Id();
        //UrlToSend urlToSend=new UrlToSend(Url,context);
        TextView TVMachine = (TextView) view.findViewById(R.id.TVMachine);
        LVCash =(ListView) view.findViewById(R.id.LVCash);
        String nomMachine = "Machine " + currentMachine.Id();
        TVMachine.setText(nomMachine);
        //Log.i("ERROR CAST?", nomMachine);
        for(Cash c : currentMachine.ListCash()){
            Log.d("HELLOOOOOOOOOOOOOOOOOO","id machine "+Integer.toString(c.IdMachine())+"current cap "+ Integer.toString(c.CurrentCapacity()));
        }


        CashAdapter Ca = new CashAdapter(context, R.layout.view_cash,currentMachine.ListCash());
        LVCash.setAdapter(Ca);
        Log.d("hauteur liste",Integer.toString(calculateHeight(LVCash)));
        ViewGroup.LayoutParams params = LVCash.getLayoutParams();
        params.height = calculateHeight(LVCash);
        LVCash.setLayoutParams(params);



        /*
        GetCashData getCashData =new GetCashData();
        getCashData.setCallBackCash(new GetCashData.CallBackCash() {
            @Override
            public void setCallBack(ArrayList<Cash> lc, Context context) {
                CashAdapter Ca = new CashAdapter(context,R.layout.view_cash,lc);
                LVCash.setAdapter(Ca);
            }
        });
        getCashData.execute(urlToSend);

 */
        // returns the view for the current row
        return view;
    }
    private int calculateHeight(ListView list) {

        int height = 0;

        for (int i = 0; i < list.getCount(); i++) {
            View childView = list.getAdapter().getView(i, null, list);
            childView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            height+= childView.getMeasuredHeight();
        }

        //dividers height
        height += list.getDividerHeight() * list.getCount();

        return height;
    }
}
