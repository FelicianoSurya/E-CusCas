package id.ac.umn.e_cuscas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class AksesorisDetailOrder extends AppCompatActivity {

    private AppCompatButton OrderAksesoris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailorderaksesoris);

        OrderAksesoris= (AppCompatButton) findViewById(R.id.btnOrderAksesoris);
        OrderAksesoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToNextActivity = new Intent(getApplicationContext(), AksesorisDetailOrderTransaction.class);
                startActivity(goToNextActivity);
            }
        });

    }
}
