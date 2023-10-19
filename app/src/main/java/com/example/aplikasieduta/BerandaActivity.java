package com.example.aplikasieduta;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.aplikasieduta.jadwalfragments.JadwalImunisasiFragment;
import com.example.aplikasieduta.jadwalfragments.JadwalPenimbanganFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BerandaActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    public static String FRAGMENT = "fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        String nama;

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment = null;

                if (menuItem.getItemId() == R.id.beranda) {
                    selectedFragment = new BerandaFragment();
                } else if (menuItem.getItemId() == R.id.jadwal) {
                    selectedFragment = new JadwalFragment();
                } else if (menuItem.getItemId() == R.id.laporan) {
                    selectedFragment = new LaporanFragment();
                } else if (menuItem.getItemId() == R.id.profil) {
                    selectedFragment = new ProfilFragment();
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.flFragment, selectedFragment)
                            .commit();
                }

                return true;
            }
        });
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flFragment, new BerandaFragment())
                .commit();

        if (getIntent().getIntExtra(BerandaActivity.FRAGMENT,0) == R.layout.fragment_jadwal){
            bottomNavigationView.setSelectedItemId(R.id.jadwal);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flFragment,new JadwalFragment())
                    .commit();
        }

        if (getIntent().getIntExtra(BerandaActivity.FRAGMENT,0) == R.layout.fragment_jadwal){
            bottomNavigationView.setSelectedItemId(R.id.jadwal);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flFragment,new JadwalFragment())
                    .commit();
        }

        if (getIntent().getIntExtra(BerandaActivity.FRAGMENT,0) == R.layout.fragment_laporan){
            bottomNavigationView.setSelectedItemId(R.id.laporan);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flFragment,new LaporanFragment())
                    .commit();
        }

        if (getIntent().getIntExtra(BerandaActivity.FRAGMENT,0) == R.layout.fragment_laporan){
            bottomNavigationView.setSelectedItemId(R.id.laporan);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flFragment,new LaporanFragment())
                    .commit();
        }
    }
}
