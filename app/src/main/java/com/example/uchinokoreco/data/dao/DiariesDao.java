package com.example.uchinokoreco.data.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Upsert;

import com.example.uchinokoreco.data.entities.Diaries;
import com.example.uchinokoreco.data.entities.PetsList;

import java.util.List;

@Dao
public interface DiariesDao {
    @Upsert
    void  insert(Diaries diaries);

    @Query("SELECT * FROM diaries")
    List<Diaries> getAll();

    @Query("SELECT * FROM diaries WHERE pets_list_id = :petsListId")
    List<Diaries> getDiariesListById(int petsListId);

    @Query("SELECT COUNT(*) FROM diaries WHERE pets_list_id = :petsListId")
    int getDiariesCount(int petsListId);
}
