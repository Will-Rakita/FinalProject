package com.example.androidlabs;

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
public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    public String currentDate;

    @Override
    protected void onPause() {
        super.onPause();


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.buttonPickDate);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");


            }
        }
        );

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        currentDate = year + "-" + month + "-" + day;
        System.out.println(currentDate);
        SharedPreferences prefs = getSharedPreferences("SavedDateWeek13", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();
        Intent nextPage = new Intent(this, DisplayImage.class);
        edit.putString("DateText", currentDate);
        edit.commit();
        startActivityForResult(nextPage, 1);
    }

    }
