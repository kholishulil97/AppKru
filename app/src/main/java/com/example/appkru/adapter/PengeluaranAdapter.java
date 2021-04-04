package com.example.appkru.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.appkru.R;
import com.example.appkru.activity.PengeluaranActivity;
import com.example.appkru.api.ApiClient;
import com.example.appkru.api.ApiInterface;
import com.example.appkru.model.LaporanHarian.DatumLaporanHarian;
import com.example.appkru.model.Pengeluaran.DatumPengeluaran;
import com.example.appkru.model.Pengeluaran.Pengeluaran;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PengeluaranAdapter extends RecyclerView.Adapter<PengeluaranAdapter.LHViewHolder> {
    private Context mContext;
    private List<DatumPengeluaran> listPengeluaran;
    private ApiInterface apiInterface;
    private AwesomeValidation awesomeValidation;

    public PengeluaranAdapter(Context mContext, List<DatumPengeluaran> listPengeluaran) {
        this.listPengeluaran = listPengeluaran;
        this.mContext = mContext;
    }

    public void setData(List<DatumPengeluaran> listPengeluaran) {
        this.listPengeluaran = listPengeluaran;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PengeluaranAdapter.LHViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_pengeluaran, parent, false);
        return new LHViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PengeluaranAdapter.LHViewHolder holder, final int position) {
        holder.tvTanggal.setText(listPengeluaran.get(position).getTanggal());
        holder.tvWaktu.setText(listPengeluaran.get(position).getWaktu());
        holder.tvNama.setText(listPengeluaran.get(position).getNamaPengeluaran());
        holder.tvLokasi.setText(listPengeluaran.get(position).getLokasi());
        holder.tvNominal.setText(listPengeluaran.get(position).getNominal());
        holder.ivEditPengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = listPengeluaran.get(position).getNamaPengeluaran();
                String waktu = listPengeluaran.get(position).getTanggal() + " " + listPengeluaran.get(position).getWaktu();
                String keterangan = listPengeluaran.get(position).getKeterangan();
                String lokasi = listPengeluaran.get(position).getLokasi();
                String nominal = listPengeluaran.get(position).getAngka();
                String ID = listPengeluaran.get(position).getId();
                editPengeluaran(waktu, nama, keterangan, lokasi, nominal, ID);
            }
        });
        holder.ivHapusPengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = listPengeluaran.get(position).getNamaPengeluaran();
                String lokasi = listPengeluaran.get(position).getLokasi();
                String ID = listPengeluaran.get(position).getId();
                hapusPengeluaran(nama, lokasi, ID);
            }
        });
    }

    private void editPengeluaran(String waktu,String nama, String keterangan, String lokasi, String nominal, String ID) {
        ImageView ivClose;
        TextView tvHeaderPengeluaran, tvTanggalPengeluaran;
        final EditText etNamaPengeluaran, etLokasiPengeluaran, etNominalPengeluaran, etKeteranganPengeluaran, etID;
        final AppCompatButton btnBatal, btnSimpan;
        final Dialog dialog;

        dialog = new Dialog(mContext);

        dialog.setContentView(R.layout.modal_pengeluaran);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy H.mm");
        String format = simpleDateFormat.format(new Date());

        ivClose = (ImageView) dialog.findViewById(R.id.img_pengeluaran_batal);
        tvHeaderPengeluaran = (TextView) dialog.findViewById(R.id.txt_pengeluaran_header);
        tvTanggalPengeluaran = (TextView) dialog.findViewById(R.id.txt_pengeluaran_tanggal);
        etNamaPengeluaran = (EditText) dialog.findViewById(R.id.et_pengeluaran_nama);
        etKeteranganPengeluaran = (EditText) dialog.findViewById(R.id.et_pengeluaran_keterangan);
        etLokasiPengeluaran = (EditText) dialog.findViewById(R.id.et_pengeluaran_lokasi);
        etNominalPengeluaran = (EditText) dialog.findViewById(R.id.et_pengeluaran_nominal);
        etID = (EditText) dialog.findViewById(R.id.et_pengeluaran_id);
        btnBatal = (AppCompatButton) dialog.findViewById(R.id.btn_pengeluaran_batal);
        btnSimpan = (AppCompatButton) dialog.findViewById(R.id.btn_pengeluaran_simpan);

        tvHeaderPengeluaran.setText("Edit Pengeluaran");
        tvTanggalPengeluaran.setText(waktu);
        etNamaPengeluaran.setText(nama);
        etKeteranganPengeluaran.setText(keterangan);
        etLokasiPengeluaran.setText(lokasi);
        etNominalPengeluaran.setText(nominal);
        etID.setText(ID);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(etNamaPengeluaran, RegexTemplate.NOT_EMPTY, "Masukkan nama pengeluaran");
        awesomeValidation.addValidation(etNamaPengeluaran, "[a-zA-Z\\s]+", "Tidak boleh terdapat tanda baca");
        awesomeValidation.addValidation(etLokasiPengeluaran, RegexTemplate.NOT_EMPTY, "Masukkan lokasi pengeluaran");
        awesomeValidation.addValidation(etNominalPengeluaran, RegexTemplate.NOT_EMPTY, "Masukkan nominal pengeluaran");
        awesomeValidation.addValidation(etNominalPengeluaran, "^[1-9][0-9]{2,6}", "Masukkan nominal yang valid");

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()){
                    String nama = etNamaPengeluaran.getText().toString();
                    String lokasi = etLokasiPengeluaran.getText().toString();
                    String nominal = etNominalPengeluaran.getText().toString();
                    String keterangan = etKeteranganPengeluaran.getText().toString();
                    String ID = etID.getText().toString();
                    updateData(nama, lokasi, nominal, keterangan, ID);
                    dialog.dismiss();
                } else {
                    Toast.makeText(mContext, "Validasi gagal!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void updateData(String nama, String lokasi, String nominal, String keterangan, String ID){
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Pengeluaran> call1=apiInterface.updatePengeluaran(ID, nama, lokasi, nominal, keterangan);
        call1.enqueue(new Callback<Pengeluaran>() {
            @Override
            public void onResponse(Call<Pengeluaran> call, Response<Pengeluaran> response) {
                if(response.isSuccessful()){
                    Toast.makeText(mContext, "Data pengeluaran berhasil diubah", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext, "Terjadi kesalahan!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pengeluaran> call, Throwable t) {
                Toast.makeText(mContext,"Terjadi kesalahan jaringan!",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void deleteData(String ID){
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Pengeluaran> call1=apiInterface.deletePengeluaran(ID);
        call1.enqueue(new Callback<Pengeluaran>() {
            @Override
            public void onResponse(Call<Pengeluaran> call, Response<Pengeluaran> response) {
                if(response.isSuccessful()){
                    Toast.makeText(mContext, "Data pengeluaran berhasil dihapus", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext, "Terjadi kesalahan!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pengeluaran> call, Throwable t) {
                Toast.makeText(mContext,"Terjadi kesalahan jaringan!",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void hapusPengeluaran(String nama, String lokasi, String ID) {
        ImageView ivClose;
        TextView tvHeaderPengeluaran, tvNamaPengeluaran, tvLokasiPengeluaran, tvID;
        Button btnBatal, btnHapus;
        final Dialog dialog;

        dialog = new Dialog(mContext);

        dialog.setContentView(R.layout.modal_hapus_pengeluaran);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy H.mm");
        String format = simpleDateFormat.format(new Date());

        ivClose = (ImageView) dialog.findViewById(R.id.img_pengeluaran_batal);
        tvHeaderPengeluaran = (TextView) dialog.findViewById(R.id.txt_pengeluaran_header);
        tvNamaPengeluaran = (TextView) dialog.findViewById(R.id.txt_hapus_nama_pengeluaran);
        tvLokasiPengeluaran = (TextView) dialog.findViewById(R.id.txt_hapus_lokasi_pengeluaran);
        tvID = (TextView) dialog.findViewById(R.id.txt_hapus_id_pengeluaran);
        btnBatal = (Button) dialog.findViewById(R.id.btn_pengeluaran_batal);
        btnHapus = (Button) dialog.findViewById(R.id.btn_pengeluaran_hapus);

        tvHeaderPengeluaran.setText("Hapus Pengeluaran");
        tvNamaPengeluaran.setText(nama);
        tvLokasiPengeluaran.setText(lokasi);
        tvID.setText(ID);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID = tvID.getText().toString();
                deleteData(ID);
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return (listPengeluaran != null) ? listPengeluaran.size() : 0;
    }

    public class LHViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTanggal, tvWaktu, tvKeterangan, tvLokasi, tvNominal, tvNama;
        private ImageView ivEditPengeluaran, ivHapusPengeluaran;

        public LHViewHolder (View view){
            super(view);
            tvTanggal = view.findViewById(R.id.tv_peng_tanggal);
            tvWaktu = view.findViewById(R.id.tv_peng_waktu);
            tvNama = view.findViewById(R.id.tv_peng_nama);
            tvLokasi = view.findViewById(R.id.tv_peng_lokasi);
            tvNominal = view.findViewById(R.id.tv_peng_nominal);
            ivEditPengeluaran = view.findViewById(R.id.edit_pengeluaran);
            ivHapusPengeluaran = view.findViewById(R.id.hapus_pengeluaran);
        }
    }
}
