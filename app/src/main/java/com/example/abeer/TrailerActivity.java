package com.example.abeer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class TrailerActivity extends AppCompatActivity {

    int id;
    ListView listView;
    Async_Task_Trailer async_task;
    String title;
    ListView_Adapter_Trailer listView_adapter_trailer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        id = intent.getIntExtra("id", 0);

        ActionBar logo = getSupportActionBar();
        logo.setDisplayShowHomeEnabled(true);
        logo.setLogo(R.drawable.trailer);
        logo.setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle(" " + title);

        listView = (ListView) findViewById(R.id.list_view);


        listView_adapter_trailer = new ListView_Adapter_Trailer(this);
        async_task = new Async_Task_Trailer(this, id, listView_adapter_trailer);
        async_task.execute();
        listView.setAdapter(listView_adapter_trailer);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" +
                        listView_adapter_trailer.getItem(position).getKey()));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trailer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.movie_share) {

            String Message = "I recommended You To Watch This Movie\n\n" + "https://www.youtube.com/watch?v=" + listView_adapter_trailer.getItem(0).getKey();
            Intent Share = new Intent(Intent.ACTION_SEND);
            Share.setType("text/plain");
            Share.putExtra(Intent.EXTRA_TEXT, Message);
            startActivity(Intent.createChooser(Share, "Share Via"));
        }
        return super.onOptionsItemSelected(item);
    }
}
