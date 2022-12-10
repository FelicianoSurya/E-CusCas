package id.ac.umn.e_cuscas.remote;

import id.ac.umn.e_cuscas.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {
    @POST("v1/login")
    Call<User> loginUser(
            @Field("username") String username,
            @Field("password") String password
    );
    @POST("v1/register")
    Call<User> addUser(@Body User user);
}
