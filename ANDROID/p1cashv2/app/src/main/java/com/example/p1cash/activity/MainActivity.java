package com.example.p1cash.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.p1cash.R;
import com.example.p1cash.utils.GetMachineData;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    private ListView LV_Machine;
    public static TextView data;
    private Button BTN_Machine;
    private Button BTN_Poke;
    String urlpoke;
    String urlmachine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = findViewById(R.id.data);
        //test de connection via une api externe : "https://pokeapi.co/api/v2/pokemon" (fonctionnel 18-1-20)
        //url originale: https://localhost:44327/api/Machine //10.0.2.2:8080 //127.0.0.1:44327 // piv4 : 10.10.19.90
        urlpoke=  "https://pokeapi.co/api/v2/pokemon";
        urlmachine= "http://10.0.2.2:5000/values";//trop galère de crer une api publiee
        BTN_Machine = findViewById(R.id.button);
        BTN_Machine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BOUTTON","AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                GetMachineData getMachineData = new GetMachineData();
                getMachineData.setCallBackMachine(new GetMachineData.CallBackMachine() {
                    @Override
                    public void setCallBack(String retour) {
                        Log.d("METHOD","BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
                        data.setText(retour);
                    }
                });
                getMachineData.execute(urlpoke);
            }
        });
        BTN_Poke =findViewById(R.id.buttonpoke);
        BTN_Poke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BOUTTON","AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                GetMachineData getMachineData = new GetMachineData();
                getMachineData.setCallBackMachine(new GetMachineData.CallBackMachine() {
                    @Override
                    public void setCallBack(String retour) {
                        Log.d("METHOD","BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
                        data.setText(retour);
                    }
                });
                getMachineData.execute(urlmachine);
            }
        });
    }
}












        //if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)!= PackageManager.PERMISSION_GRANTED)         {
            //Toast.makeText(this,"vous avez la permission",Toast.LENGTH_LONG);

        //Future<ArrayList<Machine>> Machines = getAsyncMachine();

        //else{
        //   Toast.makeText(this,"vous avez pas la permission",Toast.LENGTH_LONG);

/*
        ArrayList<Machine> Machines = new ArrayList<>();

        //implementation des données brutes
        Machines.add(new Machine(1,50,0));
        Machines.add(new Machine(2,100,5));
        Machines.add(new Machine(3,150,10));
        Machines.add(new Machine(4,200,20));
*/
    /*
    public Future<ArrayList<Machine>> getAsyncMachine(){
        Future<ArrayList<Machine>> ListMachines;
        return ListMachines;
    }


    public ArrayList<Machine> getAsyncMachine(View v){
        startAsyncMachine asyncMachine =new startAsyncMachine();
        return asyncMachine.execute();
    }
*/
    /*
    private class ThreadGetMachines extends AsyncTask<Context,Void,Void> {
        Context context;

        ThreadGetMachines(Context context) {
            this.context = context;
        }

        @Override
        protected Void doInBackground(Context... context) {
            this.context = context[0];
            ArrayList<Machine> Machines = new ArrayList<Machine>();
            URL urlMachine;
            HttpURLConnection connection;
            InputStream inputStream;
            String test = "";
            try {
                urlMachine = new URL("https://localhost:44327/Machine");
                try {
                    connection = (HttpURLConnection) urlMachine.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();
                    inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder stringBuilder = new StringBuilder(bufferedReader.read());
                    test = stringBuilder.toString();
                    Log.d("---------------TEST", "-------------wat inside " + test);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            LV_Machine = (ListView) findViewById(R.id.LVMachine);
            MachineAdapter Ma = new MachineAdapter(this.context, R.layout.activity_management_machine, Machines);
            LV_Machine.setAdapter(Ma);
            return null;
        }
    }

     */
