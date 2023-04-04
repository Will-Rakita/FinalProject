package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    protected void onPause() {
        super.onPause();


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            Button btn = findViewById(R.id.button);
            Switch swtch = findViewById(R.id.switch1);
            EditText txt = findViewById(R.id.editTextTextPersonName);
            ListView myList = findViewById(R.id.mylistview);

        updateDatabase dbOpener = new updateDatabase(this);
        SQLiteDatabase db = dbOpener.getWritableDatabase();
            MyListAdapter myAdapter = new MyListAdapter();
            myList.setAdapter(myAdapter);

        loadDataFromDatabase();
            myList.setOnItemLongClickListener((p,b,pos,id)->{
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("A Title")

                .setMessage("Do you want to delete this? " + myAdapter.getItem(pos))
                        .setPositiveButton("Yes", (click, arg) ->{
                            deleteElement(elements.get(pos));
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
            int isChecked;
            if(swtch.isChecked()){
                isChecked = 1;
            }
            else{
                isChecked = 0;
            }

            ContentValues newRowValues = new ContentValues();
            newRowValues.put(updateDatabase.COL_NAME,txt.getText().toString() );
            newRowValues.put(updateDatabase.COL_URGENT,isChecked );
            long newId = db.insert(updateDatabase.TABLE_NAME, null, newRowValues);

        elements.add(new AdapterList(txt.getText().toString(), swtch.isChecked(), myAdapter.getCount()));
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
            if(elements.get(position).getBoolean()){
                tView.setTextColor(Color.WHITE);
                newView.setBackgroundColor(Color.RED);
            }
            //return it to be put in the table
            return newView;
        }
    }
    private void loadDataFromDatabase(){
        updateDatabase dbOpener = new updateDatabase(this);
        SQLiteDatabase db = dbOpener.getWritableDatabase();

        String [] colums = {updateDatabase.COL_ID, updateDatabase.COL_NAME, updateDatabase.COL_URGENT};
        Cursor results = db.query(false, updateDatabase.TABLE_NAME, colums, null, null, null, null, null, null, null);

        int idCol = results.getColumnIndex(updateDatabase.COL_ID);
        int nameCol = results.getColumnIndex(updateDatabase.COL_NAME);
        int urgentCol = results.getColumnIndex(updateDatabase.COL_URGENT) ;
        while(results.moveToNext()){
            String name = results.getString(nameCol);
            int urgent = results.getInt(urgentCol);

            boolean isUrgent;
            if(urgent == 1){
                isUrgent = true;
            }
            else{isUrgent = false;}
            elements.add(new AdapterList(name, isUrgent, results.getCount()));
        }

    }
    protected void deleteElement(AdapterList e){
        updateDatabase dbOpener = new updateDatabase(this);
        SQLiteDatabase db = dbOpener.getWritableDatabase();
        db.delete( updateDatabase.TABLE_NAME, "_id = ?", new String[] {Long.toString(e.getLong()) });

    }
}