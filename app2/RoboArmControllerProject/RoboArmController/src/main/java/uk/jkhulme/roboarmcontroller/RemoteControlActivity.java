package uk.jkhulme.roboarmcontroller;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import java.net.HttpURLConnection;
import java.net.URL;

public class RemoteControlActivity extends Activity {
    private World world = World.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_control);
    }

    public Integer queryServer(String url) {
        Integer response_code = 0;
        try {
            response_code = new ConnectToServer().execute(url).get();
            return response_code;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String buildQuery(String command) {
        return "http://" + world.getIp() + ":8000/code=" + world.getCode()+ "&command=" + command;
    }

    public void rotateCCW(View v) {
        queryServer(buildQuery(world.getROTATE_CCW()));
    }

    public void rotateCW(View v) {
        queryServer(buildQuery(world.getROTATE_CW()));
    }

    public void shoulderUp(View v) {
        queryServer(buildQuery(world.getSHOULDER_UP()));
    }

    public void shoulderDown(View v) {
        queryServer(buildQuery(world.getSHOULDER_DOWN()));
    }

    public void elbowUp(View v) {
        queryServer(buildQuery(world.getELBOW_UP()));
    }

    public void elbowDown(View v) {
        queryServer(buildQuery(world.getELBOW_DOWN()));
    }

    public void wristUp(View v) {
        queryServer(buildQuery(world.getWRIST_UP()));
    }

    public void wristDown(View v) {
        queryServer(buildQuery(world.getWRIST_DOWN()));
    }

    public void gripOpen(View v) {
        queryServer(buildQuery(world.getGRIP_OPEN()));
    }

    public void gripClose(View v) {
        queryServer(buildQuery(world.getGRIP_CLOSE()));
    }

    public void toggleLight(View v) {
        queryServer(buildQuery(world.getLIGHT_ON()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.remote_control, menu);
        return true;
    }

    private class ConnectToServer extends AsyncTask<String, Void, Integer> {

        public Integer doInBackground(String... urls) {
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                int response = con.getResponseCode();
                return response;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }
    }
}
