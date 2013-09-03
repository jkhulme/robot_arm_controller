package uk.jkhulme.roboarmcontroller;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
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
        EditText ip_address = (EditText)findViewById(R.id.editTextIp);
        String ip = ip_address.getText().toString();
        System.out.println(ip);
        EditText code = (EditText)findViewById(R.id.editTextCode);
        String code_text = code.getText().toString();
        String url = "http://" + ip + ":8000/code=" + code_text;
        try {
            Boolean success = new ConnectToServer().execute(url).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(this, RemoteControlActivity.class);
        startActivity(intent);

    }

    private class ConnectToServer extends AsyncTask<String, Void, Boolean> {

        public Boolean doInBackground(String... urls) {
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL(urls[0] + "&command=0000");
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
