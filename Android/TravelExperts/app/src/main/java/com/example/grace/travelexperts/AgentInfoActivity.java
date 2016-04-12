package com.example.grace.travelexperts;

//Created by Mary Grace
// agent info list

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

// for agent informations
// the third activity
public class AgentInfoActivity extends AppCompatActivity {
    TextView tvAgtName;
    TextView tvAgtBusPhone;
    TextView tvAgtEmail;
    TextView tvAgtPosition;
    ImageView ivAgtPic;
    Bitmap bitmap;
    ProgressDialog pDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agent_info_activity);
        tvAgtName = (TextView) findViewById( R.id.tvAgtName);
        tvAgtPosition = (TextView) findViewById(R.id.tvAgtPosition);
        tvAgtBusPhone = (TextView) findViewById(R.id.tvAgtBusPhone);
        tvAgtEmail = (TextView) findViewById(R.id.tvAgtEmail);
        ivAgtPic = (ImageView) findViewById(R.id.ivAgtPic);

        // Fill the TextView with value selected
        Intent intent = getIntent();
        tvAgtName.setText(intent.getStringExtra("agtName"));
        tvAgtPosition.setText(intent.getStringExtra("agtPosition"));
        tvAgtBusPhone.setText(intent.getStringExtra("agtBusPhone"));
        tvAgtEmail.setText(intent.getStringExtra("agtEmail"));
        new LoadImage().execute("http://" + MyConfig.myLink + "/AgentPicture/" + intent.getStringExtra("agtId") + ".jpg"); // agent picture stored in www directory

    }

    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AgentInfoActivity.this);
            pDialog.setMessage("Loading Image ....");
            pDialog.show();

        }
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream)new URL(args[0]).getContent());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap image) {

            if(image != null){
                ivAgtPic.setImageBitmap(image);
                pDialog.dismiss();

            }else{

                pDialog.dismiss();
                Toast.makeText(AgentInfoActivity.this, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();

            }
        }
    }
}