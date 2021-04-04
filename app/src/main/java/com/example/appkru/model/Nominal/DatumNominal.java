package com.example.appkru.model.Nominal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatumNominal {

    @SerializedName("harga_bayar")
    @Expose
    private Integer hargaBayar;
    @SerializedName("lima")
    @Expose
    private Integer lima;
    @SerializedName("duapuluh")
    @Expose
    private Integer duapuluh;
    @SerializedName("limapuluh")
    @Expose
    private Integer limapuluh;
    @SerializedName("seratus")
    @Expose
    private Integer seratus;
    @SerializedName("harga_bayar_format")
    @Expose
    private String hargaBayarFormat;
    @SerializedName("lima_format")
    @Expose
    private String limaFormat;
    @SerializedName("duapuluh_format")
    @Expose
    private String duapuluhFormat;
    @SerializedName("limapuluh_format")
    @Expose
    private String limapuluhFormat;
    @SerializedName("seratus_format")
    @Expose
    private String seratusFormat;

    public Integer getHargaBayar() {
        return hargaBayar;
    }

    public void setHargaBayar(Integer hargaBayar) {
        this.hargaBayar = hargaBayar;
    }

    public Integer getLima() {
        return lima;
    }

    public void setLima(Integer lima) {
        this.lima = lima;
    }

    public Integer getDuapuluh() {
        return duapuluh;
    }

    public void setDuapuluh(Integer duapuluh) {
        this.duapuluh = duapuluh;
    }

    public Integer getLimapuluh() {
        return limapuluh;
    }

    public void setLimapuluh(Integer limapuluh) {
        this.limapuluh = limapuluh;
    }

    public Integer getSeratus() {
        return seratus;
    }

    public void setSeratus(Integer seratus) {
        this.seratus = seratus;
    }

    public String getHargaBayarFormat() {
        return hargaBayarFormat;
    }

    public void setHargaBayarFormat(String hargaBayarFormat) {
        this.hargaBayarFormat = hargaBayarFormat;
    }

    public String getLimaFormat() {
        return limaFormat;
    }

    public void setLimaFormat(String limaFormat) {
        this.limaFormat = limaFormat;
    }

    public String getDuapuluhFormat() {
        return duapuluhFormat;
    }

    public void setDuapuluhFormat(String duapuluhFormat) {
        this.duapuluhFormat = duapuluhFormat;
    }

    public String getLimapuluhFormat() {
        return limapuluhFormat;
    }

    public void setLimapuluhFormat(String limapuluhFormat) {
        this.limapuluhFormat = limapuluhFormat;
    }

    public String getSeratusFormat() {
        return seratusFormat;
    }

    public void setSeratusFormat(String seratusFormat) {
        this.seratusFormat = seratusFormat;
    }

}
