package com.example.uchinokoreco.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Image {

    /**
     * ID
     */
    @PrimaryKey(autoGenerate = true)
    public int id;

    /**
     * 画像ファイル名
     */
    @ColumnInfo(name = "file_name")
    public String fileName;

    /**
     * 作成日時
     */
    @ColumnInfo(name = "created_at")
    public Date CreatedAt;
}
