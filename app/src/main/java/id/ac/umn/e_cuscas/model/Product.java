package id.ac.umn.e_cuscas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("harga")
    @Expose
    private int harga;

    @SerializedName("jumlah")
    @Expose
    private int jumlah;

    @SerializedName("image")
    @Expose
    private String image;

    public Product(int id, String name, int harga, int jumlah, String image){
        setId(id);
        setName(name);
        setHarga(harga);
        setJumlah(jumlah);
        setImage(image);
    }

    public Product() {}

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setHarga(int harga) { this.harga = harga; }
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }
    public void setImage(String image) { this.image = image; }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getHarga() { return harga; }
    public int getJumlah() { return jumlah; }
    public String getImage() { return image; }
}
