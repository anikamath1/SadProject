package com.example.android.restuarantfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class BillPayment extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_bill_payment );

        ArrayList<allMenuMao> list = new ArrayList<>();

        int i;
        for(i=0; i<ItemListAdapter.menuMaos.size(); i++){
            if(ItemListAdapter.menuMaos.get(i).getSelected()){
                list.add(new allMenuMao(ItemListAdapter.menuMaos.get(i).getItemName(), ItemListAdapter.menuMaos.get(i).getItemPrice()));
            }
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true); //every item in the recycler view will have fixed size
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new SelectedItemListAdapter(list, this);

        recyclerView.setAdapter(adapter); //sets adapter to recycler view

    }
}
