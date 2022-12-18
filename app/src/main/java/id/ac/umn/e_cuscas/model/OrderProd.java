package id.ac.umn.e_cuscas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderProd {

    @SerializedName("id_user")
    @Expose
    private int id_user;

    @SerializedName("pembayaran")
    @Expose
    private String pembayaran;

    @SerializedName("ongkir")
    @Expose
    private float ongkir;

    @SerializedName("voucher")
    @Expose
    private float voucher;

    @SerializedName("address")
    @Expose
    private String address;

    public OrderProd(int id_user, String pembayaran, float ongkir, float voucher, String address) {
        this.id_user = id_user;
        this.pembayaran = pembayaran;
        this.ongkir = ongkir;
        this.voucher = voucher;
        this.address = address;
    }
}
