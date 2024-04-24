package com.example.uchinokoreco.ui.createPets;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.uchinokoreco.R;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class CreatePetsActivity extends AppCompatActivity {

    private static final String TAG = CreatePetsActivity.class.getSimpleName();

    private ImageView selectedImageView = null;

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

                        // TODO: アプリ内に保存した画像を表示

                        try {
                            InputStream stream = CreatePetsActivity.this.getContentResolver().openInputStream(uri);
                            Bitmap bmp = BitmapFactory.decodeStream(new BufferedInputStream(stream));
                            selectedImageView.setImageBitmap(bmp);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
    );

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pets);

        //表示更新
        selectedImageView = findViewById(R.id.image);


        // galleryボタン設定
        galleryButtonSetting();
    }
    private void galleryButtonSetting(){
        ImageButton imageBtn = findViewById(R.id.gallery_button);
        imageBtn.setOnClickListener(view -> {
            launcher.launch(new PickVisualMediaRequest.Builder()
                    .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                    .build()
            );
        });
    }
}
