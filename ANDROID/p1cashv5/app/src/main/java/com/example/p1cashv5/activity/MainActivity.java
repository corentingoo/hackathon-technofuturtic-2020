package com.example.p1cashv5.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.p1cashv5.R;
import com.example.p1cashv5.adapters.MachineAdapter;
import com.example.p1cashv5.models.Cash;
import com.example.p1cashv5.models.Machine;
import com.example.p1cashv5.models.UrlToSend;
import com.example.p1cashv5.utils.GetCashData;
import com.example.p1cashv5.utils.GetMachineData;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ListView LVMachine;
    private ListView LVCash;
    private Context context=this;
    private String urlMachine = "http://10.0.2.2:5000/machine";
    private String urlCash = "http://10.0.2.2:5000/cash/machine/";
    private UrlToSend urlToMachine = new UrlToSend(urlMachine,context);
    private UrlToSend urlToCash = new UrlToSend(urlCash,context);
    private ArrayList<Machine> machineList ;
    private ArrayList<Cash> cashList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LVMachine = (ListView) findViewById(R.id.LVMachine);
        GetMachineData getMachineData =new GetMachineData();
        getMachineData.setCallBackMachine(new GetMachineData.CallBackMachine() {
            @Override
            public void setCallBack(ArrayList<Machine> Machines,Context context) {

                machineList=Machines;
                for(int i=0 ;i<machineList.size();i++){
                    GetCashData getCashData =new GetCashData();
                    final int finalI = i;
                    getCashData.setCallBackCash(new GetCashData.CallBackCash() {
                        @Override
                        public void setCallBack(ArrayList<Cash> lc, Context context) {
                            cashList=lc;
                            for(Cash c:lc) {
                                machineList.get(finalI).addCash(c);
                            }
                            for(int j=0;j<machineList.get(finalI).ListCash().size();j++) {
                                Log.d("machineList recup", "id:"+Integer.toString(machineList.get(finalI).ListCash().get(j).IdMachine())+"type:"+Integer.toString(machineList.get(finalI).ListCash().get(j).IdTypeCash())+"current capacity:"+Integer.toString(machineList.get(finalI).ListCash().get(j).CurrentCapacity()));
                            }
                            MachineAdapter Ma = new MachineAdapter(context,R.layout.view_machine,machineList);
                            LVMachine.setAdapter(Ma);
                        }
                    });
                    UrlToSend urlToCash=new UrlToSend(urlCash+machineList.get(i).Id(),context);
                    getCashData.execute(urlToCash);
                }
            }
        });
        getMachineData.execute(urlToMachine);
    }
}

