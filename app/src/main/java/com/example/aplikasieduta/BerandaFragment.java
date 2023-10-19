package com.example.aplikasieduta;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bdtopcoder.smart_slider.SliderAdapter;
import com.bdtopcoder.smart_slider.SliderItem;
import com.example.aplikasieduta.AkunActivity;
import com.example.aplikasieduta.jadwalfragments.JadwalImunisasiFragment;

import java.util.ArrayList;
import java.util.List;

public class BerandaFragment extends Fragment {

    TextView txtNamaOrangTua;
    ImageButton imunisasi;
    private String NamaOrangTua;
    private String KEY_NAME = "NAMA";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_beranda, container, false);

            txtNamaOrangTua = rootView.findViewById(R.id.B_txt_5);
            Bundle extras = getActivity().getIntent().getExtras();
            if(extras != null) {
                NamaOrangTua = extras.getString("KEY_NAME");
                txtNamaOrangTua.setText("Halo, Bu " + NamaOrangTua + "!");
            }else {
                txtNamaOrangTua.setText("Halo, Bu ... !" );
            }

        ImageButton akun = rootView.findViewById(R.id.btn_akun);
        akun.setOnClickListener(v -> {
            Intent buka = new Intent(getContext(), AkunActivity.class);
            startActivity(buka);
        });

            ViewPager2 viewPager2 = rootView.findViewById(R.id.B_smartSlider);

            List<SliderItem> sliderItems = new ArrayList<>();
            sliderItems.add(new SliderItem(R.drawable.gambarartikel1,"artikel 1"));
            sliderItems.add(new SliderItem(R.drawable.gambarartikel2,"artikel 2"));
            sliderItems.add(new SliderItem(R.drawable.gambarartikel3,"artikel 3"));

            viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2, 3000));

            viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    SliderItem item = sliderItems.get(position);
                }

        });

        imunisasi = rootView.findViewById(R.id.B_img_1);
        imunisasi.setOnClickListener(v->{
        Intent pindah = new Intent(getActivity(),BerandaActivity.class)
                    .putExtra(BerandaActivity.FRAGMENT,R.layout.fragment_jadwal);
        startActivity(pindah);
        });

        imunisasi = rootView.findViewById(R.id.B_img_2);
        imunisasi.setOnClickListener(v->{
            Intent pindah = new Intent(getActivity(),BerandaActivity.class)
                    .putExtra(BerandaActivity.FRAGMENT,R.layout.fragment_jadwal);
            startActivity(pindah);
        });

        imunisasi = rootView.findViewById(R.id.B_img_3);
        imunisasi.setOnClickListener(v->{
            Intent pindah = new Intent(getActivity(),BerandaActivity.class)
                    .putExtra(BerandaActivity.FRAGMENT,R.layout.fragment_laporan);
            startActivity(pindah);
        });

        imunisasi = rootView.findViewById(R.id.B_img_4);
        imunisasi.setOnClickListener(v->{
            Intent pindah = new Intent(getActivity(),BerandaActivity.class)
                    .putExtra(BerandaActivity.FRAGMENT,R.layout.fragment_laporan);
            startActivity(pindah);
        });

        return rootView; // Mengembalikan rootView, bukan membuat View baru
    }
}
