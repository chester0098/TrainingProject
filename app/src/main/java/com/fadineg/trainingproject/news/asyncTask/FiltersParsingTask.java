package com.fadineg.trainingproject.news.asyncTask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.fadineg.trainingproject.news.Filters;
import com.fadineg.trainingproject.news.JsonInArray;
import com.fadineg.trainingproject.news.eventBus.FiltersBus;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class FiltersParsingTask extends AsyncTask<Void, Void, Void> {
    private List<Filters> filtersList;
    @SuppressLint("StaticFieldLeak")
    private Context context;

    public FiltersParsingTask(Context context){
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
        filtersList = new ArrayList<>(jsonInArray.filtersPars(context));
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        EventBus.getDefault().post(new FiltersBus(filtersList));
    }
}
