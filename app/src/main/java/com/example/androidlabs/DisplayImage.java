package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.Calendar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import android.content.Intent;
import android.widget.TextView;

public class DisplayImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);
        Button GoBack = findViewById(R.id.BackToFirstPage);
        NasaImages req = new NasaImages();
        SharedPreferences prefs = getSharedPreferences("SavedDateWeek13", Context.MODE_PRIVATE);
        String editSent = prefs.getString("DateText", "2020-01-01" );
        req.execute("https://api.nasa.gov/planetary/apod?api_key=DgPLcIlnmN0Cwrzcg3e9NraFaYLIDI68Ysc6Zh3d&date=" + editSent);
        GoBack.setOnClickListener(click ->{
            setResult(0);
            finish();
        });

    }
    private class NasaImages extends AsyncTask<String, Integer, String> {
        public Bitmap image;
        public String Explanation;

        @Override
        protected String doInBackground(String... args) {
            for (int i = 0; i < 100; i++) {
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
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    String result = sb.toString(); //result is the whole string
                    // convert string to JSON:
                    JSONObject uvReport = new JSONObject(result);
                    //THIS IS THE FINALLY THE JSON ABOVE
                    String fileName = uvReport.getString("title");
                    String jpgUrl = uvReport.getString("hdurl");
                    String fileExplanation = uvReport.getString("explanation");
                    Explanation = fileExplanation;

                    //getting the image
                    URL url2 = new URL(jpgUrl);
                    HttpURLConnection urlConnection2 = (HttpURLConnection) url2.openConnection();
                    InputStream response2 = urlConnection2.getInputStream();
                    image = BitmapFactory.decodeStream(response2);
                    publishProgress(i);

                } catch (Exception e) {
                    System.out.println("This caught");
                }

            }

            return "Done";
        }

        @Override
        protected void onProgressUpdate(Integer... args) {
            ImageView i = findViewById(R.id.imageViewSecondPage);
            TextView t = (TextView)findViewById(R.id.textView3);
            t.setText(Explanation.toString());
            i.setImageBitmap(image);
        }

        @Override
        protected void onPostExecute(String fromDoInBackground) {
            ImageView i = findViewById(R.id.imageViewSecondPage);
            i.setImageBitmap(image);
        }
    }
}