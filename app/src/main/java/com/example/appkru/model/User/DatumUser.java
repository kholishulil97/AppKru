package com.example.appkru.model.User;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatumUser {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nip")
    @Expose
    private String nip;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("fotoprofil")
    @Expose
    private String fotoprofil;
    @SerializedName("role_id")
    @Expose
    private String roleId;
    @SerializedName("nopol")
    @Expose
    private String nopol;
    @SerializedName("kelas")
    @Expose
    private String kelas;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("posawal")
    @Expose
    private String posawal;
    @SerializedName("posakhir")
    @Expose
    private String posakhir;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getFotoprofil() {
        return fotoprofil;
    }

    public void setFotoprofil(String fotoprofil) {
        this.fotoprofil = fotoprofil;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getNopol() {
        return nopol;
    }

    public void setNopol(String nopol) {
        this.nopol = nopol;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPosawal() {
        return posawal;
    }

    public void setPosawal(String posawal) {
        this.posawal = posawal;
    }

    public String getPosakhir() {
        return posakhir;
    }

    public void setPosakhir(String posakhir) {
        this.posakhir = posakhir;
    }

}