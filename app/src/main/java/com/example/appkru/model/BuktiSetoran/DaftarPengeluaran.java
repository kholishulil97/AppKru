package com.example.appkru.model.BuktiSetoran;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DaftarPengeluaran {

    @SerializedName("nama_pengeluaran")
    @Expose
    private String namaPengeluaran;
    @SerializedName("nominal")
    @Expose
    private String nominal;

    public String getNamaPengeluaran() {
        return namaPengeluaran;
    }

    public void setNamaPengeluaran(String namaPengeluaran) {
        this.namaPengeluaran = namaPengeluaran;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

}
