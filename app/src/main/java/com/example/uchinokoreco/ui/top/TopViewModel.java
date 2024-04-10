package com.example.uchinokoreco.ui.top;

import androidx.lifecycle.ViewModel;

import com.example.uchinokoreco.data.entities.PetsList;
import com.example.uchinokoreco.data.repositories.UchinokoRecoRepository;

import java.util.Date;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class TopViewModel extends ViewModel {

    private UchinokoRecoRepository repository;
    @Inject
    TopViewModel(UchinokoRecoRepository repository) {
        this.repository = repository;
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
}
