package com.example.appkru.model.LaporanKontrol;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatumLaporanKontrol {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nopol")
    @Expose
    private String nopol;
    @SerializedName("trayek")
    @Expose
    private String trayek;
    @SerializedName("kelas")
    @Expose
    private String kelas;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("laporan_harian_id")
    @Expose
    private String laporanHarianId;
    @SerializedName("jenis_pelanggaran")
    @Expose
    private String jenisPelanggaran;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("naik_kontrol")
    @Expose
    private String naikKontrol;
    @SerializedName("turun_kontrol")
    @Expose
    private String turunKontrol;
    @SerializedName("tanggal_naik_kontrol")
    @Expose
    private String tanggalNaikKontrol;
    @SerializedName("tanggal_turun_kontrol")
    @Expose
    private String tanggalTurunKontrol;
    @SerializedName("jumlah_penumpang")
    @Expose
    private String jumlahPenumpang;
    @SerializedName("pendapatan_kontrol")
    @Expose
    private String pendapatanKontrol;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLaporanHarianId() {
        return laporanHarianId;
    }

    public void setLaporanHarianId(String laporanHarianId) {
        this.laporanHarianId = laporanHarianId;
    }

    public String getJenisPelanggaran() {
        return jenisPelanggaran;
    }

    public void setJenisPelanggaran(String jenisPelanggaran) {
        this.jenisPelanggaran = jenisPelanggaran;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNaikKontrol() {
        return naikKontrol;
    }

    public void setNaikKontrol(String naikKontrol) {
        this.naikKontrol = naikKontrol;
    }

    public String getTurunKontrol() {
        return turunKontrol;
    }

    public void setTurunKontrol(String turunKontrol) {
        this.turunKontrol = turunKontrol;
    }

    public String getTanggalNaikKontrol() {
        return tanggalNaikKontrol;
    }

    public void setTanggalNaikKontrol(String tanggalNaikKontrol) {
        this.tanggalNaikKontrol = tanggalNaikKontrol;
    }

    public String getTanggalTurunKontrol() {
        return tanggalTurunKontrol;
    }

    public void setTanggalTurunKontrol(String tanggalTurunKontrol) {
        this.tanggalTurunKontrol = tanggalTurunKontrol;
    }

    public String getJumlahPenumpang() {
        return jumlahPenumpang;
    }

    public void setJumlahPenumpang(String jumlahPenumpang) {
        this.jumlahPenumpang = jumlahPenumpang;
    }

    public String getPendapatanKontrol() {
        return pendapatanKontrol;
    }

    public void setPendapatanKontrol(String pendapatanKontrol) {
        this.pendapatanKontrol = pendapatanKontrol;
    }


}
