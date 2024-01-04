package com.cinta.foodcycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cinta.foodcycle.model.UserModel;

public class LoginActivity extends AppCompatActivity {
    private EditText mViewUser, mViewPassword;
    UserModel userModel;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mViewUser=findViewById(R.id.txtUsername);
        mViewPassword=findViewById(R.id.txtPass);
        userModel = new UserModel(this);
    }
    public void login(View v){
        userModel.open();
        String username = mViewUser.getText().toString().trim();
        String password = mViewPassword.getText().toString().trim();
        Boolean res = userModel.checkuser(username, password);
        if (res == true) {
            Toast.makeText(this, "Login Succed ", Toast.LENGTH_SHORT).show();
            Intent homepage = new Intent(this, admin.class);
            startActivity(homepage);
        }
        else {
            Toast.makeText(this, "Login Failed ", Toast.LENGTH_SHORT).show();
        }
        userModel.close();
    }
    public void signin(View v) {
        Intent registerintent = new Intent(this, RegisterActivity.class);
        startActivity(registerintent);
    }
}