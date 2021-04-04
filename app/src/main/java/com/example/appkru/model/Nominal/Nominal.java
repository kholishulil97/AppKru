package com.example.appkru.model.Nominal;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Nominal {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<DatumNominal> data = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<DatumNominal> getData() {
        return data;
    }

    public void setData(List<DatumNominal> data) {
        this.data = data;
    }

}
