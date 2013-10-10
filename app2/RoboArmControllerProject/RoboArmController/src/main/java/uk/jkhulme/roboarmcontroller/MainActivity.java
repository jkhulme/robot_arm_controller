package uk.jkhulme.roboarmcontroller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity {
    private World world = World.getInstance();
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
        world.setIp(ip);
        EditText code = (EditText)findViewById(R.id.editTextCode);
        String code_text = code.getText().toString();
        world.setCode(code_text);
        String url = "http://" + ip + ":8000/code=" + code_text;
        Integer response_code = 0;
        try {
            response_code = new ConnectToServer().execute(url).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (response_code == 200) {
            Intent intent = new Intent(this, RemoteControlActivity.class);
            startActivity(intent);
        } else if (response_code == 401) {
            new AlertDialog.Builder(this)
                    .setTitle("Authentication Error")
                    .setMessage("Incorrect code")
                    .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Connection Error")
                    .setMessage("Could not connect to that ip address.\n" +
                                "Please ensure the details are correct")
                    .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();
        }
    }

    private class ConnectToServer extends AsyncTask<String, Void, Integer> {

        public Integer doInBackground(String... urls) {
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL(urls[0] + "&command=" + world.getLOGIN());
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                int response = con.getResponseCode();
                System.out.println("RESPONSE2: " + Integer.toString(response));
                return response;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }
    }
    
}
