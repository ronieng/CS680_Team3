package com.example.team3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


public class Contact extends AppCompatActivity implements View.OnClickListener {
    private Button map, text_us;
    private TextView call_us, email_us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);

        //Setup references
        map = (Button) findViewById(R.id.map);
        text_us = (Button) findViewById(R.id.text_us);
        call_us = (TextView) findViewById(R.id.call_us);
        email_us = (TextView) findViewById(R.id.email_us);

        //Setup onclick listeners
        map.setOnClickListener(this);
        text_us.setOnClickListener(this);
        call_us.setOnClickListener(this);
        email_us.setOnClickListener(this);


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

    public void onClick(View v) {
        switch (v.getId()) {

            //Clicking Map opens up map to Bentley Bath Bombs (Bentley University - LaCava Building)
            case R.id.map:
                Intent i1 = new Intent(this, MapsActivity.class);
                startActivity(i1);
                break;

            //Clicking Text Us opens up text messaging to phone number
            case R.id.text_us:
                Uri uri2 = Uri.parse("sms:7818912000");
                Intent i2 = new Intent (Intent.ACTION_VIEW, uri2);
                startActivity(i2);
                break;

            //Clicking phone number opens up phone with dialer set to Bentley Bath Bombs (Bentley's automated system) phone #
            case R.id.call_us:
                Uri uri3 = Uri.parse("tel:7818912000");
                Intent i3 = new Intent(Intent.ACTION_VIEW, uri3);
                startActivity(i3);
                break;

            //Clicking on email address opens up Email to send to Roni Eng at Bentley University.
            case R.id.email_us:
                Uri uri4 = Uri.parse("mailto:reng@bentley.edu");
                Intent i4 = new Intent(Intent.ACTION_SENDTO, uri4);
                startActivity(i4);
                break;
        }
    }



}