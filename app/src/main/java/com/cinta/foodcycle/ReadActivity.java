package com.cinta.foodcycle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.cinta.foodcycle.model.Food;
import com.cinta.foodcycle.model.FoodAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReadActivity extends AppCompatActivity implements
        FoodAdapter.FirebaseDataListener{

    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Food> daftarMakanan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        rvView = (RecyclerView) findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);

        database = FirebaseDatabase.getInstance().getReference();

        database.child("food").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
         daftarMakanan = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {

                    Food food = noteDataSnapshot.getValue(Food.class);
                    food.setKey(noteDataSnapshot.getKey());

                    daftarMakanan.add(food);
                }
                adapter = new FoodAdapter(daftarMakanan,
                        ReadActivity.this);
                rvView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+databaseError.getMessage());
            }
        });
    }
    public static Intent getActIntent(Activity activity){
        return new Intent(activity, ReadActivity.class);
    }
    @Override
    public void onDeleteData(Food food, final int position) {
        if(database!=null){
            database.child("makanan").child(food.getKey()).removeValue().addOnSuccessListener
                    (new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(ReadActivity.this,"success delete",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }
}