package com.example.appkru.model.PosKarcis;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PosKarcis {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<DatumPosKarcis> data = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<DatumPosKarcis> getData() {
        return data;
    }

    public void setData(List<DatumPosKarcis> data) {
        this.data = data;
    }

}