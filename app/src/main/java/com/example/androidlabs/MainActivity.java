package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
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
import java.util.ArrayList;
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

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    public String currentDate;
    private ArrayList<AdapterList> elements = new ArrayList<>();
    @Override
    protected void onPause() {
        super.onPause();


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.buttonPickDate);
        ListView myList = findViewById(R.id.ListView);

        updateDatabase dbOpener = new updateDatabase(this);
        SQLiteDatabase db = dbOpener.getWritableDatabase();
        String [] colums = {updateDatabase.COL_ID, updateDatabase.COL_DATE, updateDatabase.COL_HDURL, updateDatabase.COL_EXPLANATION};
        Cursor results = db.query(false, updateDatabase.TABLE_NAME, colums, null, null, null, null, null, null, null);
        printCursor(results);
        MyListAdapter myAdapter = new MyListAdapter();
        myList.setAdapter(myAdapter);



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
    private class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return elements.size();
        }

        @Override
        public Object getItem(int position) {
            return "This is row " + (position);
        }

        @Override
        public long getItemId(int position) {
            return (long)position;
        }

        @Override
        public View getView(int position, View old, ViewGroup parent) {
            View newView = old;
            LayoutInflater inflater = getLayoutInflater();

            //make new row:
            if(newView ==null){
                newView = inflater.inflate(R.layout.photo_display,parent, false );
            }
            TextView tView = newView.findViewById(R.id.textView2);
            tView.setText(elements.get(position).getDate());
            //return it to be put in the table
            return newView;
        }
    }
    private void printCursor(Cursor c){
        updateDatabase dbOpener = new updateDatabase(this);
        SQLiteDatabase db = dbOpener.getWritableDatabase();

        c.moveToFirst();
        while(!c.isAfterLast() ){

            String fn = c.getString( 1 );
            System.out.println("Page: " + fn);
            c.moveToNext(); }
    }
    private void loadDataFromDatabase(){
        updateDatabase dbOpener = new updateDatabase(this);
        SQLiteDatabase db = dbOpener.getWritableDatabase();

        String [] colums = {updateDatabase.COL_ID, updateDatabase.COL_DATE, updateDatabase.COL_HDURL, updateDatabase.COL_EXPLANATION};
        Cursor results = db.query(false, updateDatabase.TABLE_NAME, colums, null, null, null, null, null, null, null);

        int idCol = results.getColumnIndex(updateDatabase.COL_ID);
        int dateCol = results.getColumnIndex(updateDatabase.COL_DATE);
        int hdurlCol = results.getColumnIndex(updateDatabase.COL_HDURL) ;
        int explanationCol = results.getColumnIndex(updateDatabase.COL_EXPLANATION) ;
        while(results.moveToNext()){
            String date = results.getString(dateCol);
            String hdurl = results.getString(hdurlCol);
            String explanation = results.getString(explanationCol);
            elements.add(new AdapterList(date, explanation, hdurl));
        }

    }
}
