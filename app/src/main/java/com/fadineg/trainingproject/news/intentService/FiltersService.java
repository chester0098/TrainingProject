package com.fadineg.trainingproject.news.intentService;

import android.app.IntentService;
import android.content.Intent;

import com.fadineg.trainingproject.news.eventBus.FiltersBus;
import com.fadineg.trainingproject.news.JsonInArray;

import org.greenrobot.eventbus.EventBus;

public class FiltersService extends IntentService {

    public FiltersService() {
        super("filtersService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JsonInArray jsonInArray = new JsonInArray();
        EventBus.getDefault().post(new FiltersBus(jsonInArray.filtersPars(getApplicationContext())));
    }

}
