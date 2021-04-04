package com.example.appkru.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toast;

import com.example.appkru.adapter.LaporanHarianPagerAdapter;
import com.example.appkru.api.ApiClient;
import com.example.appkru.api.ApiInterface;
import com.example.appkru.fragment.LaporanHarianFragment;
import com.example.appkru.fragment.LaporanHarianRiwayatFragment;
import com.example.appkru.R;
import com.example.appkru.manger.Constants;
import com.example.appkru.manger.SharedPrefManager;
import com.example.appkru.model.LaporanHarian.DatumLaporanHarian;
import com.example.appkru.model.LaporanHarian.LaporanHarian;
import com.example.appkru.model.User.User;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaporanHarianActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private LaporanHarianPagerAdapter laporanHarianPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_harian);

        tabLayout = findViewById(R.id.tablayout_lh);
        viewPager = findViewById(R.id.viewpager_lh);
        laporanHarianPagerAdapter = new LaporanHarianPagerAdapter(getSupportFragmentManager());

        laporanHarianPagerAdapter.TambahFragment(new LaporanHarianFragment(), "Belum Disetor");
        laporanHarianPagerAdapter.TambahFragment(new LaporanHarianRiwayatFragment(), "Sudah Disetor");


        viewPager.setAdapter(laporanHarianPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }



}