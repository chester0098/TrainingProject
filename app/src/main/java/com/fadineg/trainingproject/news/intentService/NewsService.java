package com.fadineg.trainingproject.news.intentService;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.fadineg.trainingproject.news.JsonInArray;
import com.fadineg.trainingproject.news.eventBus.NewsBus;

import org.greenrobot.eventbus.EventBus;

public class NewsService extends IntentService {

    public NewsService() {
        super("newsService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JsonInArray jsonInArray = new JsonInArray();
        EventBus.getDefault().post(new NewsBus(jsonInArray.newsPars(getApplicationContext())));
    }

}
