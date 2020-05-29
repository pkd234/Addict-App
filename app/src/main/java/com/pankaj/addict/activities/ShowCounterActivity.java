package com.pankaj.addict.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pankaj.addict.R;
import com.pankaj.addict.constants.SharedPrefreceKeys;
import com.pankaj.addict.helpers.viewHelpers.DialogHelper;
import com.pankaj.addict.helpers.ListHelper;
import com.pankaj.addict.helpers.viewHelpers.NotificationHelper;
import com.pankaj.addict.helpers.SharedPreferencesHelper;
import com.pankaj.addict.helpers.adapters.CounterRecyclerViewAdapter;
import com.pankaj.addict.interfaces.CreateAddictInterface;
import com.pankaj.addict.interfaces.RecyclerViewCallbackInterface;
import com.pankaj.addict.models.AddictModel;

import java.util.List;

public class ShowCounterActivity extends AppCompatActivity implements RecyclerViewCallbackInterface, CreateAddictInterface {

    private Button resetAll, resetCurrent;
    private RecyclerView recyclerView;


    private NotificationHelper notificationHelper;
    private List<AddictModel> addictModelList;
    private String currentUUID;
    private DialogHelper dialogHelper;
    private ListHelper listHelper;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_counter);
        initViews();
        setupRecyclerView();
        resetAll.setOnClickListener(v -> resetSettings());
        resetCurrent.setOnClickListener(v -> resetValues());
    }


    private void resetSettings() {
        SharedPreferencesHelper.put(context, SharedPrefreceKeys.SAVE_SETTINGS, false);
        SharedPreferencesHelper.put(context, SharedPrefreceKeys.MY_LIST, "");
        Intent goToMain = new Intent(context, SetupActivity.class);
        startActivity(goToMain);
        finish();
    }

    void setupRecyclerView() {
        addictModelList = listHelper.getAll();
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        recyclerView.setAdapter(new CounterRecyclerViewAdapter(addictModelList, ShowCounterActivity.this));

    }

    private void initViews() {
        context = ShowCounterActivity.this;
        resetAll = findViewById(R.id.reset_all);
        resetCurrent = findViewById(R.id.reset_current);
        recyclerView = findViewById(R.id.root_recyclerview);
        listHelper = new ListHelper(context);
        notificationHelper = new NotificationHelper(context);
        dialogHelper = new DialogHelper(context, ShowCounterActivity.this);
    }

    private void resetValues() {
        for (AddictModel addictModel : addictModelList) {
            addictModel.setAddictCounter(0);
        }
        listHelper.saveList(addictModelList);
        setupRecyclerView();
    }


    @Override
    public void itemClicked(String uuid) {
        currentUUID = uuid;
        for (AddictModel addictModel : addictModelList) {
            if (addictModel.getUid().equals(currentUUID)) {
                Integer counter = addictModel.getAddictCounter();
                if (counter >= addictModel.getAddictTarget() - 1) {
                    dialogHelper.simpleWarningDialog();
                } else {
                    addictModel.setAddictCounter(++counter);
                    listHelper.saveList(addictModelList);
                    setupRecyclerView();
                }
            }
        }


    }

    @Override
    public void onItemCreated(AddictModel addictModel) {

    }

    @Override
    public void onNegativeResponse() {

    }

    @Override
    public void onPositiveResponse() {
        for (AddictModel addictModel : addictModelList) {
            if (addictModel.getUid().equals(currentUUID)) {
                Integer counter = addictModel.getAddictCounter();
                addictModel.setAddictCounter(++counter);
            }
        }
        notificationHelper.showWarningNotification();
        currentUUID = "";
        listHelper.saveList(addictModelList);
        setupRecyclerView();
    }
}
