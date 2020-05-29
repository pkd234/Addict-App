package com.pankaj.addict.helpers.viewHelpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatSpinner;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.pankaj.addict.R;
import com.pankaj.addict.interfaces.CreateAddictInterface;
import com.pankaj.addict.models.AddictModel;

public class DialogHelper {
    private Context context;
    private CreateAddictInterface createAddictInterface;
    private String[] arraySpinner = new String[]{
            "Daily", "Weekly", "Monthly", "Yearly"
    };
    private String selectedSpinnerValue;

    public DialogHelper(Context context, CreateAddictInterface createAddictInterface) {
        this.context = context;
        this.createAddictInterface = createAddictInterface;
    }

    public void showCreateAddictDialog() {
        View view = LayoutInflater.from(context).inflate(R.layout.add_addict_layout, null, false);
        EditText name;
        Spinner spinner;
        NumberPicker numberPicker;
        name = view.findViewById(R.id.add_addict_name);
        spinner = view.findViewById(R.id.add_addict_spinner);
        numberPicker = view.findViewById(R.id.add_addict_number_picker);
        numberPicker.setMaxValue(100);
        numberPicker.setMinValue(1);
        numberPicker.setValue(10);
        spinner.setAdapter(getSpinnerMenu());

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != -1) {
                    selectedSpinnerValue = arraySpinner[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        new MaterialAlertDialogBuilder(context)
                .setView(view)
                .setNegativeButton("Discard", (dialog, which) -> dialog.dismiss())
                .setPositiveButton("Save", (dialog, which) -> {
                    if (name.getText().toString().length() > 0) {
                        AddictModel addictModel = new AddictModel(name.getText().toString(), numberPicker.getValue(), selectedSpinnerValue, 0);
                        createAddictInterface.onItemCreated(addictModel);
                        dialog.dismiss();
                    } else {
                        Toast.makeText(context, "Name can't be blank", Toast.LENGTH_SHORT).show();
                    }

                })
                .show();

    }

    public void simpleWarningDialog() {
        new MaterialAlertDialogBuilder(context)
                .setTitle("Limit Warning")
                .setMessage("You are exceeding your target limit, do you still want to continue?")
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .setPositiveButton("Continue", (dialog, which) -> {
                    createAddictInterface.onPositiveResponse();
                    dialog.dismiss();
                })
                .show();

    }

    private ArrayAdapter<String> getSpinnerMenu() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }


}
