package com.omar45.team8project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.ContentValues.TAG;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

    private List<Product> productsList = new ArrayList<>();
    Context context;

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false));
    }

    public ProductsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        //holder.prodName.setText(productsList.get(position).getName());
        //holder.prodPrice.setText(productsList.get(position).getPrice());
        //Picasso.get().load(productsList.get(position).getImg1()).placeholder(R.drawable.logo_white)
         //       .into(holder.prodImg);
        Log.e(TAG, "onBindViewHolder: "+productsList.get(position).getName());
        Log.e(TAG, "onBindViewHolder: "+productsList.get(position).getDescription());
        Log.e(TAG, "onBindViewHolder: "+productsList.get(position).getSpecifications());
        Log.e(TAG, "onBindViewHolder: "+productsList.get(position).getPrice());
        Log.e(TAG, "onBindViewHolder: "+productsList.get(position).getC_id());
        Log.e(TAG, "onBindViewHolder: "+productsList.get(position).getImg1());
        holder.p_name.setText(productsList.get(position).getName());
        holder.p_price.setText(productsList.get(position).getPrice());
       // holder.p_description.setText(productsList.get(position).getDescription());
        //holder.p_specs.setText(productsList.get(position).getSpecifications());
//        holder.p_img1.setImageResource(Integer.parseInt(productsList.get(position).getImg1()));
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
        //TextView prodName, prodPrice;
        //ImageView prodImg;
        TextView p_name,p_price,p_description,p_specs;
       // EditText p_image1,p_image2,c_id;
        ImageView p_img1;
        @SuppressLint("ResourceType")
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
          //  prodName    = itemView.findViewById(R.id.product_Name);
           // prodPrice   = itemView.findViewById(R.id.product_Price);
            //prodImg     = itemView.findViewById(R.id.product_Image);

            p_name=itemView.findViewById(R.id.product_Name);
            p_price=itemView.findViewById(R.id.product_Price);
         //   p_description=itemView.findViewById(R.id.prod);
           // p_specs=itemView.findViewById(R.id.p_specs);
           p_img1=itemView.findViewById(R.id.product_Image);





//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                }
//            });
        }
    }
}
