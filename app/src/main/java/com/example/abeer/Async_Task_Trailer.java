package com.example.abeer;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.List;

/**
 * Created by ABeeR on 22-Apr-16.
 */
public class Async_Task_Trailer extends AsyncTask<String, Void, String> {

    String url1 = "https://api.themoviedb.org/3/movie/";
    String url2 = "/videos?api_key=272f7f5ca4e84122fde686ff11175500";
    int id = 209112;
    Context context;
    ListView_Adapter_Trailer listView_adapter_trailer;

    public Async_Task_Trailer(Context context, int id, ListView_Adapter_Trailer listView_adapter_trailer) {
        this.context = context;
        this.id = id;
        this.listView_adapter_trailer = listView_adapter_trailer;
    }


    @Override
    protected String doInBackground(String... params) {
        HTTPServer sh = new HTTPServer();
        String jsonStr = null;
        try {
            Log.d("ID", String.valueOf(id));
            jsonStr = sh.call_connect(url1 + String.valueOf(id) + url2);
            Log.d("jsonStr", jsonStr);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonStr;
    }

    protected void onPostExecute(String result)  //result da return from back
    {
        List<movie> list_trailer;

        Json_Trailer json_trailer = new Json_Trailer();
        list_trailer = json_trailer.json_trailer(result);

        listView_adapter_trailer.clear();
        for (movie m : list_trailer) {
            listView_adapter_trailer.add(m);
        }
    }
}