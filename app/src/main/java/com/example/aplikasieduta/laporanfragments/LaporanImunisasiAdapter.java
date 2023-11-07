package com.example.aplikasieduta.laporanfragments;

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

public class LaporanImunisasiAdapter extends RecyclerView.Adapter<LaporanImunisasiAdapter.RecycleViewHolder> {

    List<LaporanImunisasiModel> items;

//    public LaporanImunisasiAdapter(ArrayList<LaporanImunisasiModel> items){this.items = items;}

    public LaporanImunisasiAdapter(List<LaporanImunisasiModel> itemList, LaporanImunisasiFragment laporanImunisasiFragment) {
        this.items = itemList;
    }

    @NonNull
    @Override
    public LaporanImunisasiAdapter.RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_laporan_imunisasi_item, parent, false);
        return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LaporanImunisasiAdapter.RecycleViewHolder holder, int position) {
        LaporanImunisasiModel laporan = items.get(position);
        holder.nama_anak.setText(items.get(position).getNama_anak());
        holder.umur.setText(items.get(position).getUmur());
        holder.tanggal_imunisasi.setText(items.get(position).getTanggal_imunisasi());
        holder.jenis_imunisasi.setText(items.get(position).getJenis_imunisasi());
    }

    @Override
    public int getItemCount() {
        return (items != null)? items.size():0;
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder {
        private TextView nama_anak, umur, tanggal_imunisasi, jenis_imunisasi;

        public RecycleViewHolder(View view){
            super(view);
            nama_anak = view.findViewById(R.id.LI_textnama);
            umur = view.findViewById(R.id.LI_textumur);
            tanggal_imunisasi = view.findViewById(R.id.LI_texttanggal);
            jenis_imunisasi = view.findViewById(R.id.LI_textjenis);
        }
    }
}
