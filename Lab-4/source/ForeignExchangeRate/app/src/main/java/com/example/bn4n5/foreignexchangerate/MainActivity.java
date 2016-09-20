package com.example.bn4n5.foreignexchangerate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    String API_URL = "https://api.fullcontact.com/v2/person.json?";
    String API_KEY = "b29103a702edd6a";
    public int to;
    public int from;
    public String [] val;
    TextView output;
    public String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = (TextView) findViewById(R.id.result);
        Spinner s1 = (Spinner) findViewById(R.id.spinner1);
        Spinner s2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.country_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        val = getResources().getStringArray(R.array.value);
        s1.setAdapter(adapter);
        s2.setAdapter(adapter);
        s1.setOnItemSelectedListener(new spinOne(1));
        s2.setOnItemSelectedListener(new spinOne(2));

    }
    public void logout(View v) {
        Intent redirect = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(redirect);
    }
    public void exchange(View v) {
        String getURL ="http://api.fixer.io/latest?base="+val[from]+"&symbols="+val[to];
        OkHttpClient client = new OkHttpClient();
        try {
            Request request = new Request.Builder()
                    .url(getURL)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println(e.getMessage());
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final JSONObject jsonResult;
                    final String result = response.body().string();
                    try {
                        jsonResult = new JSONObject(result);
                        final String convertedText=jsonResult.getJSONObject("rates").getString(val[to]);
                        Log.d("okHttp", jsonResult.toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                s="1 "+val[from]+" = "+convertedText+" "+val[to];
                                output.setText(s);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });


        } catch (Exception ex) {
            output.setText(ex.getMessage());

        }

    }
        private class spinOne implements OnItemSelectedListener
        {
            int ide;
            spinOne(int i)
            {
                ide =i;
            }
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int index, long id) {
                if(ide == 1)
                    from = index;
                else if(ide == 2)
                    to = index;

            }

            public void onNothingSelected(AdapterView<?> arg0) {

            }
    }
}
