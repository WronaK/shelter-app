package com.example.shelter_app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.shelter_app.R;

public class FragmentMain extends Fragment {

    private PagerAdapter pagerAdapter;
    private ViewPager viewPager;
    public FragmentMain() {
        // require a empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setSaveEnabled(false);

        pagerAdapter = new ScreenSliderPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);


        return view;
    }



    private class ScreenSliderPagerAdapter extends FragmentStatePagerAdapter {



        public ScreenSliderPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return new ScreenSlidePageFragment(position);
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}