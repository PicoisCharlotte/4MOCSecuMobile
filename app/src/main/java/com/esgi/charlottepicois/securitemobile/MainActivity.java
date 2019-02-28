package com.esgi.charlottepicois.securitemobile;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
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
        String key = "";
        String value = "";
        textValue.setLength(0);
        for (Map.Entry<String, String> entry : listValues.entrySet()){
            value = entry.getValue();
            key = entry.getKey();

            listValues2.put(key, value);
            if(key.equals(idSelected.substring(idSelected.length() - 1))) {
                textValue.append('\n');
                textValue.append(value);
            }
        };
        tvValue.setText("");
        tvValue.setText(textValue.toString());
    }

    @Override public void onNothingSelected(AdapterView<?> parent) {}

    public void readMyJsonFile(){
        tvValue.setText("");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("sms.txt"), "UTF-8"));
            String line;
            String text = "";
            String value = "";
            String key = "";
            while ((line = br.readLine()) != null) {
                if(line.contains("#")){
                    key = line.replace("#", "");
                    text = "";
                    listIds.add("Sms n° " + key);
                } else {
                    if(line.contains(": ")) {
                        text += '\n';
                        text += line;
                    }
                }
                listValues.put(key, text);
            }

            br.close();

        } catch (IOException e) {
            Log.v(TAG, "try/catch error : " + e);
        }

    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {}
}
