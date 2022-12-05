package id.ac.umn.e_cuscas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import id.ac.umn.e_cuscas.model.User;
import id.ac.umn.e_cuscas.remote.APIUtils;
import id.ac.umn.e_cuscas.remote.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup_page extends AppCompatActivity {

    private EditText etName, etUsername, etPassword, etPhone, etEmail, etAddress;
    private Button btnRegister;
    private TextView tvLogin, tvWarning;
    private String name, username, password, phone, email, address;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);

        etName = (EditText) findViewById(R.id.etName);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etAddress = (EditText) findViewById(R.id.etAddress);

        btnRegister = (Button) findViewById(R.id.btnRegister);

        tvLogin = (TextView) findViewById(R.id.tvLogin);
        tvWarning = (TextView) findViewById(R.id.tvWarning);
        tvWarning.setText("");

        userService = APIUtils.getUserService();

        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                name = etName.getText().toString();
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();
                phone = etPhone.getText().toString();
                email = etEmail.getText().toString();
                address = etAddress.getText().toString();

                if(!isNull(name) || !isNull(username) || !isNull(password) || !isNull(phone) || !isNull(email) || !isNull(address)) {
                    User u = new User();
                    u.setName(name);
                    u.setUsername(username);
                    u.setPassword(password);
                    u.setPhone(phone);
                    u.setEmail(email);
                    u.setAddress(address);

                    addUser(u);
                } else {
                    tvWarning.setText("Please fill out all field!");
                }
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent ans = new Intent();
                setResult(RESULT_CANCELED, ans);
                finish();
            }
        });
    }

    boolean isNull(String string){
        return string == null || string.isEmpty();
    }

    public void addUser(User u){
        Call<User> call = userService.addUser(u);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Intent ans = new Intent();
                ans.putExtra("Username", username);
                setResult(RESULT_OK, ans);
                finish();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Error Register: ", t.getMessage());
            }
        });
    }
}

