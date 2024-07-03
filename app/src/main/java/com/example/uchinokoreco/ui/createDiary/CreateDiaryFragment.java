package com.example.uchinokoreco.ui.createDiary;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.uchinokoreco.R;
import com.example.uchinokoreco.data.DiaryData;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class CreateDiaryFragment extends Fragment implements CreateDiaryViewModel.CreateDiaryCallback {
    private static final String KEY_PETS_LIST_ID = "com.example.uchinokoreco.CreateDiaryFragment.key_pets_list_id";
    public static Fragment getInstance(int petsListId) {
        Fragment fragment = new CreateDiaryFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_PETS_LIST_ID, petsListId);
        fragment.setArguments(args);
        return fragment;
    }
    private int petsListId = -1;
    private ImageView selectedImageView = null;
    private EditText diaryEditText;
    private CreateDiaryViewModel viewModel;

    private CallbackListener callbackListener;
    private TextView createDateText;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            callbackListener = (CreateDiaryActivity) context;

        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(requireContext()).inflate(R.layout.fragment_create_diary, container, false);
    }
    @Override
    public void onViewCreated(@Nullable View view, @androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            petsListId = getArguments().getInt(KEY_PETS_LIST_ID);
        }
        //ViewModelの取得
        viewModel = new ViewModelProvider(requireActivity()).get(CreateDiaryViewModel.class);
        // 作成日時
        createDateText = view.findViewById(R.id.create_date_text);
        // 今日の日付を表示する
        String currentDate = getCurrentDate();
        createDateText.setText(currentDate);
        //選択画像表示用ImageView
        selectedImageView = view.findViewById(R.id.image);
        //日記用EditText
        diaryEditText = view.findViewById(R.id.diary_edit_text);
        //各種ボタン設定
        buttonSetting(view);
    }

    /**
     *  今日の日付を文字列で取得する
     *
     * @return yyyy/MM/dd形式の今日の日付
     */
    private String getCurrentDate(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(calendar.getTime());
    }

    private void buttonSetting(View view) {
        //okボタン設定
        Button okBtn = view.findViewById(R.id.ok_button);
        okBtn.setOnClickListener(v -> {
           String diaryTextStr = diaryEditText.getText().toString().trim();
           if (petsListId != -1) {
               DiaryData data = new DiaryData(petsListId, diaryTextStr);
               viewModel.saveDiaryData(data, CreateDiaryFragment.this);
           }
        });
    }

    @Override
    public void onFailed(String massage) {

    }

    @Override
    public void onComplete() {
        if (callbackListener != null) {
            callbackListener.doBackPress();
        }
    }
}

