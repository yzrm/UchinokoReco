package com.example.uchinokoreco.ui.top;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.uchinokoreco.R;
import com.example.uchinokoreco.data.entities.PetsList;
import com.example.uchinokoreco.ui.calendar.CalendarFragment;
import com.example.uchinokoreco.ui.createDiary.CreateDiaryActivity;
import com.example.uchinokoreco.ui.createPets.CreatePetsActivity;
import com.example.uchinokoreco.ui.diaries.DiariesListFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements CallbackListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.app_name);
        }

        // TopFragmentのインスタンスを取得
        Fragment topFragment = TopFragment.getInstance();
        // TopFragmentをセット
        getSupportFragmentManager().beginTransaction()
                .add( R.id.main_container, topFragment)
                .commit();

        // プラスボタン設定
        plusButtonSetting();

        //カレンダーボタン設定
        calendarButtonSetting();
    }

    //プラスボタン設定
    private void plusButtonSetting(){
        FloatingActionButton addBtn = findViewById(R.id.add_button);
        addBtn.setOnClickListener(view -> {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_container);
            //表示中のFragmentによって処理を分ける
            //TopFragmentの場合
            if(fragment instanceof TopFragment){
                Intent intent = new Intent(MainActivity.this, CreatePetsActivity.class);
                startActivity(intent);

            //DiariesListFragmentの場合
            }else if (fragment instanceof DiariesListFragment){
                int petsListId = ((DiariesListFragment) fragment).getPetsListId();
                //CreateDiaryActivityに推移する。
                Intent intent = new Intent(MainActivity.this, CreateDiaryActivity.class);
                intent.putExtra(CreateDiaryActivity.KEY_PETS_LIST_ID, petsListId);
                startActivity(intent);
            }
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

    /**
     * DiariesFragmentへ遷移する
     * @param petsList ペット情報
     */
    @Override
    public void moveToDiariesFragment(PetsList petsList) {
        Fragment diariesFragment = DiariesListFragment.getInstance(petsList);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, diariesFragment)
                .addToBackStack(null)
                .commit();
    }

    /**
     * タイトル変更
     * @param title
     */
    @Override
    public void changeTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }
}