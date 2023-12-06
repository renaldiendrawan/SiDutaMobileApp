package com.example.aplikasieduta.laporan;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.aplikasieduta.laporan.LaporanFragment;
import com.example.aplikasieduta.laporanfragments.LaporanImunisasiFragment;
import com.example.aplikasieduta.laporanfragments.LaporanPenimbanganFragment;

public class MyViewPagerAdapter2 extends FragmentStateAdapter {
    private final Fragment[] fragments;

    public MyViewPagerAdapter2(@NonNull LaporanFragment fragmentActivity) {
        super(fragmentActivity);
        fragments = new Fragment[]{
                new LaporanImunisasiFragment(),
                new LaporanPenimbanganFragment()
        };
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments[position];
    }

    @Override
    public int getItemCount() {
        return fragments.length;
    }

    public Fragment getFragment(int position) {
        return fragments[position];
    }

}
