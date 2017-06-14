package com.brahmakumari.powerofmind.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.brahmakumari.powerofmind.R;
import com.brahmakumari.powerofmind.model.Events;
import com.brahmakumari.powerofmind.ui.activity.EventsSingle;

import java.util.List;

/**
 * Created by ishitabhandari on 12/06/17.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewInfoHolder> {
    private List<Events> events;
    private Context context;

    public EventsAdapter(Context ctx, List<Events> events){
        this.context = ctx;
        this.events=events;
    }

    @Override
    public EventsAdapter.ViewInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(context).inflate(R.layout.list_item_events,parent,false);

        return new EventsAdapter.ViewInfoHolder(itemview);
    }

    @Override
    public void onBindViewHolder(EventsAdapter.ViewInfoHolder holder, final int position) {

        String events_desc_short=events.get(position).getDesc();
        if(events.get(position).getDesc().length()>70){
            events_desc_short=events_desc_short.substring(0,70).concat("...");
        }
        holder.event_date_tv.setText(events.get(position).getDate());
        holder.event_title_tv.setText(events.get(position).getTitle());
        holder.event_venue_tv.setText(events.get(position).getVenue());
        holder.event_desc_wv.loadData(events_desc_short, "text/html;charset=utf-8","utf-8");

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, EventsSingle.class);
                intent.putExtra("date",""+events.get(position).getDate());
                intent.putExtra("title",""+events.get(position).getTitle());
                intent.putExtra("venue",""+events.get(position).getVenue());
                intent.putExtra("desc",""+events.get(position).getDesc());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewInfoHolder extends RecyclerView.ViewHolder{

        protected TextView event_title_tv, event_venue_tv,event_date_tv;
        protected WebView event_desc_wv;
        protected RelativeLayout relativeLayout;

        public ViewInfoHolder(View itemView) {
            super(itemView);
            event_date_tv=(TextView) itemView.findViewById(R.id.event_date_tv);
            event_title_tv=(TextView) itemView.findViewById(R.id.event_title_tv);
            event_venue_tv=(TextView) itemView.findViewById(R.id.event_venue_tv);
            event_desc_wv=(WebView) itemView.findViewById(R.id.event_desc_wv);
            relativeLayout=(RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}
