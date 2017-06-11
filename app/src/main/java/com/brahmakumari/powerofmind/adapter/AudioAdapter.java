package com.brahmakumari.powerofmind.adapter;

import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.brahmakumari.powerofmind.R;
import com.brahmakumari.powerofmind.model.Audio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.DOWNLOAD_SERVICE;
import static java.io.File.separator;


/**
 * Created by rishabhpanwar on 09/04/17.
 */

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.ViewInfoHolder>
{
    List<Audio> audios;
    Context ctx;
    SharedPreferences sharedPreferences;
    ArrayList<String> presentAudioIds;
    MediaPlayer mediaPlayer;

    public AudioAdapter(Context context, List<Audio> audios) {
        this.ctx = context;
        this.audios=audios;
    }

    public void demo(final ViewInfoHolder holder,final int position) {
        holder.audio_download_btn.setImageResource(R.drawable.ic_play);
    }
    @Override
    public ViewInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_audio, parent, false);
        mediaPlayer=new MediaPlayer();
        sharedPreferences = ctx.getSharedPreferences("com.brahmakumari.powerofmind",Context.MODE_PRIVATE);
        return new AudioAdapter.ViewInfoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewInfoHolder holder, final int position) {

        holder.audio_details.setText(audios.get(position).getAudio_title());
        presentAudioIds=new ArrayList<>();

        try {
            presentAudioIds= (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("audioId",ObjectSerializer.serialize(new ArrayList<String>())));
            Log.i("Demo: ","SavedInSP"+presentAudioIds.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        if(presentAudioIds.contains(audios.get(position).get_id())){
            demo(holder,position);
        }

        holder.audio_download_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v)
            {
                int count=0;
                String audioId=audios.get(position).get_id();

                for(int i = 0; i< presentAudioIds.size(); i++)
                {
                    if (audioId.equals(presentAudioIds.get(i)))
                    {
                        count++;
                        break;
                    }
                }
                if(count==0)
                {
                    Uri uri = Uri.parse(ctx.getString(R.string.server_url) + audios.get(position).getAudio_path());

                    // Create request for the download manager
                    DownloadManager downloadManager = (DownloadManager) ctx.getSystemService(DOWNLOAD_SERVICE);
                    DownloadManager.Request request = new DownloadManager.Request(uri);

                    //Setting title of request
                    request.setTitle(audios.get(position).getAudio_title());

                    //Setting description of request
                    request.setDescription("Power Of Mind");

                    //Set the local destination for the downloaded file to a path within the application's external files directory

                    request.setDestinationInExternalFilesDir(ctx,Environment.DIRECTORY_MUSIC,audios.get(position).getAudio_desc()+".mp3");

                    //Enqueue download and save into referenceId
                    long downloadReference = downloadManager.enqueue(request);
                    demo(holder,position);

                    presentAudioIds.add(audios.get(position).get_id());

                    try {
                        sharedPreferences.edit().putString("audioId",ObjectSerializer.serialize(presentAudioIds)).apply();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(ctx,"Already Downloaded",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return audios.size();
    }

    public class ViewInfoHolder extends RecyclerView.ViewHolder
    {
        protected TextView audio_details;
        protected ImageView audio_download_btn;

        public ViewInfoHolder(final View itemView)
        {
            super(itemView);
            audio_details = (TextView) itemView.findViewById(R.id.audio_details);
            audio_download_btn = (ImageView) itemView.findViewById(R.id.ic_download);
        }
    }

}
