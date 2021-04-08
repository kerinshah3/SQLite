package com.example.a2starter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txt_flav, txtdescription, Scoop, txtprice,finalprice;
    EditText scoopnumber;
    Button btnPlaceOrder, btnRestock;
    DatabaseHandler databaseHandler ;
    int SCOOP,PRICE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHandler  = new DatabaseHandler(MainActivity.this);

        txt_flav = findViewById(R.id.txt_flav);
        txtprice = findViewById(R.id.txtprice);
        txtdescription = findViewById(R.id.txtdescription);
        Scoop = findViewById(R.id.Scoop);
        scoopnumber = findViewById(R.id.scoopnumber);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
        btnRestock = findViewById(R.id.btnRestock);
        finalprice = findViewById(R.id.finalprice);

        ShowData();

        //
//        Toast.makeText(this, " hey "+ showflavours.getName(), Toast.LENGTH_LONG).show();
//
//        System.out.println(showflavours.getName());

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int scoop = Integer.parseInt(scoopnumber.getText().toString());

                if (scoop > SCOOP) {
                    Toast.makeText(MainActivity.this, "Enter Valid Amount", Toast.LENGTH_SHORT).show();
                }
                else{
                    int finalscoop = SCOOP - scoop;
                    databaseHandler.UpdateData(finalscoop,"Moose Tracks");
                    int finalpriceshow = scoop * PRICE;
                    finalprice.setText("TOTAL COST : " + finalpriceshow);
                    ShowData();
                }
            }
        });
        btnRestock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHandler.UpdateData(20,"Moose Tracks");
                ShowData();
            }
        });

    }

    void ShowData() {
        Flavours showflavours = new Flavours();

        showflavours = databaseHandler.ShowData();

        SCOOP = showflavours.getQuantity();
        PRICE = showflavours.getPrice();
        txt_flav.setText("Today's Flavour : " + showflavours.getName());
        txtprice.setText("price : " + showflavours.getPrice());
        txtdescription.setText(" " + showflavours.getDescription());
        Scoop.setText(showflavours.getQuantity() + "  LEFT");

    }
}