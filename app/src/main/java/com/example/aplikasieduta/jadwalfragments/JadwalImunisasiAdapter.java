package com.example.aplikasieduta.jadwalfragments;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasieduta.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class JadwalImunisasiAdapter extends RecyclerView.Adapter<JadwalImunisasiAdapter.RecycleViewHolder> {

    List<JadwalImunisasiModel> items;

    public JadwalImunisasiAdapter(List<JadwalImunisasiModel> itemList, JadwalImunisasiFragment jadwalImunisasiFragment) {
        this.items = itemList;
    }

    @NonNull
    @Override
    public JadwalImunisasiAdapter.RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jadwal_imunisasi, parent, false);
        return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JadwalImunisasiAdapter.RecycleViewHolder holder, int position) {
        JadwalImunisasiModel jadwal = items.get(position);
        holder.tanggal.setText(items.get(position).getTgl_imunisasi());
        holder.jam.setText(items.get(position).getJam_imunisasi());
        holder.tempat.setText(items.get(position).getTempat_imunisasi());
        holder.jenis.setText(items.get(position).getJenis_imunisasi());
    }

    @Override
    public int getItemCount() {
        return (items != null) ? items.size() : 0;
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder {
        private TextView tanggal, jam, tempat, jenis;

        public RecycleViewHolder(View view) {
            super(view);
            tanggal = view.findViewById(R.id.JI_texttanggal);
            jam = view.findViewById(R.id.JI_textjam);
            tempat = view.findViewById(R.id.JI_texttempat);
            jenis = view.findViewById(R.id.JI_textvaksin);
        }
    }
}
