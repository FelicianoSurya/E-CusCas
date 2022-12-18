package id.ac.umn.e_cuscas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import id.ac.umn.e_cuscas.model.OrderProd;
import id.ac.umn.e_cuscas.remote.APIUtils;
import id.ac.umn.e_cuscas.remote.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AksesorisDetailOrderTransaction extends AppCompatActivity {

    public UserService userService;

    private ArrayList<String> detProdName;
    private ArrayList<Integer> detProdHarga, detProdJumlah;
    private ArrayAdapter<String> bMetodePem;
    private RecyclerView mRecyclerView;
    private DetProdOrderAdapter mAdapter;
    private String[] metodePem;

    private Button BayarAksesoris;
    private Spinner sMetodePem;
    private TextView tvAksesorisTotal;
    private EditText etAksesorisAddress;

    private int total=0, idUser;
    public float ongkir=10000, voucher=0.0f;
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aksesorisdetailtransaction_page);

        userService = APIUtils.getUserService();

        sMetodePem = findViewById(R.id.sMetodePem);
        mRecyclerView = findViewById(R.id.recyclesviewDetProdOrder);
        tvAksesorisTotal = findViewById(R.id.tvAksesorisTotal);
        etAksesorisAddress = findViewById(R.id.etAksesorisAddress);

        Intent a = getIntent();
        detProdName = new ArrayList<>(a.getStringArrayListExtra("detProdNama"));
        detProdHarga = new ArrayList<>(a.getIntegerArrayListExtra("detProdHarga"));
        detProdJumlah = new ArrayList<>(a.getIntegerArrayListExtra("detProdJumlah"));
        idUser = Integer.parseInt(a.getStringExtra("idUser"));

        mAdapter = new DetProdOrderAdapter(this, detProdName, detProdHarga, detProdJumlah);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        metodePem = new String[]{
                "BCA",
                "Gopay",
                "Cash On Delivery",
                "Bank lainnya"
        };
        bMetodePem = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, metodePem);
        sMetodePem.setAdapter(bMetodePem);

        for(int i=0; i<detProdHarga.size(); i++){
            total += detProdHarga.get(i);
        }
        total += ongkir;
        tvAksesorisTotal.setText("Rp " + total);

        BayarAksesoris= findViewById(R.id.btnBayarAksesoris);
        BayarAksesoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                address = String.valueOf(etAksesorisAddress.getText());
                //Log.i("isi data", idUser + ", " + sMetodePem.getSelectedItem().toString() + ", " + ongkir + ", " + voucher + ", " + address);
                if(!isNull(address)){
                    Call<OrderProd> call = userService.orderCategory(idUser, sMetodePem.getSelectedItem().toString(), ongkir, voucher, address);
                    call.enqueue(new Callback<OrderProd>() {
                        @Override
                        public void onResponse(Call<OrderProd> call, Response<OrderProd> response) {
                            if(response.isSuccessful()){
                                Intent goToNextActivity = new Intent(AksesorisDetailOrderTransaction.this, OrderAccept.class);
                                startActivity(goToNextActivity);
                            } else {
                                Log.e("Error database", response.errorBody().toString());
                            }
                        }

                        @Override
                        public void onFailure(Call<OrderProd> call, Throwable t) {
                            Log.e("Error AksesorisDetailOrderTransaction", t.getMessage());
                        }
                    });
                } else {
                    Toast.makeText(view.getContext(), "Alamat harus di isi!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    boolean isNull(String string){
        return string == null || string.isEmpty();
    }
}
