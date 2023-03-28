package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ArrayList<AdapterList> elements = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            Button btn = findViewById(R.id.button);
            Switch swtch = findViewById(R.id.switch1);
            EditText txt = findViewById(R.id.editTextTextPersonName);
            ListView myList = findViewById(R.id.mylistview);


            MyListAdapter myAdapter = new MyListAdapter();
            myList.setAdapter(myAdapter);

            myList.setOnItemLongClickListener((p,b,pos,id)->{
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("A Title")

                .setMessage("Do you want to delete this? " + myAdapter.getItem(pos))
                        .setPositiveButton("Yes", (click, arg) ->{

                            elements.remove(pos);
                            myAdapter.notifyDataSetChanged();
                        })
                        .setNegativeButton("NO", (click, arg) ->{})
                        .setNeutralButton("Maybe", (click, arg) ->{})
                        .setView(getLayoutInflater().inflate(R.layout.finish_todo, null))
                        .create().show();
                return true;
            });


        btn.setOnClickListener((click) -> {
        elements.add(new AdapterList(txt.getText().toString(), swtch.isChecked()));
        txt.setText("");
        myAdapter.notifyDataSetChanged();
        });

    }
    private class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return elements.size();
        }

        @Override
        public Object getItem(int position) {
            return "This is row " + (position +1);
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
                newView = inflater.inflate(R.layout.finish_todo,parent, false );
            }
            TextView tView = newView.findViewById(R.id.textView);
            tView.setText(elements.get(position).getText());
            if(elements.get(position).getBoolean() == true){
                tView.setTextColor(Color.WHITE);
                newView.setBackgroundColor(Color.RED);
            }
            //return it to be put in the table
            return newView;
        }
    }
}