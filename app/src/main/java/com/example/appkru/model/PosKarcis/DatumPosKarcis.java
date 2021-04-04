package com.example.appkru.model.PosKarcis;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatumPosKarcis {

    @SerializedName("trayek_id")
    @Expose
    private String trayekId;
    @SerializedName("kondektur")
    @Expose
    private String kondektur;
    @SerializedName("bus")
    @Expose
    private String bus;
    @SerializedName("kode")
    @Expose
    private String kode;
    @SerializedName("posnaik")
    @Expose
    private List<String> posnaik = null;

    public String getTrayekId() {
        return trayekId;
    }

    public void setTrayekId(String trayekId) {
        this.trayekId = trayekId;
    }

    public String getKondektur() {
        return kondektur;
    }

    public void setKondektur(String kondektur) {
        this.kondektur = kondektur;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public List<String> getPosnaik() {
        return posnaik;
    }

    public void setPosnaik(List<String> posnaik) {
        this.posnaik = posnaik;
    }

}