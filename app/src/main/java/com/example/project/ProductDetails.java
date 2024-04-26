package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetails extends AppCompatActivity {

    ImageView img, back;

    EditText ptQ1;
    EditText ptP1;
    Button Button1;
    TextView proName, proPrice, proDesc, proQty, proUnit;

    String name, price, desc, qty, unit;
    int image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent i = getIntent();

         name = i.getStringExtra("name");
         image = i.getIntExtra("image", R.drawable.b1);
         price = i.getStringExtra("price");
         desc = i.getStringExtra("desc");
         qty = i.getStringExtra("qty");
         unit = i.getStringExtra("unit");

         proName = findViewById(R.id.productName);
         proDesc = findViewById(R.id.prodDesc);
         proPrice = findViewById(R.id.prodPrice);
         img = findViewById(R.id.big_image);
         back = findViewById(R.id.back2);
         proQty = findViewById(R.id.qty);
         proUnit = findViewById(R.id.unit);
        ptP1 = findViewById(R.id.ptP);
        ptQ1 = findViewById(R.id.ptQ);
        Button1 = findViewById(R.id.button);

         proName.setText(name);
         proPrice.setText(price);
         proDesc.setText(desc);
         proQty.setText(qty);
         proUnit.setText(unit);


        img.setImageResource(image);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ProductDetails.this, MainActivity.class);
                startActivity(i);
                finish();

            }

        });

        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayInfo();

            }
        });
    }

    private void displayInfo() {
        String Q = "Quantity: " + ptQ1.getText().toString();
        String P = "Price: " + ptP1.getText().toString();
        int Qn = Integer.parseInt(ptQ1.getText().toString());
        int Pn = Integer.parseInt(ptP1.getText().toString());
        double Tn = Qn * Pn;
        String T = "Total price: " + Tn;
        String msg = Q + "; " + P + "; " + T;
        Toast.makeText(ProductDetails.this, msg, Toast.LENGTH_SHORT).show();
    }

    }






