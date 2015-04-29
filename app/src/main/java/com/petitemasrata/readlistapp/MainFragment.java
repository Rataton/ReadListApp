package com.petitemasrata.readlistapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.petitemasrata.readlistapp.adapters.EventAdapter;
import com.petitemasrata.readlistapp.model.Event;
import com.petitemasrata.readlistapp.rest.AsyncTaskRequest;

import java.util.ArrayList;


public class MainFragment extends Fragment implements AsyncTaskRequest.AsyncResponse {

    private ListView mListView;
    private ArrayList<Event> mItems;
    private EventAdapter mAdapter;
    private Context CONTEXT;


    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItems = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mListView = (ListView) rootView.findViewById(R.id.list_fragment);
        mAdapter = new EventAdapter(CONTEXT, mItems);

       //mListView.setAdapter(mAdapter);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        CONTEXT = activity;
    }

    @Override
    public void onResume() {
        AsyncTaskRequest eventfulRequest = new AsyncTaskRequest(this);
        eventfulRequest.execute();
        super.onResume();
    }

    @Override
    public void onResponse(ArrayList<Event> responseObject) {
        //mItems = new ArrayList<>();
        mItems.addAll(responseObject);
        /*
        for (Event event : responseObject){
            mItems.add(event);
            Log.i("Event", event.toString());
        }
        */
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onError() {

    }
}