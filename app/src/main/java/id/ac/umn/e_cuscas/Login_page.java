package id.ac.umn.e_cuscas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login_page extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;
    private TextView warning, signUp;
    private String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                            warning.setText("Incorrect Password!");
                        }
                    } else {
                        warning.setText("Incorrect Username!");
                    }
                } else {
                    warning.setText("Username and Password cannot be empty!");
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toSignUp = new Intent(Login_page.this, Signup_page.class);
                startActivity(toSignUp);
            }
        });
    }

    boolean isNull(String string){
        return string == null || string.isEmpty();
    }
}
