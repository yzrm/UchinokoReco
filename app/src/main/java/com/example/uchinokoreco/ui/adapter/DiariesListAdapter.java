package com.example.uchinokoreco.ui.adapter;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uchinokoreco.R;
import com.example.uchinokoreco.data.entities.Diaries;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DiariesListAdapter extends RecyclerView.Adapter<DiariesListAdapter.ViewHolder> {

    public interface OnClickItemLister {
        void onClickItem (Diaries diaries);
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView createAtText;
        TextView diaryText;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            createAtText = itemView.findViewById(R.id.create_at_text);
            diaryText = itemView.findViewById(R.id.diary_text);
        }
    }
    private OnClickItemLister listener;
    private List<Diaries> diaries;

    public DiariesListAdapter(List<Diaries> diaries){
        this.diaries = diaries;
    }
    public void updateDiaries(List<Diaries> diaries){
        this.diaries.clear();
        this.diaries.addAll(diaries);
        notifyDataSetChanged();
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
        Diaries data = diaries.get(position);
        holder.createAtText.setText(dateToString(data.createdAt));
        holder.diaryText.setText(data.detail);

        holder.itemView.setOnClickListener(view -> {
            if (listener != null ){
                listener.onClickItem(data);
            }
        });
    }
    @Override
    public int getItemCount(){
        return diaries.size();
    }

    public void setOnClickItemListener(OnClickItemLister listener){
        this.listener = listener;
    }
    private String dateToString(Date date) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(calendar.getTime());
    }
}
