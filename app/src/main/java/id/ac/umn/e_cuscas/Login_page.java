package id.ac.umn.e_cuscas;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login_page extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;
    private TextView warning;
    private String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        warning = (TextView) findViewById(R.id.warning);

        warning.setText("");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();

                if(!isNull(username)) {
                    if(!isNull(password)) {
                        if(username.equals("admin")) {
                            if(password.equals("admin")) {
                                //move activity
                            } else {
                                warning.setText("Password salah.");
                            }
                        } else {
                            warning.setText("Username salah.");
                        }
                    } else {
                        warning.setText("Password harap diisi.");
                    }
                } else {
                    warning.setText("Username harap diisi.");
                }
            }
        });
    }

    boolean isNull(String string){
        return string == null || string.isEmpty();
    }
}
