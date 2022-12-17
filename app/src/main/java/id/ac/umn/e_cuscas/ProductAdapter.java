package id.ac.umn.e_cuscas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.LinkedList;

import id.ac.umn.e_cuscas.model.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProdViewHolder> {

    private final LinkedList<Product> mDaftarProduct;
    private LayoutInflater mInflater;

    private Context context;

    public ProductAdapter(Context context, LinkedList<Product> daftarProd){
        mInflater = LayoutInflater.from(context);
        mDaftarProduct = daftarProd;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductAdapter.ProdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.daftarprod_item, parent, false);
        return new ProdViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProdViewHolder holder, int position) {
        Product mCurr = mDaftarProduct.get(position);
        String url = mCurr.getImage();
        Picasso.get().load(url).into(holder.ivProd);
        holder.tvNamaprod.setText(mCurr.getName());
        holder.tvHargaprod.setText("Rp " + mCurr.getHarga());
        holder.tvStokprod.setText("Stock: " + mCurr.getJumlah());
    }

    @Override
    public int getItemCount() {
        return mDaftarProduct.size();
    }

    public class ProdViewHolder extends RecyclerView.ViewHolder{
        public final ImageView ivProd;
        public final TextView tvNamaprod, tvHargaprod, tvStokprod;
        final ProductAdapter mAdapter;

        public ProdViewHolder(@NonNull View itemView, ProductAdapter adapter) {
            super(itemView);
            ivProd = itemView.findViewById(R.id.ivProd);
            tvNamaprod = itemView.findViewById(R.id.tvNamaprod);
            tvHargaprod = itemView.findViewById(R.id.tvHargaprod);
            tvStokprod = itemView.findViewById(R.id.tvStokProd);
            this.mAdapter = adapter;
        }
    }
}
