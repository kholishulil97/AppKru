package com.example.appkru.model.Penumpang;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatumPenumpang {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("laporan_harian_id")
    @Expose
    private String laporanHarianId;
    @SerializedName("waktu")
    @Expose
    private String waktu;
    @SerializedName("posnaik")
    @Expose
    private String posnaik;
    @SerializedName("posturun")
    @Expose
    private String posturun;
    @SerializedName("tarif")
    @Expose
    private String tarif;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLaporanHarianId() {
        return laporanHarianId;
    }

    public void setLaporanHarianId(String laporanHarianId) {
        this.laporanHarianId = laporanHarianId;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getPosnaik() {
        return posnaik;
    }

    public void setPosnaik(String posnaik) {
        this.posnaik = posnaik;
    }

    public String getPosturun() {
        return posturun;
    }

    public void setPosturun(String posturun) {
        this.posturun = posturun;
    }

    public String getTarif() {
        return tarif;
    }

    public void setTarif(String tarif) {
        this.tarif = tarif;
    }

}
