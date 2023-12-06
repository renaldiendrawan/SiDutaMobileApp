package com.example.aplikasieduta.jadwal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aplikasieduta.R;
import com.google.android.material.tabs.TabLayout;

public class JadwalFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    MyViewPagerAdapter1 myViewPagerAdapter;

    public JadwalFragment() {
        // require an empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jadwal, container, false);

        tabLayout = view.findViewById(R.id.J_tablayout);
        viewPager2 = view.findViewById(R.id.J_viewpager);
        myViewPagerAdapter = new MyViewPagerAdapter1(this);
        viewPager2.setAdapter(myViewPagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());

                TabLayout tabLayout;
                if (getActivity() != null) {
                    tabLayout = getActivity().findViewById(R.id.LP_tablayout);
                    // Lakukan sesuatu dengan TabLayout, seperti memilih tab
                    if (tabLayout != null) {
                        TabLayout.Tab tabToSelect = tabLayout.getTabAt(1); // Misalnya, memilih tab pertama
                        if (tabToSelect != null) {
                            tabToSelect.select();
                        }
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
