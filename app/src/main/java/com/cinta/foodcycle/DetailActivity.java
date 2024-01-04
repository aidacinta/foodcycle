package com.cinta.foodcycle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cinta.foodcycle.model.Food;

public class DetailActivity extends AppCompatActivity {
    private Button btSubmit;
    private EditText etNamaRestaurant;
    private EditText etNamaMakanan;
    private EditText etHarga;
    private EditText etStock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        etNamaRestaurant = (EditText) findViewById(R.id.et_namarestaurant);
        etNamaMakanan = (EditText) findViewById(R.id.et_namamakanan);
        etHarga = (EditText) findViewById(R.id.et_hargamakanan);
        etStock = (EditText) findViewById(R.id.et_stock);
        btSubmit = (Button) findViewById(R.id.btnsubmit);

        etNamaRestaurant.setEnabled(false);
        etNamaMakanan.setEnabled(false);
        etHarga.setEnabled(false);
        btSubmit.setVisibility(View.GONE);

        Food food = (Food) getIntent().getSerializableExtra("data");
        if(food!=null){
            etNamaRestaurant.setText(food.getRestaurant());
            etNamaMakanan.setText(food.getFood());
            etHarga.setText(food.getPrice());
            etStock.setText(food.getStock());
        }
    }
    public static Intent getActIntent(Activity activity){
        return new Intent(activity, DetailActivity.class);
    }
}