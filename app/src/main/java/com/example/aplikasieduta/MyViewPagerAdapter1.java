package com.example.aplikasieduta;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.aplikasieduta.jadwalfragments.JadwalImunisasiFragment;
import com.example.aplikasieduta.jadwalfragments.JadwalPenimbanganFragment;

public class MyViewPagerAdapter1 extends FragmentStateAdapter {
    public MyViewPagerAdapter1(@NonNull JadwalFragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new JadwalImunisasiFragment();
            case 1:
                return new JadwalPenimbanganFragment();
            default:
                return new JadwalImunisasiFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
