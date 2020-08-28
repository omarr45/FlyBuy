package com.omar45.team8project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
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

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>  {
    private ArrayList<String> idList = new ArrayList<>();
    private Context context;
    private ProductClickListener productClickListener;
    private List<Product> productList = new ArrayList<>();
    private float total = 0;




    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false));
    }

    public CartAdapter(Context context) {
        this.context = context;
    }

    public CartAdapter(Context context, ProductClickListener productClickListener) {
        this.context = context;
        this.productClickListener = productClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder holder, final int position) {
        final ProductDatabase database = ProductDatabase.getInstance(context);

        for ( int i = 0; i < idList.size();i++){
            database.productDao().getProductID(Integer.parseInt(idList.get(i)))
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<List<Product>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(List<Product> products) {
                            //Log.i("Item" , idList.get(0));

                            productList.add(products.get(0));
                            try {
                                holder.p_name.setText(productList.get(position).getName());
                                holder.p_price.setText(String.format("%s EGP", productList.get(position).getPrice()));
                                Glide.with(context).load(productList.get(position).getImg1().toString()).into(holder.p_img1);
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                            Log.i("Product",products.get(0).getName());
                            Log.i("ProductList",productList.get(0).getName());
                            total += productList.get(position).getPrice();
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();

                        }
                    });
        }
//        Log.i("ProductList",productList.get(0).getName());
/*

*/
    }

    @Override
    public int getItemCount() {
        return idList.size(); }

    public void setList(SharedPreferences sh) {
        try {
            this.idList = (ArrayList<String>)ObjectSerializer.deserialize(sh.getString("ID" , ObjectSerializer.serialize(new ArrayList<>())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        notifyDataSetChanged();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        TextView p_name,p_price;
        ImageView p_img1;
        @SuppressLint("ResourceType")

        public CartViewHolder(View itemView) {
            super(itemView);
            p_name=itemView.findViewById(R.id.product_Name);
            p_price=itemView.findViewById(R.id.product_Price);
            p_img1=itemView.findViewById(R.id.product_Image);





            itemView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Product product = productList.get(getAdapterPosition());
                                                productClickListener.onClickProduct(product);
                                            }
                                        }
            );
        }
        }

}
