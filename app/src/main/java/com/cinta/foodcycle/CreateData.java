package com.cinta.foodcycle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.cinta.foodcycle.model.Food;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateData extends AppCompatActivity {
    private DatabaseReference database;
    // variable fields EditText dan Button
    private Button btnSubmit;
    private EditText txtRestaueant;
    private EditText txtFood;
    private EditText txtPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_data);
        txtRestaueant = (EditText) findViewById(R.id.txtrestaurant);
        txtFood = (EditText) findViewById(R.id.txtfood);
        txtPrice = (EditText) findViewById(R.id.txtprice);
        btnSubmit = (Button) findViewById(R.id.btnsubmit);
        database = FirebaseDatabase.getInstance().getReferenceFromUrl
                ("https://food-cycle-8cc02-default-rtdb.firebaseio.com/");
    }
    public void save(View v) {
        if(!isEmpty(txtRestaueant.getText().toString()) &&
                !isEmpty(txtFood.getText().toString()) && !isEmpty(txtPrice.getText().toString()))
            submitfood(new Food(txtRestaueant.getText().toString(),
                    txtFood.getText().toString(), txtPrice.getText().toString()));
        else
            Snackbar.make(findViewById(R.id.btnsubmit), "Data barang tidak boleh " +
                    "kosong", Snackbar.LENGTH_LONG).show();
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(
                txtRestaueant.getWindowToken(), 0);
    }
    private boolean isEmpty(String s) {
        return TextUtils.isEmpty(s);
    }
    private void updatefood(Food food) {
// ...
    }
    private void submitfood(Food food) {


        database.child("food").push().setValue(food).addOnSuccessListener(this, new
                OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        txtRestaueant.setText("");
                        txtFood.setText("");
                        txtPrice.setText("");
                        Snackbar.make(findViewById(R.id.btnsubmit), "Data berhasil " +
                                "ditambahkan", Snackbar.LENGTH_LONG).show();
                    }
                });
    }
    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, CreateData.class);
    }
}