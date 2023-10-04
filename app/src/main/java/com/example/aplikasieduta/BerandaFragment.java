package com.example.aplikasieduta;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.aplikasieduta.AkunActivity;

public class BerandaFragment extends Fragment {

    TextView txtNamaOrangTua;
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

        return rootView; // Mengembalikan rootView, bukan membuat View baru
    }
}




