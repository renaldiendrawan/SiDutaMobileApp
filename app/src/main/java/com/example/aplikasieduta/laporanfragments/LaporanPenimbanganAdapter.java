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

import java.util.List;

public class LaporanPenimbanganAdapter extends RecyclerView.Adapter<com.example.aplikasieduta.laporanfragments.LaporanPenimbanganAdapter.RecycleViewHolder> {


    List<LaporanPenimbanganModel> items;

//    public LaporanPenimbanganAdapter(ArrayList<LaporanPenimbanganModel> items){this.items = items;}

    public LaporanPenimbanganAdapter(List<LaporanPenimbanganModel> itemList, LaporanPenimbanganFragment laporanPenimbanganFragment) {
        this.items = itemList;
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
        holder.umur.setText(items.get(position).getUmur());
        holder.tanggal_penimbangan.setText(items.get(position).getTanggal_penimbangan());
        holder.berat_badan.setText(items.get(position).getBerat_badan());
        holder.tinggi_badan.setText(items.get(position).getTinggi_badan());
    }

    @Override
    public int getItemCount() {
        return (items != null)? items.size():0;
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder {
        private TextView nama_anak, umur, tanggal_penimbangan, berat_badan, tinggi_badan;

        public RecycleViewHolder(View view){
            super(view);
            nama_anak = view.findViewById(R.id.LP_textnama);
            umur = view.findViewById(R.id.LP_textumur);
            tanggal_penimbangan = view.findViewById(R.id.LP_texttanggal);
            berat_badan = view.findViewById(R.id.LP_textberat);
            tinggi_badan = view.findViewById(R.id.LP_texttinggi);

        }
    }
}
