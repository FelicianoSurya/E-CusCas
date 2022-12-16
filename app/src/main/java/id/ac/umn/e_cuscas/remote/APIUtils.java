package id.ac.umn.e_cuscas.remote;

public class APIUtils {
    private APIUtils() {}

        public static final String API_URL = "http://192.168.3.102:8000/api/";
    public static UserService getUserService(){
        return RetrofitClient.getClient(API_URL).create(UserService.class);
    }
}