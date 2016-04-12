package com.example.grace.travelexperts;

//Created by Mary Grace
// List Activity to choose the agency


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

// for agencies list
// first activity
public class ListActivity extends AppCompatActivity {

    EditText inputNum = null;
    Button loadAgentInfo;
    URL url = null;
    String json = null;
    ListView agencyLV;
    ArrayList<Agency> agencies;
    ArrayList<HashMap<String,String>> agenciesData;
    SimpleAdapter adapter;
    String agtFirstName;
    String agtMiddleInitial;
    String agtLastName;
    String agtBusPhone;
    String agtEmail;
    String agtPosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        agencyLV = (ListView) findViewById(R.id.agencyLV);

        new Thread(new Runnable() {
            public void run() {
                agencies = new ArrayList<Agency>();
                agenciesData = new ArrayList<HashMap<String, String>>();

                try{
                    URL url = new URL ("http://" + MyConfig.myLink + "/Agenciesinfo/get_agencies.php"); //php connections
                    URLConnection connection = url.openConnection();
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    json = in.readLine();
                    in.close();
                    JSONObject  jsonRootObject = new JSONObject(json);
                    JSONArray jsonArray = jsonRootObject.optJSONArray("agents");

                    //Iterate the jsonArray and print the info of JSONObjects
                    for(int i=0; i < jsonArray.length(); i++){
                        Agency agency = new Agency();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        agency.setAgencyId(Integer.parseInt(jsonObject.optString("AgencyId").toString()));
                        agency.setAgncyAddress(jsonObject.optString("AgncyAddress").toString());
                        agency.setAgncyCity(jsonObject.optString("AgncyCity").toString());
                        agency.setAgncyProv(jsonObject.optString("AgncyProv").toString());
                        agency.setAgncyPostal(jsonObject.optString("AgncyPostal").toString());
                        agency.setAgncyCountry(jsonObject.optString("AgncyCountry").toString());
                        agency.setAgncyPhone(jsonObject.optString("AgncyPhone").toString());
                        agency.setAgncyFax(jsonObject.optString("AgncyFax").toString());
                        agencies.add(agency);
                    }

                    for (Agency agncy : agencies)
                    {
                        String agncyAddress = agncy.getAgncyAddress() + " " + agncy.getAgncyCity() + "\n" + agncy.getAgncyProv() + " " + agncy.getAgncyCountry() + ", " + agncy.getAgncyPostal();
                        String agncyContact = "Phone: " + agncy.getAgncyPhone() + "\nFax: " + agncy.getAgncyFax();
                        HashMap<String, String> hm = new HashMap<String, String>();
                        hm.put("AgncyAddress", agncyAddress);
                        hm.put("AgncyContact", agncyContact);
                        agenciesData.add(hm);
                    }

                    runOnUiThread(new Runnable() {
                        public void run() {
                            //inputNum1.setText(json);
                            displayAgencies();
                        }
                    });
                }catch(Exception e)
                {
                    Log.d("Exception",e.toString());
                }
            }
        }).start();

        agencyLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Store variable into the intent
                Agency agncy = agencies.get(position);
                Intent intent = new Intent(getApplicationContext(), ListAgentActivity.class);
                intent.putExtra("agencyId", agncy.getAgencyId() + "");
                startActivity(intent);
            }
        });
    }

    public void displayAgencies() {
        // Set the adapter
        String [] from = {"AgncyAddress", "AgncyContact"};
        int [] to = {R.id.tvAgncyAddress, R.id.tvAgncyContact};
        adapter = new SimpleAdapter(this, agenciesData,R.layout.listview_agency, from, to);
        agencyLV.setAdapter(adapter);
    }
}