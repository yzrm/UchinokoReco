package com.example.uchinokoreco.ui.top;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uchinokoreco.R;
import com.example.uchinokoreco.data.entities.PetsList;
import com.example.uchinokoreco.ui.adapter.PetsListAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TopFragment extends Fragment implements TopViewModel.OnEventListener, PetsListAdapter.OnClickItemListener {

    public static Fragment getInstance(){
        return new TopFragment();
    }

    private TopViewModel topViewModel;
    private RecyclerView recyclerView;
    private PetsListAdapter adapter;
    private CallbackListener callbackListener;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            callbackListener = (MainActivity) context;
        } catch(ClassCastException e){
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(requireContext()).inflate(R.layout.fragment_top, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // ViewModelの取得
        topViewModel = new ViewModelProvider(requireActivity()).get(TopViewModel.class);
        topViewModel.setOnEventListener(this);
        //RecyclerViewの取得
        recyclerView = view.findViewById(R.id.top_recycler_view);
        //LayoutManagerの設定
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        //Adapterの設定
        adapter = new PetsListAdapter(new ArrayList<>());
        adapter.setOnClickItemListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (callbackListener != null){
            callbackListener.changeTitle(getString(R.string.app_name));
        }
        if (topViewModel != null) {
            topViewModel.getPetsList();
        }
    }

    @Override
    public void getPetsList(List<PetsList> petsLists) {
        Activity activity = getActivity();
        if (activity == null) return;
        activity.runOnUiThread(() -> adapter.updatePetsList(petsLists));
    }

    @Override
    public void onClickItem(PetsList petsList) {
        if (callbackListener != null) {
            callbackListener.moveToDiariesFragment(petsList);
        }
    }
}
