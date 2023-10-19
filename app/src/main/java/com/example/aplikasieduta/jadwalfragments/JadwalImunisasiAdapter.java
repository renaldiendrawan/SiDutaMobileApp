package com.example.aplikasieduta.jadwalfragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasieduta.R;

import java.util.List;

public class JadwalImunisasiAdapter extends RecyclerView.Adapter<JadwalImunisasiAdapter.ViewHolder> {

    private List<JadwalImunisasiModel> imunisasiList;

    public JadwalImunisasiAdapter(List<JadwalImunisasiModel> imunisasiList) {
        this.imunisasiList = imunisasiList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jadwal_imunisasi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JadwalImunisasiModel imunisasi = imunisasiList.get(position);
        holder.textTanggal.setText(imunisasi.getTanggalimunisasi());
        holder.textJam.setText(imunisasi.getJamimunisasi());
        holder.textTempat.setText(imunisasi.getTempatimunisasi());
        holder.textVaksin.setText(imunisasi.getJenisvaksin());
    }

    @Override
    public int getItemCount() {
        return imunisasiList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTanggal, textJam, textTempat, textVaksin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTanggal = itemView.findViewById(R.id.JI_texttanggal);
            textJam = itemView.findViewById(R.id.JI_textjam);
            textTempat = itemView.findViewById(R.id.JI_texttempat);
            textVaksin = itemView.findViewById(R.id.JI_textvaksin);
        }
    }
}
