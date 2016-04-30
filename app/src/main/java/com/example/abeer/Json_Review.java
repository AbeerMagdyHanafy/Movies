package com.example.ABeeR;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ABeeR on 22-Apr-16.
 */
public class Json_Review {

    public ArrayList<movie> json_review(String result) {
        ArrayList<movie> movieArrayList = new ArrayList<>();

        try {
            JSONObject jsonRootObject = new JSONObject(result);
            JSONArray jsonArray = jsonRootObject.optJSONArray("results");
            Log.d("jsonArray", String.valueOf(jsonArray));


            for (int i = 0; i < jsonArray.length(); i++) {

                movie m = new movie();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String author = (jsonObject.optString("author"));
                String content = (jsonObject.optString("content"));

                Log.d("author", author);
                Log.d("content", content);

                m.setAuthor_review(author);
                m.setContent_review(content);

                movieArrayList.add(m);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieArrayList;
    }
}
