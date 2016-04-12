package com.example.grace.travelexperts;

//Created by grace
// list agent activity

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

import android.content.Intent;
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

// agents information
// seconds screen
public class ListAgentActivity extends AppCompatActivity {

    URL url = null;
    String json = null;
    ListView agentLV;
    ArrayList<Agent> agents;
    ArrayList<HashMap<String,String>> agentsData;
    SimpleAdapter adapter;
    String agtMiddleInitial;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agent_activity);

        agentLV = (ListView) findViewById(R.id.agentLV);

        new Thread(new Runnable() {
            public void run() {
                agents = new ArrayList<Agent>();
                agentsData = new ArrayList<HashMap<String, String>>();

                try{
                    URL url = new URL("http://" + MyConfig.myLink + "/Agentsinfo/get_agents.php"); // php connections
                    URLConnection connection = url.openConnection();
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    json = in.readLine();
                    in.close();
                    JSONObject  jsonRootObject = new JSONObject(json);
                    JSONArray jsonArray = jsonRootObject.optJSONArray("agents");
                    Intent intent = getIntent();

                    //Iterate the jsonArray and print the info of JSONObjects
                    for(int i=0; i < jsonArray.length(); i++){
                        Agent agent = new Agent();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        if(Integer.parseInt(intent.getStringExtra("agencyId")) == Integer.parseInt(jsonObject.optString("AgencyId").toString())) {
                            agent.setAgentId(Integer.parseInt(jsonObject.optString("AgentId").toString()));
                            agent.setAgtFirstName(jsonObject.optString("AgtFirstName").toString());
                           // agent.setAgtMiddleInitial(jsonObject.optString("AgtMiddleInitial").toString());
                            agent.setAgtLastName(jsonObject.optString("AgtLastName").toString());
                            agent.setAgtBusPhone(jsonObject.optString("AgtBusPhone").toString());
                            agent.setAgtEmail(jsonObject.optString("AgtEmail").toString());
                            agent.setAgtPosition(jsonObject.optString("AgtPosition").toString());
                            agent.setAgencyId(Integer.parseInt(jsonObject.optString("AgencyId").toString()));
                            agents.add(agent);
                        }
                    }

                   for (Agent a : agents)
                   {
                 //       if (a.getAgtMiddleInitial().isEmpty()) {
                  //          agtMiddleInitial = "";
                  //      } else {
                    //        agtMiddleInitial = " " + a.getAgtMiddleInitial();
                  //      }
                        String agtName = a.getAgtFirstName() + " " + a.getAgtLastName();
                        String agtBusPhone = "Phone: " + a.getAgtBusPhone();
                        String agtEmail = "Email: " + a.getAgtEmail();
                        String agtPosition = "Position: " + a.getAgtPosition();
                        HashMap<String, String> hm = new HashMap<String, String>();
                        hm.put("AgtName", agtName);
                        hm.put("AgtBusPhone", agtBusPhone);
                        hm.put("AgtEmail", agtEmail);
                        hm.put("AgtPosition", agtPosition);
                        agentsData.add(hm);
                    }

                    runOnUiThread(new Runnable() {
                        public void run() {
                            //inputNum1.setText(json);
                            displayAgents();
                        }
                    });
                }catch(Exception e)
                {
                    Log.d("Exception",e.toString());
                }
            }
        }).start();

        agentLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Store variable into the intent
                Agent a = agents.get(position);

               // if (a.getAgtMiddleInitial().isEmpty()) {
                //    agtMiddleInitial = "";
             //   } else {
             //       agtMiddleInitial = " " + a.getAgtMiddleInitial();
            //    }
                String agtId = Integer.toString(a.getAgentId());
                String agtName = a.getAgtFirstName() + " " + a.getAgtLastName();
                String agtPosition = "( " + a.getAgtPosition() + " )";
                String agtBusPhone = a.getAgtBusPhone();
                String agtEmail = a.getAgtEmail();
                String agencyId = Integer.toString(a.getAgencyId());

                Intent intent = new Intent(getApplicationContext(), AgentInfoActivity.class);
                intent.putExtra("agtId", agtId);
                intent.putExtra("agtName", agtName);
                intent.putExtra("agtPosition", agtPosition);
                intent.putExtra("agtBusPhone", agtBusPhone);
                intent.putExtra("agtEmail", agtEmail);
                intent.putExtra("agencyId", agencyId);
                startActivity(intent);
            }
        });
    }

    public void displayAgents() {
        // Set the adapter
        String [] from = {"AgtName", "AgtPosition", "AgtBusPhone", "AgtEmail"};
        int [] to = {R.id.tvAgtName, R.id.tvAgtPosition, R.id.tvAgtBusPhone, R.id.tvAgtEmail};
        adapter = new SimpleAdapter(this, agentsData,R.layout.listview_agent, from, to);
        agentLV.setAdapter(adapter);
    }
}