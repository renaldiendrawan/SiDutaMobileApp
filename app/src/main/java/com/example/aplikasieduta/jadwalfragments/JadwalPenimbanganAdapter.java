package com.example.aplikasieduta.jadwalfragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasieduta.R;

import java.util.List;

public class JadwalPenimbanganAdapter extends RecyclerView.Adapter<JadwalPenimbanganAdapter.ViewHolder> {
    private List<JadwalPenimbanganModel> penimbanganList;

    public JadwalPenimbanganAdapter(List<JadwalPenimbanganModel> penimbanganList) {
        this.penimbanganList = penimbanganList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jadwal_penimbangan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JadwalPenimbanganModel penimbangan = penimbanganList.get(position);
        holder.textTanggal.setText(penimbangan.getTanggalpenimbangan());
        holder.textJam.setText(penimbangan.getJampenimbangan());
        holder.textTempat.setText(penimbangan.getTempatpenimbangan());
    }

    @Override
    public int getItemCount() {
        return penimbanganList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTanggal, textJam, textTempat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTanggal = itemView.findViewById(R.id.JP_texttanggal);
            textJam = itemView.findViewById(R.id.JP_textjam);
            textTempat = itemView.findViewById(R.id.JP_texttempat);
        }
    }
}
