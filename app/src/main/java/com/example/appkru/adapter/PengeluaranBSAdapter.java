package com.example.appkru.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appkru.R;
import com.example.appkru.model.BuktiSetoran.DaftarPengeluaran;
import com.example.appkru.model.Pengeluaran.DatumPengeluaran;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PengeluaranBSAdapter extends RecyclerView.Adapter<PengeluaranBSAdapter.LHViewHolder> {
    private Context mContext;
    private List<DaftarPengeluaran> listPengeluaran;

    public PengeluaranBSAdapter(Context mContext, List<DaftarPengeluaran> listPengeluaran) {
        this.listPengeluaran = listPengeluaran;
        this.mContext = mContext;
    }

    public void setData(List<DaftarPengeluaran> listPengeluaran) {
        this.listPengeluaran = listPengeluaran;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PengeluaranBSAdapter.LHViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_bs_pengeluaran, parent, false);
        return new LHViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PengeluaranBSAdapter.LHViewHolder holder, final int position) {
        holder.tvNama.setText(listPengeluaran.get(position).getNamaPengeluaran());
        holder.tvNominal.setText(listPengeluaran.get(position).getNominal());
    }

    @Override
    public int getItemCount() {
        return (listPengeluaran != null) ? listPengeluaran.size() : 0;
    }

    public class LHViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNama, tvNominal;

        public LHViewHolder (View view){
            super(view);

            tvNama = view.findViewById(R.id.tv_bs_pengeluaran_nama);
            tvNominal = view.findViewById(R.id.tv_bs_pengeluaran_nominal);
        }
    }
}
