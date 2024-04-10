package com.example.uchinokoreco.ui.top;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.uchinokoreco.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TopFragment extends Fragment {

    public static Fragment getInstance(){
        return new TopFragment();
    }

    private TopViewModel topViewModel;

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

    }

    @Override
    public void onResume() {
        super.onResume();
        if (topViewModel != null){
            topViewModel.addPetsList();
        }
    }
}
