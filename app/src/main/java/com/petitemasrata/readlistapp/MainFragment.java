package com.petitemasrata.readlistapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.petitemasrata.readlistapp.model.Event;
import com.petitemasrata.readlistapp.rest.AsyncTaskRequest;

import java.util.ArrayList;

/**
 * Created by irata on 28/04/15.
 */
public class MainFragment extends Fragment implements AsyncTaskRequest.AsyncResponse{

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }

    @Override
    public void onResume() {
        AsyncTaskRequest eventfulRequest = new AsyncTaskRequest(this);
        eventfulRequest.execute();
        super.onResume();
    }

    @Override
    public void onResponse(ArrayList<Event> responseObject) {
        for (Event event : responseObject){
            Log.i("Event", event.toString());
            Log.i("space", "---------");
        }
    }

    @Override
    public void onError() {

    }
}