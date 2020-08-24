package com.omar45.team8project;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

    private ArrayList<Product> productsList = new ArrayList<>();

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.prodName.setText(productsList.get(position).getName());
        holder.prodPrice.setText(productsList.get(position).getPrice());
        Picasso.get().load(productsList.get(position).getImg1()).placeholder(R.drawable.logo_white)
                .into(holder.prodImg);
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public void setList(ArrayList<Product> productsList) {
        this.productsList=productsList;
        notifyDataSetChanged();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView prodName, prodPrice;
        ImageView prodImg;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            prodName    = itemView.findViewById(R.id.product_Name);
            prodPrice   = itemView.findViewById(R.id.product_Price);
            prodImg     = itemView.findViewById(R.id.product_Image);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                }
//            });
        }
    }
}
