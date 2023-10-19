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

public class JadwalPenimbanganFragment extends Fragment {

    private RecyclerView recyclerView;
    private JadwalPenimbanganAdapter adapter;
    private List<JadwalPenimbanganModel> penimbanganList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jadwal_penimbangan, container, false);

        recyclerView = view.findViewById(R.id.recyclerview_jadwalpenimbangan);
        penimbanganList = new ArrayList<>();

        // Tambahkan data jadwal penimbangan ke penimbanganList
        penimbanganList.add(new JadwalPenimbanganModel("  05 Oktober 2023", "  10.00", "  Posyandu Kauman"));
        penimbanganList.add(new JadwalPenimbanganModel("  05 November 2023", "  10.00", "  Posyandu Kauman"));
        penimbanganList.add(new JadwalPenimbanganModel("  05 Desember 2023", "  10.00", "  Posyandu Kauman"));
        penimbanganList.add(new JadwalPenimbanganModel("  05 Januari 2024", "  10.00", "  Posyandu Kauman"));
        penimbanganList.add(new JadwalPenimbanganModel("  05 Februari 2024", "  10.00", "  Posyandu Kauman"));
        penimbanganList.add(new JadwalPenimbanganModel("  05 Maret 2024", "  10.00", "  Posyandu Kauman"));
        // Tambahkan data lainnya sesuai kebutuhan

        adapter = new JadwalPenimbanganAdapter(penimbanganList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return view;
    }
}