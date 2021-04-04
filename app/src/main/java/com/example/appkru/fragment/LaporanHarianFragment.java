package com.example.appkru.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appkru.R;
import com.example.appkru.adapter.LaporanHarianAdapter;
import com.example.appkru.api.ApiClient;
import com.example.appkru.api.ApiInterface;
import com.example.appkru.manger.SharedPrefManager;
import com.example.appkru.model.LaporanHarian.DatumLaporanHarian;
import com.example.appkru.model.LaporanHarian.LaporanHarian;

import java.util.List;

import io.supercharge.shimmerlayout.ShimmerLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LaporanHarianFragment extends Fragment {

    private RecyclerView recyclerView;
    private LaporanHarianAdapter laporanHarianAdapter;
    private List<DatumLaporanHarian> laporanHarianArrayList;

    private Context context;
    private ApiInterface apiInterface;
    private TextView tvKosong;
    private ShimmerLayout shimmerLayout;
    private SwipeRefreshLayout swipeRefreshLayout;

    public LaporanHarianFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        fetchData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_laporan_harian, container, false);

        recyclerView = view.findViewById(R.id.recycleview_lh1);
        tvKosong = view.findViewById(R.id.tv_item_lh_kosong);
        shimmerLayout = view.findViewById(R.id.shimmer_item_laporan);
        swipeRefreshLayout = view.findViewById(R.id.swipe_lh);

        shimmerLayout.startShimmerAnimation();

        laporanHarianAdapter = new LaporanHarianAdapter(getContext(),laporanHarianArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(laporanHarianAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                shimmerLayout.setVisibility(View.VISIBLE);
                shimmerLayout.startShimmerAnimation();
                fetchData();
                laporanHarianAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        return view;
    }

    public void fetchData(){
        Call<LaporanHarian> call1=apiInterface.getLaporanHarian(SharedPrefManager.getInstance(getContext()).getIDUser(), "0");
        call1.enqueue(new Callback<LaporanHarian>() {
            @Override
            public void onResponse(Call<LaporanHarian> call, Response<LaporanHarian> response) {
                if(response.isSuccessful()){
                    shimmerLayout.stopShimmerAnimation();
                    shimmerLayout.setVisibility(View.GONE);
                    List<DatumLaporanHarian> datumLaporanHarian = response.body().getData();
                    laporanHarianAdapter.setData(datumLaporanHarian);
                    recyclerView.setAdapter(laporanHarianAdapter);
                    tvKosong.setVisibility(View.GONE);
                }else{
                    shimmerLayout.stopShimmerAnimation();
                    shimmerLayout.setVisibility(View.GONE);
                    tvKosong.setText("Tidak ada data yang ditampilkan");
                }
            }

            @Override
            public void onFailure(Call<LaporanHarian> call, Throwable t) {
                shimmerLayout.setVisibility(View.VISIBLE);
                shimmerLayout.stopShimmerAnimation();
                Toast.makeText(getContext(),"Terjadi kesalahan jaringan!",Toast.LENGTH_LONG).show();
            }
        });
    }
}