package com.example.shelter_app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.shelter_app.R;


public class ScreenSlidePageFragment extends Fragment {

    private TextView name;
    private ImageView imageView;
    private int position;

    public ScreenSlidePageFragment(int position) {
        this.position = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
        name = view.findViewById(R.id.name);
        name.setText("Dog " + position);
        imageView = view.findViewById(R.id.imageView);

        imageView.setImageResource(R.drawable.dog);

        return view;
    }
}