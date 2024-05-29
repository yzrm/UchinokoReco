package com.example.uchinokoreco.ui.createPets;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.example.uchinokoreco.data.entities.PetsList;
import com.example.uchinokoreco.data.repositories.UchinokoRecoRepository;
import com.example.uchinokoreco.ui.top.TopViewModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CreatePetsViewModel extends ViewModel {
    // コールバック用インターフェース
    private UchinokoRecoRepository repository;
    private Uri selectedUri;

    private CreatePetsCallback createPetsCallback;

    //コールバック用インターフェース
    public interface CreatePetsCallback {
        void onSuccess(long id);
        void onFailed();
        void  onComplete();
    }

    public void setSelectedUri(Uri uri){
        this.selectedUri = uri;
    }
    public List<PetsList> getPetsListDataById(long id){
        return repository.getPetsListById(id);
    }
    public void savePetsListData(String petName, CreatePetsCallback callback) {
        createPetsCallback = callback;
        if (selectedUri == null ) {
            if (createPetsCallback != null) {
                createPetsCallback.onFailed();
            }
            return;
        }
        PetsList petsList = new PetsList();
        petsList.petName = petName;
        petsList.createdAt = new Date();
        petsList.imageName = "PET_IMG";

        Log.d("TEST", "ID:" + petsList.id);
        new Thread() {
            @Override
            public void run() {
                super.run();
                long id = repository.insertPetsList(petsList);
                if (createPetsCallback != null) {
                    createPetsCallback.onSuccess(id);
                }
            }
        }.start();
    }
    public void savePetsListImageData(Activity activity, File dir, String fileName) {
        if (selectedUri == null) return;
        new Thread() {
            @Override
            public void run() {
                super.run();
                Bitmap bitmap = null;
                try {
                    bitmap = Glide.with(activity)
                            .asBitmap()
                            .load(selectedUri)
                            .submit()
                            .get();

                }catch(ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (bitmap != null) {
                    saveFile(dir, fileName, bitmap);
                }
            }
        }.start();
    }
    private void saveFile(File dir, String fileName, Bitmap bitmap) {

        try (FileOutputStream fileOutputStream = new FileOutputStream( new File(dir, fileName))) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
        if (createPetsCallback != null) {
            createPetsCallback.onComplete();
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Inject
    CreatePetsViewModel(UchinokoRecoRepository repository){
        this.repository = repository;
    }
}
