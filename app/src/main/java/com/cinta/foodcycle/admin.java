
package com.cinta.foodcycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class admin extends AppCompatActivity {
    private Button bt_Create;
    private Button bt_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        bt_Create = (Button) findViewById(R.id.btncreatedata);
        bt_view = (Button) findViewById(R.id.btnviewdata);
    }
    public void Create(View v) {
        //startActivity(CreateData.getActIntent(admin.this));
        Intent intent = new Intent(this, CreateData.class);
        startActivity(intent);

    }
    public void view(View v) {
        startActivity(ReadActivity.getActIntent(admin.this));
    }
}