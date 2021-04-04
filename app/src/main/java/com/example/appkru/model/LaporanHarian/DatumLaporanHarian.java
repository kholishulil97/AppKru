package com.example.appkru.model.LaporanHarian;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatumLaporanHarian {

    @SerializedName("laporan_harian_id")
    @Expose
    private String laporanHarianId;
    @SerializedName("nominal")
    @Expose
    private String nominal;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("jam_selesai")
    @Expose
    private String jamSelesai;
    @SerializedName("nopol")
    @Expose
    private String nopol;
    @SerializedName("trayek")
    @Expose
    private String trayek;
    @SerializedName("kelas")
    @Expose
    private String kelas;
    @SerializedName("status_setor_kata")
    @Expose
    private String statusSetorKata;
    @SerializedName("nip_nama_kasir")
    @Expose
    private String nipNamaKasir;
    @SerializedName("tanggal_setor_long")
    @Expose
    private String tanggalSetorLong;

    public String getLaporanHarianId() {
        return laporanHarianId;
    }

    public void setLaporanHarianId(String laporanHarianId) {
        this.laporanHarianId = laporanHarianId;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(String jamSelesai) {
        this.jamSelesai = jamSelesai;
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

    public String getStatusSetorKata() {
        return statusSetorKata;
    }

    public void setStatusSetorKata(String statusSetorKata) {
        this.statusSetorKata = statusSetorKata;
    }

    public String getNipNamaKasir() {
        return nipNamaKasir;
    }

    public void setNipNamaKasir(String nipNamaKasir) {
        this.nipNamaKasir = nipNamaKasir;
    }

    public String getTanggalSetorLong() {
        return tanggalSetorLong;
    }

    public void setTanggalSetorLong(String tanggalSetorLong) {
        this.tanggalSetorLong = tanggalSetorLong;
    }
}