package com.cinta.foodcycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cinta.foodcycle.model.UserModel;
import com.cinta.foodcycle.model.UserModel;

public class RegisterActivity extends AppCompatActivity {
    private EditText mviewerUser, mViewPassword, mViewRepassword;
    UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mviewerUser =findViewById(R.id.txtUsername);
        mViewPassword =findViewById(R.id.txtPass);
        mViewRepassword =findViewById(R.id.txtPass1);
        userModel = new UserModel(this);
        userModel.open();
    }
    public void registerUser(View v) {
        String username = mviewerUser.getText().toString().trim();
        String password = mViewPassword.getText().toString().trim();
        String cnfpas = mViewRepassword.getText().toString().trim();
        if(password.equals(cnfpas)){
            userModel.addUser(username,password);
            Toast.makeText(this, "Anda Telah Terdafar",
                    Toast.LENGTH_SHORT).show();
            Intent moveTologin = new Intent(this, LoginActivity.class);
            startActivity(moveTologin);
        }
        else {
            Toast.makeText(this, "Password Tidak Sesuai",
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void back(View v) {
        Intent loginintent = new Intent(this, LoginActivity.class);
        startActivity(loginintent);
    }
}
