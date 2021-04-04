package com.example.appkru.model.LaporanKontrol;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LaporanKontrol {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<DatumLaporanKontrol> data = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<DatumLaporanKontrol> getData() {
        return data;
    }

    public void setData(List<DatumLaporanKontrol> data) {
        this.data = data;
    }

}
