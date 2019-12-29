package com.example.second;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PickCuisineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_cuisine);

        final Button koreanBtn = (Button) findViewById(R.id.koreanBtn);
        final Button chineseBtn = (Button) findViewById(R.id.chineseBtn);
        final Button japaneseBtn = (Button) findViewById(R.id.japaneseBtn);
        final Button westernBtn = (Button) findViewById(R.id.westernBtn);

        koreanBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String userName = intent.getStringExtra("userName");
                Intent koreanIntent = new Intent(PickCuisineActivity.this, PickKoreanMenuActivity.class);
                koreanIntent.putExtra("userName", userName);
                PickCuisineActivity.this.startActivity(koreanIntent);
            }
        });

        chineseBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String userName = intent.getStringExtra("username");
                Intent chineseIntent = new Intent(PickCuisineActivity.this, PickChineseMenuActivity.class);
                chineseIntent.putExtra("userName", userName);
                PickCuisineActivity.this.startActivity(chineseIntent);
            }
        });

        japaneseBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String userName = intent.getStringExtra("username");
                Intent japaneseIntent = new Intent(PickCuisineActivity.this, PickJapaneseMenuActivity.class);
                japaneseIntent.putExtra("userName", userName);
                PickCuisineActivity.this.startActivity(japaneseIntent);
            }
        });

        westernBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String userName = intent.getStringExtra("username");
                Intent westernIntent = new Intent(PickCuisineActivity.this, PickWesternMenuActivity.class);
                westernIntent.putExtra("userName", userName);
                PickCuisineActivity.this.startActivity(westernIntent);
            }
        });
    }
}
