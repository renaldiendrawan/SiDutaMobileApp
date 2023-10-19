package com.example.aplikasieduta.jadwalfragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aplikasieduta.R;

import java.util.ArrayList;
import java.util.List;

public class JadwalImunisasiFragment extends Fragment {
    private RecyclerView recyclerView;
    private JadwalImunisasiAdapter adapter;
    private List<JadwalImunisasiModel> imunisasiList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jadwal_imunisasi, container, false);

        recyclerView = view.findViewById(R.id.recyclerview_jadwalimunisasi);
        imunisasiList = new ArrayList<>();

        // Tambahkan data jadwal imunisasi ke imunisasiList
        imunisasiList.add(new JadwalImunisasiModel("  05 Oktober 2023", "  09.00", "  Posyandu Kauman", "  Hepatitis B"));
        imunisasiList.add(new JadwalImunisasiModel("  05 November 2023", "  09.00", "  Posyandu Kauman", "  Polio"));
        imunisasiList.add(new JadwalImunisasiModel("  05 Desember 2023", "  09.00", "  Posyandu Kauman", "  Campak"));
        imunisasiList.add(new JadwalImunisasiModel("  05 Januari 2024", "  09.00", "  Posyandu Kauman", "  Difteri"));
        imunisasiList.add(new JadwalImunisasiModel("  05 Februari 2024", "  09.00", "  Posyandu Kauman", "  Tetanus"));
        // Tambahkan data lainnya sesuai kebutuhan

        adapter = new JadwalImunisasiAdapter(imunisasiList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return view;
    }
}
