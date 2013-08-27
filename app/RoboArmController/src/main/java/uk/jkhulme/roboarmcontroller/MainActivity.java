package uk.jkhulme.roboarmcontroller;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void connectToServer(View v) throws IOException{
        String url = "http://192.168.1.74:8000";

        try {
            Boolean success = new ConnectToServer().execute(url).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private class ConnectToServer extends AsyncTask<String, Void, Boolean> {

        public Boolean doInBackground(String... urls) {
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.getInputStream();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
    }
    
}
