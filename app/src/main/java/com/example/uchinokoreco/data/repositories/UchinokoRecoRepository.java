package com.example.uchinokoreco.data.repositories;

import android.content.Context;

import androidx.room.Room;

import com.example.uchinokoreco.data.UchinokoDatabase;
import com.example.uchinokoreco.data.dao.DiariesDao;
import com.example.uchinokoreco.data.dao.ImageDao;
import com.example.uchinokoreco.data.dao.PetsListDao;
import com.example.uchinokoreco.data.entities.Diaries;
import com.example.uchinokoreco.data.entities.PetsList;

import java.util.List;

public class UchinokoRecoRepository {
    private UchinokoDatabase db;
    private PetsListDao petsListDao;
    private DiariesDao diariesDao;

    private ImageDao imageDao;

    public UchinokoRecoRepository(Context context){
        db = Room.databaseBuilder(
                context,
                UchinokoDatabase.class,
                "uchinokoreco-db"
        ).build();
        petsListDao = db.petsListDao();
        diariesDao = db.diariesDao();
        imageDao = db.imageDao();
    }

    public long insertPetsList(PetsList petsList){
        return petsListDao.insert(petsList);
    }
    public long insertDiaries(Diaries diaries) {
        return diariesDao.insert(diaries);
    }
    public List<PetsList> getPetsListAll(){
        return petsListDao.getAll();
    }
    public List<PetsList> getPetsListById(long id){
        return petsListDao.getDataById(id);
    }
    public List<Diaries> getDiariesListById(int petsListId){
        return diariesDao.getDiariesListById(petsListId);
    }
    public int getDiariesCount(int petsListId)
    {return diariesDao.getDiariesCount(petsListId);
    }
}
