package id.ac.umn.e_cuscas.remote;

import id.ac.umn.e_cuscas.DetProdAdapter;
import id.ac.umn.e_cuscas.model.Category;
import id.ac.umn.e_cuscas.model.HistoryorderCategory;
import id.ac.umn.e_cuscas.model.OrderProd;
import id.ac.umn.e_cuscas.model.OrderProdPost;
import id.ac.umn.e_cuscas.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {
    @FormUrlEncoded
    @POST("v1/login")
    Call<User> loginUser(
            @Field("username") String username,
            @Field("password") String password
    );

    @POST("v1/register")
    Call<User> addUser(@Body User user);

    @GET("v1/getCategory")
    Call<JSONResponse> getCategories();

    @GET("v1/getProduct")
    Call<JSONResponse> getProducts();

    @GET("v1/getJenisBarang")
    @Headers("Accept: application/json")
    Call<JSONResponse> getJenises(@Query("idKategori") int idKategori);

    @GET("v1/getOrderDetail/{iduser}")
    @Headers("Accept: application/json")
    Call<JSONResponse> getProductCheck(@Path("iduser") int iduser);

    @FormUrlEncoded
    @POST("v1/updateDetailBarang")
    Call<DetProdAdapter> gantiJumlah(
            @Field("id") int id,
            @Field("stat") String stat
    );

    @FormUrlEncoded
    @POST("v1/createOrderProduct")
    Call<OrderProd> orderCategory(
            @Field("id_user") int id_user,
            @Field("pembayaran") String pembayaran,
            @Field("ongkir") float ongkir,
            @Field("voucher") float voucher,
            @Field("address") String address
    );

    @FormUrlEncoded
    @POST("v1/createOrderDetail")
    Call<OrderProdPost> orderProdPost(
            @Field("id_barang") int id_barang,
            @Field("id_user") int id_user
    );

    @FormUrlEncoded
    @POST("v1/cancelDetailBarang")
    Call<OrderProdPost> deleteProdPost(
            @Field("id_barang") int id_barang,
            @Field("id_user") int id_user
    );

    @FormUrlEncoded
    @POST("v1/createOrder")
    Call<HistoryorderCategory> orderCat(
            @Field("id_user") int id_user,
            @Field("pembayaran") String pembayaran,
            @Field("id_kategori") int id_kategori,
            @Field("aksesoris") String aksesoris,
            @Field("ongkir") float ongkir,
            @Field("jenis_barang") String jenis_barang,
            @Field("warna") String warna,
            @Field("bahan") String bahan,
            @Field("address") String address,
            @Field("voucher") float voucher,
            @Field("gambar") String gambar,
            @Field("keterangan") String keterangan
    );
}
