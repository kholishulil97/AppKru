package com.example.appkru.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appkru.R;
import com.example.appkru.adapter.PenumpangAdapter;
import com.example.appkru.api.ApiClient;
import com.example.appkru.api.ApiInterface;
import com.example.appkru.manger.SharedPrefManager;
import com.example.appkru.model.Pengeluaran.DatumPengeluaran;
import com.example.appkru.model.Penumpang.DatumPenumpang;
import com.example.appkru.model.Penumpang.Penumpang;
import com.example.appkru.model.PosKarcis.PosKarcis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PenumpangActivity extends AppCompatActivity {
    private String posTurun, role_id;
    private TextView tvPos, tvJumlah;
    private AppCompatButton btnLihat, btnLaporan;
    private Spinner spPenumpang;
    private RecyclerView recyclerView;
    private PenumpangAdapter penumpangAdapter;
    private List<DatumPenumpang> penumpangArrayList;
    private RecyclerView.LayoutManager layoutManager;
    private ApiInterface apiInterface;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penumpang);

        tvJumlah = findViewById(R.id.jumlah_penumpang);
        tvPos = findViewById(R.id.pos_penumpang);
        spPenumpang = findViewById(R.id.spinner_penumpang);

        recyclerView = findViewById(R.id.recycleview_pnp);
        btnLihat = findViewById(R.id.btn_pnp_lihat);
        btnLaporan = findViewById(R.id.btn_pnp_laporan);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Tunggu sebentar...");

        btnLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);

                penumpangAdapter = new PenumpangAdapter(penumpangArrayList);
                recyclerView.setAdapter(penumpangAdapter);

                progressDialog.show();
                fetchDataPenumpang();
            }
        });

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        role_id = SharedPrefManager.getInstance(this).getRoleID();

        fetchData();
    }

    public void fetchData(){
        Bundle extras = getIntent().getExtras();
        Call<PosKarcis> call1=apiInterface.getPosKarcis(extras.getString("laporan_harian_id"));
        call1.enqueue(new Callback<PosKarcis>() {
            @Override
            public void onResponse(Call<PosKarcis> call, Response<PosKarcis> response) {
                if(response.isSuccessful()){
                    List<String> datumPosKarcis = response.body().getData().get(0).getPosnaik();

                    ArrayList<String> list = new ArrayList<String>();
                    for(int i=0; i<datumPosKarcis.size(); i++) {
                        list.add(datumPosKarcis.get(i));
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.style_spinner,list);
                    spPenumpang.setAdapter(arrayAdapter);
                    spPenumpang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            posTurun = parent.getItemAtPosition(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }else{
                    String[] value = {"-"};
                    ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(value));
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.style_spinner,arrayList);
                    spPenumpang.setAdapter(arrayAdapter);

                    Toast.makeText(getApplicationContext(),"Data Laporan Harian tidak ditemukan!",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PosKarcis> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Terjadi kesalahan jaringan!",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void fetchDataPenumpang(){
        Bundle extras = getIntent().getExtras();
        Call<Penumpang> call1=apiInterface.getPenumpang(extras.getString("laporan_harian_id"), posTurun);
        call1.enqueue(new Callback<Penumpang>() {
            @Override
            public void onResponse(Call<Penumpang> call, Response<Penumpang> response) {
                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    List<DatumPenumpang> datumPenumpang = response.body().getData();
                    penumpangAdapter.setData(datumPenumpang);
                    recyclerView.setAdapter(penumpangAdapter);
                    tvPos.setText(posTurun);
                    tvJumlah.setText(response.body().getJumlahPenumpang());
                    if (role_id.equals("4")){
                        btnLaporan.setVisibility(View.VISIBLE);
                        btnLaporan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(PenumpangActivity.this, KontrolInputLaporanActivity.class);
                                intent.putExtra("laporan_harian_id", extras.getString("laporan_harian_id"));
                                startActivity(intent);
                            }
                        });
                    }
                }else{
                    tvPos.setText("");
                    tvJumlah.setText("");
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Data tidak ditemukan",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Penumpang> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Terjadi kesalahan jaringan!",Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (role_id.equals("4")) {
            moveTaskToBack(true);
        } else {
            finish();
        }
    }

}