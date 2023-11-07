package com.example.aplikasieduta;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.aplikasieduta.beranda.BerandaFragment;
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
            public boolean onNavigationItemSelected( MenuItem menuItem) {
                Fragment selectedFragment = null;

                if (menuItem.getItemId() == R.id.beranda) {
                    selectedFragment = new BerandaFragment();
                } else if (menuItem.getItemId() == R.id.jadwal) {
                    selectedFragment = new JadwalFragment();
                } else if (menuItem.getItemId() == R.id.laporan) {
                    selectedFragment = new LaporanFragment();
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

        if (getIntent().getIntExtra(BerandaActivity.FRAGMENT,0) == R.layout.fragment_laporan){
            bottomNavigationView.setSelectedItemId(R.id.laporan);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flFragment,new LaporanFragment())
                    .commit();
        }
    }
}

