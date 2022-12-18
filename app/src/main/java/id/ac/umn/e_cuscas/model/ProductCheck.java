package id.ac.umn.e_cuscas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductCheck {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("detail_barang")
    @Expose
    private ArrayList<ProductCheck.DetProduct> detail_barang;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<DetProduct> getDetail_barang() {
        return detail_barang;
    }

    public class DetProduct{
        @SerializedName("id")
        @Expose
        private int id;

        @SerializedName("id_barang")
        @Expose
        private int id_barang;

        @SerializedName("jumlah")
        @Expose
        private int jumlah;

        @SerializedName("harga")
        @Expose
        private int harga;

        private String name;

        public void setId(int id) { this.id = id; }
        public void setId_barang(int id_barang) { this.id_barang = id_barang; }
        public void setJumlah(int jumlah) { this.jumlah = jumlah; }
        public void setHarga(int harga) { this.harga = harga; }
        public void setName(String name) { this.name = name; }

        public int getId() {
            return id;
        }
        public int getId_barang() {
            return id_barang;
        }
        public int getJumlah() {
            return jumlah;
        }
        public int getHarga() {
            return harga;
        }
        public String getName() { return name; }

        public void tambahJumlah(){
            this.jumlah += 1;
        }
    }
}
