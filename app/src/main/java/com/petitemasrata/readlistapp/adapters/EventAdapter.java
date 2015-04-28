package com.petitemasrata.readlistapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.petitemasrata.readlistapp.R;
import com.petitemasrata.readlistapp.model.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EventAdapter extends ArrayAdapter<Event> {

    ArrayList<Event> events;
    Context context;

    public EventAdapter(Context context, ArrayList<Event> events) {
        super(context, 0, events);
        this.events = events;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater layInflater = LayoutInflater.from(context);
        View itemView = layInflater.inflate(R.layout.event_layout, parent, false);

        TextView txtTitle = (TextView) itemView.findViewById(R.id.txt_title);
        TextView txtDescription = (TextView) itemView.findViewById(R.id.txt_description);
        TextView txtDate = (TextView) itemView.findViewById(R.id.txt_date);
        TextView txtVenue = (TextView) itemView.findViewById(R.id.txt_venue);
        Button btnUrl = (Button) itemView.findViewById(R.id.btn_link);

        txtTitle.setText(events.get(position).getTitle());
        txtDescription.setText(events.get(position).getDescription());
        String fecha = events.get(position).getDate();
        fecha = convertDate(fecha);

        txtDate.setText(fecha);
        //txtDate.setText(events.get(position).getDate());
        txtVenue.setText(events.get(position).getVenue().toString());

        final String goUrl = events.get(position).getUrle();
        btnUrl.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(goUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);
            }
        });
        return itemView;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    private String convertDate(String date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            Date d = format.parse(date);
            SimpleDateFormat serverFormat = new SimpleDateFormat("d 'de' MMMM 'de' yyyy ' - ' h:mm 'horas'");
            return serverFormat.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "nel";
    }
}
