package com.abiramiaudio.apps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Book> list = new ArrayList<Book>();
    RecyclerView recyclerView;
    AdView mAdview1;
    ImageView imageView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.img);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,InAppPurchaseActivity.class));
            }
        });

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        list.add(new Book("32Mnz5bOY70"));


//        list.add(new Book("_k9VKwUdq4s"));
//        list.add(new Book("vOUWKc2w8lk"));


        list.add(new Book("XdTozH06RfU"));
        list.add(new Book("khnnaXzSl4M"));
        list.add(new Book("HdiK3VmSlPo"));
        list.add(new Book("3PVy4tFjITY"));
        list.add(new Book("aKZELKrZP8U"));
        list.add(new Book("ZcODpAFqJLY"));
        list.add(new Book("TQEZPj1vZEs"));
        list.add(new Book("yEL9DT_pQ4o"));
        list.add(new Book("4fFyKUGWF8s"));



        mAdview1 = (AdView)findViewById(R.id.adView1);


        AdRequest adRequest1 = new AdRequest.Builder().addTestDevice("B71D897C6FB5FFEC8184442E74C7E952").build();
        mAdview1.loadAd(adRequest1);


        recyclerView = (RecyclerView)findViewById(R.id.rc);
        NewAdapter newAdapter = new NewAdapter(this,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
       // recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(newAdapter);



    }
}
