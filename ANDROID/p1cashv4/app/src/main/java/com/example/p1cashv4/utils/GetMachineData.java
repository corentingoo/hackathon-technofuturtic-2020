package com.example.p1cashv4.utils;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;

import com.example.p1cashv4.models.Machine;

import java.util.ArrayList;

public class GetMachineData extends AsyncTask<Context,Void,ArrayList<Machine>>{
    DatabaseHelper CashDB;
    Context AscyncContext;
    public interface CallBackMachine{
        void setCallBack(ArrayList<Machine> ml, Context context);
    }

    private CallBackMachine callBackMachine;

    public void setCallBackMachine(CallBackMachine callBackMachine){
        this.callBackMachine=callBackMachine;
    }

    @Override
    protected ArrayList<Machine> doInBackground(Context... context) {
        AscyncContext = context[0];
        ArrayList<Machine> listMachine =new ArrayList<>();
        CashDB = new DatabaseHelper(context[0]);
        Cursor res=  CashDB.getAllData();
        if(res.getCount()==0){
            Log.d("ERROR RETOUR DATA","PAS DE DONNEE DANS LA BASE DE DONNEE");
        }else{
            while(res.moveToNext()){
                Machine m =new Machine(Integer.parseInt(res.getString(0)),Integer.parseInt(res.getString(1)),Integer.parseInt(res.getString(2)));
                listMachine.add(m);
            }
            Log.d("DATA",listMachine.toString());}
        return listMachine;
    }
    @Override
    protected void onPostExecute(ArrayList<Machine> lm) {
        super.onPostExecute(lm);
        callBackMachine.setCallBack(lm,AscyncContext);

    }
}