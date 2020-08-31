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

import io.realm.Realm;

public class NewsParsingTask extends AsyncTask<Void, Void, Void> {
    private List<News> newsList;
    @SuppressLint("StaticFieldLeak")
    private Context context;
    private Realm realm = Realm.getDefaultInstance();


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

        realm.beginTransaction();
        realm.deleteAll();
        realm.copyToRealmOrUpdate(newsList);
        realm.commitTransaction();

        EventBus.getDefault().post(new NewsBus(newsList));
    }
}
