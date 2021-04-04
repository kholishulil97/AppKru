package com.example.appkru.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.appkru.R;
import com.example.appkru.api.ApiClient;
import com.example.appkru.api.ApiInterface;
import com.example.appkru.manger.SharedPrefManager;
import com.example.appkru.model.LaporanKontrol.LaporanKontrol;
import com.example.appkru.model.PosKarcis.PosKarcis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KontrolInputActivity extends AppCompatActivity {
    private EditText etKontrolInput;
    private AppCompatButton btnLihat;
    private AwesomeValidation awesomeValidation;
    private ApiInterface apiInterface;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontrol_input_id);

        etKontrolInput = findViewById(R.id.et_kontrol_input);
        btnLihat = findViewById(R.id.btn_kontrol_lihat);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(etKontrolInput, RegexTemplate.NOT_EMPTY, "Masukkan ID Laporan Harian");

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Tunggu sebentar...");

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        progressDialog.show();
        cekStatusKontrol();

        btnLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()) {
                    progressDialog.show();
                    fetchData();
                }
            }
        });

    }

    public void cekStatusKontrol(){
        Call<PosKarcis> call1=apiInterface.getStatusKontrol(SharedPrefManager.getInstance(this).getIDUser(), "1");
        call1.enqueue(new Callback<PosKarcis>() {
            @Override
            public void onResponse(Call<PosKarcis> call, Response<PosKarcis> response) {
                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    String laporan_harian_id = response.body().getData().get(0).getTrayekId();
                    Intent intent = new Intent(KontrolInputActivity.this, PenumpangActivity.class);
                    intent.putExtra("laporan_harian_id", laporan_harian_id);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Meneruskan Laporan Kontrol",Toast.LENGTH_SHORT).show();
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Silahkan memulai proses kontrol",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PosKarcis> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Terjadi kesalahan jaringan!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void fetchData(){
        Call<PosKarcis> call1=apiInterface.getPosKarcis(etKontrolInput.getText().toString());
        call1.enqueue(new Callback<PosKarcis>() {
            @Override
            public void onResponse(Call<PosKarcis> call, Response<PosKarcis> response) {
                if(response.isSuccessful()){
                    insertData();
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Data Laporan Harian tidak ditemukan!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PosKarcis> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Terjadi kesalahan jaringan!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void insertData() {
        Call<LaporanKontrol> call1=apiInterface.insertLaporanKontrol(etKontrolInput.getText().toString(), SharedPrefManager.getInstance(this).getIDUser());
        call1.enqueue(new Callback<LaporanKontrol>() {
            @Override
            public void onResponse(Call<LaporanKontrol> call, Response<LaporanKontrol> response) {
                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    String laporan_harian_id = etKontrolInput.getText().toString();
                    Intent intent = new Intent(KontrolInputActivity.this, PenumpangActivity.class);
                    intent.putExtra("laporan_harian_id", laporan_harian_id);
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
}