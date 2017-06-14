package com.brahmakumari.powerofmind.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.brahmakumari.powerofmind.R;
import com.brahmakumari.powerofmind.model.LiveDarshan;
import com.brahmakumari.powerofmind.ui.activity.LiveDarshanSingle;

import java.util.List;

/**
 * Created by rishabhpanwar on 09/04/17.
 */

public class LiveDarshanAdapter extends RecyclerView.Adapter<LiveDarshanAdapter.ViewInfoHolder>
{
    List<LiveDarshan> lds;
    Context ctx;

    public LiveDarshanAdapter(Context context, List<LiveDarshan> lds) {
        this.ctx = context;
        this.lds=lds;
    }

    @Override
    public ViewInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_live_darshan, parent, false);
        return new LiveDarshanAdapter.ViewInfoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewInfoHolder holder, final int position) {

        String lds_desc_short=lds.get(position).getDesc().substring(0,50)+"...";


        final String ld_title=lds.get(position).getTitle();
        final String ld_location=lds.get(position).getVenue();
        final String ld_time=lds.get(position).getTime();
        final String ld_desc=lds.get(position).getDesc();
        final String ld_videoPath=lds.get(position).getVideoPath();
        //final String ld_date=lds.get(position).getDate();


        /*if(position%2==0)
            holder.live_darshan_mini_layout.setBackgroundResource(R.color.ldblue);
        else
            holder.live_darshan_mini_layout.setBackgroundResource(R.color.colorAccent);*/

        holder.ld_title.setText(ld_title);
        holder.ld_location.setText(ld_location);
        holder.ld_time.setText(ld_time);
        holder.ld_desc.setText(lds_desc_short);

        holder.live_darshan_mini_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(ctx,LiveDarshanSingle.class);
                intent.putExtra("title",ld_title);
                intent.putExtra("desc",ld_desc);
                intent.putExtra("time",ld_time);
                intent.putExtra("location",ld_location);
                intent.putExtra("videoPath",ld_videoPath);
                ctx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return lds.size();
    }

    public class ViewInfoHolder extends RecyclerView.ViewHolder
    {
        TextView ld_title,ld_location,ld_time,ld_desc;
        RelativeLayout live_darshan_mini_layout;

        public ViewInfoHolder(final View itemView)
        {
            super(itemView);
            ld_title=(TextView) itemView.findViewById(R.id.ld_title);
            ld_location=(TextView) itemView.findViewById(R.id.ld_location);
            ld_time=(TextView) itemView.findViewById(R.id.ld_time);
            live_darshan_mini_layout=(RelativeLayout) itemView.findViewById(R.id.live_darshan_mini_layout);
            ld_desc=(TextView)itemView.findViewById(R.id.ld_desc);
        }
    }

}
