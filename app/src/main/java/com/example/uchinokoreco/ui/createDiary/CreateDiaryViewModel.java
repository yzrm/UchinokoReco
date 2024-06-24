package com.example.uchinokoreco.ui.createDiary;

import androidx.lifecycle.ViewModel;

import com.example.uchinokoreco.data.entities.Diaries;
import com.example.uchinokoreco.data.repositories.UchinokoRecoRepository;

import java.util.List;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CreateDiaryViewModel extends ViewModel {
    private UchinokoRecoRepository repository;
    private CreateDiaryCallback createDiaryCallback;

    public interface  CreateDiaryCallback {
        void onFailed(String massage);
        void onComplete();
    }


}
