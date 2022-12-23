package id.ac.umn.e_cuscas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProdOrderHist {

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("harga_total")
    @Expose
    private int harga_total;

    @SerializedName("status")
    @Expose
    private String status;

    public ProdOrderHist(String address, int harga_total, String status) {
        this.address = address;
        this.harga_total = harga_total;
        this.status = status;
    }

    public String getJenis_barang() {
        return "Aksesoris";
    }

    public String getAddress() {
        return address;
    }

    public String getHarga_total() {
        return String.valueOf(harga_total);
    }

    public String getStatus() {
        if(status.equals("konfirmasi_pembayaran")){
            return "Konfirmasi pembayaran";
        } else if(status.equals("pesanan_diterima")){
            return "Pesanan diterima";
        } else {
            return status;
        }
    }
}
