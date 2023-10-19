package com.example.aplikasieduta;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.aplikasieduta.jadwalfragments.JadwalImunisasiFragment;
import com.example.aplikasieduta.jadwalfragments.JadwalPenimbanganFragment;
import com.example.aplikasieduta.laporanfragments.LaporanImunisasiFragment;
import com.example.aplikasieduta.laporanfragments.LaporanPenimbanganFragment;

public class MyViewPagerAdapter2 extends FragmentStateAdapter {
    public MyViewPagerAdapter2(@NonNull LaporanFragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new LaporanImunisasiFragment();
            case 1:
                return new LaporanPenimbanganFragment();
            default:
                return new LaporanImunisasiFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
