package com.example.appkru.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.appkru.R;
import com.example.appkru.adapter.LaporanHarianAdapter;
import com.example.appkru.adapter.PengeluaranAdapter;
import com.example.appkru.api.ApiClient;
import com.example.appkru.api.ApiInterface;
import com.example.appkru.manger.SharedPrefManager;
import com.example.appkru.model.LaporanHarian.DatumLaporanHarian;
import com.example.appkru.model.LaporanHarian.LaporanHarian;
import com.example.appkru.model.Pengeluaran.DatumPengeluaran;
import com.example.appkru.model.Pengeluaran.Pengeluaran;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.supercharge.shimmerlayout.ShimmerLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PengeluaranActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Dialog dialog;
    private AppCompatButton btnTambah;
    private ApiInterface apiInterface;
    private TextView tvKosong;
    private ShimmerLayout shimmerLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<DatumPengeluaran> listPengeluaran;
    private PengeluaranAdapter pengeluaranAdapter;
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengeluaran);

        recyclerView = findViewById(R.id.recyclerview_pengeluaran);
        tvKosong = findViewById(R.id.tv_item_pengeluaran_kosong);
        shimmerLayout = findViewById(R.id.shimmer_item_pengeluaran);
        swipeRefreshLayout = findViewById(R.id.swipe_pengeluaran);

        pengeluaranAdapter = new PengeluaranAdapter(PengeluaranActivity.this,listPengeluaran);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PengeluaranActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(pengeluaranAdapter);

        dialog = new Dialog(this);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerView.setVisibility(View.GONE);
                shimmerLayout.setVisibility(View.VISIBLE);
                shimmerLayout.startShimmerAnimation();
                fetchData();
                pengeluaranAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
                tvKosong.setVisibility(View.GONE);
            }
        });

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        fetchData();
    }

    public void fetchData(){
        Bundle extras = getIntent().getExtras();
        Call<Pengeluaran> call1=apiInterface.getPengeluaran(extras.getString("laporan_harian_id"));
        call1.enqueue(new Callback<Pengeluaran>() {
            @Override
            public void onResponse(Call<Pengeluaran> call, Response<Pengeluaran> response) {
                if(response.isSuccessful()){
                    recyclerView.setVisibility(View.VISIBLE);
                    shimmerLayout.stopShimmerAnimation();
                    shimmerLayout.setVisibility(View.GONE);
                    List<DatumPengeluaran> datumPengeluaran = response.body().getData();
                    pengeluaranAdapter.setData(datumPengeluaran);
                    recyclerView.setAdapter(pengeluaranAdapter);
                    tvKosong.setVisibility(View.GONE);
                }else{
                    shimmerLayout.stopShimmerAnimation();
                    shimmerLayout.setVisibility(View.INVISIBLE);
                    tvKosong.setVisibility(View.VISIBLE);
                    tvKosong.setText("Tidak ada data yang ditampilkan");
                }
            }

            @Override
            public void onFailure(Call<Pengeluaran> call, Throwable t) {
                shimmerLayout.setVisibility(View.VISIBLE);
                shimmerLayout.stopShimmerAnimation();
                tvKosong.setVisibility(View.VISIBLE);
                tvKosong.setText("Tidak ada data yang ditampilkan");
                Toast.makeText(PengeluaranActivity.this,"Terjadi kesalahan jaringan!",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void tambahPengeluaran (View v){
        ImageView ivClose;
        TextView tvHeaderPengeluaran, tvTanggalPengeluaran;
        final EditText etNamaPengeluaran, etLokasiPengeluaran, etNominalPengeluaran, etKeteranganPengeluaran;
        Button btnBatal, btnSimpan;

        dialog.setContentView(R.layout.modal_pengeluaran);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
        String format = simpleDateFormat.format(new Date());

        ivClose = (ImageView) dialog.findViewById(R.id.img_pengeluaran_batal);
        tvHeaderPengeluaran = (TextView) dialog.findViewById(R.id.txt_pengeluaran_header);
        tvTanggalPengeluaran = (TextView) dialog.findViewById(R.id.txt_pengeluaran_tanggal);
        etNamaPengeluaran = (EditText) dialog.findViewById(R.id.et_pengeluaran_nama);
        etLokasiPengeluaran = (EditText) dialog.findViewById(R.id.et_pengeluaran_lokasi);
        etNominalPengeluaran = (EditText) dialog.findViewById(R.id.et_pengeluaran_nominal);
        etKeteranganPengeluaran = (EditText) dialog.findViewById(R.id.et_pengeluaran_keterangan);
        btnBatal = (Button) dialog.findViewById(R.id.btn_pengeluaran_batal);
        btnSimpan = (Button) dialog.findViewById(R.id.btn_pengeluaran_simpan);

        tvHeaderPengeluaran.setText("Tambah Pengeluaran");
        tvTanggalPengeluaran.setText(format);

        awesomeValidation.addValidation(etNamaPengeluaran, RegexTemplate.NOT_EMPTY, "Masukkan nama pengeluaran");
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
                    insertData(nama, lokasi, nominal, keterangan);
                } else {
                    Toast.makeText(PengeluaranActivity.this, "Validasi gagal!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void insertData(String nama, String lokasi, String nominal, String keterangan){
        Bundle extras = getIntent().getExtras();
        Call<Pengeluaran> call1=apiInterface.insertPengeluaran(extras.getString("laporan_harian_id"), nama, lokasi, nominal, keterangan);
        call1.enqueue(new Callback<Pengeluaran>() {
            @Override
            public void onResponse(Call<Pengeluaran> call, Response<Pengeluaran> response) {
                if(response.isSuccessful()){
                    fetchData();
                    dialog.dismiss();
                    Toast.makeText(PengeluaranActivity.this, "Data pengeluaran berhasil disimpan", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(PengeluaranActivity.this, "Terjadi kesalahan!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pengeluaran> call, Throwable t) {
                Toast.makeText(PengeluaranActivity.this,"Terjadi kesalahan jaringan!",Toast.LENGTH_LONG).show();
            }
        });
    }

}