package com.example.abeer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {
    private boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moviedetail);


        if (savedInstanceState != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_details, new DetailsFragment())
                    .commit();
        }

        if (savedInstanceState == null) {

            Toast.makeText(getApplicationContext(),"savedInstanceState == null",Toast.LENGTH_SHORT).show();
        }
    }
}