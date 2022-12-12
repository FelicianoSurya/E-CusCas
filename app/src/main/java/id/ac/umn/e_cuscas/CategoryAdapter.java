package id.ac.umn.e_cuscas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import id.ac.umn.e_cuscas.model.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CatViewHolder> {

    private final LinkedList<Category> mDaftarCategory;
    private LayoutInflater mInflater;

    private Context context;

    public CategoryAdapter(Context context, LinkedList<Category> daftarCat) {
        mInflater = LayoutInflater.from(context);
        mDaftarCategory = daftarCat;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryAdapter.CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.daftarcat_item, parent, false);
        return new CatViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CatViewHolder holder, int position) {
        Category mCurr = mDaftarCategory.get(position);
        int img = context.getResources().getIdentifier("@drawable/"+mCurr.getImage(), null, context.getPackageName());
        holder.ivCat.setImageResource(img);
        holder.tvCat.setText(mCurr.getName());
    }

    @Override
    public int getItemCount() {
        return mDaftarCategory.size();
    }

    public class CatViewHolder extends RecyclerView.ViewHolder {
        public final ImageView ivCat;
        public final TextView tvCat;
        final CategoryAdapter mAdapter;

        public CatViewHolder(@NonNull View itemView, CategoryAdapter adapter){
            super(itemView);
            ivCat = itemView.findViewById(R.id.ivCat);
            tvCat = itemView.findViewById(R.id.tvCat);
            this.mAdapter = adapter;
    }
}
}
