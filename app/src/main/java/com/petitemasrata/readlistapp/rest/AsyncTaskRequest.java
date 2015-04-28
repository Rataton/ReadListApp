package com.petitemasrata.readlistapp.rest;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.petitemasrata.readlistapp.model.Event;
import com.petitemasrata.readlistapp.model.Venue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by irata on 27/04/15.
 */
public class AsyncTaskRequest extends AsyncTask<String, Void, ArrayList<Event>>{

    public static final String LOG_TAG = AsyncTaskRequest.class.getSimpleName();
    private AsyncResponse responseListener;

    public AsyncTaskRequest (@NonNull AsyncResponse responseListener){
        this.responseListener = responseListener;
    }

    @Override
    protected ArrayList<Event> doInBackground(String... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String response;

        try {
            URL url = new URL(Constant.URL_KEY); //ToDo: Put your url here

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();
            if (inputStream == null) {
                responseListener.onError();
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line)
                        .append("\n");
            }

            if (buffer.length() == 0) {
                responseListener.onError();
                return null;
            }
            response = buffer.toString();
        } catch (IOException e){
            responseListener.onError();
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    responseListener.onError();
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        try{
            return parseResponse(new JSONObject(response)); //ToDo: Define your parse method
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Event> responseObject) {
        super.onPostExecute(responseObject);
        responseListener.onResponse(responseObject);
    }

    private ArrayList<Event> parseResponse(JSONObject response) throws JSONException {
        JSONObject eventsPackage = response.getJSONObject(Constant.EVENTS_KEY);
        JSONArray eventsArray = eventsPackage.getJSONArray(Constant.EVENT_KEY);
        ArrayList<Event> events = new ArrayList<>();

        for (int i = 0; i < eventsArray.length(); i++) {
            JSONObject currentE = eventsArray.getJSONObject(i);

            String title = currentE.getString(Constant.TITLE_KEY);
            String description = currentE.getString(Constant.DESCRIPTION_KEY);
            String date = currentE.getString(Constant.DATE_KEY);
            String url_e = currentE.getString(Constant.EVENT_URL_KEY);

            String name = currentE.getString(Constant.VENUE_KEY);
            String address = currentE.getString(Constant.ADDRESS_KEY);
            String city = currentE.getString(Constant.CITY_KEY);
            String region = currentE.getString(Constant.REGION_KEY);
            String country = currentE.getString(Constant.COUNTRY_KEY);

            Venue venue = new Venue(name, address, city, region, country);
            Event event = new Event(title, description, date, venue, url_e);

            events.add(event);
        }
        return events;
    }

    public interface AsyncResponse {
        public void onResponse(ArrayList<Event> responseObject);
        public void onError();
    }

}
