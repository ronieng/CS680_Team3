package com.example.team3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.team3.mode.CartItem;

import java.util.ArrayList;


public class Cart extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private TextView cartTitle;
    private TextView subtotal;
    private TextView tax;
    private TextView shipping;
    private TextView total;
    private ListView list;
    private Button removeBtn, calculateBtn, chkoutBtn;
    private CustomAdapter cart;
    private ArrayList <CartItem> cartItems;
    private int cartitem;
    private static final String tag ="cart" ;

    private double DEFAULT_SHIPPING = 5.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        cartTitle = (TextView) findViewById(R.id.cartTitle);
        subtotal = (TextView) findViewById(R.id.subtotal);
        tax = (TextView) findViewById(R.id.tax);
        shipping = (TextView) findViewById(R.id.shipping);
        total = (TextView) findViewById(R.id.total);
        removeBtn = (Button) findViewById (R.id.removeBtn);
        calculateBtn = (Button) findViewById(R.id.calculateBtn);
        chkoutBtn = (Button) findViewById(R.id.chkoutBtn);


        //Setup Action Bar with Logo
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.logo_only);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        //Set Listener for button click
        removeBtn.setOnClickListener(this);
        chkoutBtn.setOnClickListener (this);
        calculateBtn.setOnClickListener(this);

        //Create list of items in cart
        cartItems = new ArrayList<CartItem>();

        //Identify list and connect listener
        list = (ListView) findViewById(R.id.list);
        list.setOnItemClickListener(this);

        //Connect to custom adapter and custom items list layout
        cart = new CustomAdapter(this,R.layout.item, cartItems);
        list.setAdapter(cart);

        loadData();
    }

    private void loadData() {
        ArrayList<CartItem> list = DataBaseHelper.instance(getApplicationContext()).getCartList();
        cartItems.addAll(list);
        cart.notifyDataSetChanged();
    }

    //CustomAdapter extends the ArrayAdapter with a Get View to allow the listview to display the position of the row as items get added, deleted and updated.
    class CustomAdapter extends ArrayAdapter<CartItem>
    {   Context context;
        ArrayList<CartItem> cartItems;
        CustomAdapter(Context c, int item, ArrayList<CartItem> items)
        {   super(c, R.layout.item, items);
            this.context = c;
            this.cartItems=items;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item, null);
            }
            TextView tvGoodsName = convertView.findViewById(R.id.goodsName);
            TextView tvPrice = convertView.findViewById(R.id.price);
            TextView tvNum = convertView.findViewById(R.id.num);
            CartItem item = getItem(position);
            tvGoodsName.setText(item.getGoodsName());
            tvPrice.setText(String.format("$%.2f", item.getPrice()) + "");
            tvNum.setText(item.getBuyNum() + "");
            return convertView;
        }
    }

    //Called when item in cart is clicked and displays selected item in editText (insert) field
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        CartItem text = cartItems.get(position);
        //insert.setText(text);
        Log.i(tag, "Item selected");
        cartitem = position;
    }


    //Remove button removes item(s) from cart; Calculate button calculates the total in cart;
    //Checkout button brings customer to checkout page to complete transaction
    @Override
    public void onClick(View v) {

        Log.i(tag, "onClick invoked.");
        switch (v.getId()) {
            case R.id.removeBtn:
                cartItems.remove(cartitem);
                cart.notifyDataSetChanged();
                Log.i(tag, "Item(s) Removed");
                Toast.makeText(getApplicationContext(), "Item(s) Removed From Cart", Toast.LENGTH_SHORT).show();

            case R.id.calculateBtn:
                double dsubTotal = 0;
                for (CartItem item: cartItems) {
                    dsubTotal += item.getPrice() * item.getBuyNum();
                }
                double dtax = dsubTotal * 0.0625;
                double dshipping = DEFAULT_SHIPPING;

                subtotal.setText(String.format("$%.2f", dsubTotal) + "");
                tax.setText(String.format("$%.2f", dtax) + "");
                shipping.setText (String.format("$%.2f", dshipping) + "");
                total.setText((String.format("$%.2f", dsubTotal + dtax + dshipping)) + "");
                Log.i(tag, "Total Calculated");
                break;

            case R.id.chkoutBtn:
                Intent i2 = new Intent(this, Checkout.class);
                startActivity(i2);
                Log.i(tag, "Checkout Button Clicked");
                break;
        }
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


}