package id.ac.umn.e_cuscas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.LinkedList;

import id.ac.umn.e_cuscas.model.CatOrderHist;
import id.ac.umn.e_cuscas.model.ProdOrderHist;
import id.ac.umn.e_cuscas.remote.APIUtils;
import id.ac.umn.e_cuscas.remote.JSONResponse;
import id.ac.umn.e_cuscas.remote.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyorderFragment extends Fragment {

    private int idUser;
    private LinkedList<CatOrderHist> catorders;
    private LinkedList<ProdOrderHist> prodorders;
    private CatOrderHistAdapter mAdapterCOHA;
    private ProdOrderHistAdapter mAdapterPOHA;
    private RecyclerView mRecyclerViewCOHA, mRecyclerViewPOHA;
    private UserService userService;
    private TextView tvEmpty;

    public MyorderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myorder, container, false);

        idUser = this.getArguments().getInt("idUser");
        userService = APIUtils.getUserService();

        getCatOrd();
        mRecyclerViewCOHA = view.findViewById(R.id.recyclesviewCatHist);
        mRecyclerViewCOHA.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerViewPOHA = view.findViewById(R.id.recyclesviewProdHist);
        mRecyclerViewPOHA.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private void getCatOrd() {
        Call<JSONResponse> call = userService.getDataCatOrderHist(idUser);
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                if(response.body() != null){
                    JSONResponse js = response.body();
                    catorders = new LinkedList<>(Arrays.asList(js.getDataCatOrderHist()));
                    mAdapterCOHA = new CatOrderHistAdapter(getActivity(), catorders);
                    mRecyclerViewCOHA.setAdapter(mAdapterCOHA);

                    Call<JSONResponse> calling = userService.getDataProdOrderHist(idUser);
                    calling.enqueue(new Callback<JSONResponse>() {
                        @Override
                        public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                            if(response.body() != null){
                                JSONResponse js = response.body();
                                prodorders = new LinkedList<>(Arrays.asList(js.getDataProdOrderHist()));
                                mAdapterPOHA = new ProdOrderHistAdapter(getActivity(), prodorders);
                                mRecyclerViewPOHA.setAdapter(mAdapterPOHA);
                            }
                        }

                        @Override
                        public void onFailure(Call<JSONResponse> call, Throwable t) {
                            Log.e("Error MyorderFragment Prod", t.getMessage());
                        }
                    });

                } else {
                    tvEmpty.setText("Belum ada pesanan!");
                }
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.e("Error MyorderFragment Cat", t.getMessage());
            }
        });
    }
}