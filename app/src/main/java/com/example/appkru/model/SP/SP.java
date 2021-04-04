package com.example.appkru.model.SP;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SP {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<DatumSP> data = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<DatumSP> getData() {
        return data;
    }

    public void setData(List<DatumSP> data) {
        this.data = data;
    }

}
