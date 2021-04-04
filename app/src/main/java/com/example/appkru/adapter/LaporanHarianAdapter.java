package com.example.appkru.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appkru.R;
import com.example.appkru.activity.BuktiSetoranActivity;
import com.example.appkru.activity.KarcisActivity;
import com.example.appkru.activity.MainActivity;
import com.example.appkru.activity.TambahKarcisActivity;
import com.example.appkru.model.LaporanHarian.DatumLaporanHarian;
import com.example.appkru.model.LaporanHarian.LaporanHarian;

import java.util.ArrayList;
import java.util.List;

public class LaporanHarianAdapter extends RecyclerView.Adapter<LaporanHarianAdapter.LHViewHolder> {
    private Context mContext;
    private List<DatumLaporanHarian> listLaporanHarian;
    private Dialog myDialog;

    public LaporanHarianAdapter(Context mContext, List<DatumLaporanHarian> listLaporanHarian) {
        this.listLaporanHarian = listLaporanHarian;
        this.mContext = mContext;
    }

    public void setData(List<DatumLaporanHarian> datumLaporanHarian) {
        this.listLaporanHarian = datumLaporanHarian;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LaporanHarianAdapter.LHViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_laporan_harian, parent, false);
        final LHViewHolder vHolder = new LHViewHolder(view);

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

                tvJudul.setText("Laporan Harian");
                tvTglSelesai.setText(listLaporanHarian.get(vHolder.getAdapterPosition()).getTanggal() + " | " + listLaporanHarian.get(vHolder.getAdapterPosition()).getJamSelesai());
                tvStatusSetor.setText(listLaporanHarian.get(vHolder.getAdapterPosition()).getStatusSetorKata());
                tvNipNamaKasir.setText(listLaporanHarian.get(vHolder.getAdapterPosition()).getNipNamaKasir());
                tvTglSetor.setText(listLaporanHarian.get(vHolder.getAdapterPosition()).getTanggalSetorLong());
                btnNopol.setText(listLaporanHarian.get(vHolder.getAdapterPosition()).getNopol());
                btnTrayek.setText(listLaporanHarian.get(vHolder.getAdapterPosition()).getTrayek());
                btnKelas.setText(listLaporanHarian.get(vHolder.getAdapterPosition()).getKelas());
                btnNominal.setText(listLaporanHarian.get(vHolder.getAdapterPosition()).getNominal());
                String laporan_harian_id = listLaporanHarian.get(vHolder.getAdapterPosition()).getLaporanHarianId();
                btnDetail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();

                        Intent intent = new Intent(mContext, BuktiSetoranActivity.class);
                        intent.putExtra("laporan_harian_id", laporan_harian_id);
                        mContext.startActivity(intent);
                    }
                });
                myDialog.show();
            }
        });
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LaporanHarianAdapter.LHViewHolder holder, int position) {
        holder.tvNopol.setText(listLaporanHarian.get(position).getNopol());
        holder.tvTanggal.setText(listLaporanHarian.get(position).getTanggal());
        holder.tvNominal.setText(listLaporanHarian.get(position).getNominal());
    }

    @Override
    public int getItemCount() {
        return (listLaporanHarian != null) ? listLaporanHarian.size() : 0;
    }

    public class LHViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout llLPItem;
        private TextView tvNopol, tvTanggal, tvNominal;

        public LHViewHolder (View view){
            super(view);

            llLPItem = view.findViewById(R.id.ll_lp_item);
            tvNopol = view.findViewById(R.id.tv_nopol);
            tvTanggal = view.findViewById(R.id.tv_tanggal);
            tvNominal = view.findViewById(R.id.tv_nominal);
        }
    }
}
