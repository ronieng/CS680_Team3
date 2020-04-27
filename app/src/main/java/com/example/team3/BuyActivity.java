package com.example.team3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.team3.mode.Product;

public class BuyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_activity);

        int id = getIntent().getIntExtra("ProductId", -1);
        if (id == -1) {
            Toast.makeText(getApplicationContext(), "Item Not Found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        ImageView iconView = findViewById(R.id.icon);
        TextView titleView = findViewById(R.id.title);
        final  EditText numView = findViewById(R.id.buy_number);
        Button addBtn = findViewById(R.id.add_cart);
        TextView contentView = findViewById(R.id.content);

        final Product product = DataBaseHelper.instance(getApplicationContext()).getProductById(id);

        iconView.setImageResource(product.getIcon());
        titleView.setText(product.getName());
        contentView.setText(product.getDescription());
        numView.setText("1");

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numStr = numView.getText().toString();
                int num = Integer.parseInt(numStr);
                if (num <= 0) {
                    Toast.makeText(getApplicationContext(), "No Item(s) Added", Toast.LENGTH_SHORT).show();
                    return;
                }
                DataBaseHelper.instance(getApplicationContext()).addCart(product.getName(), num, product.getPrice());
                Toast.makeText(getApplicationContext(), "Item(s) Added to Cart", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
