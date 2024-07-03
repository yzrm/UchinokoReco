package com.example.uchinokoreco.ui.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uchinokoreco.R;
import com.example.uchinokoreco.data.entities.PetsList;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class PetsListAdapter extends RecyclerView.Adapter<PetsListAdapter.ViewHolder>{

    public interface OnClickItemListener{
        void onClickItem(PetsList petsList);
    }
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView petNameText;
        ImageView petImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            petNameText = itemView.findViewById(R.id.pet_name);
            petImg = itemView.findViewById(R.id.pet_img);
        }
    }
    private List<PetsList> petsList;
    private OnClickItemListener listener;
    private File filesDir;
    public PetsListAdapter(File filesDir, List<PetsList> petsList) {
        this.filesDir = filesDir;
        this.petsList = petsList;
    }
    public void updatePetsList(List<PetsList> petsList){
        this.petsList.clear();
        this.petsList.addAll(petsList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.pets_list_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetsListAdapter.ViewHolder holder, int position) {
        PetsList data = petsList.get(position);
        holder.petNameText.setText(data.petName);
        if (filesDir != null ) {
            //画像取得処理を行う
            Bitmap bitmap = getPetsImageBitmap(data.id, data.imageName);
            if (bitmap != null){
                holder.petImg.setImageBitmap(bitmap);
            }
        }
        holder.itemView.setOnClickListener(view -> {
            if (listener != null){
                listener.onClickItem(data);
            }
        });
    }

    private Bitmap getPetsImageBitmap(int petListId, String imageName) {
        String fileName = imageName + ".jpg";
        File dir = new File(filesDir, String.valueOf(petListId));
        Bitmap bitmap = null;
        try (FileInputStream fileInputStream = new FileInputStream(new File(dir, fileName))){
            bitmap = BitmapFactory.decodeStream(fileInputStream);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap ;
    }

    @Override
    public int getItemCount() {
        return petsList.size();
    }

    public void setOnClickItemListener(OnClickItemListener listener) {
        this.listener = listener;
    }
}
