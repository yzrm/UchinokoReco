package com.example.uchinokoreco.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
@Entity
public class PetsList {
    /**
     * ID
     */
    @PrimaryKey(autoGenerate = true)
    public int id;

    /**
     * ペット名
     */
    @ColumnInfo(name = "pet_name")
    public String petName;

    /**
     * 作成日時
     */
    @ColumnInfo(name = "created_at")
    public Date createdAt;

    /**
     * 画像のパス
     */
    @ColumnInfo(name = "image_path")
    public String imagePath;
}

