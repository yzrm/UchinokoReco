package com.example.uchinokoreco.ui.diaries;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.uchinokoreco.R;
import com.example.uchinokoreco.data.entities.PetsList;
import com.example.uchinokoreco.ui.top.CallbackListener;
import com.example.uchinokoreco.ui.top.MainActivity;

public class DiariesListFragment extends Fragment {



    private static final String KEY_PETS_LIST_ID = "key_pets_list_id";
    private static final String KEY_PETS_LIST_NAME = "key_pets_list_name";

    private CallbackListener callbackListener;
    private int petsListId= -1;
    private String petsName = "";

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            callbackListener = (MainActivity) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    public static Fragment getInstance(PetsList petsList){
        Fragment fragment = new DiariesListFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_PETS_LIST_ID, petsList.id);
        args.putString(KEY_PETS_LIST_NAME, petsList.petName);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(requireContext()).inflate(R.layout.fragment_diaries_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            petsListId = getArguments().getInt(KEY_PETS_LIST_ID);
            petsName = getArguments().getString(KEY_PETS_LIST_NAME);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(callbackListener != null) {
            // タイトル変更
            callbackListener.changeTitle(petsName);
        }
        //TODO:データの読み込みと表示

    }
}
