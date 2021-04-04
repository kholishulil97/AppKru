package com.example.appkru.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.appkru.R;

public class KarcisActivity extends AppCompatActivity {
    private TextView tvID;
    private CardView cvKarcis, cvPenumpang, cvLaporanHarian, cvPengeluaran;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karcis);

        initView();
        setData();
    }

    private void initView() {
        cvKarcis = findViewById(R.id.sub_menu_karcis);
        cvPenumpang = findViewById(R.id.sub_menu_penumpang);
        cvLaporanHarian = findViewById(R.id.sub_menu_lp);
        cvPengeluaran = findViewById(R.id.sub_menu_pengeluaran);
        tvID = findViewById(R.id.tv_karcis_id);

    }

    public void setData(){
        Bundle extras = getIntent().getExtras();
        final String laporan_harian_id = extras.getString("laporan_harian_id");
        tvID.setText("ID Laporan Harian : " + laporan_harian_id);

        cvKarcis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KarcisActivity.this, TambahKarcisActivity.class);
                intent.putExtra("laporan_harian_id", laporan_harian_id);
                startActivity(intent);
            }
        });

        cvPenumpang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KarcisActivity.this, PenumpangActivity.class);
                intent.putExtra("laporan_harian_id", laporan_harian_id);
                startActivity(intent);
            }
        });

        cvLaporanHarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KarcisActivity.this, BuktiSetoranActivity.class);
                intent.putExtra("laporan_harian_id", laporan_harian_id);
                startActivity(intent);
            }
        });
        cvPengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KarcisActivity.this, PengeluaranActivity.class);
                intent.putExtra("laporan_harian_id", laporan_harian_id);
                startActivity(intent);
            }
        });
    }

}