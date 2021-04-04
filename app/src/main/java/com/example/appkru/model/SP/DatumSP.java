package com.example.appkru.model.SP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatumSP {

    @SerializedName("dinas_id")
    @Expose
    private String dinasId;
    @SerializedName("nopol")
    @Expose
    private String nopol;
    @SerializedName("mesin")
    @Expose
    private String mesin;
    @SerializedName("kelas")
    @Expose
    private String kelas;
    @SerializedName("kode")
    @Expose
    private String kode;
    @SerializedName("trayek")
    @Expose
    private String trayek;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("nip_nama")
    @Expose
    private String nipNama;
    @SerializedName("posisi")
    @Expose
    private String posisi;
    @SerializedName("nip_nama_mengganti")
    @Expose
    private String nipNamaMengganti;
    @SerializedName("nama_pengatur")
    @Expose
    private String namaPengatur;
    @SerializedName("posisi_pengatur")
    @Expose
    private String posisiPengatur;
    @SerializedName("tanggal_panjang")
    @Expose
    private String tanggalPanjang;
    @SerializedName("tanggal_pendek")
    @Expose
    private String tanggalPendek;

    public String getDinasId() {
        return dinasId;
    }

    public void setDinasId(String dinasId) {
        this.dinasId = dinasId;
    }

    public String getNopol() {
        return nopol;
    }

    public void setNopol(String nopol) {
        this.nopol = nopol;
    }

    public String getMesin() {
        return mesin;
    }

    public void setMesin(String mesin) {
        this.mesin = mesin;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getTrayek() {
        return trayek;
    }

    public void setTrayek(String trayek) {
        this.trayek = trayek;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNipNama() {
        return nipNama;
    }

    public void setNipNama(String nipNama) {
        this.nipNama = nipNama;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public String getNipNamaMengganti() {
        return nipNamaMengganti;
    }

    public void setNipNamaMengganti(String nipNamaMengganti) {
        this.nipNamaMengganti = nipNamaMengganti;
    }

    public String getNamaPengatur() {
        return namaPengatur;
    }

    public void setNamaPengatur(String namaPengatur) {
        this.namaPengatur = namaPengatur;
    }

    public String getPosisiPengatur() {
        return posisiPengatur;
    }

    public void setPosisiPengatur(String posisiPengatur) {
        this.posisiPengatur = posisiPengatur;
    }

    public String getTanggalPanjang() {
        return tanggalPanjang;
    }

    public void setTanggalPanjang(String tanggalPanjang) {
        this.tanggalPanjang = tanggalPanjang;
    }

    public String getTanggalPendek() {
        return tanggalPendek;
    }

    public void setTanggalPendek(String tanggalPendek) {
        this.tanggalPendek = tanggalPendek;
    }
}
