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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CatImages req = new CatImages();
        req.execute("https://cataas.com/cat?json=true");
    }
    private class CatImages extends AsyncTask<String, Integer, String> {
        public Bitmap nerd;
        @Override
        protected String doInBackground(String... args) {
            try {

                //create a URL object of what server to contact:
                URL url = new URL(args[0]);

                //open the connection
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                //wait for data:
                InputStream response = urlConnection.getInputStream();


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
                String fileName = uvReport.getString("url");
                String id = uvReport.getString("id");
                URL url2 = new URL("https://cataas.com" + fileName);
                System.out.println("https://cataas.com" + fileName);
                HttpURLConnection urlConnection2 = (HttpURLConnection) url2.openConnection();
                InputStream response2 = urlConnection2.getInputStream();
                nerd = BitmapFactory.decodeStream(response2);
            }
            catch (Exception e)
            {
                System.out.println("This caught");
            }

            return "Done";
        }

        @Override
        protected void onProgressUpdate(Integer... args) {
            ImageView i = findViewById(R.id.imageView);
            i.setImageBitmap(nerd);
        }

        @Override
        protected void onPostExecute(String fromDoInBackground) {
            //It goes here last, the picture remains null the whole time
            ImageView i = findViewById(R.id.imageView);
            i.setImageBitmap(nerd);
            Log.i("HTTP", fromDoInBackground);
        }
    }
}