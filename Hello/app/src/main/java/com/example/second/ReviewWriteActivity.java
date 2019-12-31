package com.example.second;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class ReviewWriteActivity extends AppCompatActivity {
    // 빈곳 눌렀을때 키보드창 내리기
    InputMethodManager imm;
    EditText reviewContent;
    RatingBar rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_write);

        final ConstraintLayout background = (ConstraintLayout) findViewById(R.id.background);
        reviewContent = (EditText) findViewById(R.id.reviewText);
        rating = (RatingBar) findViewById(R.id.ratingBar);

        rating.setStepSize((float) 0.5);        //별 색깔이 1칸씩줄어들고 늘어남 0.5로하면 반칸씩 들어감
        rating.setIsIndicator(false);           //true - 별점만 표시 사용자가 변경 불가 , false - 사용자가 변경가능

        // 키보드창 내리기
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        final Button reviewBtn = (Button) findViewById(R.id.reviewBtn);

        background.setOnClickListener(myClickListener);
        reviewBtn.setOnClickListener(myClickListener);

        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //EditText에 현재 입력 되어 있는 값을 가져온다

                Intent intent = getIntent();
                final String storeNum = intent.getStringExtra("storeNum");
                String userName = intent.getStringExtra("userName");


                String review = reviewContent.getText().toString();
                String rate = String.valueOf(rating.getRating());
                Log.d("@@@@@@rate", rate);
                // 특정요청 이후 원하는 결과값을 다를 수 있게함
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if(success) {

                                AlertDialog.Builder builder = new AlertDialog.Builder(ReviewWriteActivity.this);
                                builder.setMessage("리뷰작성에 성공했습니다.")
                                        .setPositiveButton("확인", null)
                                        .create()
                                        .show();
                                Intent intent = new Intent(ReviewWriteActivity.this, PickCuisineActivity.class);
                                intent.putExtra("restaurantNum", storeNum);
                                startActivity(intent);
                                ReviewWriteActivity.this.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                            } else {

                                AlertDialog.Builder builder = new AlertDialog.Builder(ReviewWriteActivity.this);
                                builder.setMessage("리뷰작성에 실패했습니다.")
                                        .setNegativeButton("확인", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                com.example.second.ReviewWriteRequest reviewWriteRequest = new com.example.second.ReviewWriteRequest(storeNum, userName, review, rate, responseListener);
                RequestQueue queue = Volley.newRequestQueue(ReviewWriteActivity.this);
                queue.add(reviewWriteRequest);
            }
        });
    }

    // 빈화면이나 로그인 버튼을 눌렀을때도 키보드 내려가도록함
    View.OnClickListener myClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            hideKeyboard();
            switch (v.getId())
            {
                case R.id.background :
                    break;
                case R.id.reviewBtn :
                    break;
            }
        }
    };

    private void hideKeyboard() {
        imm.hideSoftInputFromWindow(reviewContent.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(rating.getWindowToken(), 0 );
    }
}
