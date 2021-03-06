package com.brahmakumari.powerofmind.adapter;

import android.content.Context;
import android.content.Intent;

import android.graphics.Color;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.brahmakumari.powerofmind.R;
import com.brahmakumari.powerofmind.model.Message;
import com.brahmakumari.powerofmind.ui.activity.MessageSingle;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        /*Button share_btn=(Button) itemView.findViewById(R.id.share_btn);
        share_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView msg_det=(TextView)itemView.findViewById(R.id.message_details);
                String string=msg_det.getText().toString();
                shareIt(string);
            }
        });*/
        return new MessageAdapter.ViewInfoHolder(itemView);
    }


    /*private void shareIt(String string, String Url) {
        Uri uri = Uri.parse(Url);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share Through");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, ""+string);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        ctx.startActivity(Intent.createChooser(intent, "Share via"));
    }*/

    @Override
    public void onBindViewHolder(ViewInfoHolder holder, final int position) {
        holder.message_details.setText(messages.get(position).getMessage());
        holder.message_date.setText(messages.get(position).getDate());
        holder.message_bckgrnd.setColorFilter(Color.argb(129,0,0,0));
        Url=messages.get(position).getImagePath();

        Picasso.with(ctx)
                .load(ctx.getString(R.string.server_url)+messages.get(position).getImagePath())
                .resize(280,132)
                .into(holder.message_bckgrnd);

        final String string= (String) holder.message_details.getText();
        /*holder.share_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                shareIt(string,Url);
            }
        });*/

        holder.message_constraintlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ctx, MessageSingle.class);
                intent.putExtra("url",messages.get(position).getImagePath());
                intent.putExtra("date",""+messages.get(position).getDate());
                intent.putExtra("msg",""+messages.get(position).getMessage());
                ctx.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewInfoHolder extends RecyclerView.ViewHolder
    {
        protected TextView message_details,message_date;
        protected ImageView message_bckgrnd;
        //Button share_btn;
        protected ConstraintLayout message_constraintlayout;
        public ViewInfoHolder(View itemView)
        {
            super(itemView);
            message_details = (TextView) itemView.findViewById(R.id.message_details);
            message_date = (TextView) itemView.findViewById(R.id.message_date);
            message_bckgrnd=(ImageView) itemView.findViewById(R.id.message_bckgrnd);
            message_constraintlayout=(ConstraintLayout) itemView.findViewById(R.id.msg_constraintlayout);
            //share_btn=(Button) itemView.findViewById(R.id.share_btn);
        }

    }
}
