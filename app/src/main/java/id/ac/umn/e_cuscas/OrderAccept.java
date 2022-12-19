package id.ac.umn.e_cuscas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import id.ac.umn.e_cuscas.model.User;

public class OrderAccept extends AppCompatActivity {

    private Button Accept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderaccepted);

        Accept= (Button) findViewById(R.id.btnAccept);
        Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToNextActivity = new Intent(getApplicationContext(), Login_page.class);
                startActivity(goToNextActivity);
            }
        });
    }
}
