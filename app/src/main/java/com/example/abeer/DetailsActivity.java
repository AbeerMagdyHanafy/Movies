package com.example.abeer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {
    private boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moviedetail);


        // if (findViewById(R.id.fragment_details) != null) {

        mTwoPane = true;

        if (savedInstanceState != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_details, new DetailsFragment())
                    .commit();
        }
//        } else {
//            mTwoPane = false;
//        }
    }
}