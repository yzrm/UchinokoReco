package com.example.uchinokoreco.ui.createDiary;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.uchinokoreco.R;

import java.util.Calendar;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CreateDiaryActivity extends AppCompatActivity implements CallbackListener {

    public static final String KEY_PETS_LIST_ID = "com.example.uchinokoreco.CreateDiaryActivity.key_pets_list_id";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ペットリストIDの取得
        int petsListId = getIntent().getIntExtra(KEY_PETS_LIST_ID, - 1);
        setContentView(R.layout.activity_create_diary);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        }

        // CreateDiaryFragmentのインスタンスを取得
        Fragment createDiaryFragment = CreateDiaryFragment.getInstance(petsListId);
        // CreateDiaryFragmentをセット
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_container, createDiaryFragment)
                .commit();
    }

    @Override
    public void doBackPress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });
    }
}
