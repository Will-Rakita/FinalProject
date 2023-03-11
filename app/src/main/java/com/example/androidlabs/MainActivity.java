package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_constraint);
        Button btn = (Button)findViewById(R.id.button);
        EditText edt = (EditText)findViewById(R.id.editText) ;
        TextView txt = (TextView)findViewById(R.id.textView) ;

    }
}