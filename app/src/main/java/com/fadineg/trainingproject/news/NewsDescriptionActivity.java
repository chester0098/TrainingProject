package com.fadineg.trainingproject.news;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.fadineg.trainingproject.R;
import com.fadineg.trainingproject.news.news_fragment.NewsRecyclerAdapter;

public class NewsDescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_description);

        Toolbar toolbar = (Toolbar) findViewById(R.id.newsDescription_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_24dp);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        TextView toolbarTitle = findViewById(R.id.newsDescr_tv_toolbar_title);
        TextView title = findViewById(R.id.newsDescription_title);
        TextView fund = findViewById(R.id.newsDescription_tv_fund);
        TextView date = findViewById(R.id.newsDescription_tv_date);
        TextView address = findViewById(R.id.newsDescription_tv_address);
        TextView phone = findViewById(R.id.newsDescription_tv_phone);
        ImageView img = findViewById(R.id.newsDescription_iv_img1);
        TextView description = findViewById(R.id.newsDescription_tv_description);
        toolbarTitle.setSelected(true);

        Intent intent = getIntent();

        toolbarTitle.setText(intent.getStringExtra(NewsRecyclerAdapter.EXTRA_TITLE));
        title.setText(intent.getStringExtra(NewsRecyclerAdapter.EXTRA_TITLE));
        fund.setText(intent.getStringExtra(NewsRecyclerAdapter.EXTRA_FUND));
        date.setText(intent.getStringExtra(NewsRecyclerAdapter.EXTRA_DATE));
        address.setText(intent.getStringExtra(NewsRecyclerAdapter.EXTRA_ADDRESS));
        phone.setText(intent.getStringExtra(NewsRecyclerAdapter.EXTRA_PHONE));
        description.setText(intent.getStringExtra(NewsRecyclerAdapter.EXTRA_DESCRIPTION));

    }
}
