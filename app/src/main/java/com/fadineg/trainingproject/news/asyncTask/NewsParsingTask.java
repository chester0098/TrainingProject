package com.fadineg.trainingproject.news.asyncTask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.fadineg.trainingproject.news.JsonInArray;
import com.fadineg.trainingproject.news.eventBus.NewsBus;
import com.fadineg.trainingproject.news.model.News;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class NewsParsingTask extends AsyncTask<Void, Void, Void> {
    private List<News> newsList;
    @SuppressLint("StaticFieldLeak")
    private Context context;

    public NewsParsingTask(Context context){
        this.context = context;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JsonInArray jsonInArray = new JsonInArray();
        newsList = new ArrayList<>(jsonInArray.newsPars(context));
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        EventBus.getDefault().post(new NewsBus(newsList));
    }
}
