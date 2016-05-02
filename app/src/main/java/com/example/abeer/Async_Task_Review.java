package com.example.abeer;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.List;

/**
 * Created by ABeeR on 22-Apr-16.
 */
public class Async_Task_Review extends AsyncTask<String, Void, String> {

    String url1 = "https://api.themoviedb.org/3/logo/";
    String url2 = "/reviews?api_key=272f7f5ca4e84122fde686ff11175500";
    int id;
    Context context;
    ListView_Adapter_Review adapter;

    public Async_Task_Review(Context context, int id, ListView_Adapter_Review adapter) {
        this.context = context;
        this.id = id;
        this.adapter = adapter;
    }

    @Override
    protected String doInBackground(String... params) {
        HTTPServer sh = new HTTPServer();

        String jsonStr = null;

        try {
            Log.d("ID", String.valueOf(id));
            jsonStr = sh.call_connect(url1 + String.valueOf(id) + url2);
            Log.d("jsonStr_review", jsonStr);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonStr;
    }


    protected void onPostExecute(String result)  //result da return from back
    {
        List<movie> list_review;
        Json_Review json_review = new Json_Review();
        list_review = json_review.json_review(result);

        adapter.clear();
        for (movie m : list_review) {
            adapter.add(m);
        }
    }
}
