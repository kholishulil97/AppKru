package com.example.appkru.activity;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.appkru.R;
import com.example.appkru.adapter.LaporanKontrolAdapter;
import com.example.appkru.adapter.PengeluaranAdapter;
import com.example.appkru.api.ApiClient;
import com.example.appkru.api.ApiInterface;
import com.example.appkru.manger.SharedPrefManager;
import com.example.appkru.model.LaporanKontrol.DatumLaporanKontrol;
import com.example.appkru.model.LaporanKontrol.LaporanKontrol;
import com.example.appkru.model.Pengeluaran.DatumPengeluaran;
import com.example.appkru.model.Pengeluaran.Pengeluaran;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.supercharge.shimmerlayout.ShimmerLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaporanKontrolActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Dialog dialog;
    private AppCompatButton btnTambah;
    private ApiInterface apiInterface;
    private TextView tvKosong;
    private ShimmerLayout shimmerLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<DatumLaporanKontrol> listLaporanKontrol;
    private LaporanKontrolAdapter laporanKontrolAdapter;
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_kontrol);

        recyclerView = findViewById(R.id.recyclerview_lk);
        tvKosong = findViewById(R.id.tv_item_lk_kosong);
        shimmerLayout = findViewById(R.id.shimmer_item_lk);
        swipeRefreshLayout = findViewById(R.id.swipe_lk);

        laporanKontrolAdapter = new LaporanKontrolAdapter(LaporanKontrolActivity.this, listLaporanKontrol);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(LaporanKontrolActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(laporanKontrolAdapter);

        dialog = new Dialog(this);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerView.setVisibility(View.GONE);
                shimmerLayout.setVisibility(View.VISIBLE);
                shimmerLayout.startShimmerAnimation();
                fetchData();
                laporanKontrolAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
                tvKosong.setVisibility(View.GONE);
            }
        });

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        fetchData();
    }

    public void fetchData(){
        Call<LaporanKontrol> call1=apiInterface.getLaporanKontrol(SharedPrefManager.getInstance(this).getIDUser());
        call1.enqueue(new Callback<LaporanKontrol>() {
            @Override
            public void onResponse(Call<LaporanKontrol> call, Response<LaporanKontrol> response) {
                if(response.isSuccessful()){
                    recyclerView.setVisibility(View.VISIBLE);
                    shimmerLayout.stopShimmerAnimation();
                    shimmerLayout.setVisibility(View.GONE);
                    List<DatumLaporanKontrol> datumLaporanKontrol = response.body().getData();
                    laporanKontrolAdapter.setData(datumLaporanKontrol);
                    recyclerView.setAdapter(laporanKontrolAdapter);
                    tvKosong.setVisibility(View.GONE);
                }else{
                    shimmerLayout.stopShimmerAnimation();
                    shimmerLayout.setVisibility(View.INVISIBLE);
                    tvKosong.setVisibility(View.VISIBLE);
                    tvKosong.setText("Tidak ada data yang ditampilkan");
                }
            }

            @Override
            public void onFailure(Call<LaporanKontrol> call, Throwable t) {
                shimmerLayout.setVisibility(View.VISIBLE);
                shimmerLayout.stopShimmerAnimation();
                tvKosong.setVisibility(View.VISIBLE);
                tvKosong.setText("Tidak ada data yang ditampilkan");
                Toast.makeText(LaporanKontrolActivity.this,"Terjadi kesalahan jaringan!",Toast.LENGTH_LONG).show();
            }
        });
    }

}