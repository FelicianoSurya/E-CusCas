package id.ac.umn.e_cuscas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category {
    private List<Category> category;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("image")
    @Expose
    private String image;

    public Category(int id, String name, String image){
        this.setId(id);
        this.setName(name);
        this.setImage(image);
    }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setImage(String image) { this.image = image; }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getImage() {return image; }

    public List<Category> getCategory() { return category; }
    public void setCategory(List<Category> category) { this.category = category; }

}
