package com.example.dgdg.ui.mission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dgdg.R;

public class MissionActivity extends AppCompatActivity {
    private Button bt_mission3, bt_mission3_check; // test
    private Button check_reward_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);

        /* Mission buttons */
        bt_mission3 = findViewById(R.id.bt_mission3);
        bt_mission3_check = findViewById(R.id.bt_mission3_check);

        bt_mission3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt_mission3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.image_gray_box_checked));
                bt_mission3.setTextColor(getColor(R.color.dark_gray));
                bt_mission3_check.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.image_gray_circle_fill));
                bt_mission3_check.setTextColor(getColor(R.color.dark_gray));
            }
        });

        /* Button to check myPoint & reward */
        check_reward_button = findViewById(R.id.check_reward_button);

        check_reward_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myPointActivity = new Intent(getApplicationContext(), MyPointActivity.class);
                startActivity(myPointActivity);
            }
        });

    }

}
// screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(), back_images[random_number]));
/* 미션 툴바는 뺄까? 뒤로가기 버튼?
        Toolbar toolbar = findViewById(R.id.mission_toolbar);
        toolbar.setTitle("미션");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(toolbar);

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

<androidx.appcompat.widget.Toolbar
        android:id="@+id/mission_toolbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toTopOf="parent"
        android:background="@color/dark_blue"
        android:theme="@style/ThemeOverlay.AppCompat.ActionBar"
        app:popupTheme="@style/ThemeOverlay.AppCompat.Light"/>
 */