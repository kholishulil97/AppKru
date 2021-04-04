package com.example.appkru.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appkru.R;
import com.example.appkru.model.LaporanHarian.DatumLaporanHarian;
import com.example.appkru.model.Penumpang.DatumPenumpang;
import com.example.appkru.model.Penumpang.Penumpang;

import java.util.ArrayList;
import java.util.List;

public class PenumpangAdapter extends RecyclerView.Adapter<PenumpangAdapter.LHViewHolder> {
    private Context mContext;
    private List<DatumPenumpang> listPenumpang;

    public PenumpangAdapter(Context mContext, List<DatumPenumpang> listPenumpang) {
        this.listPenumpang = listPenumpang;
        this.mContext = mContext;
    }

    public PenumpangAdapter(List<DatumPenumpang> listPenumpang) {
        this.listPenumpang = listPenumpang;
    }

    public void setData(List<DatumPenumpang> datumPenumpang) {
        this.listPenumpang = datumPenumpang;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PenumpangAdapter.LHViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_penumpang, parent, false);
        return new LHViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PenumpangAdapter.LHViewHolder holder, int position) {
        holder.tvWktTransaksi.setText(listPenumpang.get(position).getWaktu());
        holder.tvPosNaik.setText(listPenumpang.get(position).getPosnaik());
        holder.tvPosTurun.setText(listPenumpang.get(position).getPosturun());
        holder.tvNominal.setText(listPenumpang.get(position).getTarif());
        holder.tvID.setText(listPenumpang.get(position).getLaporanHarianId() + "-" + listPenumpang.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return (listPenumpang != null) ? listPenumpang.size() : 0;
    }

    public class LHViewHolder extends RecyclerView.ViewHolder{

        private TextView tvWktTransaksi, tvPosNaik, tvPosTurun, tvNominal, tvID;

        public LHViewHolder (View view){
            super(view);

            tvWktTransaksi = view.findViewById(R.id.tv_pnp_wkt_transaksi);
            tvPosNaik = view.findViewById(R.id.tv_pnp_pos_naik);
            tvPosTurun = view.findViewById(R.id.tv_pnp_pos_turun);
            tvNominal = view.findViewById(R.id.tv_pnp_nominal);
            tvID = view.findViewById(R.id.tv_pnp_id);
        }
    }
}
