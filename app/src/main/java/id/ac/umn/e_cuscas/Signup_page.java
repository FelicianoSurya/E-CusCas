package id.ac.umn.e_cuscas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Signup_page extends AppCompatActivity {

    private EditText etName, etUsername, etPassword, etPhone, etEmail, etAddress;
    private Button btnRegister;
    private TextView tvLogin, tvWarning;
    private String name, username, password, phone, email, address;

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
                    //input to database

                } else {
                    tvWarning.setText("Please fill out all field!");
                }
            }
        });
    }

    boolean isNull(String string){
        return string == null || string.isEmpty();
    }
}
