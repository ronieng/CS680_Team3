package com.example.team3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team3.mode.Product;

import java.util.ArrayList;
import java.util.List;


public class ProductActivity extends AppCompatActivity {

    List<Product> lstProd;

    private RecyclerViewAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        //Setup Action Bar with Logo
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.logo_only);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerview);
        this.lstProd = new ArrayList<>();
        myAdapter = new RecyclerViewAdapter(this, lstProd);
        rv.setLayoutManager(new GridLayoutManager(this, 3));
        rv.setAdapter(myAdapter);

        loadData();
    }

    private void loadData() {
        ArrayList<Product> list = DataBaseHelper.instance(getApplicationContext()).getAllProducts();
        this.lstProd.clear();
        this.lstProd.addAll(list);
        myAdapter.notifyDataSetChanged();
    }

    //Insert Option Menu to Action Bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //When Menu Item is Clicked - determines what to do
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent i1 = new Intent(this, Home.class);
                startActivity(i1);
                return true;

            case R.id.products:
                Intent i2 = new Intent(this, ProductActivity.class);
                startActivity(i2);
                return true;

            case R.id.dye:
                Intent i3 = new Intent(this, Dye.class);
                startActivity(i3);
                return true;

            case R.id.cart:
                Intent i4 = new Intent(this, Cart.class);
                startActivity(i4);
                return true;

            case R.id.contact:
                Intent i5 = new Intent(this, Contact.class);
                startActivity(i5);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.myViewHolder> {

        private Context mContext;
        private List<Product> mData;

        public RecyclerViewAdapter(Context mContext, List<Product> mData) {
            this.mContext = mContext;
            this.mData = mData;
        }

        @Override
        public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view;
            LayoutInflater mInflater = LayoutInflater.from(mContext);
            view = mInflater.inflate(R.layout.cardview_item, parent, false);
            return new myViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull myViewHolder holder, final int position) {

            holder.tv_prod_name.setText(mData.get(position).getName());
            holder.img_prod_thumbnail.setImageResource(mData.get(position).getIcon());
            // setting click listener
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // passing data to the book activity
                    Intent intent = new Intent(mContext, BuyActivity.class);
                    intent.putExtra("ProductId", mData.get(position).getId());
                    mContext.startActivity(intent); // start the activity
                }
            });

        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class myViewHolder extends RecyclerView.ViewHolder {

            TextView tv_prod_name;
            ImageView img_prod_thumbnail;
            CardView cardView;

            public myViewHolder(View itemView) {
                super(itemView);

                tv_prod_name = (TextView) itemView.findViewById(R.id.prod_name_id);
                img_prod_thumbnail = (ImageView) itemView.findViewById(R.id.product_img_id);
                cardView = (CardView) itemView.findViewById(R.id.cardview_id);
            }

        }

    }
}
