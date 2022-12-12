package id.ac.umn.e_cuscas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class OnProgress extends AppCompatActivity {

    private AppCompatButton History;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onprogress_page);

        History= (AppCompatButton) findViewById(R.id.btnHistory);
        History= (AppCompatButton) findViewById(R.id.btnHistory);

        History.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(String.valueOf(MyorderFragment.class));
                startActivity(intent);
            }
        });
    }
}
