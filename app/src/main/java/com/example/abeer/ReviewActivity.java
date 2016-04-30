package com.example.ABeeR;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class ReviewActivity extends AppCompatActivity {

    int id;
    ListView listView;
    Async_Task_Review async_task;
    ListView_Adapter_Review listView_adapter_review;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);


        ActionBar logo = getSupportActionBar();
        logo.setDisplayShowHomeEnabled(true);
        logo.setLogo(R.drawable.review);
        logo.setDisplayUseLogoEnabled(true);


        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        id = intent.getIntExtra("id", 0);
        listView = (ListView) findViewById(R.id.list_view2);
        listView_adapter_review = new ListView_Adapter_Review(this);
        async_task = new Async_Task_Review(this, id, listView_adapter_review);
        async_task.execute();
        listView.setAdapter(listView_adapter_review);

        getSupportActionBar().setTitle(" " + title);
    }
}
