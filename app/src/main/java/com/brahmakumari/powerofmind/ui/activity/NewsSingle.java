package com.brahmakumari.powerofmind.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.brahmakumari.powerofmind.R;
import com.google.android.gms.vision.text.Text;
import com.squareup.picasso.Picasso;

public class NewsSingle extends AppCompatActivity {

    ImageView iv;
    TextView date_tv,title_tv;
    WebView details_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_single);

        iv=(ImageView)findViewById(R.id.news_bckgrnd);
        details_tv=(WebView) findViewById(R.id.news_details);
        date_tv=(TextView)findViewById(R.id.news_date);
        title_tv=(TextView)findViewById(R.id.news_title);


        Intent intent=getIntent();
        String Url=getApplicationContext().getString(R.string.server_url)+intent.getStringExtra("url");
        String msg=intent.getStringExtra("desc");
        String title=intent.getStringExtra("title");
        String date=intent.getStringExtra("date");

        Picasso.with(getApplicationContext())
                .load(Url)
                .into(iv);

        details_tv.loadData(msg,"text/html;charset=utf-8","utf-8");
        date_tv.setText(date);
        title_tv.setText(title);
    }
}
