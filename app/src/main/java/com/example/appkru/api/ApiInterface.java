package com.example.appkru.api;

import com.example.appkru.model.BuktiSetoran.BuktiSetoran;
import com.example.appkru.model.LaporanHarian.LaporanHarian;
import com.example.appkru.model.LaporanKontrol.LaporanKontrol;
import com.example.appkru.model.Nominal.Nominal;
import com.example.appkru.model.Pengeluaran.Pengeluaran;
import com.example.appkru.model.Penumpang.Penumpang;
import com.example.appkru.model.PosKarcis.PosKarcis;
import com.example.appkru.model.Premi.Premi;
import com.example.appkru.model.SP.SP;
import com.example.appkru.model.User.DatumUser;
import com.example.appkru.model.User.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by haerul on 15/03/18.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("rest-api/smdlh-rest-server/api/user")
    public Call<User> getUser(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("rest-api/smdlh-rest-server/api/sp")
    public Call<SP> getSP(
            @Field("id") String id
    );


    @GET("rest-api/smdlh-rest-server/api/laporanharian")
    public Call<LaporanHarian> getLaporanHarian(
            @Query("id") String id,
            @Query("status") String status
    );


    @GET("rest-api/smdlh-rest-server/api/laporanharian")
    public Call<LaporanHarian> getStatusLH(
            @Query("id") String id
    );
    @FormUrlEncoded
    @POST("rest-api/smdlh-rest-server/api/laporanharian")
    public Call<LaporanHarian> insertLaporanHarian(
            @Field("dinas_id") String dinas_id
    );

    @FormUrlEncoded
    @POST("rest-api/smdlh-rest-server/api/premi")
    public Call<Premi> getPremi(
            @Field("id") String id,
            @Field("status_ambil") String status_ambil
    );


    @GET("rest-api/smdlh-rest-server/api/karcis")
    public Call<PosKarcis> getPosKarcis(
            @Query("laporan_harian_id") String laporan_harian_id
    );

    @GET("rest-api/smdlh-rest-server/api/karcis")
    public Call<Penumpang> getPenumpang(
            @Query("laporan_harian_id") String laporan_harian_id,
            @Query("posturun") String posturun
    );

    @FormUrlEncoded
    @POST("rest-api/smdlh-rest-server/api/invoice")
    public Call<Nominal> getNominal(
            @Field("trayek_id") String trayek_id,
            @Field("posnaik") String posnaik,
            @Field("posturun") String posturun,
            @Field("jml_pnp") String jml_pnp
    );

    @GET("rest-api/smdlh-rest-server/api/pengeluaran/{laporan_harian_id}")
    public Call<Pengeluaran> getPengeluaran(
            @Path("laporan_harian_id") String laporan_harian_id
    );

    @FormUrlEncoded
    @POST("rest-api/smdlh-rest-server/api/pengeluaran")
    public Call<Pengeluaran> insertPengeluaran(
            @Field("laporan_harian_id") String laporan_harian_id,
            @Field("nama_pengeluaran") String nama_pengeluaran,
            @Field("lokasi") String lokasi,
            @Field("nominal") String nominal,
            @Field("keterangan") String keterangan
    );

    @FormUrlEncoded
    @POST("rest-api/smdlh-rest-server/api/karcis")
    public Call<Penumpang> insertKarcis(
            @Field("laporan_harian_id") String laporan_harian_id,
            @Field("posnaik") String posnaik,
            @Field("posturun") String posturun,
            @Field("tarif") String tarif
    );

    @FormUrlEncoded
    @PUT("rest-api/smdlh-rest-server/api/pengeluaran")
    public Call<Pengeluaran> updatePengeluaran(
            @Field("id") String id,
            @Field("nama_pengeluaran") String nama_pengeluaran,
            @Field("lokasi") String lokasi,
            @Field("nominal") String nominal,
            @Field("keterangan") String keterangan
    );

    @FormUrlEncoded
    @PUT("rest-api/smdlh-rest-server/api/laporanharian")
    public Call<BuktiSetoran> updateBuktiSetoran(
            @Field("id") String id
    );

    @DELETE("rest-api/smdlh-rest-server/api/pengeluaran/{id}")
    public Call<Pengeluaran> deletePengeluaran(
            @Path("id") String id
    );

    @FormUrlEncoded
    @POST("rest-api/smdlh-rest-server/api/buktisetoran")
    public Call<BuktiSetoran> getBuktiSetoran(
            @Field("laporan_harian_id") String laporan_harian_id
    );

    @GET("rest-api/smdlh-rest-server/api/laporankontrol")
    public Call<LaporanKontrol> getLaporanKontrol(
            @Query("id") String id
    );

    @GET("rest-api/smdlh-rest-server/api/laporankontrol")
    public Call<PosKarcis> getStatusKontrol(
            @Query("id") String id,
            @Query("cek") String cek
    );

    @GET("rest-api/smdlh-rest-server/api/laporankontrol")
    public Call<LaporanKontrol> getLaporanKontrolBS(
            @Query("laporan_harian_id") String laporan_harian_id
    );

    @FormUrlEncoded
    @POST("rest-api/smdlh-rest-server/api/laporankontrol")
    public Call<LaporanKontrol> insertLaporanKontrol(
            @Field("laporan_harian_id") String laporan_harian_id,
            @Field("petugas_id") String petugas_id
    );

    @FormUrlEncoded
    @PUT("rest-api/smdlh-rest-server/api/laporankontrol")
    public Call<LaporanKontrol> updateLaporanKontrol(
            @Field("laporan_harian_id") String laporan_harian_id,
            @Field("petugas_id") String petugas_id,
            @Field("jenis_pelanggaran") String jenis_pelanggaran,
            @Field("keterangan") String keterangan,
            @Field("naik") String naik,
            @Field("turun") String turun,
            @Field("jml_pnp") String jml_pnp,
            @Field("jml_pendapatan") String jml_pendapatan
    );
}
