package com.example.uchinokoreco.data.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Upsert;

import com.example.uchinokoreco.data.entities.PetsList;

import java.util.List;

@Dao
public interface PetsListDao {
    @Upsert
    void insert(PetsList petsList);

    @Query("SELECT * FROM petslist")
    List<PetsList> getAll();
}
