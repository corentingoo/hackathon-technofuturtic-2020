package com.example.p1cashv3.utils;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;

import com.example.p1cashv3.models.Machine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class GetMachineData extends AsyncTask<Context,Void,ArrayList<Machine>>{
    DatabaseHelper CashDB;
    Context AscyncContext;
    public interface CallBackMachine{
        void setCallBack(ArrayList<Machine> ml,Context context);
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