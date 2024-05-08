package com.example.uchinokoreco.ui.createPets;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.uchinokoreco.R;
import com.example.uchinokoreco.ui.top.TopFragment;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Nullable;

public class CreatePetsFragment extends Fragment {
    public static Fragment getInstance(){ return new CreatePetsFragment();}

    private static final String TAG = CreatePetsActivity.class.getSimpleName();
    private ImageView selectedImageView = null;
    private EditText petNameEditText;
    private File file;

    // PhotoPicker起動用ランチャー
    ActivityResultLauncher<PickVisualMediaRequest> launcher = registerForActivityResult(
            new ActivityResultContracts.PickVisualMedia(), uri -> {
                if (uri == null) {
                    //TODO:写真取得失敗時の処理
                    Log.d(TAG, "取得失敗");
                } else {
                    // TODO: 写真取得成功時の処理
                    Log.d(TAG, "取得成功！" + uri);
                    if (selectedImageView != null) {
                        // TODO: uriからイメージデータを複製してアプリ内に保存する

                        saveFile("test.txt", "test");

                        // TODO: アプリ内に保存した画像を表示

                        try {
                            InputStream stream = requireActivity().getContentResolver().openInputStream(uri);
                            Bitmap bmp = BitmapFactory.decodeStream(new BufferedInputStream(stream));
                            selectedImageView.setImageBitmap(bmp);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
    );
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return  LayoutInflater.from(requireContext()).inflate(R.layout.fragment_create_pets, container, false);
    }

    @Override
    public void onViewCreated(@Nullable View view, @androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //選択画像表示用ImageView
        selectedImageView = view.findViewById(R.id.image);
        //ペット名用EditText
        petNameEditText = view.findViewById(R.id.pet_name_edit_text);
        //各種ボタン設定
        buttonSetting(view);

    }

    private void buttonSetting(View view) {
        //okボタン設定
        Button okBtn = view.findViewById(R.id.ok_button);
//        if (petNameEditText.getText().toString().isEmpty() || selectedImageView.getDrawable() == null) {
//            okBtn.setEnabled(false);
//        } else {
//            okBtn.setEnabled(true);
//        }

        okBtn.setOnClickListener(v ->{
            //TODO:DBにPetsListのデータを保存する
            //TODO：保存後にデータを読み込んでパスを作成
            //TODO:画像をパスに保存する
        });

        // galleryボタン設定
        ImageButton imageBtn = view.findViewById(R.id.gallery_button);
        imageBtn.setOnClickListener(v -> {
            launcher.launch(new PickVisualMediaRequest.Builder()
                    .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                    .build()
            );
        });
        // cameraボタン設定
        ImageButton imageBtb = view.findViewById(R.id.camera_button);
        imageBtb.setOnClickListener(v -> {
            Log.d(TAG, "Cameraボタンが押された");
        });
    }
    private void saveFile(String file, String str) {

        try (FileOutputStream fileOutputStream = requireActivity().openFileOutput(file, Context.MODE_PRIVATE)) {
            fileOutputStream.write(str.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
