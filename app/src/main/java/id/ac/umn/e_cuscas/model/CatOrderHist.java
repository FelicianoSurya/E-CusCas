package id.ac.umn.e_cuscas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CatOrderHist {

    @SerializedName("jenis_barang")
    @Expose
    private String jenis_barang;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("gambar")
    @Expose
    private String gambar;

    @SerializedName("total_harga")
    @Expose
    private int total_harga;

    @SerializedName("status")
    @Expose
    private String status;

    public CatOrderHist(String jenis_barang, String address, String gambar, int total_harga, String status) {
        this.jenis_barang = jenis_barang;
        this.address = address;
        this.gambar = gambar;
        this.total_harga = total_harga;
        this.status = status;
    }

    public String getJenis_barang() {
        return jenis_barang;
    }

    public String getAddress() {
        return address;
    }

    public String getGambar() {
        return gambar;
    }

    public String getTotal_harga() {
        return String.valueOf(total_harga);
    }

    public String getStatus() {
        if(status.equals("konfirmasi_pembyaran")){
            return "Konfirmasi pembayaran";
        } else if(status.equals("pesanan_diterima")){
            return "Pesanan diterima";
        } else {
            return status;
        }
    }
}
