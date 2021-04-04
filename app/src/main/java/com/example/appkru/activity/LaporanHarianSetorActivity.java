package com.example.appkru.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.appkru.R;
import com.example.appkru.adapter.LaporanHarianPagerAdapter;
import com.example.appkru.fragment.LaporanHarianFragment;
import com.example.appkru.fragment.LaporanHarianRiwayatFragment;
import com.google.android.material.tabs.TabLayout;

public class LaporanHarianSetorActivity extends AppCompatActivity {
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}