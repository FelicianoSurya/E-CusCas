package id.ac.umn.e_cuscas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import id.ac.umn.e_cuscas.model.ProdOrderHist;

public class ProdOrderHistAdapter extends RecyclerView.Adapter<ProdOrderHistAdapter.ProdOrderViewHolder> {

    private final LinkedList<ProdOrderHist> mDaftarProdOrderHist;
    private LayoutInflater mInflater;
    private Context context;

    public ProdOrderHistAdapter(Context context, LinkedList<ProdOrderHist> daftarProdOrderHist){
        mInflater = LayoutInflater.from(context);
        mDaftarProdOrderHist = daftarProdOrderHist;
        this.context = context;
    }

    @NonNull
    @Override
    public ProdOrderHistAdapter.ProdOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.daftarcatorder_item, parent, false);
        return new ProdOrderViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdOrderHistAdapter.ProdOrderViewHolder holder, int position) {
        ProdOrderHist mCurr = mDaftarProdOrderHist.get(position);
        holder.ivGambarCatHist.setImageResource(R.drawable.logo);
        holder.tvJenisCatHist.setText(mCurr.getJenis_barang());
        holder.tvAlamatCatHist.setText(mCurr.getAddress());
        holder.tvHargaCatHist.setText(mCurr.getHarga_total());
        holder.tvStatusCatHist.setText(mCurr.getStatus());
    }

    @Override
    public int getItemCount() {
        return mDaftarProdOrderHist.size();
    }

    public class ProdOrderViewHolder extends RecyclerView.ViewHolder{

        public final ImageView ivGambarCatHist;
        public final TextView tvJenisCatHist, tvAlamatCatHist, tvHargaCatHist, tvStatusCatHist;
        final ProdOrderHistAdapter mAdapter;

        public ProdOrderViewHolder(@NonNull View itemView, ProdOrderHistAdapter adapter){
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
