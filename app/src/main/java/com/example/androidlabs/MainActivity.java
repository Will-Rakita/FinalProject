package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            Button btn = findViewById(R.id.button);
            Switch swtch = findViewById(R.id.switch1);
            EditText txt = findViewById(R.id.editTextTextPersonName);
            ListView mylistview = findViewById(R.id.mylistview);

        ArrayList<AdapterList> todo = new ArrayList<AdapterList>();

        btn.setOnClickListener((click) -> {
        todo.add(new AdapterList(txt.toString(), false));
        });

    }
}