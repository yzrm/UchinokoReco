package com.example.uchinokoreco.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.uchinokoreco.data.dao.DiariesDao;
import com.example.uchinokoreco.data.dao.ImageDao;
import com.example.uchinokoreco.data.dao.PetsListDao;
import com.example.uchinokoreco.data.entities.Diaries;
import com.example.uchinokoreco.data.entities.Image;
import com.example.uchinokoreco.data.entities.PetsList;

@Database(entities = { PetsList.class, Diaries.class, Image.class }, exportSchema = false, version = 1)
@TypeConverters({ DateTimeConverter.class })
public abstract class UchinokoDatabase extends RoomDatabase {
    public abstract PetsListDao petsListDao();
    public abstract DiariesDao diariesDao();

    public abstract ImageDao imageDao();
}
