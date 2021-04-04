package com.example.appkru.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.example.appkru.R;
import com.example.appkru.adapter.KontrolBSAdapter;
import com.example.appkru.adapter.PengeluaranAdapter;
import com.example.appkru.adapter.PengeluaranBSAdapter;
import com.example.appkru.api.ApiClient;
import com.example.appkru.api.ApiInterface;
import com.example.appkru.manger.SharedPrefManager;
import com.example.appkru.model.BuktiSetoran.BuktiSetoran;
import com.example.appkru.model.BuktiSetoran.DaftarPengeluaran;
import com.example.appkru.model.LaporanHarian.LaporanHarian;
import com.example.appkru.model.LaporanKontrol.DatumLaporanKontrol;
import com.example.appkru.model.LaporanKontrol.LaporanKontrol;
import com.example.appkru.model.Pengeluaran.DatumPengeluaran;
import com.example.appkru.model.Pengeluaran.Pengeluaran;
import com.example.appkru.model.PosKarcis.PosKarcis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuktiSetoranActivity extends AppCompatActivity {
    private TextView tvNopol, tvTrayek, tvKelas, tvWaktuBerangkat, tvNipNamaSopir, tvNipNamaKondektur, tvNipNamaKernet, tvPendapatanRit, tvPengeluaran, tvPendapatanRit2, tvPengeluaran2, tvSisaPendapatan, tvNamaSopir, tvNamaKondektur, tvNamaKernet, tvPremiSopir, tvPremiKondektur, tvPremiKernet, tvJumlahPremi, tvSisaPendapatan2, tvJumlahPremi2, tvSetoranKasAngka, tvSetoranKasTerbilang;
    private SwipeButton swipeButton;
    private ApiInterface apiInterface;
    private RecyclerView recyclerView, recyclerView2;
    private List<DaftarPengeluaran> listPengeluaran;
    private PengeluaranBSAdapter pengeluaranAdapter;
    private List<DatumLaporanKontrol> listLaporanKontrol;
    private KontrolBSAdapter kontrolBSAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bukti_setoran);

        initView();
        setData();
        fetchData();
        fetchDataKontrol();
    }

    private void initView() {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        recyclerView = findViewById(R.id.recyclerview_bs_pengeluaran);
        recyclerView2 = findViewById(R.id.recyclerview_bs_kontrol);
        swipeButton = findViewById(R.id.swipe_button);
        tvNopol = findViewById(R.id.tv_bs_nopol);
        tvTrayek = findViewById(R.id.tv_bs_trayek);
        tvKelas = findViewById(R.id.tv_bs_kelas);
        tvWaktuBerangkat = findViewById(R.id.tv_bs_waktu_berangkat);
        tvNipNamaSopir = findViewById(R.id.tv_bs_nip_nama_sopir);
        tvNipNamaKondektur = findViewById(R.id.tv_bs_nip_nama_kondektur);
        tvNipNamaKernet = findViewById(R.id.tv_bs_nip_nama_kernet);
        tvPendapatanRit=findViewById(R.id.tv_bs_pendapatan_rit);
        tvPengeluaran=findViewById(R.id.tv_bs_pengeluaran);
        tvPendapatanRit2=findViewById(R.id.tv_bs_pendapatan_rit2);
        tvPengeluaran2=findViewById(R.id.tv_bs_pengeluaran2);
        tvSisaPendapatan=findViewById(R.id.tv_bs_sisa_pendapatan);
        tvNamaSopir=findViewById(R.id.tv_bs_nama_sopir);
        tvNamaKondektur=findViewById(R.id.tv_bs_nama_kondektur);
        tvNamaKernet=findViewById(R.id.tv_bs_nama_kernet);
        tvPremiSopir=findViewById(R.id.tv_bs_premi_sopir);
        tvPremiKondektur=findViewById(R.id.tv_bs_premi_kondektur);
        tvPremiKernet=findViewById(R.id.tv_bs_premi_kernet);
        tvJumlahPremi=findViewById(R.id.tv_bs_jumlah_premi_atas);
        tvSisaPendapatan2=findViewById(R.id.tv_bs_sisa_pendapatan2);
        tvJumlahPremi2=findViewById(R.id.tv_bs_jumlah_premi_atas2);
        tvSetoranKasAngka=findViewById(R.id.tv_bs_setoran_kas_angka);
        tvSetoranKasTerbilang=findViewById(R.id.tv_bs_setoran_kas_terbilang);

        pengeluaranAdapter = new PengeluaranBSAdapter(BuktiSetoranActivity.this,listPengeluaran);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BuktiSetoranActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(pengeluaranAdapter);

        kontrolBSAdapter = new KontrolBSAdapter(BuktiSetoranActivity.this,listLaporanKontrol);

        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(BuktiSetoranActivity.this);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setAdapter(kontrolBSAdapter);
    }

    private void setData() {
        swipeButton.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                updateData();
            }
        });
    }

    public void updateData(){
        Bundle extras = getIntent().getExtras();
        Call<BuktiSetoran> call1=apiInterface.updateBuktiSetoran(extras.getString("laporan_harian_id"));
        call1.enqueue(new Callback<BuktiSetoran>() {
            @Override
            public void onResponse(Call<BuktiSetoran> call, Response<BuktiSetoran> response) {
                if(response.isSuccessful()){
                    Toast.makeText(BuktiSetoranActivity.this, "Dinas selesai", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuktiSetoranActivity.this, LaporanHarianSetorActivity.class));
                }else{
                    Toast.makeText(BuktiSetoranActivity.this, "Terjadi kesalahan!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BuktiSetoran> call, Throwable t) {
                Toast.makeText(BuktiSetoranActivity.this,"Terjadi kesalahan jaringan!",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void fetchData(){
        Bundle extras = getIntent().getExtras();
        Call<BuktiSetoran> call1=apiInterface.getBuktiSetoran(extras.getString("laporan_harian_id"));
        call1.enqueue(new Callback<BuktiSetoran>() {
            @Override
            public void onResponse(Call<BuktiSetoran> call, Response<BuktiSetoran> response) {
                if(response.isSuccessful()){
                    tvNopol.setText(response.body().getData().get(0).getNopol());
                    tvTrayek.setText(response.body().getData().get(0).getTrayek());
                    tvKelas.setText(response.body().getData().get(0).getKelas());
                    tvWaktuBerangkat.setText(response.body().getData().get(0).getWaktuBerangkat());
                    tvNipNamaSopir.setText(response.body().getData().get(0).getNipNamaSopir());
                    tvNipNamaKondektur.setText(response.body().getData().get(0).getNipNamaKondektur());
                    tvNipNamaKernet.setText(response.body().getData().get(0).getNipNamaKernet());
                    tvPendapatanRit.setText(response.body().getData().get(0).getPendapatanRit());
                    tvPengeluaran.setText(response.body().getData().get(0).getPengeluaran());
                    tvPendapatanRit2.setText(response.body().getData().get(0).getPendapatanRit());
                    tvPengeluaran2.setText(response.body().getData().get(0).getPengeluaran());
                    tvSisaPendapatan.setText(response.body().getData().get(0).getSisaPendapatan());
                    tvNamaSopir.setText(response.body().getData().get(0).getNamaSopir());
                    tvNamaKondektur.setText(response.body().getData().get(0).getNamaKondektur());
                    tvNamaKernet.setText(response.body().getData().get(0).getNamaKernet());
                    tvPremiSopir.setText(response.body().getData().get(0).getPremiSopir());
                    tvPremiKondektur.setText(response.body().getData().get(0).getPremiKondektur());
                    tvPremiKernet.setText(response.body().getData().get(0).getPremiKernet());
                    tvJumlahPremi.setText(response.body().getData().get(0).getJumlahPremi());
                    tvSisaPendapatan2.setText(response.body().getData().get(0).getSisaPendapatan());
                    tvJumlahPremi2.setText(response.body().getData().get(0).getJumlahPremi());
                    tvSetoranKasAngka.setText(response.body().getData().get(0).getSetoranKasAngka());
                    tvSetoranKasTerbilang.setText(response.body().getData().get(0).getSetoranKasTerbilang());

                    String statusSelesai = response.body().getData().get(0).getStatusSelesai();
                    if (statusSelesai.equals("1"))
                        swipeButton.setVisibility(View.GONE);
                    else
                        swipeButton.setVisibility(View.VISIBLE);

                    List<DaftarPengeluaran> datumPengeluaran = response.body().getData().get(0).getDaftarPengeluaran();
                    pengeluaranAdapter.setData(datumPengeluaran);
                    recyclerView.setAdapter(pengeluaranAdapter);
                }else{
                    Toast.makeText(getApplicationContext(),"Gagal menyusun data\nSilahkan coba lagi",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BuktiSetoran> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Terjadi kesalahan jaringan!",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void fetchDataKontrol(){
        Bundle extras = getIntent().getExtras();
        Call<LaporanKontrol> call1=apiInterface.getLaporanKontrolBS(extras.getString("laporan_harian_id"));
        call1.enqueue(new Callback<LaporanKontrol>() {
            @Override
            public void onResponse(Call<LaporanKontrol> call, Response<LaporanKontrol> response) {
                if(response.isSuccessful()){
                    List<DatumLaporanKontrol> datumKontrol = response.body().getData();
                    kontrolBSAdapter.setData(datumKontrol);
                    recyclerView2.setAdapter(kontrolBSAdapter);
                }else{
                    Toast.makeText(getApplicationContext(),"Data kontrol tidak ditemukan",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LaporanKontrol> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Terjadi kesalahan jaringan!",Toast.LENGTH_LONG).show();
            }
        });
    }
}