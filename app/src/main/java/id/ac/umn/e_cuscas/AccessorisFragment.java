package id.ac.umn.e_cuscas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import id.ac.umn.e_cuscas.model.Product;
import id.ac.umn.e_cuscas.model.ProductCheck;
import id.ac.umn.e_cuscas.remote.APIUtils;
import id.ac.umn.e_cuscas.remote.JSONResponse;
import id.ac.umn.e_cuscas.remote.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link AccessorisFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class AccessorisFragment extends Fragment {

    private UserService userService;
    private LinkedList<Product> products;
    private RecyclerView mRecyclerView, mRecyclerViewDP;
    private ProductAdapter mAdapter;
    private DetProdAdapter mAdapterDP;
    private ArrayList<String> detProdNama;
    private ArrayList<Integer> detProdHarga, detProdJumlah;
    private LinkedList<ProductCheck> detProductUser;
    private LinkedList<ProductCheck.DetProduct> detProduct;
    private Button btnDetProd;
    private int idUser;
    private GridLayoutManager glm;

    public AccessorisFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        detProdNama = new ArrayList<>();
        detProdHarga = new ArrayList<>();
        detProdJumlah = new ArrayList<>();

        idUser = this.getArguments().getInt("idUser");
        userService = APIUtils.getUserService();
        getProd();
        //getProdCheck();

        View view = inflater.inflate(R.layout.fragment_accessoris, container, false);

        mRecyclerView = view.findViewById(R.id.recyclesviewProd);
        glm = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(glm);

        mRecyclerViewDP = view.findViewById(R.id.recyclesviewDetProd);
        mRecyclerViewDP.setLayoutManager(new LinearLayoutManager(getActivity()));

        btnDetProd = view.findViewById(R.id.btnDetProd);

        return view;
    }

    private void getProd() {
        Call<JSONResponse> call = userService.getProducts();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                if (response.body() != null) {
                    JSONResponse js = response.body();
                    products = new LinkedList<>(Arrays.asList(js.getDataProduct()));
                    mAdapter = new ProductAdapter(getActivity(), products, idUser);
                    mRecyclerView.setAdapter(mAdapter);

                    Call<JSONResponse> calling = userService.getProductCheck(idUser);
                    calling.enqueue(new Callback<JSONResponse>() {
                        @Override
                        public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                            if (response.isSuccessful()) {
                                JSONResponse js = response.body();
                                detProductUser = new LinkedList<>(Arrays.asList(js.getDataDetProduct()));
                                for (int a = 0; a < detProductUser.size(); a++) {
                                    detProduct = new LinkedList<>(detProductUser.get(a).getDetail_barang());
                                    for (int p = 0; p < detProduct.size(); p++) {
                                        for (int l = 0; l < products.size(); l++) {
                                            if (detProduct.get(p).getId_barang() == products.get(l).getId()) {
                                                detProduct.get(p).setName(products.get(l).getName());
                                            }
                                        }
                                    }
                                    mAdapterDP = new DetProdAdapter(getActivity(), detProduct, idUser);
                                    mRecyclerViewDP.setAdapter(mAdapterDP);

                                    btnDetProd.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent a = new Intent(getActivity(), AksesorisDetailOrderTransaction.class);
                                            a.putExtra("idUser", String.valueOf(idUser));
                                            for(int f=0; f<detProduct.size(); f++){
                                                detProdNama.add(detProduct.get(f).getName());
                                                detProdHarga.add(detProduct.get(f).getHarga());
                                                detProdJumlah.add(detProduct.get(f).getJumlah());
                                            }
                                            a.putStringArrayListExtra("detProdNama", detProdNama);
                                            a.putIntegerArrayListExtra("detProdHarga", detProdHarga);
                                            a.putIntegerArrayListExtra("detProdJumlah", detProdJumlah);
                                            startActivity(a);
                                        }
                                    });
                                }
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


                }
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.e("Error AccessorisFragment", t.getMessage());
            }
        });
    }
}

//    private void getProdCheck(){
//        Call<JSONResponse> call = userService.getProductCheck(2);
//        call.enqueue(new Callback<JSONResponse>() {
//            @Override
//            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
//                if(response.isSuccessful()){
//                    JSONResponse js = response.body();
//                    detProductUser = new LinkedList<>(Arrays.asList(js.getDataDetProduct()));
//                    for(int a=0; a<detProductUser.size(); a++){
//                        detProduct = new LinkedList<>(detProductUser.get(a).getDetail_barang());
//                        mAdapterDP = new DetProdAdapter(getActivity(), detProduct);
//                        mRecyclerViewDP.setAdapter(mAdapterDP);
//                        break;
//                    }
//                } else {
//                    try {
//                        Log.e("error", response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<JSONResponse> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });