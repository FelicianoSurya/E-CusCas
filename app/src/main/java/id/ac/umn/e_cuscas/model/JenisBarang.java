package id.ac.umn.e_cuscas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class JenisBarang {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("jenis_barang")
    @Expose
    private ArrayList<Jenis> jenis_barang;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public ArrayList<Jenis> getJenis_barang() {
        return jenis_barang;
    }

    @Override
    public String toString() {
        return "JenisBarang{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", jenis_barang=" + jenis_barang +
                '}';
    }

    public class Jenis{
        @SerializedName("id")
        @Expose
        private int id;

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("id_kategori")
        @Expose
        private int id_kategori;

        @SerializedName("panjang")
        @Expose
        private float panjang;

        @SerializedName("lebar")
        @Expose
        private float lebar;

        @SerializedName("tinggi")
        @Expose
        private float tinggi;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getId_kategori() {
            return id_kategori;
        }

        public float getPanjang() {
            return panjang;
        }

        public float getLebar() {
            return lebar;
        }

        public float getTinggi() {
            return tinggi;
        }

        @Override
        public String toString() {
            return "Jenis{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", id_kategori=" + id_kategori +
                    ", panjang=" + panjang +
                    ", lebar=" + lebar +
                    ", tinggi=" + tinggi +
                    '}';
        }
    }
}
