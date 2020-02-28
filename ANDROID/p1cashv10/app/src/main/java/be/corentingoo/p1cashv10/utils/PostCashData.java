package be.corentingoo.p1cashv10.utils;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import be.corentingoo.p1cashv10.models.Cash;

public class PostCashData extends AsyncTask<Cash,Void,Void> {

    @Override
    protected Void doInBackground(Cash... cash) {
        Log.d("postcashdata","je passe ici!!!!!!!!!!!!!!!");
        JSONObject jo = new JSONObject();
        jo = cash[0].toJSONObject();
        URL urlCash=null;
        try {
            urlCash=new URL ("http://10.0.2.2:5000/Cash");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection connection;
        OutputStream outputStream=null;
        BufferedWriter bufferedWriter;
        StringBuilder stringBuilder=new StringBuilder();
        JSONArray json = new JSONArray();
        try {
            //Log.d(" URL ENCODEE",urlMachine.toString());
            //Log.d("avancement thread","string url envoiyee");

            connection = (HttpURLConnection) urlCash.openConnection();
            //Log.d("avancement thread","objet connection creee");

            connection.setRequestMethod("POST");

            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            //Log.d("avancement thread","get sur connection cree");
            //Log.d("type requete",connection.getRequestMethod());
            //Log.d("url",connection.getURL().toString());
            outputStream = connection.getOutputStream();
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            bufferedWriter.write(jo.toString());
            Log.d("jo.toString()",jo.toString());
            bufferedWriter.close();
            outputStream.close();
            connection.connect();
            Log.d("contenus reponse",connection.getResponseMessage());


            //Log.d("----retour inputStream", "--------------------wat inside :" + inputStream);


        }

        catch (MalformedURLException e) {
            e.printStackTrace();
            Log.d("MalformedURLException","----------------"+e.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
            Log.d("IOException","----------------"+e.toString());
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}