package com.brahmakumari.powerofmind.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.brahmakumari.powerofmind.R;
import com.squareup.picasso.Picasso;

public class DisplayMessage extends AppCompatActivity {

    ImageView iv;
    TextView msg_tv,date_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        iv=(ImageView)findViewById(R.id.message_bckgrnd);
        msg_tv=(TextView) findViewById(R.id.message_details);
        date_tv=(TextView)findViewById(R.id.message_date);


        Intent intent=getIntent();
        String Url=getApplicationContext().getString(R.string.server_url)+intent.getStringExtra("url");
        String msg=intent.getStringExtra("msg");
        String date=intent.getStringExtra("date");

        Picasso.with(getApplicationContext())
                .load(Url)
                .into(iv);

        msg_tv.setText(msg);
        date_tv.setText(date);
    }
}
