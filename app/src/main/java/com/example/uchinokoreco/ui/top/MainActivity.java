package com.example.uchinokoreco.ui.top;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.uchinokoreco.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TopFragmentのインスタンスを取得
        Fragment topFragment = TopFragment.getInstance();
        // TopFragmentをセット
        getSupportFragmentManager().beginTransaction()
                .add( R.id.main_container, topFragment)
                .commit();
    }
}