package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public Bitmap nerd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CatImages req = new CatImages();
        req.execute("https://cataas.com/cat?json=true");
    }
    private class CatImages extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {

                //create a URL object of what server to contact:
                URL url = new URL(strings[0]);
                URL url2 = new URL("https://cataas.com/cat");
                //open the connection
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                HttpURLConnection urlConnection2 = (HttpURLConnection) url2.openConnection();
                //wait for data:
                InputStream response = urlConnection.getInputStream();
                InputStream response2 = urlConnection2.getInputStream();

                nerd = BitmapFactory.decodeStream(response2);

                //JSON reading:
                //Build the entire string response:
                BufferedReader reader = new BufferedReader(new InputStreamReader(response, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();

                String line = null;
                while ((line = reader.readLine()) != null)
                {
                    sb.append(line + "\n");
                }
                String result = sb.toString(); //result is the whole string


                // convert string to JSON:
                JSONObject uvReport = new JSONObject(result);
                //THIS IS THE FINALLY THE JSON ABOVE
                String fileName = uvReport.getString("file");

            }
            catch (Exception e)
            {

            }

            return "Done";
        }

        @Override
        protected void onProgressUpdate(Integer... args) {

        }

        @Override
        protected void onPostExecute(String fromDoInBackground) {
            //It goes here last, the picture remains null the whole time
            Log.i("HTTP", fromDoInBackground);
        }
    }
}