package id.ac.umn.e_cuscas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccessorisFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccessorisFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private UserService userService;
    private LinkedList<Product> products;
    private RecyclerView mRecyclerView, mRecyclerViewDP;
    private ProductAdapter mAdapter;
    private DetProdAdapter mAdapterDP;

    private LinkedList<ProductCheck> detProductUser;
    private LinkedList<ProductCheck.DetProduct> detProduct;

    private int idUser;

    private GridLayoutManager glm;

    public AccessorisFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccessorisFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccessorisFragment newInstance(String param1, String param2) {
        AccessorisFragment fragment = new AccessorisFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        idUser = this.getArguments().getInt("idUser");
        userService = APIUtils.getUserService();
        getProd();
        //getProdCheck();

        View view = inflater.inflate(R.layout.fragment_accessoris, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclesviewProd);
        glm = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(glm);

        mRecyclerViewDP = view.findViewById(R.id.recyclesviewDetProd);
        mRecyclerViewDP.setLayoutManager(new LinearLayoutManager(getActivity()));

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
                    mAdapter = new ProductAdapter(getActivity(), products);
                    mRecyclerView.setAdapter(mAdapter);

                    Call<JSONResponse> calling = userService.getProductCheck(2);
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
                                    mAdapterDP = new DetProdAdapter(getActivity(), detProduct);
                                    mRecyclerViewDP.setAdapter(mAdapterDP);
                                    break;
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