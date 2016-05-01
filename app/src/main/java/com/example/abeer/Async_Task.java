package com.example.ABeeR;

import android.content.Context;
import android.os.AsyncTask;
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

    public Async_Task(Context context, GridView_Adapter gridView_adapter, String url, GridView gridView) {
        this.gridView_adapter = gridView_adapter;
        this.context = context;
        this.url = url;
        this.gridView = gridView;
    }

    protected String doInBackground(String... params) {
        HTTPServer sh = new HTTPServer();
        String jsonStr = null;
        try {
            jsonStr = sh.call_connect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    protected void onPostExecute(String result)  //result da return from back
    {
        List<movie> list_movie;
        JsonParsing jsonParsing = new JsonParsing();
        list_movie = jsonParsing.json_parse(result);
     gridView = new GridView(context);
        gridView_adapter.clear();
        for (movie m : list_movie) {
            gridView_adapter.add(m);
        }

       // gridView.setSelection(MainFragment.POSITION);

    }
}