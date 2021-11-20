package com.example.shelter_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ScreenSlidePageFragment extends Fragment {

    private TextView textView;
    private int position;

    public ScreenSlidePageFragment(int position) {
        this.position = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
        textView = view.findViewById(R.id.textView);
        textView.setText("Page " + position);
        return view;
    }
}