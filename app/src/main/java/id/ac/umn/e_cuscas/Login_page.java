package id.ac.umn.e_cuscas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import id.ac.umn.e_cuscas.model.User;
import id.ac.umn.e_cuscas.remote.APIUtils;
import id.ac.umn.e_cuscas.remote.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_page extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;
    private TextView warning, signUp;
    private String username, password;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        etUsername = (EditText) findViewById(R.id.etName);
        etPassword = (EditText) findViewById(R.id.etUsername);
        btnLogin = (Button) findViewById(R.id.btnRegister);
        warning = (TextView) findViewById(R.id.tvWarning);
        signUp = (TextView) findViewById(R.id.tvLogin);
        userService = APIUtils.getUserService();

        warning.setText("");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();

                if(!isNull(username) || !isNull(password)) {
                    reqLogin(username, password);
//                    if(username.equals("admin")) {
//                        if(password.equals("admin")) {
//                            Intent goToNextActivity = new Intent(getApplicationContext(), MainActivity.class);
//                            startActivity(goToNextActivity);
//                        } else {
//                            warning.setTextColor(Color.RED);
//                            warning.setText("Incorrect Password!");
//                        }
//                    } else {
//                        warning.setTextColor(Color.RED);
//                        warning.setText("Incorrect Username!");
//                    }
                } else {
                    warning.setTextColor(Color.RED);
                    warning.setText("Username and Password cannot be empty!");
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toSignUp = new Intent(Login_page.this, Signup_page.class);
                startActivityForResult(toSignUp, 1);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                String ans = data.getStringExtra("Username");
                etUsername.setText(ans);
                warning.setTextColor(Color.GREEN);
                warning.setText("Registration Complete!\n Please enter your Password");
            }
        }
    }

    boolean isNull(String string){
        return string == null || string.isEmpty();
    }

    private void reqLogin(String username, String password){
        Call<User> call = userService.loginUser(username, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Intent goToNextActivity = new Intent(getApplicationContext(), MainActivity.class);
                    User a = response.body();
                    String idUser = String.valueOf(a.getId());
                    goToNextActivity.putExtra("id_user", idUser);
                    startActivity(goToNextActivity);
                } else {
                    warning.setTextColor(Color.RED);
                    warning.setText("Wrong Username or Password!");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                warning.setTextColor(Color.RED);
                warning.setText("Error System");
                Log.e("Error Login", t.getMessage());
            }
        });
    }
}
