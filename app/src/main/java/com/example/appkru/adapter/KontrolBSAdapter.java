package com.example.appkru.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appkru.R;
import com.example.appkru.model.BuktiSetoran.DaftarPengeluaran;
import com.example.appkru.model.LaporanKontrol.DatumLaporanKontrol;

import java.util.List;

public class KontrolBSAdapter extends RecyclerView.Adapter<KontrolBSAdapter.LHViewHolder> {
    private Context mContext;
    private List<DatumLaporanKontrol> listLaporanKontrol;

    public KontrolBSAdapter(Context mContext, List<DatumLaporanKontrol> listLaporanKontrol) {
        this.listLaporanKontrol = listLaporanKontrol;
        this.mContext = mContext;
    }

    public void setData(List<DatumLaporanKontrol> listLaporanKontrol) {
        this.listLaporanKontrol = listLaporanKontrol;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KontrolBSAdapter.LHViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_bs_kontrol, parent, false);
        return new LHViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KontrolBSAdapter.LHViewHolder holder, final int position) {
        holder.tvNama.setText(listLaporanKontrol.get(position).getNama());
        holder.tvNominal.setText(listLaporanKontrol.get(position).getNaikKontrol());
    }

    @Override
    public int getItemCount() {
        return (listLaporanKontrol != null) ? listLaporanKontrol.size() : 0;
    }

    public class LHViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNama, tvNominal;

        public LHViewHolder (View view){
            super(view);

            tvNama = view.findViewById(R.id.tv_bs_kontrol_nama);
            tvNominal = view.findViewById(R.id.tv_bs_kontrol_nominal);
        }
    }
}
