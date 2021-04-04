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
import com.example.appkru.adapter.PremiAdapter;
import com.example.appkru.api.ApiClient;
import com.example.appkru.api.ApiInterface;
import com.example.appkru.manger.SharedPrefManager;
import com.example.appkru.model.Premi.DatumPremi;
import com.example.appkru.model.Premi.Premi;

import java.util.ArrayList;
import java.util.List;

import io.supercharge.shimmerlayout.ShimmerLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PremiRiwayatFragment extends Fragment {

    private RecyclerView recyclerView;
    private PremiAdapter premiAdapter;
    private List<DatumPremi> datumPremiList;

    private Context context;
    private ApiInterface apiInterface;
    private TextView tvKosong;
    private ShimmerLayout shimmerLayout;
    private SwipeRefreshLayout swipeRefreshLayout;

    public PremiRiwayatFragment() {
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
        View view = inflater.inflate(R.layout.fragment_premi_riwayat, container, false);

        recyclerView = view.findViewById(R.id.recycleview_premi2);
        tvKosong = view.findViewById(R.id.tv_item_pr_r_kosong);
        shimmerLayout = view.findViewById(R.id.shimmer_item_premi);
        swipeRefreshLayout = view.findViewById(R.id.swipe_premi_r);

        shimmerLayout.startShimmerAnimation();

        premiAdapter = new PremiAdapter(getContext(), datumPremiList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(premiAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                shimmerLayout.setVisibility(View.VISIBLE);
                shimmerLayout.startShimmerAnimation();
                fetchData();
                premiAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        return view;
    }

    public void fetchData(){
        Call<Premi> call1=apiInterface.getPremi(SharedPrefManager.getInstance(getContext()).getIDUser(), "1");
        call1.enqueue(new Callback<Premi>() {
            @Override
            public void onResponse(Call<Premi> call, Response<Premi> response) {
                if(response.isSuccessful()){
                    shimmerLayout.stopShimmerAnimation();
                    shimmerLayout.setVisibility(View.GONE);
                    List<DatumPremi> datumPremi = response.body().getData();
                    premiAdapter.setData(datumPremi);
                    recyclerView.setAdapter(premiAdapter);
                    tvKosong.setVisibility(View.GONE);
                }else{
                    shimmerLayout.stopShimmerAnimation();
                    shimmerLayout.setVisibility(View.GONE);
                    tvKosong.setText("Tidak ada data yang ditampilkan");
                }
            }

            @Override
            public void onFailure(Call<Premi> call, Throwable t) {
                shimmerLayout.setVisibility(View.VISIBLE);
                shimmerLayout.stopShimmerAnimation();
                Toast.makeText(getContext(),"Terjadi kesalahan jaringan!",Toast.LENGTH_LONG).show();
            }
        });
    }
}