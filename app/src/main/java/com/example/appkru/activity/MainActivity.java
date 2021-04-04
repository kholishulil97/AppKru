package com.example.appkru.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Layout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appkru.R;
import com.example.appkru.api.ApiClient;
import com.example.appkru.api.ApiInterface;
import com.example.appkru.manger.Constants;
import com.example.appkru.manger.SharedPrefManager;
import com.example.appkru.model.LaporanHarian.DatumLaporanHarian;
import com.example.appkru.model.LaporanHarian.LaporanHarian;
import com.example.appkru.model.Pengeluaran.Pengeluaran;
import com.example.appkru.model.SP.SP;
import com.example.appkru.model.User.DatumUser;
import com.example.appkru.model.User.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.supercharge.shimmerlayout.ShimmerLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private String dinasID;
    private TextView tVUsername, tVPassword, tvNopol, tvTrayek, tvKelas;
    private CardView cvLaporanHarian, cvPremi, cvKarcis, cvSuratPerintah, cvKontrol, cvLapKontrol, cvUtama;
    private CircleImageView ivFotoProfil;
    private ImageView ivFotoBus;
    private Context context;
    private ApiInterface apiInterface;
    private ShimmerLayout shimmerLayout;
    private SwipeRefreshLayout swipeRefreshLayout, swipeRefreshLayoutPH;
    private Layout layout;
    private ProgressDialog progressDialog;
    private Dialog dialog;

    private BottomNavigationView bottomNavigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigationMyProfile:
                    return true;
                case R.id.navigationHome:
                    return true;
                case  R.id.navigationMenu:
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.openDrawer(GravityCompat.START);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        bottomNavigationView.setSelectedItemId(R.id.navigationHome);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        initView();
        setData();
        fetchData();
    }

    public void initView(){
        tVUsername = findViewById(R.id.txt_username);
        tVPassword = findViewById(R.id.txt_password);
        cvLaporanHarian = findViewById(R.id.menu_lp);
        cvPremi = findViewById(R.id.menu_premi);
        cvKarcis = findViewById(R.id.menu_karcis);
        cvSuratPerintah = findViewById(R.id.menu_sp);
        cvKontrol = findViewById(R.id.menu_kontrol);
        cvLapKontrol = findViewById(R.id.menu_laporan_kontrol);
        cvUtama = findViewById(R.id.card_db);
        ivFotoProfil = findViewById(R.id.iv_foto_profil);
        ivFotoBus = findViewById(R.id.iv_main_bus);

        tvNopol = findViewById(R.id.tv_main_nopol);
        tvTrayek = findViewById(R.id.tv_main_trayek);
        tvKelas = findViewById(R.id.tv_main_kelas);
        shimmerLayout = findViewById(R.id.shimmer_main);
        swipeRefreshLayout = findViewById(R.id.swipe_main);
        swipeRefreshLayoutPH = findViewById(R.id.swipe_main_ph);
        shimmerLayout.startShimmerAnimation();

        String role_id = SharedPrefManager.getInstance(this).getRoleID();
        if (role_id.equals("31") || role_id.equals("33")) {
            cvKarcis.setVisibility(View.GONE);
            cvKontrol.setVisibility(View.GONE);
            cvLapKontrol.setVisibility(View.GONE);
        } else if (role_id.equals("32") ) {
            cvKarcis.setVisibility(View.VISIBLE);
            cvKontrol.setVisibility(View.GONE);
            cvLapKontrol.setVisibility(View.GONE);
        } else {
            cvLaporanHarian.setVisibility(View.GONE);
            cvPremi.setVisibility(View.GONE);
            cvKarcis.setVisibility(View.GONE);
            cvSuratPerintah.setVisibility(View.GONE);
            cvUtama.setVisibility(View.GONE);
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Tunggu sebentar...");

        dialog = new Dialog(this);
    }

    public void setData(){
        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        cvLaporanHarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LaporanHarianActivity.class);
                startActivity(intent);
            }
        });

        cvPremi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PremiActivity.class);
                startActivity(intent);
            }
        });

        cvKarcis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                fetchDataSP();
            }
        });

        cvSuratPerintah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SuratPerintahActivity.class);
                startActivity(intent);
            }
        });

        cvLapKontrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LaporanKontrolActivity.class);
                startActivity(intent);
            }
        });

        cvKontrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KontrolInputActivity.class);
                startActivity(intent);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                shimmerLayout.setVisibility(View.VISIBLE);
                shimmerLayout.startShimmerAnimation();
                fetchData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        swipeRefreshLayoutPH.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                shimmerLayout.setVisibility(View.VISIBLE);
                shimmerLayout.startShimmerAnimation();
                fetchData();
                swipeRefreshLayoutPH.setRefreshing(false);
            }
        });
    }

    public void fetchData(){
        shimmerLayout.startShimmerAnimation();
        Call<User> call1=apiInterface.getUser(SharedPrefManager.getInstance(this).getIDUser());
        call1.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                tVUsername.setText(response.body().getData().get(0).getNama());
                tVPassword.setText(response.body().getData().get(0).getNip());

                tvNopol.setText(response.body().getData().get(0).getNopol());
                tvTrayek.setText(response.body().getData().get(0).getPosawal() + " - " + response.body().getData().get(0).getPosakhir());
                tvKelas.setText(response.body().getData().get(0).getKelas());

                String url = Constants.URL_FOTO_PROFIL + response.body().getData().get(0).getFotoprofil();
                Picasso.with(getApplicationContext()).load(url).into(ivFotoProfil);

                String url2 = Constants.URL_FOTO_BUS + response.body().getData().get(0).getUrl();
                Picasso.with(getApplicationContext()).load(url2).into(ivFotoBus);
                shimmerLayout.stopShimmerAnimation();
                shimmerLayout.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                shimmerLayout.setVisibility(View.VISIBLE);
                shimmerLayout.stopShimmerAnimation();
                Toast.makeText(MainActivity.this,"Terjadi kesalahan jaringan!",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void fetchDataSP(){
        Call<SP> call1=apiInterface.getSP(SharedPrefManager.getInstance(this).getIDUser());
        call1.enqueue(new Callback<SP>() {
            @Override
            public void onResponse(Call<SP> call, Response<SP> response) {
                Boolean status = response.body().getStatus();
                if (status) {
                    dinasID = response.body().getData().get(0).getDinasId();
                    fetchDataStatusLH();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this,"Anda belum memiliki\nSurat Perintah Dinas!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SP> call, Throwable t) {
                progressDialog.dismiss();
                t.printStackTrace();
                Log.d("Fail1", t.getMessage());
                Toast.makeText(MainActivity.this,"Terjadi kesalahan jaringan!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void fetchDataStatusLH(){
        Call<LaporanHarian> call1=apiInterface.getStatusLH(SharedPrefManager.getInstance(this).getIDUser());
        call1.enqueue(new Callback<LaporanHarian>() {
            @Override
            public void onResponse(Call<LaporanHarian> call, Response<LaporanHarian> response) {
                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    String laporan_harian_id = response.body().getData().get(0).getLaporanHarianId();
                    Intent intent = new Intent(MainActivity.this, KarcisActivity.class);
                    intent.putExtra("laporan_harian_id", laporan_harian_id);
                    startActivity(intent);
                }else{
                    progressDialog.dismiss();

                    dialog.setContentView(R.layout.modal_dinas_baru);

                    AppCompatButton btnSiap = dialog.findViewById(R.id.btn_dia_dinas_baru_siap);
                    AppCompatButton btnBatal = dialog.findViewById(R.id.btn_dia_dinas_baru_batal);
                    ImageView ivTutup = dialog.findViewById(R.id.iv_dia_dinas_baru_tutup);

                    btnBatal.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    ivTutup.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    btnSiap.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            progressDialog.show();
                            insertLaporanHarian();
                        }
                    });
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }
            }

            @Override
            public void onFailure(Call<LaporanHarian> call, Throwable t) {
                progressDialog.dismiss();
                t.printStackTrace();
                Log.d("Fail2", t.getMessage());
                Toast.makeText(MainActivity.this,"Terjadi kesalahan jaringan!",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void insertLaporanHarian(){
        Call<LaporanHarian> call1=apiInterface.insertLaporanHarian(dinasID);
        call1.enqueue(new Callback<LaporanHarian>() {
            @Override
            public void onResponse(Call<LaporanHarian> call, Response<LaporanHarian> response) {
                if(response.isSuccessful()){
                    dialog.dismiss();
                    fetchDataStatusLH();
                    Toast.makeText(MainActivity.this,"Berhasil menambah dinas baru",Toast.LENGTH_LONG).show();
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Terjadi kesalahan!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LaporanHarian> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this,"Terjadi kesalahan jaringan!",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_user:
                Toast.makeText(this, "Anda menekan menu Pengguna", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_logout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                Toast.makeText(this, "Anda telah Logout dari sistem", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}