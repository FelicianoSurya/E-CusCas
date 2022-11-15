package id.ac.umn.e_cuscas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class Login_page extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;
    private TextView warning, signUp;
    private String username, password;

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

        warning.setText("");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();

                if(!isNull(username) || !isNull(password)) {
                    if(username.equals("admin")) {
                        if(password.equals("admin")) {
                            Intent goToNextActivity = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(goToNextActivity);
                        } else {
                            warning.setTextColor(Color.RED);
                            warning.setText("Incorrect Password!");
                        }
                    } else {
                        warning.setTextColor(Color.RED);
                        warning.setText("Incorrect Username!");
                    }
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
}
