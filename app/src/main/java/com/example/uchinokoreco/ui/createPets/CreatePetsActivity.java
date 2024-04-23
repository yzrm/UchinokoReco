package com.example.uchinokoreco.ui.createPets;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.uchinokoreco.R;

public class CreatePetsActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pets);

        // galleryボタン設定
        galleryButtonSetting();
    }
    private void galleryButtonSetting(){
        ImageButton imageBtn = findViewById(R.id.gallery_button);
        imageBtn.setOnClickListener(view -> {

            //テストでトースト表示できるか確認。
            Context context = getApplicationContext();
            CharSequence text = "ギャラリーボタンが押されました。";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        });
    }
}
