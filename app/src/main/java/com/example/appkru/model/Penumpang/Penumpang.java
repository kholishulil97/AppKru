package com.example.appkru.model.Penumpang;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Penumpang {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("jumlah_penumpang")
    @Expose
    private String jumlahPenumpang;
    @SerializedName("id_karcis")
    @Expose
    private String idKarcis;
    @SerializedName("data")
    @Expose
    private List<DatumPenumpang> data = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getJumlahPenumpang() {
        return jumlahPenumpang;
    }

    public void setJumlahPenumpang(String jumlahPenumpang) {
        this.jumlahPenumpang = jumlahPenumpang;
    }

    public String getIdKarcis() {
        return idKarcis;
    }

    public void setIdKarcis(String idKarcis) {
        this.idKarcis = idKarcis;
    }

    public List<DatumPenumpang> getData() {
        return data;
    }

    public void setData(List<DatumPenumpang> data) {
        this.data = data;
    }

}
