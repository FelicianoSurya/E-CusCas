package id.ac.umn.e_cuscas;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.LinkedList;

import id.ac.umn.e_cuscas.model.HistoryorderCategory;
import id.ac.umn.e_cuscas.model.JenisBarang;
import id.ac.umn.e_cuscas.model.OrderProdPost;
import id.ac.umn.e_cuscas.model.Product;
import id.ac.umn.e_cuscas.remote.APIUtils;
import id.ac.umn.e_cuscas.remote.JSONResponse;
import id.ac.umn.e_cuscas.remote.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailOrder extends AppCompatActivity {

    private AppCompatButton Barang;

//    private Spinner sKategori;
    private LinkedList<JenisBarang> jenis;
    private LinkedList<JenisBarang.Jenis> jenis1;
    private ArrayList<String> jenisList, Aksesoris;
    private UserService userService;

    private String kategori, imgbase, idUser;
    private int id;
    private TextView tvJenis, tvKategoriIsi;
    private ImageView ivGambar;
    private Button btnGambarUpl, btnGambarCam;
    private Spinner sBahan, sWarna, sJenis, sAksesoris;
    private LinkedList<Product> products;

    private String[] Bahan, Warna;
    private ArrayAdapter<String> bAdapter, bWarna, bJenis, bAksesoris;

    private Bitmap bitmap;
    int SELECT_PICTURE = 3, CAMERA=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailorder_page);
        Intent i = getIntent();
        kategori = i.getStringExtra("jenis");
        id = Integer.parseInt(i.getStringExtra("id"));
        idUser = i.getStringExtra("idUser");
//        Kat = i.getStringArrayExtra("kategori");
        userService = APIUtils.getUserService();

//        sKategori = findViewById(R.id.sKategori);
//        Kats = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Kat);
//        sKategori.setAdapter(Kats);

        tvJenis = findViewById(R.id.tvJenis);
        ivGambar = findViewById(R.id.ivGambar);
        btnGambarUpl = findViewById(R.id.btnGambarUpl);
        btnGambarCam = findViewById(R.id.btnGambarCam);
        sJenis = findViewById(R.id.sJenis);
        sBahan = findViewById(R.id.sBahan);
        sWarna = findViewById(R.id.sWarna);
        sAksesoris = findViewById(R.id.sAksesoris);
        tvKategoriIsi = findViewById(R.id.tvKategoriIsi);
        bitmap = null;

        jenisList = new ArrayList<>();
        Aksesoris = new ArrayList<>();

        tvKategoriIsi.setText(kategori);
        tvJenis.setText("Jenis " + kategori);

        btnGambarUpl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ambilGambar(1);
            }
        });

        btnGambarCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ambilGambar(2);
            }
        });

        Warna = new String[]{
                "Transparant",
                "Hitam",
                "Putih",
        };
        bWarna = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Warna);
        sWarna.setAdapter(bWarna);

        Bahan = new String[]{
                "Alcantra",
                "Serat Karbon",
                "Kulit",
                "Polyurethane",
                "Kain",
                "Kayu"
        };
        bAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Bahan);
        sBahan.setAdapter(bAdapter);

        Call<JSONResponse> calling = userService.getProducts();
        calling.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse js = response.body();
                products = new LinkedList<>(Arrays.asList(js.getDataProduct()));
                for(int p=0; p<products.size(); p++) {
                    Aksesoris.add(products.get(p).getName());
                }
                bAksesoris = new ArrayAdapter<>(DetailOrder.this, android.R.layout.simple_spinner_dropdown_item, Aksesoris);
                sAksesoris.setAdapter(bAksesoris);
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.e("Error DetailOrder", t.getMessage());
            }
        });

        Call<JSONResponse> call = userService.getJenises(id);
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                if(response.isSuccessful()){
                    JSONResponse js = response.body();
                    jenis = new LinkedList<JenisBarang>(Arrays.asList(js.getDataJenisBarang()));
//                    String a = jenis.get(0).getJenis_barang().get(0).getName();
//                    Log.i("isi data", a);
                    for(int b=0; b<jenis.size(); b++){
                        jenis1 = new LinkedList<>(jenis.get(b).getJenis_barang());
                        for (int c=0; c<jenis1.size(); c++){
                            jenisList.add(jenis1.get(c).getName());
                        }
                    }
                    bJenis = new ArrayAdapter<>(DetailOrder.this, android.R.layout.simple_spinner_dropdown_item, jenisList);
                    sJenis.setAdapter(bJenis);
                } else {
                    try {
                        Log.e("error", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });


        Barang= (AppCompatButton) findViewById(R.id.tvBarang);
        Barang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToNextActivity = new Intent(getApplicationContext(), DetailOrderTransaction.class);
                goToNextActivity.putExtra("idUser", idUser);
                goToNextActivity.putExtra("idKat", String.valueOf(id));
                goToNextActivity.putExtra("Aksesoris", sAksesoris.getSelectedItem().toString());
                goToNextActivity.putExtra("jenis_barang", sJenis.getSelectedItem().toString());
                goToNextActivity.putExtra("warna", sWarna.getSelectedItem().toString());
                goToNextActivity.putExtra("bahan", sBahan.getSelectedItem().toString());
                goToNextActivity.putExtra("gambar", imgbase);
                startActivity(goToNextActivity);
            }
        });
    }

    void ambilGambar(int i){
        if(i == 1){
            Intent a = new Intent();
            a.setType("image/*");
            a.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(a, "Pilih Gambar"), SELECT_PICTURE);
        } else {
            Intent b = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(b, CAMERA);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_CANCELED){
            return;
        } else if(requestCode==SELECT_PICTURE && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            ivGambar.setImageBitmap(bitmap);
        } else if(requestCode==CAMERA && resultCode == Activity.RESULT_OK){
            bitmap = (Bitmap) data.getExtras().get("data");
            ivGambar.setImageBitmap(bitmap);
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, baos);
        byte[] inputData = baos.toByteArray();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            imgbase = Base64.getEncoder().encodeToString(inputData);
        }

    }

    boolean isNull(String string){
        return string == null || string.isEmpty();
    }
}
