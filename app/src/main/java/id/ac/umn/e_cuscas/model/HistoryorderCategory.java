package id.ac.umn.e_cuscas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryorderCategory {

    @SerializedName("id_user")
    @Expose
    private int id_user;

    @SerializedName("pembayaran")
    @Expose
    private String pembayaran;

    @SerializedName("id_kategori")
    @Expose
    private int id_kategori;

    @SerializedName("aksesoris")
    @Expose
    private String aksesoris;

    @SerializedName("ongkir")
    @Expose
    private float ongkir;

    @SerializedName("jenis_barang")
    @Expose
    private String jenis_barang;

    @SerializedName("warna")
    @Expose
    private String warna;

    @SerializedName("bahan")
    @Expose
    private String bahan;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("voucher")
    @Expose
    private float voucher;

    @SerializedName("gambar")
    @Expose
    private String gambar;

    @SerializedName("keterangan")
    @Expose
    private String keterangan;

    public HistoryorderCategory(int id_user, String pembayaran, int id_kategori, String aksesoris, float ongkir, String jenis_barang, String warna, String bahan, String address, float voucher, String gambar, String keterangan) {
        this.id_user = id_user;
        this.pembayaran = pembayaran;
        this.id_kategori = id_kategori;
        this.aksesoris = aksesoris;
        this.ongkir = ongkir;
        this.jenis_barang = jenis_barang;
        this.warna = warna;
        this.bahan = bahan;
        this.address = address;
        this.voucher = voucher;
        this.gambar = gambar;
        this.keterangan = keterangan;
    }
}
