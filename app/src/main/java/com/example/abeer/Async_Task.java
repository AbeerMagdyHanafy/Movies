package com.example.abeer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;

import java.io.IOException;
import java.util.List;

/**
 * Created by ABeeR on 25/03/2016.
 */
public class Async_Task extends AsyncTask<String, Void, String> {

    String url = "";
    GridView_Adapter gridView_adapter;
    Context context;
    GridView gridView;
    String sort;

    public Async_Task(Context context, GridView_Adapter gridView_adapter, String url, GridView gridView, String sort) {
        this.gridView_adapter = gridView_adapter;
        this.context = context;
        this.url = url;
        this.gridView = gridView;
        this.sort = sort;
    }

    protected String doInBackground(String... params) {
        HTTPServer sh = new HTTPServer();
        SharedPreferences sharedpreferences;
        SharedPreferences sharedpreferences2;

        String jsonStrPopular = "p";
        String jsonStrTop = "t";

        String jsonPopular = null;
        String jsonTop = null;
        String JsonString = null;
        //Log.d("jsonReturn" , jsonStr);
        try {

            if (sort == "most_popular") {

                //if (!(DetailsFragment.isNetworkAvailable(context))) {
                // JsonString = null;
                sharedpreferences = context.getSharedPreferences(jsonStrPopular, Context.MODE_PRIVATE);
                //}
                //else
                if ((JsonString == null)) {
                    if (DetailsFragment.isNetworkAvailable(context)) {
                        JsonString = sh.call_connect(url);
                        //sharedpreferences = context.getSharedPreferences(jsonStrPopular, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(jsonStrPopular, JsonString);
                        editor.commit();
                    }
                }
                JsonString = sharedpreferences.getString(jsonStrPopular, jsonStrPopular);

                return JsonString;
            }
            if (sort == "top_rated") {
                JsonString = null;
                //if (!(DetailsFragment.isNetworkAvailable(context))) {
                sharedpreferences2 = context.getSharedPreferences(jsonStrTop, Context.MODE_PRIVATE);
                // }
                if ((JsonString == null)) {
                    // else{
                    if (DetailsFragment.isNetworkAvailable(context)) {
                        JsonString = sh.call_connect(url);
                        //sharedpreferences2 = context.getSharedPreferences(jsonStrTop, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences2.edit();
                        editor.putString(jsonStrTop, JsonString);
                        editor.commit();
                    }
                }
                JsonString = sharedpreferences2.getString(jsonStrTop, jsonStrTop);

                return JsonString;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return JsonString;
    }

    protected void onPostExecute(String result)  //result da return from back
    {

//        Log.d("result", result);
        if (result != null) {
            JsonParsing jsonParsing = new JsonParsing();

            // Log.d("return", String.valueOf(jsonParsing.getMovieArrayList()));
            List<movie> list_movie;
            list_movie = jsonParsing.json_parse(result);

            gridView = new GridView(context);
            gridView_adapter.clear();
            for (movie m : list_movie) {
                gridView_adapter.add(m);
            }
            // gridView.setItemChecked(0, true);
            gridView.setSelection(MainFragment.POSITION);
            // gridView.isPressed();
//            gridView.setItemChecked(0,true);
//            Log.d("itemselected", String.valueOf(gridView.getCheckedItemPosition()));

        }
        Log.d("select", String.valueOf(MainFragment.POSITION));

//
    }
}