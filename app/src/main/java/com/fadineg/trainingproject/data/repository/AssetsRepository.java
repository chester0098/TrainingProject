package com.fadineg.trainingproject.data.repository;

import android.content.Context;

import com.fadineg.trainingproject.domain.model.news.News;
import com.fadineg.trainingproject.domain.repositoryInterface.AssetsRepositoryInterface;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class AssetsRepository implements AssetsRepositoryInterface {
    private static final String NEWS = "news.json";

    public List<News> getNewsFromAssets(Context context) {
        String jsonFileString = getJsonFromAssets(context, NEWS);

        Gson gson = new Gson();
        Type listNewsType = new TypeToken<List<News>>() {
        }.getType();

        return gson.fromJson(jsonFileString, listNewsType);
    }

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
}
