package id.ac.umn.e_cuscas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class OrderAccept extends AppCompatActivity {

    private Button Accept;
    String idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderaccepted);
        Intent i = getIntent();
        idUser = i.getStringExtra("id_user");

        Accept= (Button) findViewById(R.id.btnAccept);
        Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToNextActivity = new Intent(getApplicationContext(), Rating.class);
                goToNextActivity.putExtra("id_user", idUser);
                startActivity(goToNextActivity);
            }
        });
    }
}
