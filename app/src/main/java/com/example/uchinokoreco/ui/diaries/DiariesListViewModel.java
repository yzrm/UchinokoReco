package com.example.uchinokoreco.ui.diaries;

import androidx.lifecycle.ViewModel;

import com.example.uchinokoreco.data.entities.Diaries;
import com.example.uchinokoreco.data.repositories.UchinokoRecoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DiariesListViewModel extends ViewModel {

    interface OnEventListener {
        void getDiariesList(List<Diaries> diariesList);
    }
    private OnEventListener listener;
    private UchinokoRecoRepository repository;
    @Inject
    DiariesListViewModel(UchinokoRecoRepository repository){
        this.repository = repository;
    }

    public void setOnEventListener(OnEventListener listener){
        this.listener = listener;
    }

    public void getDiariesListByPetsListId(int id) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                if (listener != null) {
                    listener.getDiariesList(repository.getDiariesListById(id));
                }
            }
        }.start();
    }
}
