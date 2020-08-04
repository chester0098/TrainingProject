package com.fadineg.trainingproject.news;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class JsonInArray {
    private static final String FILTERS = "filters.json";
    private static final String NEWS = "news.json";

    private static String getJsonFromAssets(Context context, String fileName) {
        String jsonString;
        try {
            InputStream is = context.getAssets().open(fileName);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return jsonString;
    }

    public List<Filters> filtersPars(Context context){
        String jsonFileString = getJsonFromAssets(context, FILTERS);

        Gson gson = new Gson();
        Type listFiltersType = new TypeToken<List<Filters>>() { }.getType();

        List<Filters> filters = gson.fromJson(jsonFileString, listFiltersType);
        for (Filters f: filters){
            f.setSwitchCheck(true);
        }
        return filters;
    }

    public List<News> newsPars(Context context){
        String jsonFileString = getJsonFromAssets(context, NEWS);

        Gson gson = new Gson();
        Type listNewsType = new TypeToken<List<News>>() { }.getType();

        List<News> news = gson.fromJson(jsonFileString, listNewsType);
        return news;
    }
}
