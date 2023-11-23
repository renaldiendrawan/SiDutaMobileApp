package com.example.aplikasieduta;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.tabs.TabLayout;

public class LaporanFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    MyViewPagerAdapter2 myViewPagerAdapter;
    String[] items = {"Anak 1", "Anak 2"};
    AutoCompleteTextView laporan_autocompletetxt;
    ArrayAdapter<String> adapteritems;

    public LaporanFragment() {
        // require an empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_laporan, container, false);

//        laporan_autocompletetxt = view.findViewById(R.id.laporan_autocompletetxt);
//        adapteritems = new ArrayAdapter<String>(this, R.layout.list_item, items);
//
//        laporan_autocompletetxt.setAdapter(adapteritems);
//
//        laporan_autocompletetxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String item = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getContext(), "Item: " + item, Toast.LENGTH_SHORT).show();
//            }
//        });

        tabLayout = view.findViewById(R.id.LP_tablayout);
        viewPager2 = view.findViewById(R.id.LP_viewpager);
        myViewPagerAdapter = new MyViewPagerAdapter2(this);
        viewPager2.setAdapter(myViewPagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
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
}
