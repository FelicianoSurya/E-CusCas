package id.ac.umn.e_cuscas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Rating extends AppCompatActivity {

    private Button Rating;
    private String idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_page);

        Intent i = getIntent();
        idUser = i.getStringExtra("id_user");

        Rating= (Button) findViewById(R.id.btnRating);
        Rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToNextActivity = new Intent(getApplicationContext(), MainActivity.class);
                goToNextActivity.putExtra("id_user", idUser);
                startActivity(goToNextActivity);
            }
        });
    }
}