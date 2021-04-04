package com.example.appkru.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appkru.R;
import com.example.appkru.activity.BuktiSetoranActivity;
import com.example.appkru.model.LaporanHarian.DatumLaporanHarian;
import com.example.appkru.model.Premi.DatumPremi;
import com.example.appkru.model.Premi.Premi;

import java.util.ArrayList;
import java.util.List;

public class PremiAdapter extends RecyclerView.Adapter<PremiAdapter.PremiViewHolder> {
    private Context mContext;
    private List<DatumPremi> listPremi;
    private Dialog myDialog;

    public PremiAdapter(Context mContext, List<DatumPremi> listPremi) {
        this.listPremi = listPremi;
        this.mContext = mContext;
    }

    public void setData(List<DatumPremi> datumPremi) {
        this.listPremi = datumPremi;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PremiAdapter.PremiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_premi, parent, false);
        final PremiAdapter.PremiViewHolder vHolder = new PremiAdapter.PremiViewHolder(view);

        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.dialog_laporan);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        vHolder.llLPItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tvJudul = myDialog.findViewById(R.id.tv_dia_judul);
                TextView tvTglSelesai = myDialog.findViewById(R.id.tv_dia_tanggal_selesai);
                TextView tvStatusSetor = myDialog.findViewById(R.id.tv_dia_status_setor);
                AppCompatButton btnNopol = myDialog.findViewById(R.id.btn_dia_nopol);
                AppCompatButton btnTrayek = myDialog.findViewById(R.id.btn_dia_trayek);
                AppCompatButton btnKelas = myDialog.findViewById(R.id.btn_dia_kelas);
                AppCompatButton btnNominal = myDialog.findViewById(R.id.btn_dia_nominal);
                AppCompatButton btnDetail = myDialog.findViewById(R.id.btn_dia_detail);
                TextView tvNipNamaKasir = myDialog.findViewById(R.id.tv_dia_nama_nip_kasir);
                TextView tvTglSetor = myDialog.findViewById(R.id.tv_dia_tanggal_setor);
                ImageView ivLogo = myDialog.findViewById(R.id.iv_dia_logo);

                ivLogo.setImageResource(R.drawable.ic_cash_pr);
                tvJudul.setText("Premi");
                tvTglSelesai.setText(listPremi.get(vHolder.getAdapterPosition()).getBulanSetor());
                tvStatusSetor.setText(listPremi.get(vHolder.getAdapterPosition()).getStatusAmbil());
                tvNipNamaKasir.setText(listPremi.get(vHolder.getAdapterPosition()).getKasir());
                tvTglSetor.setText(listPremi.get(vHolder.getAdapterPosition()).getTanggalAmbil());
                btnNopol.setText(listPremi.get(vHolder.getAdapterPosition()).getNopol());
                btnTrayek.setText(listPremi.get(vHolder.getAdapterPosition()).getTrayek());
                btnKelas.setText(listPremi.get(vHolder.getAdapterPosition()).getKelas());
                btnNominal.setText(listPremi.get(vHolder.getAdapterPosition()).getNominal());

                btnDetail.setVisibility(View.INVISIBLE);
                myDialog.show();
            }
        });
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PremiAdapter.PremiViewHolder holder, int position) {
        holder.tvNopol.setText(listPremi.get(position).getNopol());
        holder.tvTanggal.setText(listPremi.get(position).getBulanSetor());
        holder.tvNominal.setText(listPremi.get(position).getNominal());
    }

    @Override
    public int getItemCount() {
        return (listPremi != null) ? listPremi.size() : 0;
    }

    public class PremiViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNopol, tvTanggal, tvNominal;
        private ImageView ivItem;
        private LinearLayout llLPItem;

        public PremiViewHolder (View view){
            super(view);
            llLPItem = view.findViewById(R.id.ll_premi_item);
            tvNopol = view.findViewById(R.id.tv_pr_nopol);
            tvTanggal = view.findViewById(R.id.tv_pr_tanggal);
            tvNominal = view.findViewById(R.id.tv_pr_nominal);
            ivItem = view.findViewById(R.id.iv_item_premi);
        }
    }

}
