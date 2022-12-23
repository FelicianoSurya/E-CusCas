package id.ac.umn.e_cuscas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.ArrayList;

import id.ac.umn.e_cuscas.model.HistoryorderCategory;
import id.ac.umn.e_cuscas.remote.APIUtils;
import id.ac.umn.e_cuscas.remote.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailOrderTransaction extends AppCompatActivity {

    private AppCompatButton Bayar;

    private int idUser, idKat;
    private String Aksesoris, jenis_barang, warna, bahan, gambar, keterangan;
    private float panjang, lebar, tinggi;

    private EditText etAlamat, etKeterangan;
    private TextView tvJenis, tvWarna, tvBahan, tvAksesoris, tvOngkir, tvTotal;
    private Spinner sMetodePembayaran;

    private float ongkir=10000.0f, voucher=0.0f, total=200000.0f;

    private UserService userService;
    private String[] metodePembayaran;
    private ArrayAdapter<String> bMetodePembayaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailtransaction_page);

        userService = APIUtils.getUserService();

        Intent a = getIntent();
        idUser = Integer.parseInt(a.getStringExtra("idUser"));
        idKat = Integer.parseInt(a.getStringExtra("idKat"));
        Aksesoris = a.getStringExtra("Aksesoris");
        jenis_barang = a.getStringExtra("jenis_barang");
        warna = a.getStringExtra("warna");
        bahan = a.getStringExtra("bahan");
        gambar = a.getStringExtra("gambar");

        etAlamat = findViewById(R.id.etAlamat);
        etKeterangan = findViewById(R.id.etKeterangan);

        tvJenis = findViewById(R.id.tvJenis);
        tvJenis.setText(jenis_barang);

        tvWarna = findViewById(R.id.tvWarna);
        tvWarna.setText(warna);

        tvBahan = findViewById(R.id.tvBahan);
        tvBahan.setText(bahan);

        tvAksesoris = findViewById(R.id.tvAksesoris);
        tvAksesoris.setText(Aksesoris);

        tvOngkir = findViewById(R.id.tvOngkir);
        tvOngkir.setText(String.valueOf(ongkir));

        tvTotal = findViewById(R.id.tvTotal);
        tvTotal.setText(String.valueOf(total));

        sMetodePembayaran = findViewById(R.id.sMetodePembayaran);
        metodePembayaran = new String[]{
                "BCA",
                "Gopay",
                "Cash On Delivery",
                "Bank lainnya"
        };
        bMetodePembayaran = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, metodePembayaran);
        sMetodePembayaran.setAdapter(bMetodePembayaran);

        Bayar= (AppCompatButton) findViewById(R.id.tvBayar);
        Bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String alam = String.valueOf(etAlamat.getText());
                String ket = String.valueOf(etKeterangan.getText());
                if(isNull(alam)){
                    Toast.makeText(DetailOrderTransaction.this, "Alamat harus diisi!", Toast.LENGTH_LONG).show();
                } else {
                    if(isNull(ket)){
                        ket = " ";
                    }
                    Call<HistoryorderCategory> call = userService.orderCat(idUser, sMetodePembayaran.getSelectedItem().toString(), idKat, Aksesoris, ongkir, jenis_barang, warna, bahan, alam, voucher, gambar, ket);
                    call.enqueue(new Callback<HistoryorderCategory>() {
                        @Override
                        public void onResponse(Call<HistoryorderCategory> call, Response<HistoryorderCategory> response) {
                            if(response.isSuccessful()){
                                Intent goToNextActivity = new Intent(getApplicationContext(), OrderAccept.class);
                                goToNextActivity.putExtra("id_user", String.valueOf(idUser));
                                startActivity(goToNextActivity);
                            } else {
                                Log.e("Error response", response.errorBody().toString());
                            }
                        }


                        @Override
                        public void onFailure(Call<HistoryorderCategory> call, Throwable t) {
                            Log.e("Error DetailOrderTransaction", t.getMessage());
                        }
                    });

                }

            }
        });

    }

    boolean isNull(String string){
        return string == null || string.isEmpty();
    }
}
