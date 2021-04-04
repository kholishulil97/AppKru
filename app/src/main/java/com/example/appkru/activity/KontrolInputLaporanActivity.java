package com.example.appkru.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.example.appkru.R;
import com.example.appkru.api.ApiClient;
import com.example.appkru.api.ApiInterface;
import com.example.appkru.manger.SharedPrefManager;
import com.example.appkru.model.LaporanKontrol.LaporanKontrol;
import com.example.appkru.model.Penumpang.DatumPenumpang;
import com.example.appkru.model.Penumpang.Penumpang;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KontrolInputLaporanActivity extends AppCompatActivity {
    private EditText etKeterangan, etNaik, etTurun, etJmlPnp, etJmlPendapatan;
    private Chip chipTidakAda, chipTarif, chipIndisipliner, chipKebersihan;
    private SwipeButton swipeButton;
    private ApiInterface apiInterface;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontrol_input_laporan);

        etKeterangan = findViewById(R.id.et_lk_keterangan);
        etNaik = findViewById(R.id.et_lk_naik);
        etTurun = findViewById(R.id.et_lk_turun);
        etJmlPnp = findViewById(R.id.et_lk_penumpang);
        etJmlPendapatan =findViewById(R.id.et_lk_pendapatan);

        chipTidakAda = findViewById(R.id.chip_lk_tidak_ada);
        chipTarif = findViewById(R.id.chip_lk_tarif);
        chipIndisipliner =findViewById(R.id.chip_lk_indisipliner);
        chipKebersihan = findViewById(R.id.chip_lk_kebersihan);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Tunggu sebentar...");

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        swipeButton = findViewById(R.id.swipe_button_lk_simpan);
        swipeButton.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                progressDialog.show();
                String keterangan = etKeterangan.getText().toString();
                String naik = etNaik.getText().toString();
                String turun = etTurun.getText().toString();
                String jmlPnp = etJmlPnp.getText().toString();
                String jmlPendapatan = etJmlPendapatan.getText().toString();

                String jenisPelanggaran = "";
                ChipGroup chg = findViewById(R.id.chip_group_lk);
                int chipsCount = chg.getChildCount();
                if (chipsCount == 0) {
                    jenisPelanggaran += "";
                } else {
                    int i = 0;
                    while (i < chipsCount) {
                        Chip chip = (Chip) chg.getChildAt(i);
                        if (chip.isChecked() ) {
                            jenisPelanggaran += chip.getText().toString() + "," ;
                        }
                        i++;
                    };
                }
                insertData(jenisPelanggaran, keterangan, naik, turun, jmlPnp, jmlPendapatan);
            }
        });
    }

    private void insertData(String jenisPelanggaran, String keterangan, String naik, String turun, String jmlPnp, String jmlPendapatan) {
        Bundle extras = getIntent().getExtras();
        Call<LaporanKontrol> call1=apiInterface.updateLaporanKontrol(extras.getString("laporan_harian_id"), SharedPrefManager.getInstance(this).getIDUser(), jenisPelanggaran, keterangan, naik, turun, jmlPnp, jmlPendapatan);
        call1.enqueue(new Callback<LaporanKontrol>() {
            @Override
            public void onResponse(Call<LaporanKontrol> call, Response<LaporanKontrol> response) {
                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Data Laporan Kontrol berhasil disimpan",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(KontrolInputLaporanActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Terjadi kesalahan!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LaporanKontrol> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Terjadi kesalahan jaringan!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onBackPressed() {
        moveTaskToBack(true);
    }
}