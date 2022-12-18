package id.ac.umn.e_cuscas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderProdPost {

    @SerializedName("id_barang")
    @Expose
    private int id_barang;

    @SerializedName("id_user")
    @Expose
    private int id_user;

    public OrderProdPost(int id_barang, int id_user, int jumlah, int harga) {
        this.id_barang = id_barang;
        this.id_user = id_user;
    }

    public void setId_barang(int id_barang) {
        this.id_barang = id_barang;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_barang() {
        return id_barang;
    }

    public int getId_user() {
        return id_user;
    }
}
