package id.ac.umn.e_cuscas.model;

import java.util.List;

public class Category {
    private List<Category> category;

    private int id;
    private String name, image;

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
