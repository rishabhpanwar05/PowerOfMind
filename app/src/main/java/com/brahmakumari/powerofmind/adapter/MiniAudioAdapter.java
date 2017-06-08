package com.brahmakumari.powerofmind.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brahmakumari.powerofmind.R;
import com.brahmakumari.powerofmind.model.Audio;

import java.util.List;


/**
 * Created by rishabhpanwar on 09/04/17.
 */

public class MiniAudioAdapter extends RecyclerView.Adapter<MiniAudioAdapter.ViewInfoHolder> {

    List<Audio> audios;
    Context ctx;

    public MiniAudioAdapter(Context context, List<Audio> audios) {
        this.ctx = context;
        this.audios=audios;
    }
    @Override
    public ViewInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_mini_audio, parent, false);
        final ViewInfoHolder result = new ViewInfoHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Log.d("Pos: ",""+result.getAdapterPosition());
            }
        });
        return new MiniAudioAdapter.ViewInfoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewInfoHolder holder, int position) {
        holder.home_audio_tv.setText(audios.get(position).getAudio_title());

        if(position%3==0)
            holder.constraintLayout.setBackgroundResource(R.color.miniAudioRed);
        else if(position%3==1)
            holder.constraintLayout.setBackgroundResource(R.color.miniAudioGreen);
        else
            holder.constraintLayout.setBackgroundResource(R.color.miniAudioGrey);
    }

    @Override
    public int getItemCount() {
        return audios.size();
    }

    public class ViewInfoHolder extends RecyclerView.ViewHolder
    {
        protected TextView home_audio_tv;
        ConstraintLayout constraintLayout;

        public ViewInfoHolder(View itemView)
        {
            super(itemView);
            home_audio_tv = (TextView) itemView.findViewById(R.id.home_audio_tv);
            constraintLayout=(ConstraintLayout)itemView.findViewById(R.id.constraint_layout_mini_audio);
        }
    }
}
