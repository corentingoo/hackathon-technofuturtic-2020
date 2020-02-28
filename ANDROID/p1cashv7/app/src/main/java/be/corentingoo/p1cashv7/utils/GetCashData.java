package be.corentingoo.p1cashv7.utils;

import android.content.Context;
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

import be.corentingoo.p1cashv7.models.Cash;


public class GetCashData extends AsyncTask<URL,Void, ArrayList<Cash>>{

    public interface CallBackCash{
        void setCallBackCash(ArrayList<Cash> lc);
    }

    private CallBackCash callBackCash;

    public void setCallBackCash(CallBackCash callBackCash){
    this.callBackCash=callBackCash;
    }

        @Override
        protected ArrayList<Cash> doInBackground(URL... urls) {

        ArrayList<Cash> listCash =new ArrayList<>();
        Cash c ;
        String line ="";
        URL urlCash;
        HttpURLConnection connection;
        InputStream inputStream;
        BufferedReader bufferedReader;
        StringBuilder stringBuilder=new StringBuilder();
        JSONArray json = new JSONArray();
        try {
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
                c = new Cash(jsonObject.getInt("id"),jsonObject.getInt("idTypeCash"),jsonObject.getString("typeCash"),jsonObject.getInt("maxCapacity"),jsonObject.getInt("currentCapacity"),jsonObject.getInt("idMachine"));
                listCash.add(c);
            }
            for(int i=0;i<listCash.size();i++) {
                Log.d("listcash", "id:"+Integer.toString(listCash.get(i).IdMachine())+"type:"+Integer.toString(listCash.get(i).IdTypeCash())+"current capacity:"+Integer.toString(listCash.get(i).CurrentCapacity()));
            }
            //          StringBuilder stringBuilder = new StringBuilder(bufferedReader.read());
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
        return listCash;
    }
        @Override
        protected void onPostExecute(ArrayList<Cash> lc) {
        super.onPostExecute(lc);
        callBackCash.setCallBackCash(lc);

    }
}
