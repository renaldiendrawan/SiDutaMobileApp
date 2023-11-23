package com.example.aplikasieduta;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.aplikasieduta.MyViewPagerAdapter1;
import com.example.aplikasieduta.MyViewPagerAdapter2;
import com.example.aplikasieduta.R;
import com.google.android.material.tabs.TabLayout;

public class JadwalFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager2 viewPager2;
    MyViewPagerAdapter1 myViewPagerAdapter;
    String[] items = {"Anak 1", "Anak 2"};
    AutoCompleteTextView jadwal_autocompletetxt;
    ArrayAdapter<String> adapteritems;

    public JadwalFragment(){
        // require an empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jadwal, container, false);

//        jadwal_autocompletetxt = view.findViewById(R.id.jadwal_autocompletetxt);
//        adapteritems = new ArrayAdapter<String>(this, R.layout.list_item, items);
//
//        jadwal_autocompletetxt.setAdapter(adapteritems);
//
//        jadwal_autocompletetxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String item = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getContext(), "Item: " + item, Toast.LENGTH_SHORT).show();
//            }
//        });

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
                    // Lakukan sesuatu  dengan TabLayout, seperti memilih tab
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
}