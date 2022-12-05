package id.ac.umn.e_cuscas.remote;

import id.ac.umn.e_cuscas.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {
    @GET("api/posts/")
    Call<User> getUsers();
    @POST("api/posts/")
    Call<User> addUser(@Body User user);
    @PUT("api/posts/{id}")
    Call<User> updateUser(@Path("id") int id, @Body User user);
    @DELETE("api/posts/{id}")
    Call<User> deleteUser(@Path("id") int id);
}
