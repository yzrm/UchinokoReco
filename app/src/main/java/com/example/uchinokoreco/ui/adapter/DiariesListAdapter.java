package com.example.uchinokoreco.ui.adapter;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uchinokoreco.R;
import java.util.List;

public class DiariesListAdapter extends RecyclerView.Adapter<DiariesListAdapter.ViewHolder> {

    public DiariesListAdapter(){

    }
    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.diaries_list_item_layout, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiariesListAdapter.ViewHolder holder, int position) {

    }
    @Override
    public int getItemCount(){

        return 0;
    }

}
