package com.example.uchinokoreco.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Diaries {
    /**
     * ID
     */
    @PrimaryKey(autoGenerate = true)
    public int id;

    /**
     * PetsListのID
     */
    @ColumnInfo(name = "pets_list_id")
    public int petsListId;

    /**
     * 詳細
     */
    @ColumnInfo(name = "detail")
    public String detail;

    /**
     * 作成日時
     */
    @ColumnInfo(name = "created_at")
    public Date createdAt;


    // pets → PetsListID → DiariesID → 画像ファイル名
    // 実際のパス例：pets/1/1/PET_1.jpg
    //
}
