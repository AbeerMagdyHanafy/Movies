package com.example.abeer.movies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView grid_view = (GridView) findViewById(R.id.grid_view);
        grid_view.setAdapter(new ImageAdapter(this));

    }
}
