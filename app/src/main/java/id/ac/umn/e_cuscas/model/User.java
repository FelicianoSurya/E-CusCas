package id.ac.umn.e_cuscas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {
    //private List<User> user;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("phone")
    @Expose
    private String phone;


    public User(String username, String password, String name, String email, String addres, String phone) {
        this.setUsername(username);
        this.setPassword(password);
        this.setName(name);
        this.setEmail(email);
        this.setAddress(addres);
        this.setPhone(phone);
    }

    public User() {}

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String passowrd) { this.password = passowrd; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setAddress(String address) { this.address = address; }
    public void setPhone(String phone) { this.phone = phone; }

    //public String getUsername() { return username; }
    //public String getPassword() { return password; }
    //public String getName() { return name; }
    //public String getEmail() { return email; }
    //public String getAddress() { return address; }
    //public String getPhone() { return phone; }


    //public List<User> getUser() { return data; }
    //public void setData(List<User> data) { this.data = data; }
}
