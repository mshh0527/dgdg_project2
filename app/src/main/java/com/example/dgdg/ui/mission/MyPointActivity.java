package com.example.dgdg.ui.mission;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.dgdg.R;

public class MyPointActivity extends AppCompatActivity {
    private ImageView my_point_imageView, reward_imageView, reward_info_imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_point);

        my_point_imageView = (ImageView) findViewById(R.id.my_point_imageView);
        reward_imageView = (ImageView) findViewById(R.id.reward_imageView);
        reward_info_imageView = (ImageView) findViewById(R.id.reward_info_imageView);

        my_point_imageView.setImageResource(R.drawable.image_my_point);
        reward_imageView.setImageResource(R.drawable.image_reward);
        reward_info_imageView.setImageResource(R.drawable.image_reward_info_tmp);
    }
}