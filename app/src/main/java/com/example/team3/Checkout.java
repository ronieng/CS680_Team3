package com.example.team3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


public class Checkout extends AppCompatActivity implements View.OnClickListener {

    private Button processOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout);

        processOrder = (Button) findViewById(R.id.processOrder);
        processOrder.setOnClickListener(this);

        //Setup Action Bar with Logo
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.logo_only);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
    }

    @Override
    public void onClick(View v) {
        Intent i1 = new Intent(this, CompleteOrder.class);
        startActivity(i1);
        Log.i("OrderComplete", "Process Order Clicked");
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

            case R.id.processOrder:
                Intent i6 = new Intent(this, Checkout.class);
                startActivity(i6);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
