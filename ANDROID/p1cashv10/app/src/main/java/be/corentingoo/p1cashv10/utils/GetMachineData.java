package be.corentingoo.p1cashv10.utils;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import be.corentingoo.p1cashv10.models.Cash;
import be.corentingoo.p1cashv10.models.Machine;


public class GetMachineData extends AsyncTask<URL,Void, ArrayList<Machine>> {

    public interface CallBackMachine{
        void setCallBackMachine(ArrayList<Machine> ml);
    }

    private CallBackMachine callBackMachine;

    public void setCallBackMachine(CallBackMachine callBackMachine){
        this.callBackMachine=callBackMachine;
    }

    @Override
    protected ArrayList<Machine> doInBackground(URL... urls) {
        ArrayList<Cash> listCash ;
        ArrayList<Machine> listMachine =new ArrayList<>();
        Machine m ;
        Cash c;
        HttpURLConnection connection;
        InputStream inputStream;
        BufferedReader bufferedReader;
        JSONArray json ;
        JSONObject jsonCash;
        JSONArray jsonListCash;
        try {
            //test de connection via une api externe : "https://pokeapi.co/api/v2/pokemon" (fonctionnel 18-1-20)
            //url originale: https://localhost:44327/api/Machine //10.0.2.2:8080 //piv4 : 10.10.19.90
            //Log.d(" URL ENCODEE",urlMachine.toString());
            //Log.d("avancement thread","string url envoiyee");

            connection = (HttpURLConnection) urls[0].openConnection();
            //Log.d("avancement thread","objet connection creee");

            connection.setRequestMethod("GET");
            //Log.d("avancement thread","get sur connection cree");
            //Log.d("type requete",connection.getRequestMethod());
            //Log.d("url",connection.getURL().toString());

            connection.connect();
            //Log.d("avancement thread","connection faite");
            //Log.d("contenus reponse",connection.getResponseMessage());

            inputStream = connection.getInputStream();
            //Log.d("----retour inputStream", "--------------------wat inside :" + inputStream);

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            //Log.d("----retour buffer", "--------------------wat inside :" + bufferedReader);

            json = new JSONArray(bufferedReader.readLine());
            for(int i=0;i<json.length();i++){
                JSONObject jsonObject=new JSONObject(json.getString(i));
                //Log.d("json",json.getString(i));
                listCash = new ArrayList<>();
                jsonListCash = jsonObject.getJSONArray("cashes");

                for(int j=0;j<jsonListCash.length();j++){
                    jsonCash=new JSONObject(jsonListCash.get(j).toString());
                    //Log.d("jsonCash",jsonCash.toString());
                    c=new Cash(jsonCash.getInt("id"),jsonCash.getInt("idTypeCash"),jsonCash.getString("typeCash"),jsonCash.getInt("maxCapacity"),jsonCash.getInt("currentCapacity"),jsonCash.getInt("idMachine"));
                    listCash.add(c);
                }/*
                for(Cash ca:listCash){
                    Log.d("cash in list ",ca.Id()+" "+ca.Type_cash());
                }*/
                m = new Machine(jsonObject.getInt("id"),jsonObject.getInt("idParking"),listCash);
                listMachine.add(m);
            }
            /*
            for(int i=0;i<listMachine.size();i++) {
                Log.d("listmachine", "id machine"+Integer.toString(listMachine.get(i).Id()));
            }

             */
            //StringBuilder stringBuilder = new StringBuilder(bufferedReader.read());
            //test = stringBuilder.toString();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            Log.d("MalformedURLException","----------------"+e.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
            Log.d("IOException","----------------"+e.toString());
        }
        catch (JSONException e) {
            e.printStackTrace();
            Log.d("JSONException","----------------"+e.toString());

        }
        return listMachine;
    }
    @Override
    protected void onPostExecute(ArrayList<Machine> lm) {
        super.onPostExecute(lm);
        callBackMachine.setCallBackMachine(lm);

    }
}