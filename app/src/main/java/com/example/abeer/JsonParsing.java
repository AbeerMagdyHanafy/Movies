package com.example.abeer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ABeeR on 20-Apr-16.
 */
public class JsonParsing {

    public ArrayList<movie> json_parse(String result) {
        ArrayList<movie> movieArrayList = new ArrayList<>();

        try {
            JSONObject jsonRootObject = new JSONObject(result);

            JSONArray jsonArray = jsonRootObject.optJSONArray("results");

            for (int i = 0; i < jsonArray.length(); i++) {

                movie m = new movie();
                JSONObject jsonObject = jsonArray.getJSONObject(i);


                String poster_path = (jsonObject.optString("poster_path"));
                String overview = (jsonObject.optString("overview"));
                String original_title = (jsonObject.optString("original_title"));
                String backdrop_path = (jsonObject.optString("backdrop_path"));
                String release_date = (jsonObject.optString("release_date"));
                String key = (jsonObject.optString("key"));///
                double vote_average = Double.parseDouble((jsonObject.optString("vote_average")));
                double popularity = Double.parseDouble((jsonObject.optString("popularity")));
                int vote_count = Integer.parseInt((jsonObject.optString("vote_count")));
                int id = Integer.parseInt((jsonObject.optString("id")));
                String url_review = (jsonObject.optString("url_review"));

                m.setPoster_path(poster_path);
                m.setOverview(overview);
                m.setOriginal_title(original_title);
                m.setBackdrop_path(backdrop_path);
                m.setRelease_date(release_date);
                m.setKey(key);///
                m.setVote_average(vote_average);
                m.setPopularity(popularity);
                m.setVote_count(vote_count);
                m.setId(id);
                m.setUrl_review(url_review);

                movieArrayList.add(m);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movieArrayList;
    }
}
