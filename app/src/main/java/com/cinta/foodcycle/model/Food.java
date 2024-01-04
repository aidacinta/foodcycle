package com.cinta.foodcycle.model;

import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;

@IgnoreExtraProperties
public class Food implements Serializable{
    private String restaurant;
    private String food;
    private String price;
    private String stock;
    private String key;
    public Food(){
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getRestaurant() {
        return restaurant;
    }
    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }
    public String getFood() {
        return food;
    }
    public void setFood(String food) {
        this.food = food;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getStock() {return stock;
    }
    public void setStock(String stock) {
        this.stock = food;
    }
    @Override
    public String toString() {
        return " "+restaurant+"\n" +
                " "+food +"\n" +
                " "+price +"\n" +
                " "+stock;
    }
    public Food(String restaurant, String food, String price){
        restaurant = restaurant;
        food = food;
        price = price;
        stock = stock;


    }
}
