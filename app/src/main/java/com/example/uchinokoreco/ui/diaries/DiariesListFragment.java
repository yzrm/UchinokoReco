package com.example.uchinokoreco.ui.diaries;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uchinokoreco.R;
import com.example.uchinokoreco.data.entities.Diaries;
import com.example.uchinokoreco.data.entities.PetsList;
import com.example.uchinokoreco.ui.adapter.DiariesListAdapter;
import com.example.uchinokoreco.ui.adapter.PetsListAdapter;
import com.example.uchinokoreco.ui.top.CallbackListener;
import com.example.uchinokoreco.ui.top.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class DiariesListFragment extends Fragment implements DiariesListViewModel.OnEventListener {
    private static final String KEY_PETS_LIST_ID = "key_pets_list_id";
    private static final String KEY_PETS_LIST_NAME = "key_pets_list_name";

    private CallbackListener callbackListener;
    private int petsListId= -1;
    private String petsName = "";
    private DiariesListViewModel diariesListViewModel;
    private RecyclerView recyclerView;
    private DiariesListAdapter adapter;

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
        // ViewModelの取得
        diariesListViewModel = new ViewModelProvider(requireActivity()).get(DiariesListViewModel.class);
        diariesListViewModel.setOnEventListener(this);
        //RecyclerViewの取得
        recyclerView = view.findViewById(R.id.diaries_recycler_view);
        //LayoutManagerの設定
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        //Adapterの設定
        adapter = new DiariesListAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);


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
        //データの読み込みと表示
        if (diariesListViewModel != null && petsListId != -1) {
            diariesListViewModel.getDiariesListByPetsListId(petsListId);
        }
    }

    public int getPetsListId() {
        return petsListId;
    }

    @Override
    public void getDiariesList(List<Diaries> diariesList) {
        Activity activity = getActivity();
        if (activity == null) return;
        activity.runOnUiThread(() -> adapter.updateDiaries(diariesList));
    }
}
