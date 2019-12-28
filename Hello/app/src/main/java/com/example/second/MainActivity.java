package com.example.second;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button loginButton = (Button) findViewById(R.id.loginBtn);
        final Button pickCuisineButton = (Button) findViewById(R.id.pickCuisineBtn);

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(loginIntent);
            }

        });

        pickCuisineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String userName = intent.getStringExtra("username");
                Log.d("userName", userName);
                Intent pickCuisineIntent = new Intent(MainActivity.this, PickCuisineActivity.class);
                pickCuisineIntent.putExtra("userName", userName);
                MainActivity.this.startActivity(pickCuisineIntent);
            }
        });

    }
}
