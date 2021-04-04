package com.example.appkru.model.Pengeluaran;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatumPengeluaran {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("laporan_harian_id")
    @Expose
    private String laporanHarianId;
    @SerializedName("nama_pengeluaran")
    @Expose
    private String namaPengeluaran;
    @SerializedName("lokasi")
    @Expose
    private String lokasi;
    @SerializedName("nominal")
    @Expose
    private String nominal;
    @SerializedName("angka")
    @Expose
    private String angka;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("waktu")
    @Expose
    private String waktu;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;

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

    public String getNamaPengeluaran() {
        return namaPengeluaran;
    }

    public void setNamaPengeluaran(String namaPengeluaran) {
        this.namaPengeluaran = namaPengeluaran;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getAngka() {
        return angka;
    }

    public void setAngka(String angka) {
        this.angka = angka;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }


}
