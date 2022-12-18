package id.ac.umn.e_cuscas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DetProdOrderAdapter extends RecyclerView.Adapter<DetProdOrderAdapter.DetProdOrderViewHolder> {

    private final ArrayList<String> mDaftarOrderProdNama;
    private final ArrayList<Integer> mDaftarOrderProdHarga, mDaftarOrderProdJumlah;
    private LayoutInflater mInflater;
    private Context context;

    public DetProdOrderAdapter(Context context, ArrayList<String> DaftarOrderProdNama, ArrayList<Integer>  DaftarOrderProdHarga, ArrayList<Integer> DaftarOrderProdJumlah){
        mInflater = LayoutInflater.from(context);
        mDaftarOrderProdNama = DaftarOrderProdNama;
        mDaftarOrderProdHarga = DaftarOrderProdHarga;
        mDaftarOrderProdJumlah = DaftarOrderProdJumlah;
        this.context = context;
    }

    @NonNull
    @Override
    public DetProdOrderAdapter.DetProdOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.detprodorder_item, parent, false);
        return new DetProdOrderViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull DetProdOrderAdapter.DetProdOrderViewHolder holder, int position) {
        holder.tvDetOrderName.setText(mDaftarOrderProdNama.get(position) + "(" + mDaftarOrderProdJumlah.get(position) + ")");
        holder.tvDetOrderHarga.setText("Rp " + mDaftarOrderProdHarga.get(position));
    }

    @Override
    public int getItemCount() {
        return mDaftarOrderProdNama.size();
    }

    public class DetProdOrderViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvDetOrderName, tvDetOrderHarga;
        final DetProdOrderAdapter mAdapter;

        public DetProdOrderViewHolder(@NonNull View itemView, DetProdOrderAdapter adapter){
            super(itemView);
            tvDetOrderName = itemView.findViewById(R.id.tvDetOrderName);
            tvDetOrderHarga = itemView.findViewById(R.id.tvDetOrderHarga);
            this.mAdapter = adapter;
        }
    }
}
