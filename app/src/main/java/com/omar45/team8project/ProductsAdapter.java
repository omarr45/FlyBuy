package com.omar45.team8project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.ContentValues.TAG;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

    private List<Product> productsList = new ArrayList<>();
    private Context context;
    private ProductClickListener productClickListener;

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false));
    }

    public ProductsAdapter(Context context) {
        this.context = context;
    }

    public ProductsAdapter(Context context, ProductClickListener p) {
        this.context = context;
        this.productClickListener = p;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        holder.p_name.setText(productsList.get(position).getName());
        holder.p_price.setText(String.format("%s EGP", productsList.get(position).getPrice()));
        Glide.with(context).load(productsList.get(position).getImg1().toString()).into(holder.p_img1);


    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public void setList(List<Product> productsList) {
        this.productsList=productsList;
        notifyDataSetChanged();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView p_name,p_price;
        ImageView p_img1;
        @SuppressLint("ResourceType")
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            p_name=itemView.findViewById(R.id.product_Name);
            p_price=itemView.findViewById(R.id.product_Price);
           p_img1=itemView.findViewById(R.id.product_Image);





            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Product product = productsList.get(getAdapterPosition());
                    productClickListener.onClickProduct(product);
                }
            });
        }
    }
}
