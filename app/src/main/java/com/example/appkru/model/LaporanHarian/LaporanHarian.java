package com.example.appkru.model.LaporanHarian;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LaporanHarian {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<DatumLaporanHarian> data = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<DatumLaporanHarian> getData() {
        return data;
    }

    public void setData(List<DatumLaporanHarian> data) {
        this.data = data;
    }
}
