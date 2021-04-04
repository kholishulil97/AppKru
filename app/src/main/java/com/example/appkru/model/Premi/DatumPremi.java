package com.example.appkru.model.Premi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatumPremi {

    @SerializedName("bulan_setor")
    @Expose
    private String bulanSetor;
    @SerializedName("nopol")
    @Expose
    private String nopol;
    @SerializedName("trayek")
    @Expose
    private String trayek;
    @SerializedName("kelas")
    @Expose
    private String kelas;
    @SerializedName("nominal")
    @Expose
    private String nominal;
    @SerializedName("kasir")
    @Expose
    private String kasir;
    @SerializedName("tanggal_ambil")
    @Expose
    private String tanggalAmbil;
    @SerializedName("jam_ambil")
    @Expose
    private String jamAmbil;
    @SerializedName("status_ambil")
    @Expose
    private String statusAmbil;

    public String getBulanSetor() {
        return bulanSetor;
    }

    public void setBulanSetor(String bulanSetor) {
        this.bulanSetor = bulanSetor;
    }

    public String getNopol() {
        return nopol;
    }

    public void setNopol(String nopol) {
        this.nopol = nopol;
    }

    public String getTrayek() {
        return trayek;
    }

    public void setTrayek(String trayek) {
        this.trayek = trayek;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getKasir() {
        return kasir;
    }

    public void setKasir(String kasir) {
        this.kasir = kasir;
    }

    public String getTanggalAmbil() {
        return tanggalAmbil;
    }

    public void setTanggalAmbil(String tanggalAmbil) {
        this.tanggalAmbil = tanggalAmbil;
    }

    public String getJamAmbil() {
        return jamAmbil;
    }

    public void setJamAmbil(String jamAmbil) {
        this.jamAmbil = jamAmbil;
    }

    public String getStatusAmbil() {
        return statusAmbil;
    }

    public void setStatusAmbil(String statusAmbil) {
        this.statusAmbil = statusAmbil;
    }

}
