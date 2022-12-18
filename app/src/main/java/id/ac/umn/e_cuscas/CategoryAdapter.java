package id.ac.umn.e_cuscas;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.LinkedList;

import id.ac.umn.e_cuscas.model.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CatViewHolder> {

    private final LinkedList<Category> mDaftarCategory;
    private String midUser;
//    private String Kat[];
    private LayoutInflater mInflater;

    private Context context;

    public CategoryAdapter(Context context, LinkedList<Category> daftarCat, int idUser) {
        mInflater = LayoutInflater.from(context);
        midUser = String.valueOf(idUser);
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
        String url = mCurr.getImage();
        Picasso.get().load(url).into(holder.ivCat);
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

        public CatViewHolder(@NonNull View itemView, CategoryAdapter adapter) {
            super(itemView);
            ivCat = itemView.findViewById(R.id.ivCat);
            tvCat = itemView.findViewById(R.id.tvCat);
//            Kat = new String[mDaftarCategory.size()];
            this.mAdapter = adapter;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int mPos = getLayoutPosition();
                    Category a = mDaftarCategory.get(mPos);
                    String b = a.getName();
                    String c = String.valueOf(a.getId());
                    Intent intent = new Intent(context, DetailOrder.class);
//                    for (int i=0; i<mDaftarCategory.size(); i++){
//                        Kat[i] = mDaftarCategory.get(i).getName();
//                    }
                    intent.putExtra("jenis", b);
                    intent.putExtra("id", c);
                    intent.putExtra("idUser", midUser);
//                    intent.putExtra("kategori", Kat);
                    context.startActivity(intent);
                }
            });
        }
    }
}
