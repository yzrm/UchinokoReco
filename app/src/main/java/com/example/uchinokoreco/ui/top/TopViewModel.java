package com.example.uchinokoreco.ui.top;

import androidx.lifecycle.ViewModel;

import com.example.uchinokoreco.data.entities.Diaries;
import com.example.uchinokoreco.data.entities.PetsList;
import com.example.uchinokoreco.data.repositories.UchinokoRecoRepository;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class TopViewModel extends ViewModel {
    interface OnEventListener {
        void getPetsList(List<PetsList> petsLists);
    }

    private OnEventListener listener;

    private UchinokoRecoRepository repository;
    @Inject
    TopViewModel(UchinokoRecoRepository repository) {
        this.repository = repository;
    }

    public void setOnEventListener(OnEventListener listener){
        this.listener = listener;
    }
    public void getPetsList(){
        new Thread() {
            @Override
            public void run() {
                super.run();
                if (listener != null){
                    listener.getPetsList(repository.getPetsListAll());
                }
            }
        }.start();
    }

    public void addPetsList(){

        new Thread() {
            @Override
            public void run() {
                super.run();
                PetsList petsList = new PetsList();
                petsList.petName = "ペット";
                petsList.createdAt = new Date();
                petsList.imageName = "imageName";
                repository.insertPetsList(petsList);
            }
        }.start();
    }
    public void addDiariesList(){

        new Thread() {
            @Override
            public void run() {
                super.run();
                Diaries diaries = new Diaries();
                diaries.detail = "テスト日記です。";
                diaries.createdAt = new Date();
                repository.insertDiaries(diaries);
            }
        }.start();
    }
}
