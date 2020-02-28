package com.example.p1cash.utils;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.p1cash.R;
import com.example.p1cash.activity.MainActivity;
import com.example.p1cash.models.Machine;

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
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.callback.Callback;

public class GetMachineData extends AsyncTask<String,Void,String>{

    public interface CallBackMachine{
        void setCallBack(String data);
    }

    private CallBackMachine callBackMachine;

    public void setCallBackMachine(CallBackMachine callBackMachine){
        this.callBackMachine=callBackMachine;
    }

    @Override
    protected String doInBackground(String... url) {
        String data="";
        String dataParse="";
        String singleParsed="";
        String line ="";
        //--------------------------------------------
        try {
            TrustManager[] victimizedManager = new TrustManager[]{

                    new X509TrustManager() {

                        public X509Certificate[] getAcceptedIssuers() {

                            X509Certificate[] myTrustedAnchors = new X509Certificate[0];

                            return myTrustedAnchors;
                        }

                        @Override
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }
                    }
            };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, victimizedManager, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        //-----------------------------------------------------------------
        URL urlMachine;
        HttpURLConnection connection;
        InputStream inputStream;
        BufferedReader bufferedReader;
        try {
            //test de connection via une api externe : "https://pokeapi.co/api/v2/pokemon" (fonctionnel 18-1-20)
            //url originale: https://localhost:44327/api/Machine //10.0.2.2:8080 //piv4 : 10.10.19.90
            urlMachine = new URL(url[0]);
            Log.d(" URL ENCODEE",urlMachine.toString());
            Log.d("avancement thread","string url envoiyee");
            connection = (HttpURLConnection) urlMachine.openConnection();
            Log.d("avancement thread","objet connection creee");
            connection.setRequestMethod("GET");
            Log.d("avancement thread","get sur connection cree");
            Log.d("type requete",connection.getRequestMethod());
            Log.d("url",connection.getURL().toString());
            connection.connect();
            Log.d("avancement thread","connection faite");
            Log.d("contenus reponse",connection.getResponseMessage());

            inputStream = connection.getInputStream();
            Log.d("----retour inputStream", "--------------------wat inside :" + inputStream);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            Log.d("----retour buffer", "--------------------wat inside :" + bufferedReader);
            while(line!=null){
                line = bufferedReader.readLine();
                data +=line;
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
        Log.d("------------retour data", "--------------------wat inside :" + data);
        return data;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        callBackMachine.setCallBack(s);

    }
}