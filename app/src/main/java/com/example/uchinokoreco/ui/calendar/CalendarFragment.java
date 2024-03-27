package com.example.uchinokoreco.ui.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.uchinokoreco.R;
import com.example.uchinokoreco.ui.top.TopFragment;

public class CalendarFragment extends Fragment {

    public static Fragment getInstance(){
        return new CalendarFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(requireContext()).inflate(R.layout.fragment_calender, container, false);
    }
}
