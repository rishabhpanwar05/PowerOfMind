package com.brahmakumari.powerofmind.adapter;

import android.content.Context;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.brahmakumari.powerofmind.R;
import com.brahmakumari.powerofmind.model.Message;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by rishabhpanwar on 11/04/17.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewInfoHolder> {

    List<Message> messages;
    Context ctx;
    String Url;

    public MessageAdapter(Context context, List<Message> messages) {
        this.ctx = context;
        this.messages=messages;
    }
    @Override
    public MessageAdapter.ViewInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_message, parent, false);
        Button share_btn=(Button) itemView.findViewById(R.id.share_btn);
        share_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView msg_det=(TextView)itemView.findViewById(R.id.message_details);
                String string=msg_det.getText().toString();
                shareIt(string);
            }
        });
        return new MessageAdapter.ViewInfoHolder(itemView);
    }


    private void shareIt(String string) {

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,""+string);
        ctx.startActivity(Intent.createChooser(sharingIntent, "Share via"));




        /*Uri uri = Uri.parse(messages.get(position).getImagePath());
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "");
        intent.putExtra(Intent.EXTRA_STREAM, uri);*/

    }

    @Override
    public void onBindViewHolder(ViewInfoHolder holder, int position) {
        holder.message_details.setText(messages.get(position).getMessage());
        holder.message_date.setText(messages.get(position).getDate().split("T")[0]);
        Url=messages.get(position).getImagePath();

        Picasso.with(ctx)
                .load(ctx.getString(R.string.server_url)+messages.get(position).getImagePath()).resize(300,120)
                .into(holder.message_bckgrnd);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewInfoHolder extends RecyclerView.ViewHolder
    {
        protected TextView message_details,message_date;
        protected ImageView message_bckgrnd;
        public ViewInfoHolder(View itemView)
        {
            super(itemView);
            message_details = (TextView) itemView.findViewById(R.id.message_details);
            message_date = (TextView) itemView.findViewById(R.id.message_date);
            message_bckgrnd=(ImageView) itemView.findViewById(R.id.message_bckgrnd);
        }

    }
}
