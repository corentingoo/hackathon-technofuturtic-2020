package be.corentingoo.p1cashv8.utils;

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

import be.corentingoo.p1cashv8.models.Machine;


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

        ArrayList<Machine> listMachine =new ArrayList<>();
        Machine m ;
        String line ="";
        URL urlMachine;
        HttpURLConnection connection;
        InputStream inputStream;
        BufferedReader bufferedReader;
        StringBuilder stringBuilder=new StringBuilder();
        JSONArray json = new JSONArray();
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
                m = new Machine(jsonObject.getInt("id"),jsonObject.getInt("idParking"));
                listMachine.add(m);
            }
            for(int i=0;i<listMachine.size();i++) {
                Log.d("listmachine", "id machine"+Integer.toString(listMachine.get(i).Id()));
            }
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