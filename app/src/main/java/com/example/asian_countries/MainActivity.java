package com.example.asian_countries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.asian_countries.Adapter.CountryAdapter;
import com.example.asian_countries.Model.AllCountriesModel.CountryInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<CountryInfo> countryInfoList;
    CountryAdapter countryAdapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        countryInfoList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        apiCall();
    }

    private void apiCall() {

        String URL = "https://restcountries.eu/rest/v2/region/asia";

        StringRequest request = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i=0; i<jsonArray.length(); i++)
                            {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String countryname = jsonObject.getString("name");
                                String flag = jsonObject.getString("flag");

                                CountryInfo countryInfo = new CountryInfo(countryname, flag);
                                countryInfoList.add(countryInfo);
                            }
                            countryAdapter= new CountryAdapter(MainActivity.this,countryInfoList);
                            recyclerView.setAdapter(countryAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, ""+error, Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);

    }
}