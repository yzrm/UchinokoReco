package com.example.uchinokoreco.data.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Upsert;

import com.example.uchinokoreco.data.entities.PetsList;

import java.util.List;

@Dao
public interface PetsListDao {
    @Upsert
    long insert(PetsList petsList);

    @Query("SELECT * FROM petslist")
    List<PetsList> getAll();

    @Query("SELECT * FROM PetsList WHERE id = :id")
    List<PetsList> getDataById(long id);
}
