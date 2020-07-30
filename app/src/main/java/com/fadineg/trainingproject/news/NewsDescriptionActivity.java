package com.fadineg.trainingproject.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fadineg.trainingproject.R;

public class NewsDescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_description);

        Toolbar toolbar = (Toolbar) findViewById(R.id.newsDescription_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        TextView toolbarTitle = findViewById(R.id.newsDescr_tv_toolbar_title);
        TextView title = findViewById(R.id.newsDescription_title);
        TextView fund = findViewById(R.id.newsDescription_tv_fund);
        TextView date = findViewById(R.id.newsDescription_tv_date);
        TextView address = findViewById(R.id.newsDescription_tv_address);
        TextView phone = findViewById(R.id.newsDescription_tv_phone);
        ImageView img = findViewById(R.id.newsDescription_iv_img1);
        TextView description = findViewById(R.id.newsDescription_tv_descriprion);
        toolbarTitle.setSelected(true);

        Intent intent = getIntent();

        toolbarTitle.setText(intent.getStringExtra("Title"));
        title.setText(intent.getStringExtra("Title"));
        fund.setText(intent.getStringExtra("Fund"));
        date.setText(intent.getStringExtra("Date"));
        address.setText(intent.getStringExtra("Address"));
        phone.setText(intent.getStringExtra("Phone"));
        img.setImageResource(intent.getIntExtra("Image", 0));
        description.setText(intent.getStringExtra("Description"));

    }
}
