package com.example.uchinokoreco.ui.createDiary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.uchinokoreco.R;
import com.example.uchinokoreco.ui.createPets.CreatePetsViewModel;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class CreateDiaryFragment extends Fragment {
    public static Fragment getInstance(){
        return new CreateDiaryFragment();
    }
    private ImageView selectedImageView = null;
    private EditText DiaryEditText;
    private CreateDiaryViewModel viewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(requireContext()).inflate(R.layout.fragment_create_diary, container, false);
    }
    @Override
    public void onViewCreated(@Nullable View view, @androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //ViewModelの取得
        viewModel = new ViewModelProvider(requireActivity()).get(CreateDiaryViewModel.class);
        //選択画像表示用ImageView
        selectedImageView = view.findViewById(R.id.image);
        //日記用EditText
        DiaryEditText = view.findViewById(R.id.diary_edit_text);
        //各種ボタン設定
        buttonSetting(view);
    }

    private void buttonSetting(View view) {
        //okボタン設定
        Button okBtn = view.findViewById(R.id.ok_button);



    }
}

