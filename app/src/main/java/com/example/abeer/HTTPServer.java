package com.example.ABeeR;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ABeeR on 20-Apr-16.
 */
public class HTTPServer {
    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;
    String moviesJSONStr = "";

    public String call_connect(String str) throws IOException {
        URL url = new URL(str);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.connect();

        InputStream inputStream = urlConnection.getInputStream();
        StringBuffer buffer = new StringBuffer();
        if (inputStream == null) {
        }
        reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) != null) {

            buffer.append(line + "\n");
        }

        if (buffer.length() == 0) {
            return null;
        }
        moviesJSONStr = buffer.toString();
        return moviesJSONStr;
    }
}
