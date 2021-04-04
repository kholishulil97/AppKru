package com.example.appkru.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.appkru.R;
import com.example.appkru.adapter.PremiPagerAdapter;
import com.example.appkru.fragment.PremiFragment;
import com.example.appkru.fragment.PremiRiwayatFragment;
import com.google.android.material.tabs.TabLayout;

public class PremiActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PremiPagerAdapter premiPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premi);

        tabLayout = findViewById(R.id.tablayout_pr);
        viewPager = findViewById(R.id.viewpager_pr);
        premiPagerAdapter = new PremiPagerAdapter(getSupportFragmentManager());

        premiPagerAdapter.TambahFragment(new PremiFragment(), "Belum Diambil");
        premiPagerAdapter.TambahFragment(new PremiRiwayatFragment(), "Sudah Diambil");

        viewPager.setAdapter(premiPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }


}