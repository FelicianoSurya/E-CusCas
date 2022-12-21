package id.ac.umn.e_cuscas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Promo_page extends AppCompatActivity {

    private ImageButton btnDiskon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promo_detail);

        btnDiskon = (ImageButton) findViewById(R.id.btnDiskon);
        btnDiskon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Promo_page.this, Login_page.class);
                startActivity(intent);
            }
        });

    }
}
