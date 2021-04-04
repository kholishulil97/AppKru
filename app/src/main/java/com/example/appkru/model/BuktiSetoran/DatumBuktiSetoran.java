package com.example.appkru.model.BuktiSetoran;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DatumBuktiSetoran {

    @SerializedName("nopol")
    @Expose
    private String nopol;
    @SerializedName("trayek")
    @Expose
    private String trayek;
    @SerializedName("kelas")
    @Expose
    private String kelas;
    @SerializedName("waktu_berangkat")
    @Expose
    private String waktuBerangkat;
    @SerializedName("nip_nama_sopir")
    @Expose
    private String nipNamaSopir;
    @SerializedName("nip_nama_kondektur")
    @Expose
    private String nipNamaKondektur;
    @SerializedName("nip_nama_kernet")
    @Expose
    private String nipNamaKernet;
    @SerializedName("pendapatan_rit")
    @Expose
    private String pendapatanRit;
    @SerializedName("daftar_pengeluaran")
    @Expose
    private List<DaftarPengeluaran> daftarPengeluaran = null;
    @SerializedName("pengeluaran")
    @Expose
    private String pengeluaran;
    @SerializedName("sisa_pendapatan")
    @Expose
    private String sisaPendapatan;
    @SerializedName("nama_sopir")
    @Expose
    private String namaSopir;
    @SerializedName("nama_kondektur")
    @Expose
    private String namaKondektur;
    @SerializedName("nama_kernet")
    @Expose
    private String namaKernet;
    @SerializedName("premi_sopir")
    @Expose
    private String premiSopir;
    @SerializedName("premi_kondektur")
    @Expose
    private String premiKondektur;
    @SerializedName("premi_kernet")
    @Expose
    private String premiKernet;
    @SerializedName("jumlah_premi")
    @Expose
    private String jumlahPremi;
    @SerializedName("setoran_kas_angka")
    @Expose
    private String setoranKasAngka;
    @SerializedName("setoran _kas_terbilang")
    @Expose
    private String setoranKasTerbilang;
    @SerializedName("status_selesai")
    @Expose
    private String statusSelesai;

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

    public String getWaktuBerangkat() {
        return waktuBerangkat;
    }

    public void setWaktuBerangkat(String waktuBerangkat) {
        this.waktuBerangkat = waktuBerangkat;
    }

    public String getNipNamaSopir() {
        return nipNamaSopir;
    }

    public void setNipNamaSopir(String nipNamaSopir) {
        this.nipNamaSopir = nipNamaSopir;
    }

    public String getNipNamaKondektur() {
        return nipNamaKondektur;
    }

    public void setNipNamaKondektur(String nipNamaKondektur) {
        this.nipNamaKondektur = nipNamaKondektur;
    }

    public String getNipNamaKernet() {
        return nipNamaKernet;
    }

    public void setNipNamaKernet(String nipNamaKernet) {
        this.nipNamaKernet = nipNamaKernet;
    }

    public String getPendapatanRit() {
        return pendapatanRit;
    }

    public void setPendapatanRit(String pendapatanRit) {
        this.pendapatanRit = pendapatanRit;
    }

    public List<DaftarPengeluaran> getDaftarPengeluaran() {
        return daftarPengeluaran;
    }

    public void setDaftarPengeluaran(List<DaftarPengeluaran> daftarPengeluaran) {
        this.daftarPengeluaran = daftarPengeluaran;
    }

    public String getPengeluaran() {
        return pengeluaran;
    }

    public void setPengeluaran(String pengeluaran) {
        this.pengeluaran = pengeluaran;
    }

    public String getSisaPendapatan() {
        return sisaPendapatan;
    }

    public void setSisaPendapatan(String sisaPendapatan) {
        this.sisaPendapatan = sisaPendapatan;
    }

    public String getNamaSopir() {
        return namaSopir;
    }

    public void setNamaSopir(String namaSopir) {
        this.namaSopir = namaSopir;
    }

    public String getNamaKondektur() {
        return namaKondektur;
    }

    public void setNamaKondektur(String namaKondektur) {
        this.namaKondektur = namaKondektur;
    }

    public String getNamaKernet() {
        return namaKernet;
    }

    public void setNamaKernet(String namaKernet) {
        this.namaKernet = namaKernet;
    }

    public String getPremiSopir() {
        return premiSopir;
    }

    public void setPremiSopir(String premiSopir) {
        this.premiSopir = premiSopir;
    }

    public String getPremiKondektur() {
        return premiKondektur;
    }

    public void setPremiKondektur(String premiKondektur) {
        this.premiKondektur = premiKondektur;
    }

    public String getPremiKernet() {
        return premiKernet;
    }

    public void setPremiKernet(String premiKernet) {
        this.premiKernet = premiKernet;
    }

    public String getJumlahPremi() {
        return jumlahPremi;
    }

    public void setJumlahPremi(String jumlahPremi) {
        this.jumlahPremi = jumlahPremi;
    }

    public String getSetoranKasAngka() {
        return setoranKasAngka;
    }

    public void setSetoranKasAngka(String setoranKasAngka) {
        this.setoranKasAngka = setoranKasAngka;
    }

    public String getSetoranKasTerbilang() {
        return setoranKasTerbilang;
    }

    public void setSetoranKasTerbilang(String setoranKasTerbilang) {
        this.setoranKasTerbilang = setoranKasTerbilang;
    }

    public String getStatusSelesai() {
        return statusSelesai;
    }

    public void setStatusSelesai(String statusSelesai) {
        this.statusSelesai = statusSelesai;
    }
}
