package id.ac.umn.e_cuscas;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.LinkedList;

import id.ac.umn.e_cuscas.model.CatOrderHist;

public class CatOrderHistAdapter extends RecyclerView.Adapter<CatOrderHistAdapter.CatOrderViewHolder> {

    private final LinkedList<CatOrderHist> mDaftarCatOrderHist;
    private LayoutInflater mInflater;
    private Context context;

    public CatOrderHistAdapter(Context context, LinkedList<CatOrderHist> daftarCatOrderHist){
        mInflater = LayoutInflater.from(context);
        mDaftarCatOrderHist = daftarCatOrderHist;
        this.context = context;
    }

    @NonNull
    @Override
    public CatOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.daftarcatorder_item, parent, false);
        return new CatOrderViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CatOrderViewHolder holder, int position) {
        CatOrderHist mCurr = mDaftarCatOrderHist.get(position);
        String url = mCurr.getGambar();
        Picasso.get().load(url).into(holder.ivGambarCatHist);
        holder.tvJenisCatHist.setText(mCurr.getJenis_barang());
        holder.tvAlamatCatHist.setText(mCurr.getAddress());
        holder.tvHargaCatHist.setText(mCurr.getTotal_harga());
        holder.tvStatusCatHist.setText(mCurr.getStatus());
    }

    @Override
    public int getItemCount() {
        return mDaftarCatOrderHist.size();
    }

    public class CatOrderViewHolder extends RecyclerView.ViewHolder{

        public final ImageView ivGambarCatHist;
        public final TextView tvJenisCatHist, tvAlamatCatHist, tvHargaCatHist, tvStatusCatHist;
        final CatOrderHistAdapter mAdapter;

        public CatOrderViewHolder(@NonNull View itemView, CatOrderHistAdapter adapter){
            super(itemView);
            ivGambarCatHist = itemView.findViewById(R.id.ivGambarCatHist);
            tvJenisCatHist = itemView.findViewById(R.id.tvJenisCatHist);
            tvAlamatCatHist = itemView.findViewById(R.id.tvAlamatCatHist);
            tvHargaCatHist = itemView.findViewById(R.id.tvHargaCatHist);
            tvStatusCatHist = itemView.findViewById(R.id.tvStatusCatHist);
            this.mAdapter = adapter;
        }
    }
}
