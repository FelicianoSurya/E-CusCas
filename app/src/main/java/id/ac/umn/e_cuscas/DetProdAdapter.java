package id.ac.umn.e_cuscas;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.LinkedList;

import id.ac.umn.e_cuscas.model.OrderProdPost;
import id.ac.umn.e_cuscas.model.ProductCheck;
import id.ac.umn.e_cuscas.remote.APIUtils;
import id.ac.umn.e_cuscas.remote.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetProdAdapter extends RecyclerView.Adapter<DetProdAdapter.DPViewHolder> {

    private final LinkedList<ProductCheck.DetProduct> mDaftarDetprod;
    private int midUser;
    private UserService userService;
    private LayoutInflater mInflater;
    private Context context;

    public DetProdAdapter(Context context, LinkedList<ProductCheck.DetProduct> daftarDetprod, int idUser){
        mInflater = LayoutInflater.from(context);
        mDaftarDetprod = daftarDetprod;
        midUser = idUser;
        this.context = context;
    }

    @NonNull
    @Override
    public DetProdAdapter.DPViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.detprod_item, parent, false);
        return new DPViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull DetProdAdapter.DPViewHolder holder, int position) {
        ProductCheck.DetProduct mCurr = mDaftarDetprod.get(position);
        holder.tvDetnama.setText(mCurr.getName());
        holder.tvDetharga.setText("Rp " + mCurr.getHarga());
        holder.tvDetjumlah.setText(String.valueOf(mCurr.getJumlah()));
    }

    @Override
    public int getItemCount() {
        return mDaftarDetprod.size();
    }

    public class DPViewHolder extends RecyclerView.ViewHolder{
        public final TextView tvDetnama, tvDetharga, tvDetjumlah;
        public final Button btnTambah, btnKurang, btnDelete;
        final DetProdAdapter mAdapter;

        public DPViewHolder(@NonNull View itemView, DetProdAdapter adapter){
            super(itemView);
            tvDetnama = itemView.findViewById(R.id.tvDetnama);
            tvDetharga = itemView.findViewById(R.id.tvDetharga);
            tvDetjumlah = itemView.findViewById(R.id.tvDetjumlah);
            btnTambah = itemView.findViewById(R.id.btnTambah);
            btnKurang = itemView.findViewById(R.id.btnKurang);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            userService = APIUtils.getUserService();
            this.mAdapter = adapter;

             btnTambah.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     int mPos = getLayoutPosition();
                     ProductCheck.DetProduct a = mDaftarDetprod.get(mPos);
                     Call<DetProdAdapter> call = userService.gantiJumlah(a.getId(), "+");
                     call.enqueue(new Callback<DetProdAdapter>() {
                         @Override
                         public void onResponse(Call<DetProdAdapter> call, Response<DetProdAdapter> response) {
//                             AppCompatActivity a = (AppCompatActivity) view.getContext();
//                             AccessorisFragment f = new AccessorisFragment();
//                             a.getSupportFragmentManager().beginTransaction().replace(androidx.fragment.R.id.fragment_container_view_tag, f).addToBackStack(null).commit();
                         }

                         @Override
                         public void onFailure(Call<DetProdAdapter> call, Throwable t) {

                         }
                     });
                     tvDetjumlah.setText("" + (Integer.parseInt(tvDetjumlah.getText().toString()) + 1));
                 }
             });

            btnKurang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int mPos = getLayoutPosition();
                    ProductCheck.DetProduct a = mDaftarDetprod.get(mPos);
                    if(a.getJumlah() != 1){
                        Call<DetProdAdapter> call = userService.gantiJumlah(a.getId(), "-");
                        call.enqueue(new Callback<DetProdAdapter>() {
                            @Override
                            public void onResponse(Call<DetProdAdapter> call, Response<DetProdAdapter> response) {
//                            notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<DetProdAdapter> call, Throwable t) {

                            }
                        });
                        tvDetjumlah.setText("" + (Integer.parseInt(tvDetjumlah.getText().toString()) - 1));
                    } else {
                        Call<OrderProdPost> call = userService.deleteProdPost(a.getId_barang(),midUser);
                        call.enqueue(new Callback<OrderProdPost>() {
                            @Override
                            public void onResponse(Call<OrderProdPost> call, Response<OrderProdPost> response) {
                                Toast.makeText(view.getContext(), "Item berhasil dihapus", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onFailure(Call<OrderProdPost> call, Throwable t) {
                                Log.e("Error DetProdAdapter", t.getMessage());
                            }
                        });
                    }
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int mPos = getLayoutPosition();
                    ProductCheck.DetProduct a = mDaftarDetprod.get(mPos);
                    Call<OrderProdPost> call = userService.deleteProdPost(a.getId_barang(),midUser);
                    call.enqueue(new Callback<OrderProdPost>() {
                        @Override
                        public void onResponse(Call<OrderProdPost> call, Response<OrderProdPost> response) {
                            Toast.makeText(view.getContext(), "Item berhasil dihapus", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<OrderProdPost> call, Throwable t) {
                            Log.e("Error DetProdAdapter", t.getMessage());
                        }
                    });
                }
            });
        }
    }
}
