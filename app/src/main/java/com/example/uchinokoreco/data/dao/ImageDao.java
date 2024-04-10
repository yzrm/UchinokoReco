package com.example.uchinokoreco.data.dao;

import androidx.room.Dao;
import androidx.room.Update;
import androidx.room.Upsert;

import com.example.uchinokoreco.data.entities.Image;

@Dao
public interface ImageDao {

    @Upsert
    void insert(Image image);
}
