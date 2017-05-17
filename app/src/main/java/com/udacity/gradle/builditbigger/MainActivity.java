package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jokeMaker;
import com.example.merwiji.myapplication.backend.myApi.MyApi;

import com.follyapps.jokedisplay.MainJokeDisplay;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;


import java.io.IOException;
import android.util.Log;
import com.example.jokeMaker;



public class MainActivity extends AppCompatActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        new endpointAsyncTask().execute();
    }

    public class endpointAsyncTask extends AsyncTask<String, Void, String>{
        private  MyApi myApiService = null;
        private Context context;

        @Override
        protected String doInBackground(String... params)  {
            if(myApiService == null) {
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("http://97.94.225.147:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer(){
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                myApiService = builder.build();
            }
            try {
                return myApiService.makeAJoke().execute().getData();
            } catch (IOException e) {
                Log.i("MainActivity", "AsyncTask IOException e");
                return "";
            }
        }
        @Override
        protected void onPostExecute(String result) {
//                    Bundle bundle = new Bundle();
//        bundle.putString("BUNDLE_JOKE", result);
//        Intent myIntent = new Intent(context, MainJokeDisplay.class);
//        myIntent.putExtras(bundle);
//        startActivity(myIntent);
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            Log.i("MainActivity", "onPostExecute result = " + result);
        }
        }



}
