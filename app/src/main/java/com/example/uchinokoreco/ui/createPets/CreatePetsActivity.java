package com.example.uchinokoreco.ui.createPets;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.uchinokoreco.R;
import com.example.uchinokoreco.ui.top.TopFragment;

public class CreatePetsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pets);

        // CreatePetsFragmentのインスタンスを取得
        Fragment createPetsFragment = CreatePetsFragment.getInstance();
        // CreatePetsFragmentをセット
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_container, createPetsFragment)
                .commit();
    }
}
