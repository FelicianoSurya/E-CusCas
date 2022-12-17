package id.ac.umn.e_cuscas.remote;

public class APIUtils {
    private APIUtils() {}
        public static final String API_URL = "https://e-cuscas.biz.id/api/";
    public static UserService getUserService(){
        return RetrofitClient.getClient(API_URL).create(UserService.class);
    }
}