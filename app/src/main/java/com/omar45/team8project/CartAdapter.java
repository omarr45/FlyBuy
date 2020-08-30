package com.omar45.team8project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>  {
    public static List<Cart> cartList=new ArrayList<>();
    public static List<Cart>products=new ArrayList<>();
    //private List<Cart>c_list=new ArrayList<>();
    private Context context;
    private Product product;
    private CartDatabase cartDatabase;


    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cart_item, parent, false));
    }

    public CartAdapter(Context context) {
        this.context = context;
    }

    public CartAdapter(Context context, ProductClickListener productClickListener) {
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder holder, final int position) {
        Log.e("TAG","onBindViewHolder"+products.size());
        Log.e("TAG","onBindViewHolder"+position);
        holder.p_name.setText(cartList.get(position).getCart_item_name());
        holder.p_price.setText((String.valueOf(cartList.get(position).getCart_item_price())));
        holder.quantity.setText("Quantity:  "+String.valueOf(cartList.get(position).getCart_item_quantity()));
        Log.i("quantity",String.valueOf(cartList.get(position).getCart_item_quantity()));
        Glide.with(context).load(cartList.get(position).getCart_item_img().toString()).into(holder.p_img1);

    }

    @Override
    public int getItemCount() {return cartList.size();};

    public void setList(List<Cart> cartList) {
        this.cartList=cartList;
        notifyDataSetChanged();
    }


    public class CartViewHolder extends RecyclerView.ViewHolder {
        TextView p_name,p_price;
        ImageView p_img1;
        TextView quantity;
        public CartViewHolder(View itemView) {
            super(itemView);
            p_name=itemView.findViewById(R.id.item_name);
            p_price=itemView.findViewById(R.id.item_price);
            p_img1=itemView.findViewById(R.id.item_image);
            quantity=itemView.findViewById(R.id.quantity);

        }
        }

}
