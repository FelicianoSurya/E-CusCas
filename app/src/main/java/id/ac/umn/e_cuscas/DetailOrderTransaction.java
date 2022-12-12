package id.ac.umn.e_cuscas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class DetailOrderTransaction extends AppCompatActivity {

    private AppCompatButton Bayar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailtransaction_page);

        Bayar= (AppCompatButton) findViewById(R.id.tvBayar);
        Bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToNextActivity = new Intent(getApplicationContext(), OrderAccept.class);
                startActivity(goToNextActivity);
            }
        });

    }
}
