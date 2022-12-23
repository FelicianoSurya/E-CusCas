package id.ac.umn.e_cuscas;

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
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.Arrays;
import java.util.LinkedList;

import id.ac.umn.e_cuscas.model.Category;
import id.ac.umn.e_cuscas.remote.APIUtils;
import id.ac.umn.e_cuscas.remote.JSONResponse;
import id.ac.umn.e_cuscas.remote.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private int idUser;
    private UserService userService;
    private LinkedList<Category> categories;
    private RecyclerView mRecyclerView;
    private CategoryAdapter mAdapter;
    private ImageButton btnPromo;
    private GridLayoutManager glm;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        idUser = this.getArguments().getInt("idUser");

        userService = APIUtils.getUserService();
        getCat();

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclesviewCat);
        glm = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(glm);

        ImageButton Promo = (ImageButton) view.findViewById(R.id.tvPromo);

        Promo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Promo_page.class);
                startActivity(intent);
            }
        });

        /*LinearLayout Laptop = (LinearLayout) view.findViewById(R.id.gridLayout);
        Laptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DetailOrder.class);
                startActivity(intent);
            }
        });*/

        return view;
    }

    private void getCat(){
        Call<JSONResponse> call = userService.getCategories();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                if(response.body() != null) {
                    JSONResponse js = response.body();
                    categories = new LinkedList<>(Arrays.asList(js.getDataCategory()));
                    mAdapter = new CategoryAdapter(getActivity(), categories, idUser);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.e("Error HomeFragment", t.getMessage());
            }
        });
    }
}