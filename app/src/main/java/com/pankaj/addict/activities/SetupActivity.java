package com.pankaj.addict.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pankaj.addict.R;
import com.pankaj.addict.constants.SharedPrefreceKeys;
import com.pankaj.addict.helpers.viewHelpers.DialogHelper;
import com.pankaj.addict.helpers.ListHelper;
import com.pankaj.addict.helpers.SharedPreferencesHelper;
import com.pankaj.addict.helpers.adapters.SetupScreenRecyclerViewAdapter;
import com.pankaj.addict.interfaces.CreateAddictInterface;
import com.pankaj.addict.models.AddictModel;

import java.util.ArrayList;
import java.util.List;

public class SetupActivity extends AppCompatActivity implements CreateAddictInterface {

    private FloatingActionButton createNewAddiction;
    private TextView noItemText;
    private RecyclerView recyclerView;
    private Button completeBtn;


    private Context context;
    private DialogHelper dialogHelper;
    private List<AddictModel> addictModelArrayList;
    private ListHelper listHelper;
    private LinearLayoutManager linearLayoutManager;
    private SetupScreenRecyclerViewAdapter setupScreenRecyclerViewAdapter;
    private Boolean savedFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        savedFlag = (Boolean) SharedPreferencesHelper.get(context, SharedPrefreceKeys.SAVE_SETTINGS, false);
        if (savedFlag != null && savedFlag) {
            goToCounterActivity();
        }
        setupRecyclerView();
        createNewAddiction.setOnClickListener(v -> dialogHelper.showCreateAddictDialog());

        completeBtn.setOnClickListener(v -> {
            if (listHelper.getAll() != null && listHelper.getAll().size() >= 3) {
                SharedPreferencesHelper.put(context, SharedPrefreceKeys.SAVE_SETTINGS, true);
                goToCounterActivity();
            } else {
                Toast.makeText(context, "Please add at least 3 habit to continue", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToCounterActivity() {
        Intent goToCounter = new Intent(context, ShowCounterActivity.class);
        startActivity(goToCounter);
        finish();
    }


    private void init() {
        createNewAddiction = findViewById(R.id.create_habit_fab);
        recyclerView = findViewById(R.id.root_recyclerview);
        noItemText = findViewById(R.id.no_item_text);
        completeBtn = findViewById(R.id.complete_btn);
        context = SetupActivity.this;
        dialogHelper = new DialogHelper(context, SetupActivity.this);
        listHelper = new ListHelper(context);
        linearLayoutManager = new LinearLayoutManager(context);
    }

    private void setupRecyclerView() {
        addictModelArrayList = listHelper.getAll();
        if (addictModelArrayList != null && addictModelArrayList.size() > 0) {
            noItemText.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            setupScreenRecyclerViewAdapter = new SetupScreenRecyclerViewAdapter(addictModelArrayList);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(setupScreenRecyclerViewAdapter);
        } else {
            noItemText.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }

    }

    @Override
    public void onItemCreated(AddictModel addictModel) {
        if (addictModel != null) {
            if (addictModelArrayList == null) {
                addictModelArrayList = new ArrayList<>();
            }
            addictModelArrayList.add(addictModel);
            listHelper.saveList(addictModelArrayList);
            setupRecyclerView();
        }
    }

    @Override
    public void onNegativeResponse() {

    }

    @Override
    public void onPositiveResponse() {

    }
}
