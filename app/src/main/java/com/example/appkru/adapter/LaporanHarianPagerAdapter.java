package com.example.appkru.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.appkru.activity.LaporanHarianActivity;
import com.example.appkru.model.LaporanHarian.DatumLaporanHarian;

import java.util.ArrayList;
import java.util.List;

public class LaporanHarianPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> listFragment = new ArrayList<>();
    private final List<String> listTitle = new ArrayList<>();

    public LaporanHarianPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listTitle.size() ;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position);
    }

    public void TambahFragment (Fragment fragment, String title){
        listFragment.add(fragment);
        listTitle.add(title);
    }
}
