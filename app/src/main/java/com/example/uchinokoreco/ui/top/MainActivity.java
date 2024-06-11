package com.example.uchinokoreco.ui.top;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.uchinokoreco.R;
import com.example.uchinokoreco.ui.calendar.CalendarFragment;
import com.example.uchinokoreco.ui.createPets.CreatePetsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private EditText petList;
    private RecyclerView petListRecyclerView;
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

        petList = findViewById(R.id.pet_name_edit_text);
        petList.setOnKeyListener(this);

        petListRecyclerView = findViewById(R.id.top_recycler_view);


        // プラスボタン設定
        plusButtonSetting();

        //カレンダーボタン設定
        calendarButtonSetting();
    }

    //プラスボタン設定
    private void plusButtonSetting(){
        FloatingActionButton addBtn = findViewById(R.id.add_button);
        addBtn.setOnClickListener(view -> {
            //TODO:表示中のFragmentによって処理を分ける

            Intent intent = new Intent(MainActivity.this, CreatePetsActivity.class);
            startActivity(intent);
        });
    }

    //カレンダーボタン設定
    private void calendarButtonSetting(){
        FloatingActionButton calBtn = findViewById(R.id.calendar_button);
        calBtn.setOnClickListener(view -> {

            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_container);
            if (fragment instanceof TopFragment) {
                // トップ画面の場合カレンダーフラグメントを表示
                Fragment calenderFragment = CalendarFragment.getInstance();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.main_container, calenderFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}