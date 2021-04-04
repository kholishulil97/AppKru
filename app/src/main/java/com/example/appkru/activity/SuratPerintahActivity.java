package com.example.appkru.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appkru.R;
import com.example.appkru.api.ApiClient;
import com.example.appkru.api.ApiInterface;
import com.example.appkru.manger.Constants;
import com.example.appkru.manger.SharedPrefManager;
import com.example.appkru.model.SP.SP;
import com.example.appkru.model.User.User;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuratPerintahActivity extends AppCompatActivity {
    private TextView tvNipNama, tvPosisi, tvNopol, tvTrayek, tvKelas, tvNopolKosong, tvTrayekKosong, tvKelasKosong, tvNipNamaPengganti, tvTanggalMulai, tvNamaKruTtd, tvPosisiKruTtd, tvTanggalSurat, tvNamaPengatur, tvPosisiPengatur, tvMenggantikan, tvMulai;
    private ImageView ivBus;
    private ApiInterface apiInterface;
    private ProgressDialog progressDialog;
    private CardView cvSP, cvSPKosong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surat_perintah);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        initView();
        fetchData();
    }

    public void initView(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sedang menyiapkan...");

        tvNipNama = findViewById(R.id.tv_sp_nim_nama);
        tvPosisi = findViewById(R.id.tv_sp_posisi);
        tvNopol = findViewById(R.id.tv_sp_nopol);
        tvTrayek = findViewById(R.id.tv_sp_trayek);
        tvKelas = findViewById(R.id.tv_sp_kelas);
        tvNopolKosong = findViewById(R.id.tv_sp_nopol_kosong);
        tvTrayekKosong = findViewById(R.id.tv_sp_trayek_kosong);
        tvNipNamaPengganti = findViewById(R.id.tv_sp_nip_nama_pengganti);
        tvTanggalMulai = findViewById(R.id.tv_sp_tanggal_mulai);
        tvNamaKruTtd = findViewById(R.id.tv_sp_nama_kru_ttd);
        tvPosisiKruTtd = findViewById(R.id.tv_sp_posisi_kru_ttd);
        tvTanggalSurat = findViewById(R.id.tv_sp_tanggal_surat);
        tvNamaPengatur = findViewById(R.id.tv_sp_nama_pengatur);
        tvPosisiPengatur = findViewById(R.id.tv_sp_posisi_pengatur);
        tvMenggantikan = findViewById(R.id.tv_sp_menggantikan);
        tvMulai = findViewById(R.id.tv_sp_mulai);
        cvSP = findViewById(R.id.menu_sp);
        cvSPKosong = findViewById(R.id.menu_sp_kosong);
        ivBus = findViewById(R.id.iv_sp_bus);

        cvSP.setVisibility(View.GONE);
        cvSPKosong.setVisibility(View.GONE);
    }

    public void fetchData(){
        Call<SP> call1=apiInterface.getSP(SharedPrefManager.getInstance(this).getIDUser());
        call1.enqueue(new Callback<SP>() {
            @Override
            public void onResponse(Call<SP> call, Response<SP> response) {
                progressDialog.dismiss();
                Boolean status = response.body().getStatus();
                if (status) {
                    tvNipNama.setText( response.body().getData().get(0).getNipNama());
                    tvPosisi.setText(response.body().getData().get(0).getPosisi());
                    tvNopol.setText(response.body().getData().get(0).getNopol());
                    tvTrayek.setText(response.body().getData().get(0).getTrayek());
                    tvKelas.setText(response.body().getData().get(0).getKelas());
                    tvNipNamaPengganti.setText( response.body().getData().get(0).getNipNamaMengganti());
                    tvTanggalMulai.setText(response.body().getData().get(0).getTanggalPanjang());
                    tvNamaKruTtd.setText(response.body().getData().get(0).getNama());
                    tvPosisiKruTtd.setText(response.body().getData().get(0).getPosisi());
                    tvTanggalSurat.setText(response.body().getData().get(0).getTanggalPendek());
                    tvNamaPengatur.setText(response.body().getData().get(0).getNamaPengatur());
                    tvPosisiPengatur.setText(response.body().getData().get(0).getPosisiPengatur());
                    tvMenggantikan.setText("Menggantikan");
                    tvMulai.setText("Mulai");
                    cvSP.setVisibility(View.VISIBLE);
                    cvSPKosong.setVisibility(View.GONE);
                } else {
                    tvNopolKosong.setText("Dinas Belum Tersedia");
                    tvTrayekKosong.setText("Silahkan menghubungi Pengatur Dinas untuk keterangan lebih lanjut");
                    ivBus.setImageResource(R.drawable.ic_warning);
                    cvSPKosong.setVisibility(View.VISIBLE);
                    cvSP.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<SP> call, Throwable t) {
                cvSPKosong.setVisibility(View.INVISIBLE);
                cvSP.setVisibility(View.GONE);
                Toast.makeText(SuratPerintahActivity.this,"Terjadi kesalahan jaringan!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}