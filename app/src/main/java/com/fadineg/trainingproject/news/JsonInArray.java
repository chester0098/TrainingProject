package com.fadineg.trainingproject.news;

import android.content.Context;

import com.fadineg.trainingproject.news.model.News;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

import io.realm.Realm;

public class JsonInArray {
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

    public List<News> newsPars(Context context) {
        String jsonFileString = getJsonFromAssets(context, NEWS);

        Gson gson = new Gson();
        Type listNewsType = new TypeToken<List<News>>() {
        }.getType();

        List<News> news = gson.fromJson(jsonFileString, listNewsType);
        for (News n : news) {
            n.setCategorySwitch(true);
        }
        return news;
    }
}
