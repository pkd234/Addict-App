package com.pankaj.addict.helpers.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pankaj.addict.R;
import com.pankaj.addict.models.AddictModel;

import java.util.List;

public class SetupScreenRecyclerViewAdapter extends RecyclerView.Adapter<SetupScreenRecyclerViewAdapter.SetupScreenRecyclerViewHolder> {

    private List<AddictModel> addictModelList;

    public SetupScreenRecyclerViewAdapter(List<AddictModel> addictModelList) {
        this.addictModelList = addictModelList;
    }

    @NonNull
    @Override
    public SetupScreenRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addict_item_setup_screen, parent, false);
        return new SetupScreenRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SetupScreenRecyclerViewHolder holder, int position) {
        holder.name.setText(addictModelList.get(position).getAddictName());
        holder.type.setText(addictModelList.get(position).getResetType());
        holder.target.setText(addictModelList.get(position).getAddictTarget().toString());
    }

    @Override
    public int getItemCount() {
        return addictModelList.size();
    }

    static class SetupScreenRecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView name, type, target;

        SetupScreenRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.addict_name);
            type = itemView.findViewById(R.id.addict_type);
            target = itemView.findViewById(R.id.addict_target);
        }
    }
}
