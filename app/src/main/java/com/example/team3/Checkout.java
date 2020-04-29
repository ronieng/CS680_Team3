package com.example.team3;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.team3.mode.CartItem;

import java.util.ArrayList;


public class Checkout extends AppCompatActivity implements View.OnClickListener {

    private String username;
    private String phone;
    private String email;
    private String address;
    private String zip_code;
    private String cc_name;
    private String cc_num;
    private String cvc_code;
    private String exp_date;
    private double total;

    private EditText mName;
    private EditText mAddLine1;
    private EditText mAddLine2;
    private EditText mCity;
    private EditText mState;
    private EditText mZip;
    private EditText mPhone;
    private EditText mEmail;
    private EditText mCcname;
    private EditText mCcnum;
    private EditText mCvccode;
    private EditText mExpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout);

        mName = findViewById(R.id.name);
        mAddLine1 = findViewById(R.id.addLine1);
        mAddLine2 = findViewById(R.id.addLine2);
        mCity = findViewById(R.id.city);
        mState = findViewById(R.id.state);
        mZip = findViewById(R.id.zip);
        mPhone = findViewById(R.id.phone);
        mEmail = findViewById(R.id.email);
        mCcname = findViewById(R.id.cc_name);
        mCcnum = findViewById(R.id.cc_num);
        mCvccode = findViewById(R.id.cvc_code);
        mExpdate = findViewById(R.id.exp_date);
        findViewById(R.id.processOrder).setOnClickListener(this);

        //Setup Action Bar with Logo
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.logo_only);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

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

    private void fillData() {
        this.username = mName.getText().toString();
        this.phone = mPhone.getText().toString();
        this.email = mEmail.getText().toString();
        this.address = mAddLine1.getText().toString();
        this.address += ",";
        this.address += mAddLine2.getText().toString();
        this.address += ",";
        this.address += mCity.getText().toString();
        this.address += ",";
        this.address += mState.getText().toString();
        this.zip_code = mZip.getText().toString();
        this.cc_name = mCcname.getText().toString();
        this.cc_num = mCcnum.getText().toString();
        this.cvc_code = mCvccode.getText().toString();
        this.exp_date = mExpdate.getText().toString();

        ArrayList<CartItem> list = DataBaseHelper.instance(getApplicationContext()).getCartList();
        total = 0;
        for (CartItem item: list) {
            total += item.getPrice() * item.getBuyNum();
        }
        total += total * 0.0625;
        total += 5;
    }

    @Override
    public void onClick(View v) {
        fillData();

        if (v.getId() == R.id.processOrder) {
            long id = DataBaseHelper.instance(getApplicationContext()).placeOrder(username,
                    phone, email, address, zip_code, cc_name, cc_num, cvc_code,
                    exp_date, total);
            ArrayList<CartItem> list = DataBaseHelper.instance(getApplicationContext()).getCartList();
            DataBaseHelper.instance(getApplicationContext()).addOrderItems(id, list);
            DataBaseHelper.instance(getApplicationContext()).clearCart();
            Toast.makeText(getBaseContext(), "Order Complete", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, CompleteOrder.class);
            // i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    }
}
