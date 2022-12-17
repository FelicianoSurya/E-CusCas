package id.ac.umn.e_cuscas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.LinkedList;

import id.ac.umn.e_cuscas.model.ProductCheck;

public class DetProdAdapter extends RecyclerView.Adapter<DetProdAdapter.DPViewHolder> {

    private final LinkedList<ProductCheck.DetProduct> mDaftarDetprod;
    private LayoutInflater mInflater;
    private Context context;

    public DetProdAdapter(Context context, LinkedList<ProductCheck.DetProduct> daftarDetprod){
        mInflater = LayoutInflater.from(context);
        mDaftarDetprod = daftarDetprod;
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
        //holder.tvDetnama.setText("");
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
            this.mAdapter = adapter;
        }
    }
}
