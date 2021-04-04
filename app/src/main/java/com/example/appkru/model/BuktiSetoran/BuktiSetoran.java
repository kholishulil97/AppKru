package com.example.appkru.model.BuktiSetoran;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BuktiSetoran {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<DatumBuktiSetoran> data = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<DatumBuktiSetoran> getData() {
        return data;
    }

    public void setData(List<DatumBuktiSetoran> data) {
        this.data = data;
    }

}
