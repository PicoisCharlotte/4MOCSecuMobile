package com.esgi.charlottepicois.securitemobile;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.os.Environment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "MainActivity";

    Context mContext;

    HashMap<String, String> values = new HashMap<>();
    List<String> listIds = new ArrayList<>();
    //HashMap<String, HashMap<String, String>> listValues = new HashMap<>();
    HashMap<String, String> listValues = new HashMap<>();
    HashMap<String, String> listValues2 = new HashMap<>();

    private Spinner spinner;

    StringBuilder textKey = new StringBuilder();
    StringBuilder textValue = new StringBuilder();

    TextView tvKey;
    TextView tvValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getBaseContext();

        tvKey = (TextView)findViewById(R.id.textViewKey);
        tvValue = (TextView)findViewById(R.id.textViewValue);
        tvValue.setText("");

        readMyJsonFile();

        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item,listIds);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        showValues(listIds.get(position));

    }


    public void showValues(String idSelected){

        /*for (Map.Entry<String, String> entry : listValues.entrySet()){

            key2 = entry.getKey();
            value2 = entry.getValue();

            Log.v(TAG, "TEST >>>>>> ID SELECTED " + key2 + " -- " + idSelected.substring(idSelected.length() - 1));
            Log.v(TAG, "TEST >>>>>> AUTRE " + key2 + " -- " + value2);

        }*/
        /*for (String key : listValues.get(idSelected).keySet()) {
            textKey.append('\n');
            textKey.append(key);
        }
        for (String value : listValues.get(idSelected).values()) {

            textValue.append('\n');
            textValue.append(value);
        }*/
        String key = "";
        String value = "";
        textValue.setLength(0);
        for (Map.Entry<String, String> entry : listValues.entrySet()){
            value = entry.getValue();
            key = entry.getKey();

            listValues2.put(key, value);
            Log.v(TAG, "TEST >>>>>> " + key + " -- " + idSelected.substring(idSelected.length() - 1));
            if(key.equals(idSelected.substring(idSelected.length() - 1))) {
                textValue.append('\n');
                textValue.append(value);
            }
        };
        tvValue.setText("");
        tvValue.setText(textValue.toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void readMyJsonFile(){
        tvValue.setText("");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("testFile.txt"), "UTF-8"));

            String line;
            String text = "";
            String value = "";
            String key = "";
            while ((line = br.readLine()) != null) {
                if(line.contains("#")){
                    key = line.replace("#", "");
                    //if(values != null)
                        //values.put(key, value);
                    /*for(String val : values.values()){
                        Log.v(TAG, "TEST >>>>>>>>>>>> " + val);
                    }*/
                    //listValues.put(value, values);
                    text = "";
                    listIds.add("Sms n° " + key);
                } else {
                    if(line.contains(": ")) {
                        //String[] strings = line.split(": ");
                        //String key = strings[0];
                        //String value = strings[1];

                        //values.put(key, value);
                        text += '\n';
                        text += line;
                    }
                }
                //Log.v(TAG, "TEST >>>>>>>>>>> " + text);
                listValues.put(key, text);
            }

            br.close();

        }catch (IOException e) {
            Log.v(TAG, "try/catch error : " + e);
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
