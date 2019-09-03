package com.work.asinghi.marvelbooking.network;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetLocationList extends AsyncTask<String, Void, String> {


    public interface AsyncResponse {
        void processFinish(String output);
    }
    public AsyncResponse delegate = null;

    public GetLocationList(AsyncResponse delegate) {
        this.delegate = delegate;
    }


    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection con = null;
        BufferedReader reader = null;
        StringBuilder result = null;

        if (strings == null) {
            return null;
        }

        try {
            URL url = new URL(strings[0]);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            result = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            while (null != (line=reader.readLine())) {
                result.append(line).append("\n");
            }

            return result.toString();

        } catch (MalformedURLException e) {
            Log.e("Error", "doInBackground: Invalid URL " + e.getMessage());
        } catch(IOException e) {
            Log.e("Error", "doInBackground: IO Error " + e.getMessage() );
        } finally {
            if (con != null) {
                con.disconnect();
            }
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e("Error", "doInBackground: Error Closing" );
                }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        delegate.processFinish(s);
    }

}
