package com.example.uchinokoreco.ui.createPets;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.uchinokoreco.R;

import javax.annotation.Nullable;

public class CreatePetsFragment extends Fragment {
    public static Fragment getInstance(){ return new CreatePetsFragment();}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return  LayoutInflater.from(requireContext()).inflate(R.layout.fragment_create_pets, container, false);
    }
}
