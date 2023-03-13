package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_constraint);
        Button btn = (Button)findViewById(R.id.button);
        EditText edt = (EditText)findViewById(R.id.editText) ;
        TextView txt = (TextView)findViewById(R.id.textView) ;
        CheckBox chk = (CheckBox)findViewById(R.id.checkbox);
        btn.setOnClickListener((click) -> {txt.setText(edt.getText());

            Toast.makeText(MainActivity.this, R.string.toast_message_lab2 , Toast.LENGTH_LONG ).show();
        });

        chk.setOnCheckedChangeListener((cb, isChecked) -> {
            Snackbar.make(chk, "Checkbox is " + isChecked, Snackbar.LENGTH_LONG)
                    .setAction("Undo", click-> chk.setChecked(!isChecked))
                    .show();
        });





    }

}