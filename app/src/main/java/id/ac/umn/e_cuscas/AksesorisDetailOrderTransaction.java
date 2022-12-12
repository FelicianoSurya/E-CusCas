package id.ac.umn.e_cuscas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class AksesorisDetailOrderTransaction extends AppCompatActivity {

    private Button BayarAksesoris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aksesorisdetailtransaction_page);

        BayarAksesoris= (AppCompatButton) findViewById(R.id.btnBayarAksesoris);
        BayarAksesoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToNextActivity = new Intent(getApplicationContext(), OrderAccept.class);
                startActivity(goToNextActivity);
            }
        });

    }
}
