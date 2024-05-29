package com.example.uchinokoreco.ui.createPets;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.uchinokoreco.R;
import com.example.uchinokoreco.ui.top.TopFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CreatePetsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pets);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //ツールバーに戻るボタンを設置
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        }
        // CreatePetsFragmentのインスタンスを取得
        Fragment createPetsFragment = CreatePetsFragment.getInstance();
        // CreatePetsFragmentをセット
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_container, createPetsFragment)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
