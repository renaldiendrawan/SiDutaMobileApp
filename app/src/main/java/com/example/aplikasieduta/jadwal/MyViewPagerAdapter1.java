package com.example.aplikasieduta.jadwal;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.aplikasieduta.jadwal.JadwalFragment;
import com.example.aplikasieduta.jadwalfragments.JadwalImunisasiFragment;
import com.example.aplikasieduta.jadwalfragments.JadwalPenimbanganFragment;
import com.example.aplikasieduta.laporan.LaporanFragment;
import com.example.aplikasieduta.laporanfragments.LaporanImunisasiFragment;
import com.example.aplikasieduta.laporanfragments.LaporanPenimbanganFragment;

public class MyViewPagerAdapter1 extends FragmentStateAdapter {
    private final Fragment[] fragments;

    public MyViewPagerAdapter1(@NonNull JadwalFragment fragmentActivity) {
        super(fragmentActivity);
        fragments = new Fragment[]{
                new JadwalImunisasiFragment(),
                new JadwalPenimbanganFragment()
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