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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.appkru.R;
import com.example.appkru.activity.BuktiSetoranActivity;
import com.example.appkru.api.ApiClient;
import com.example.appkru.api.ApiInterface;
import com.example.appkru.model.LaporanKontrol.DatumLaporanKontrol;
import com.example.appkru.model.Pengeluaran.DatumPengeluaran;
import com.example.appkru.model.Pengeluaran.Pengeluaran;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaporanKontrolAdapter extends RecyclerView.Adapter<LaporanKontrolAdapter.LHViewHolder> {
    private Context mContext;
    private List<DatumLaporanKontrol> listLaporanKontrol;
    private Dialog myDialog;

    public LaporanKontrolAdapter(Context mContext, List<DatumLaporanKontrol> listLaporanKontrol) {
        this.listLaporanKontrol = listLaporanKontrol;
        this.mContext = mContext;
    }

    public void setData(List<DatumLaporanKontrol> listLaporanKontrol) {
        this.listLaporanKontrol = listLaporanKontrol;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LaporanKontrolAdapter.LHViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_laporan_harian, parent, false);
        final LaporanKontrolAdapter.LHViewHolder vHolder = new LaporanKontrolAdapter.LHViewHolder(view);

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

                tvJudul.setText("Laporan Kontrol");
                tvTglSelesai.setText(listLaporanKontrol.get(vHolder.getAdapterPosition()).getTanggalNaikKontrol() + "\n" + listLaporanKontrol.get(vHolder.getAdapterPosition()).getNaikKontrol());
                tvNipNamaKasir.setText(listLaporanKontrol.get(vHolder.getAdapterPosition()).getJenisPelanggaran());
                tvTglSetor.setText(listLaporanKontrol.get(vHolder.getAdapterPosition()).getTanggalTurunKontrol()+ "\n" + listLaporanKontrol.get(vHolder.getAdapterPosition()).getTurunKontrol());
                btnNopol.setText(listLaporanKontrol.get(vHolder.getAdapterPosition()).getNopol());
                btnTrayek.setText(listLaporanKontrol.get(vHolder.getAdapterPosition()).getTrayek());
                btnKelas.setText(listLaporanKontrol.get(vHolder.getAdapterPosition()).getKelas());
                btnNominal.setText(listLaporanKontrol.get(vHolder.getAdapterPosition()).getPendapatanKontrol());

                btnDetail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                        mContext.startActivity(new Intent(mContext, BuktiSetoranActivity.class));
                    }
                });
                myDialog.show();
            }
        });
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LaporanKontrolAdapter.LHViewHolder holder, final int position) {
        holder.tvNopol.setText(listLaporanKontrol.get(position).getNopol());
        holder.tvTanggal.setText(listLaporanKontrol.get(position).getTanggalNaikKontrol());
        holder.tvPos.setText(listLaporanKontrol.get(position).getNaikKontrol());
    }

    @Override
    public int getItemCount() {
        return (listLaporanKontrol != null) ? listLaporanKontrol.size() : 0;
    }

    public class LHViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout llLPItem;
        private TextView tvNopol, tvTanggal, tvPos;

        public LHViewHolder (View view){
            super(view);

            llLPItem = view.findViewById(R.id.ll_lp_item);
            tvNopol = view.findViewById(R.id.tv_nopol);
            tvTanggal = view.findViewById(R.id.tv_tanggal);
            tvPos = view.findViewById(R.id.tv_nominal);
        }
    }
}
