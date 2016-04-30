package com.example.ABeeR;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import DataBase.DataBase;
import DataBase.DataBaseHelper;

public class DetailsActivity extends AppCompatActivity {
    private boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moviedetail);


        if (findViewById(R.id.fragment_details) == null) {

            mTwoPane = true;

            if (savedInstanceState != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_details, new DetailsFragment())
                        .commit();
            }
        } else {
            mTwoPane = false;
        }
    }
}