package com.pankaj.addict.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pankaj.addict.constants.SharedPrefreceKeys;
import com.pankaj.addict.models.AddictModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListHelper {
    private Context context;

    public ListHelper(Context context) {
        this.context = context;
    }

    public void saveList(List<AddictModel> addictModels) {
        SharedPreferences mPrefs = context.getSharedPreferences(SharedPrefreceKeys.APP_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(addictModels);
        prefsEditor.putString(SharedPrefreceKeys.MY_LIST, json);
        prefsEditor.apply();

    }

    public List<AddictModel> getAll() {
        List<AddictModel> callLog;
        SharedPreferences mPrefs = context.getSharedPreferences(SharedPrefreceKeys.APP_PREF, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString(SharedPrefreceKeys.MY_LIST, "");
        if (json.isEmpty()) {
            callLog = new ArrayList<>();
        } else {
            Type type = new TypeToken<List<AddictModel>>() {
            }.getType();
            callLog = gson.fromJson(json, type);
        }
        return callLog;
    }
}
