package com.example.uchinokoreco.ui.createDiary;

import android.media.RingtoneManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.uchinokoreco.data.DiaryData;
import com.example.uchinokoreco.data.entities.Diaries;
import com.example.uchinokoreco.data.repositories.UchinokoRecoRepository;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CreateDiaryViewModel extends ViewModel {
    private UchinokoRecoRepository repository;
    private CreateDiaryCallback createDiaryCallback;

    public interface  CreateDiaryCallback {
        void onFailed(String massage);
        void onComplete();
    }

    public void saveDiaryData(DiaryData data, CreateDiaryCallback callback) {
        createDiaryCallback = callback;

        Diaries diaries = new Diaries();
        diaries.petsListId = data.getPetListId();
        diaries.detail = data.getMessage();
        diaries.createdAt = new Date();

        HandlerThread thread = new HandlerThread("saveDiaryData");
        thread.start();
        Handler handler = new Handler(thread.getLooper());
            handler.postDelayed(() -> {
                repository.insertDiaries(diaries);
                if (createDiaryCallback != null){
                            createDiaryCallback.onComplete();
                }
            }, 0);
    }
    @Inject
    CreateDiaryViewModel(UchinokoRecoRepository repository){ this.repository = repository; }

}
