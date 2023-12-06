package com.example.aplikasieduta.artikel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aplikasieduta.R;

import java.util.List;

public class ArtikelAdapter extends BaseAdapter {

    private List<ArtikelModel> items;
    private LayoutInflater inflater;

    public ArtikelAdapter(Context context, List<ArtikelModel> itemList) {
        this.items = itemList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return (items != null) ? items.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return (items != null && position < items.size()) ? items.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_artikel, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ArtikelModel artikel = items.get(position);

        holder.judulartikel.setText(artikel.getJudul_artikel());
        holder.namakader.setText(artikel.getNama_kader());
        holder.tanggalartikel.setText(artikel.getTanggal_artikel());
        holder.isiartikel.setText(artikel.getIsi_artikel());

        // Gunakan Glide untuk menampilkan gambar
        Glide.with(holder.gambarartikel.getContext())
                .load(artikel.getImg_path())
                .into(holder.gambarartikel);

        return convertView;
    }

    private static class ViewHolder {
        TextView judulartikel, namakader, tanggalartikel, isiartikel;
        ImageView gambarartikel;

        ViewHolder(View view) {
            judulartikel = view.findViewById(R.id.AR_txt_1);
            namakader = view.findViewById(R.id.AR_txt_2);
            tanggalartikel = view.findViewById(R.id.AR_txt_3);
            gambarartikel = view.findViewById(R.id.AR_img_1);
            isiartikel = view.findViewById(R.id.AR_txt_4);
        }
    }
}
