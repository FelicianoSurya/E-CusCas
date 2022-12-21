package id.ac.umn.e_cuscas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import id.ac.umn.e_cuscas.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    private int idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        idUser = Integer.parseInt(i.getStringExtra("id_user"));

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        Bundle a = new Bundle();
        a.putInt("idUser", idUser);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch(item.getItemId()){
                case R.id.home:
                    HomeFragment n = new HomeFragment();
                    n.setArguments(a);
                    replaceFragment(n);
                    break;
                case R.id.accessoris:
                    AccessorisFragment m = new AccessorisFragment();
                    m.setArguments(a);
                    replaceFragment(m);
                    break;
                case R.id.myorder:
                    MyorderFragment q = new MyorderFragment();
                    q.setArguments(a);
                    replaceFragment(q);
                    break;
                case R.id.chat:
                    replaceFragment(new ChatFragment());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}