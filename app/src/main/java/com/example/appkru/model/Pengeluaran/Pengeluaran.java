package com.example.appkru.model.Pengeluaran;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pengeluaran {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<DatumPengeluaran> data = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<DatumPengeluaran> getData() {
        return data;
    }

    public void setData(List<DatumPengeluaran> data) {
        this.data = data;
    }

}