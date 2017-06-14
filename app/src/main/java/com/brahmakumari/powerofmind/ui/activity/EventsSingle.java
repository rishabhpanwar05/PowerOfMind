package com.brahmakumari.powerofmind.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.brahmakumari.powerofmind.R;

public class EventsSingle extends AppCompatActivity {

    TextView date_tv,title_tv,venue_tv;
    WebView details_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_single);

        details_tv=(WebView) findViewById(R.id.events_details);
        date_tv=(TextView)findViewById(R.id.events_date);
        title_tv=(TextView)findViewById(R.id.events_title);
        venue_tv=(TextView) findViewById(R.id.events_venue);


        Intent intent=getIntent();
        String Url=getApplicationContext().getString(R.string.server_url)+intent.getStringExtra("url");
        String msg=intent.getStringExtra("desc");
        String title=intent.getStringExtra("title");
        String date=intent.getStringExtra("date");
        String venue=intent.getStringExtra("venue");

        details_tv.loadData(msg,"text/html;charset=utf-8","utf-8");
        date_tv.setText(date);
        title_tv.setText(title);
        venue_tv.setText(venue);
    }
}
