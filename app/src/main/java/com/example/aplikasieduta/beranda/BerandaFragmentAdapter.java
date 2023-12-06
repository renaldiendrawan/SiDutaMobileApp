package com.example.aplikasieduta.beranda;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasieduta.R;
import com.example.aplikasieduta.databalita.DataBalitaModel;
import com.example.aplikasieduta.databalita.DataBalitaResponse;
import com.example.aplikasieduta.profilakun.DataShared;

import java.util.ArrayList;
import java.util.List;

public class BerandaFragmentAdapter extends RecyclerView.Adapter<BerandaFragmentAdapter.ViewHolder> {

    private Context context;
    private OnClickListener clickListener;
    private ArrayList<DataBalitaModel> databalitaList;
    SharedPreferences sharedPreferences;

    public BerandaFragmentAdapter(ArrayList<DataBalitaModel> databalitaList, Context context, OnClickListener listener) {
        this.context = context;
        this.databalitaList = databalitaList;
        clickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_beranda_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataBalitaModel databalita = databalitaList.get(position);
        holder.textNama.setText(databalita.getNama_anak());
        holder.textTanggallahiranak.setText(databalita.getTanggal_lahir_anak());
        holder.textJenisKelamin.setText(databalita.getJenis_kelamin());

        if (holder.getAdapterPosition() != RecyclerView.NO_POSITION) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        int position = holder.getPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            DataShared dataShared = new DataShared(context);
                            dataShared.setData(DataShared.KEY.BERANDA_ID, databalitaList.get(position).getNama_anak());
                            clickListener.onClick(position);
                        }
                    }
                }
            });
        }
    }

    public interface OnClickListener {

        void onClick(int position);

    }

    @Override
    public int getItemCount() {
        return (databalitaList != null) ? databalitaList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textNama, textTanggallahiranak, textJenisKelamin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textNama = itemView.findViewById(R.id.DB_namabalita);
            textTanggallahiranak = itemView.findViewById(R.id.DB_tanggallahir);
            textJenisKelamin = itemView.findViewById(R.id.DB_jeniskelamin);
        }
    }
}
