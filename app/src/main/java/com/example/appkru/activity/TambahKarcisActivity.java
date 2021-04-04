package com.example.appkru.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appkru.R;
import com.example.appkru.api.ApiClient;
import com.example.appkru.api.ApiInterface;
import com.example.appkru.manger.Constants;
import com.example.appkru.manger.SharedPrefManager;
import com.example.appkru.model.LaporanHarian.DatumLaporanHarian;
import com.example.appkru.model.LaporanHarian.LaporanHarian;
import com.example.appkru.model.Nominal.Nominal;
import com.example.appkru.model.Pengeluaran.Pengeluaran;
import com.example.appkru.model.Penumpang.Penumpang;
import com.example.appkru.model.PosKarcis.DatumPosKarcis;
import com.example.appkru.model.PosKarcis.PosKarcis;
import com.example.appkru.model.User.User;
import com.example.appkru.printing.DeviceListActivity;
import com.example.appkru.printing.UnicodeFormatter;
import com.google.android.material.chip.Chip;
import com.mazenrashed.printooth.Printooth;
import com.mazenrashed.printooth.data.converter.ArabicConverter;
import com.mazenrashed.printooth.data.printable.Printable;
import com.mazenrashed.printooth.data.printable.RawPrintable;
import com.mazenrashed.printooth.data.printable.TextPrintable;
import com.mazenrashed.printooth.data.printer.DefaultPrinter;
import com.mazenrashed.printooth.ui.ScanningActivity;
import com.mazenrashed.printooth.utilities.Printing;
import com.mazenrashed.printooth.utilities.PrintingCallback;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahKarcisActivity extends AppCompatActivity {
    private String posNaik, posTurun, jmlPnp, trayekID, laporanHarianID, karcisID, tanggalTransaksi, tarif;
    private String nopolBus, kodeTrayek, namanipKondektur, waktuTransaksi;
    private TextView tvHarga, tvDiaHargaBayar, tvDiaKembalian, tvStatusPrinter;
    private Chip chipHargaPas, chipHarga1, chipHarga2, chipHarga3, chipHarga4;
    private AppCompatButton btnTambah, btnHubungkan, btnDiaBayar;
    private Spinner spKarcis, spKarcisTurun, spPenumpang;
    private ProgressDialog progressDialog;

    private ApiInterface apiInterface;
    private Dialog dialog;

    private Printing printing = null;
    PrintingCallback printingCallback=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_karcis);

        Log.d("xxx", "onCreate ");
        if (Printooth.INSTANCE.hasPairedPrinter())
            printing = Printooth.INSTANCE.printer();

        initViews();
        initListeners();
        fetchData();

    }

    private void initViews() {
        spKarcis = findViewById(R.id.spinner_karcis);
        spKarcisTurun = findViewById(R.id.spinner_karcis_turun);
        spPenumpang = findViewById(R.id.spinner_penumpang);
        btnTambah = findViewById(R.id.btn_tambah_karcis);
        tvHarga = findViewById(R.id.tv_tambah_karcis_harga);
        tvStatusPrinter = findViewById(R.id.tv_tambah_karcis_status_printer);
        btnHubungkan = findViewById(R.id.btn_tambah_karcis_hubungkan);

        Bundle extras = getIntent().getExtras();
        laporanHarianID = extras.getString("laporan_harian_id");

        dialog = new Dialog(this);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (posNaik.equalsIgnoreCase(posTurun)){
                    Toast.makeText(TambahKarcisActivity.this,"Pastikan Anda memilih Pos Naik dan Pos Turun yang BERBEDA",Toast.LENGTH_LONG).show();
                }
                else{
                    fetchDataInvoice();
                }
            }
        });

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        if (Printooth.INSTANCE.getPairedPrinter()!=null){
            btnHubungkan.setText(
                    (Printooth.INSTANCE.hasPairedPrinter())?("Putuskan "+ Printooth.INSTANCE.getPairedPrinter().getName()):"Pair with printer");
            tvStatusPrinter.setText("Printer Terhubung");
            tvStatusPrinter.setTextColor(Color.rgb(97, 170, 74));
            btnTambah.setEnabled(true);
        }

    }

    public void fetchData(){
        Bundle extras = getIntent().getExtras();
        Call<PosKarcis> call1=apiInterface.getPosKarcis(extras.getString("laporan_harian_id"));
        call1.enqueue(new Callback<PosKarcis>() {
            @Override
            public void onResponse(Call<PosKarcis> call, Response<PosKarcis> response) {
                if(response.isSuccessful()){
                    trayekID = response.body().getData().get(0).getTrayekId();
                    nopolBus = response.body().getData().get(0).getBus();
                    kodeTrayek = response.body().getData().get(0).getKode();
                    namanipKondektur = response.body().getData().get(0).getKondektur();
                    List<String> datumPosKarcis = response.body().getData().get(0).getPosnaik();

                    ArrayList<String> list = new ArrayList<String>();
                    for(int i=0; i<datumPosKarcis.size(); i++) {
                        list.add(datumPosKarcis.get(i));
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.style_spinner,list);
                    spKarcis.setAdapter(arrayAdapter);
                    spKarcis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            posNaik = parent.getItemAtPosition(position).toString();
                            if (posNaik.equalsIgnoreCase(posTurun)){
                                tvHarga.setText("0");
                            }
                            else{
                                fetchDataNominal();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    ArrayAdapter<String> arrayAdaptertrn = new ArrayAdapter<>(getApplicationContext(), R.layout.style_spinner,list);
                    spKarcisTurun.setAdapter(arrayAdaptertrn);
                    spKarcisTurun.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            posTurun = parent.getItemAtPosition(position).toString();
                            if (posNaik.equalsIgnoreCase(posTurun)){
                                tvHarga.setText("0");
                            }
                            else{
                                fetchDataNominal();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    String[] valuepnp = {"1", "2", "3", "4"};
                    ArrayList<String> arrayListpnp = new ArrayList<>(Arrays.asList(valuepnp));
                    ArrayAdapter<String> arrayAdapterpnp = new ArrayAdapter<>(getApplicationContext(), R.layout.style_spinner,arrayListpnp);
                    spPenumpang.setAdapter(arrayAdapterpnp);
                    spPenumpang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            jmlPnp = parent.getItemAtPosition(position).toString();
                            if (posNaik.equalsIgnoreCase(posTurun)){
                                tvHarga.setText("0");
                            }
                            else{
                                fetchDataNominal();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }else{
                    String[] value = {"-"};
                    ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(value));
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.style_spinner,arrayList);
                    spKarcis.setAdapter(arrayAdapter);

                    String[] valuetrn = {"-"};
                    ArrayList<String> arrayListtrn = new ArrayList<>(Arrays.asList(valuetrn));
                    ArrayAdapter<String> arrayAdaptertrn = new ArrayAdapter<>(getApplicationContext(), R.layout.style_spinner,arrayListtrn);
                    spKarcisTurun.setAdapter(arrayAdaptertrn);

                    String[] valuepnp = {"1", "2", "3", "4"};
                    ArrayList<String> arrayListpnp = new ArrayList<>(Arrays.asList(valuepnp));
                    ArrayAdapter<String> arrayAdapterpnp = new ArrayAdapter<>(getApplicationContext(), R.layout.style_spinner,arrayListpnp);
                    spPenumpang.setAdapter(arrayAdapterpnp);

                    Toast.makeText(getApplicationContext(),"Gagal menyusun data!",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PosKarcis> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Terjadi kesalahan jaringan!",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void fetchDataInvoice(){
        Call<Nominal> call1=apiInterface.getNominal(trayekID, posNaik, posTurun, jmlPnp);
        call1.enqueue(new Callback<Nominal>() {
            @Override
            public void onResponse(Call<Nominal> call, final Response<Nominal> response) {
                if (response.isSuccessful()) {
                    dialog.setContentView(R.layout.dialog_tambah_karcis);

                    tvDiaHargaBayar = dialog.findViewById(R.id.tv_dia_tk_harga_bayar);
                    tvDiaKembalian = dialog.findViewById(R.id.tv_dia_tk_kembalian);
                    chipHargaPas = dialog.findViewById(R.id.chip_dia_tk_harga_pas);
                    chipHarga1 = dialog.findViewById(R.id.chip_dia_tk_harga1);
                    chipHarga2 = dialog.findViewById(R.id.chip_dia_tk_harga2);
                    chipHarga3 = dialog.findViewById(R.id.chip_dia_tk_harga3);
                    chipHarga4 = dialog.findViewById(R.id.chip_dia_tk_harga4);
                    btnDiaBayar = dialog.findViewById(R.id.btn_dia_tambah_karcis_bayar);

                    tvDiaHargaBayar.setText(response.body().getData().get(0).getHargaBayarFormat());
                    chipHarga1.setText(response.body().getData().get(0).getLimaFormat());
                    chipHarga2.setText(response.body().getData().get(0).getDuapuluhFormat());
                    chipHarga3.setText(response.body().getData().get(0).getLimapuluhFormat());
                    chipHarga4.setText(response.body().getData().get(0).getSeratusFormat());

                    chipHargaPas.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int kembalian = response.body().getData().get(0).getHargaBayar() - response.body().getData().get(0).getHargaBayar();
                            String strKembalian = Integer.toString(kembalian);
                            tvDiaKembalian.setText(strKembalian);
                        }
                    });

                    chipHarga1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int kembalian = response.body().getData().get(0).getLima() - response.body().getData().get(0).getHargaBayar();
                            String strKembalian = Integer.toString(kembalian);
                            tvDiaKembalian.setText(strKembalian);
                        }
                    });

                    chipHarga2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int kembalian = response.body().getData().get(0).getDuapuluh() - response.body().getData().get(0).getHargaBayar();
                            String strKembalian = Integer.toString(kembalian);
                            tvDiaKembalian.setText(strKembalian);
                        }
                    });

                    chipHarga3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int kembalian = response.body().getData().get(0).getLimapuluh() - response.body().getData().get(0).getHargaBayar();
                            String strKembalian = Integer.toString(kembalian);
                            tvDiaKembalian.setText(strKembalian);
                        }
                    });

                    chipHarga4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int kembalian = response.body().getData().get(0).getSeratus() - response.body().getData().get(0).getHargaBayar();
                            String strKembalian = Integer.toString(kembalian);
                            tvDiaKembalian.setText(strKembalian);
                        }
                    });

                    btnDiaBayar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            insertData();
                        }
                    });

                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }else {
                    Toast.makeText(TambahKarcisActivity.this,"Terjadi kesalahan data.\nSilahkan coba lagi.",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Nominal> call, Throwable t) {
                Toast.makeText(TambahKarcisActivity.this,"Terjadi kesalahan jaringan!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void fetchDataNominal(){
        Call<Nominal> call1=apiInterface.getNominal(trayekID, posNaik, posTurun, jmlPnp);
        call1.enqueue(new Callback<Nominal>() {
            @Override
            public void onResponse(Call<Nominal> call, final Response<Nominal> response) {
                if (response.isSuccessful()) {
                    tvHarga.setText(response.body().getData().get(0).getHargaBayarFormat());
                    tarif = Integer.toString(response.body().getData().get(0).getHargaBayar());
                }
            }
            @Override
            public void onFailure(Call<Nominal> call, Throwable t) {
                Toast.makeText(TambahKarcisActivity.this,"Terjadi kesalahan jaringan!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void insertData(){
        Bundle extras = getIntent().getExtras();
        Call<Penumpang> call1=apiInterface.insertKarcis(extras.getString("laporan_harian_id"), posNaik, posTurun, tarif);
        call1.enqueue(new Callback<Penumpang>() {
            @Override
            public void onResponse(Call<Penumpang> call, Response<Penumpang> response) {
                if(response.isSuccessful()){
                    tanggalTransaksi = response.body().getData().get(0).getWaktu();
                    karcisID = response.body().getIdKarcis();
                    btnPrint();
                    dialog.dismiss();
                    Toast.makeText(TambahKarcisActivity.this,"Berhasil menyimpan transaksi.",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(TambahKarcisActivity.this, "Terjadi kesalahan!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Penumpang> call, Throwable t) {
                Toast.makeText(TambahKarcisActivity.this,"Terjadi kesalahan jaringan!",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void btnPrint() {
        if (!Printooth.INSTANCE.hasPairedPrinter())
            startActivityForResult(new Intent(this, ScanningActivity.class ),ScanningActivity.SCANNING_FOR_PRINTER);
        else printSomePrintable();
    }

    public void btnPairUnpair(View v) {
        if (Printooth.INSTANCE.hasPairedPrinter()) {
            Printooth.INSTANCE.removeCurrentPrinter();
            tvStatusPrinter.setText("Printer Belum Terhubung");
            tvStatusPrinter.setTextColor(Color.rgb(199, 59, 59));
            btnHubungkan.setText("Hubungkan");
            btnTambah.setEnabled(false);
        } else {
            startActivityForResult(new Intent(this, ScanningActivity.class ),ScanningActivity.SCANNING_FOR_PRINTER);
            initViews();
        }
    }

    private void initListeners() {
        if (printing!=null && printingCallback==null) {
            Log.d("xxx", "initListeners ");
            printingCallback = new PrintingCallback() {

                public void connectingWithPrinter() {
                    Toast.makeText(getApplicationContext(), "Menghubungkan ke printer", Toast.LENGTH_SHORT).show();
                    Log.d("xxx", "Connecting");
                }
                public void printingOrderSentSuccessfully() {
                    Toast.makeText(getApplicationContext(), "Perintah cetak berhasil terkirim", Toast.LENGTH_SHORT).show();
                    Log.d("xxx", "printingOrderSentSuccessfully");
                }
                public void connectionFailed(@NonNull String error) {
                    Toast.makeText(getApplicationContext(), "Penghubungan gagal : "+error, Toast.LENGTH_LONG).show();
                    Log.d("xxx", "connectionFailed : "+error);
                    btnHubungkan.setText("Hubungkan");
                    tvStatusPrinter.setText("Printer Belum Terhubung");
                    tvStatusPrinter.setTextColor(Color.rgb(199, 59, 59));
                    btnTambah.setEnabled(false);
                }
                public void onError(@NonNull String error) {
                    Toast.makeText(getApplicationContext(), "onError : "+error, Toast.LENGTH_SHORT).show();
                    Log.d("xxx", "onError : "+error);
                }
                public void onMessage(@NonNull String message) {
                    Toast.makeText(getApplicationContext(), "onMessage : " +message, Toast.LENGTH_SHORT).show();
                    Log.d("xxx", "onMessage : "+message);
                }
            };

            Printooth.INSTANCE.printer().setPrintingCallback(printingCallback);
        }
    }

    private void printSomePrintable() {
        Log.d("xxx", "printSomePrintable ");
        if (printing!=null)
            printing.print(getSomePrintables());
    }

    private ArrayList<Printable> getSomePrintables() {
        ArrayList<Printable> al = new ArrayList<>();
        al.add(new RawPrintable.Builder(new byte[]{27, 100, 0}).build()); // feed lines example in raw mode

        al.add( (new TextPrintable.Builder())
                .setText("PT. AKAS MILA SEJAHTERA\nJln. Pangsud 237, Probolinggo\n082-234-909090\n================================")
                .setAlignment(DefaultPrinter.Companion.getALIGNMENT_CENTER())
                .build());

        al.add( (new TextPrintable.Builder())
                .setText("ID : " + laporanHarianID+ "-" + karcisID + "\n" + nopolBus + " [" + kodeTrayek + "]" + "\n" + namanipKondektur + "\n")
                .setAlignment(DefaultPrinter.Companion.getALIGNMENT_LEFT())
                .build());

        al.add( (new TextPrintable.Builder())
                .setText("--------------------------------\n")
                .setAlignment(DefaultPrinter.Companion.getALIGNMENT_CENTER())
                .build());

        al.add( (new TextPrintable.Builder())
                .setText("POS NAIK   : " + posNaik + "\nPOS TURUN  : " + posTurun + "\n")
                .setAlignment(DefaultPrinter.Companion.getALIGNMENT_LEFT())
                .build());


        al.add( (new TextPrintable.Builder())
                .setText("--------------------------------\n")
                .setAlignment(DefaultPrinter.Companion.getALIGNMENT_CENTER())
                .setEmphasizedMode(DefaultPrinter.Companion.getEMPHASIZED_MODE_BOLD())
                .build());

        al.add( (new TextPrintable.Builder())
                .setText("HARGA      : " + "Rp " + tvHarga.getText().toString())
                .setAlignment(DefaultPrinter.Companion.getALIGNMENT_LEFT())
                .build());

        al.add( (new TextPrintable.Builder())
                .setText("\n--------------------------------")
                .setAlignment(DefaultPrinter.Companion.getALIGNMENT_CENTER())
                .setEmphasizedMode(DefaultPrinter.Companion.getEMPHASIZED_MODE_BOLD())
                .build());

        al.add( (new TextPrintable.Builder())
                .setText("WAKTU TRANSAKSI: \n" + tanggalTransaksi + "\n")
                .setAlignment(DefaultPrinter.Companion.getALIGNMENT_LEFT())
                .build());

        al.add( (new TextPrintable.Builder())
                .setText("================================")
                .setAlignment(DefaultPrinter.Companion.getALIGNMENT_CENTER())
                .build());

        al.add( (new TextPrintable.Builder())
                .setText("Perhatian!\nBarang Hilang atau Rusak Resiko Penumpang\nKarcis ini sudah termasuk Iuran Wajib Penumpang (UU 33 1964)")
                .setAlignment(DefaultPrinter.Companion.getALIGNMENT_CENTER())
                .setNewLinesAfter(1)
                .build());

        al.add( (new TextPrintable.Builder())
                .setText("")
                .setAlignment(DefaultPrinter.Companion.getALIGNMENT_CENTER())
                .setNewLinesAfter(1)
                .build());
        al.add( (new TextPrintable.Builder())
                .setText("")
                .setAlignment(DefaultPrinter.Companion.getALIGNMENT_CENTER())
                .setNewLinesAfter(1)
                .build());

        return al;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("xxx", "onActivityResult "+requestCode);

        if (requestCode == ScanningActivity.SCANNING_FOR_PRINTER && resultCode == Activity.RESULT_OK) {
            initListeners();
            printTestPrintable();
            Toast.makeText(getApplicationContext(), "Menghubungkan ke printer...\nTunggu hingga tes printer tercetak", Toast.LENGTH_LONG).show();
        }

    }

    private ArrayList<Printable> getTestPrintable() {
        ArrayList<Printable> al = new ArrayList<>();
        al.add(new RawPrintable.Builder(new byte[]{27, 100, 0}).build()); // feed lines example in raw mode

        al.add( (new TextPrintable.Builder())
                .setText("Coba Printer\nCoba Printer\nCoba Printer\n--------------------------------")
                .setAlignment(DefaultPrinter.Companion.getALIGNMENT_CENTER())
                .build());
        al.add( (new TextPrintable.Builder())
                .setText("")
                .setAlignment(DefaultPrinter.Companion.getALIGNMENT_CENTER())
                .setNewLinesAfter(1)
                .build());
        al.add( (new TextPrintable.Builder())
                .setText("")
                .setAlignment(DefaultPrinter.Companion.getALIGNMENT_CENTER())
                .setNewLinesAfter(1)
                .build());

        return al;
    }

    private void printTestPrintable() {
        Log.d("xxx", "printSomePrintable ");
        if (printing!=null)
            printing.print(getTestPrintable());
        initViews();
    }
}