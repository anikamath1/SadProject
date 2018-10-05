package com.example.android.restuarantfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class PlaceOrder extends AppCompatActivity {
    String array[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        ListView listView=(ListView)findViewById(R.id.listView);
        dataBaseAccess databaseAccess = dataBaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        List<String> drinks = databaseAccess.getDrinksMao("drinks");
        List<String> starters = databaseAccess.getDrinksMao("starters");
        drinks.addAll(starters);
        array=new String[drinks.size()];
        drinks.toArray(array);
        databaseAccess.close();
        CustomAdapter customAdapter=new CustomAdapter();
        listView.setAdapter(customAdapter);
    }
    class CustomAdapter extends BaseAdapter
    {
        @Override
        public int getCount() {
            return array.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)  {
            view = getLayoutInflater().inflate(R.layout.customlayoutfororderplacing,null);
            //TextView textView=(TextView)view.findViewById(R.id.textView);
            CheckBox checkBox=(CheckBox)view.findViewById(R.id.checkbox);
            checkBox.setText(array[i]);
            //textView.setText(array[i]);
            return view;
        }
    }

}
