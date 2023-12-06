package com.example.aplikasieduta.laporanfragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasieduta.R;
import com.example.aplikasieduta.jadwalfragments.JadwalPenimbanganFragment;
import com.example.aplikasieduta.jadwalfragments.JadwalPenimbanganModel;

import java.util.ArrayList;
import java.util.List;

public class LaporanPenimbanganAdapter extends RecyclerView.Adapter<com.example.aplikasieduta.laporanfragments.LaporanPenimbanganAdapter.RecycleViewHolder> {

    List<LaporanPenimbanganModel> items;
    private List<LaporanPenimbanganModel> filteredItems;

    public LaporanPenimbanganAdapter(List<LaporanPenimbanganModel> itemList, LaporanPenimbanganFragment laporanPenimbanganFragment) {
        this.items = itemList;
        this.filteredItems = new ArrayList<>(itemList);
    }

    @NonNull
    @Override
    public com.example.aplikasieduta.laporanfragments.LaporanPenimbanganAdapter.RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_laporan_penimbangan_item, parent, false);
        return new com.example.aplikasieduta.laporanfragments.LaporanPenimbanganAdapter.RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.aplikasieduta.laporanfragments.LaporanPenimbanganAdapter.RecycleViewHolder holder, int position) {
        LaporanPenimbanganModel laporan = items.get(position);
        holder.nama_anak.setText(items.get(position).getNama_anak());
        holder.tgl_penimbangan.setText(items.get(position).getTgl_penimbangan());
        holder.berat_badan.setText(items.get(position).getBerat_badan());
        holder.tinggi_badan.setText(items.get(position).getTinggi_badan());
    }

    @Override
    public int getItemCount() {
        return (items != null) ? items.size() : 0;
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder {
        private TextView nama_anak, umur, tgl_penimbangan, berat_badan, tinggi_badan;

        public RecycleViewHolder(View view) {
            super(view);
            nama_anak = view.findViewById(R.id.LP_textnama);
            tgl_penimbangan = view.findViewById(R.id.LP_texttanggal);
            berat_badan = view.findViewById(R.id.LP_textberat);
            tinggi_badan = view.findViewById(R.id.LP_texttinggi);

        }
    }

    public void setFilteredList(List<LaporanPenimbanganModel> filteredList) {
        items.clear();
        items.addAll(filteredList);
        notifyDataSetChanged();
    }
}
