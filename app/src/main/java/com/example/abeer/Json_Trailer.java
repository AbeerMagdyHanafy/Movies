package com.example.ABeeR;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ABeeR on 22-Apr-16.
 */
public class Json_Trailer {

    public ArrayList<movie> json_trailer(String result) {
        ArrayList<movie> movieArrayList = new ArrayList<>();

        try {
            JSONObject jsonRootObject = new JSONObject(result);
            JSONArray jsonArray = jsonRootObject.optJSONArray("results");

            for (int i = 0; i < jsonArray.length(); i++) {
                movie m = new movie();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String key = (jsonObject.optString("key"));
                m.setKey(key);
                movieArrayList.add(m);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieArrayList;
    }
}
