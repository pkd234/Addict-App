package com.pankaj.addict.helpers.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pankaj.addict.R;
import com.pankaj.addict.interfaces.RecyclerViewCallbackInterface;
import com.pankaj.addict.models.AddictModel;

import java.util.List;

import antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator;

public class CounterRecyclerViewAdapter extends RecyclerView.Adapter<CounterRecyclerViewAdapter.CounterViewHolder> {

    private List<AddictModel> addictModelList;
    private RecyclerViewCallbackInterface recyclerViewCallbackInterface;

    public CounterRecyclerViewAdapter(List<AddictModel> addictModelList, RecyclerViewCallbackInterface recyclerViewCallbackInterface) {
        this.addictModelList = addictModelList;
        this.recyclerViewCallbackInterface = recyclerViewCallbackInterface;
    }

    @NonNull
    @Override
    public CounterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.counter_item, parent, false);
        return new CounterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CounterViewHolder holder, int position) {
        holder.addictName.setText(addictModelList.get(position).getAddictName());
        holder.circularProgressIndicator.setProgress(addictModelList.get(position).getAddictCounter(), addictModelList.get(position).getAddictTarget());
        holder.rootCard.setOnClickListener(v -> recyclerViewCallbackInterface.itemClicked(addictModelList.get(position).getUid()));

    }

    @Override
    public int getItemCount() {
        return addictModelList.size();
    }

    static class CounterViewHolder extends RecyclerView.ViewHolder {
        private CircularProgressIndicator circularProgressIndicator;
        private TextView addictName;
        private LinearLayout rootCard;

        CounterViewHolder(@NonNull View itemView) {
            super(itemView);
            circularProgressIndicator = itemView.findViewById(R.id.circular_progress);
            addictName = itemView.findViewById(R.id.addict_name);
            rootCard = itemView.findViewById(R.id.root_card);

        }
    }
}
