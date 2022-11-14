package id.ac.umn.e_cuscas;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tblUser")
public class User implements Serializable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "phone")
    private String phone;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "address")
    private String address;

    public User(String username, String name, String pass, String phone, String email, String address){
        this.username = username;
        this.name = name;
        this.password = pass;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public void setUsername(String username){ this.username = username; }
    public void setName(String name){ this.name = name; }
    public void setPassword(String pass){ this.password = pass; }
    public void setPhone(String phone){ this.phone = phone; }
    public void setEmail(String email){ this.email = email; }
    public void setAddress(String address){ this.address = address; }

    public String getUsername(){ return this.username; }
    public String getName(){ return this.name; }
    public String getPassword(){ return this.password; }
    public String getPhone(){ return this.phone; }
    public String getEmail(){ return this.email; }
    public String getAddress(){ return this.address; }
}
