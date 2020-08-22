package com.omar45.team8project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>  {
    private Context context;
    private List<Product> item_list;
    private int images[];

    public CartAdapter(Context context, List<Product> item_list, int[] images) {
        this.context = context;
        this.item_list = item_list;
        this.images = images;
    }


    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
       View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_cart_item,viewGroup,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product item=item_list.get(position);
        int image_id=images[position];
        holder.item_image.setImageResource(image_id);


    }

    @Override
    public int getItemCount() {
        return item_list.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        TextView item_name;
        TextView item_price;
        TextView item_quantity;
        ImageView item_image;

        public CartViewHolder(View itemView) {
            super(itemView);
            item_name=itemView.findViewById(R.id.item_name);
            item_quantity=itemView.findViewById(R.id.quantity);
            item_price=itemView.findViewById(R.id.productPrice);
            item_image=itemView.findViewById(R.id.item_image);
        }
    }
}
