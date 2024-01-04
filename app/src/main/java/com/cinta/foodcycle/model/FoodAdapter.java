package com.cinta.foodcycle.model;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.cinta.foodcycle.CreateData;
import com.cinta.foodcycle.DetailActivity;
import com.cinta.foodcycle.R;
import com.cinta.foodcycle.ReadActivity;
import com.cinta.foodcycle.model.Food;

import java.util.ArrayList;
public class FoodAdapter extends
        RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    private ArrayList<Food> daftarMakanan;
    private Context context;
    FirebaseDataListener listener;
    private int position;

    public FoodAdapter(ArrayList<Food> foods, Context ctx) {

        daftarMakanan = foods;
        context = ctx;
        listener = (FirebaseDataListener) ctx;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        ViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.tv_namamakanan);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent,
                        false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final String name = daftarMakanan.get(position).getRestaurant();
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/**
 * Kodingan detail data
 */
                context.startActivity(DetailActivity.getActIntent((Activity)
                        context).putExtra("data", daftarMakanan.get(position)));
            }
        });
        holder.tvTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
/**
 * Kodingan Delete dan update data
 */
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog);
                dialog.setTitle("Pilih Aksi");
                dialog.show();

                Button editButton = (Button) dialog.findViewById(R.id.btnEditData);
                Button delButton = (Button) dialog.findViewById(R.id.btnDeleteData);

                editButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override

                            public void onClick(View view) {

                                dialog.dismiss();

                                context.startActivity(CreateData.getActIntent((Activity)
                                        context).putExtra("data", daftarMakanan.get(position)));
                            }
                        }
                );
                delButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();

                                listener.onDeleteData(daftarMakanan.get(position),
                                        position);
                            }
                        }
                );
                return true;
            }
        });
        holder.tvTitle.setText(name);
    }

    @Override
    public int getItemCount() {

        return daftarMakanan.size();
    }
    public interface FirebaseDataListener{
        void onDeleteData(Food food, int position);
    }
}