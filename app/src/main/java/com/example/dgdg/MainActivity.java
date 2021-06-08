package com.example.dgdg;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dgdg.network.DBHelper;
import com.example.dgdg.ui.home.TimerActivity;
import com.example.dgdg.ui.mission.MissionActivity;
import com.example.dgdg.ui.setting.AlarmSetting;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static Context context_main;
    public ArrayList<String> username=new ArrayList<>();
    public  ArrayList<String> userrecord=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("DGDG");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(toolbar);

        DBHelper helper;
        SQLiteDatabase db;
        helper = new DBHelper(this, "newdb2.db", null, 1);
        db = helper.getWritableDatabase();
        helper.onCreate(db);

        db.execSQL("DELETE FROM newtable2");


        String rec1=((TimerActivity)TimerActivity.context_main).machine_record;
        System.out.println("intent:::"+rec1);

        if (!rec1.equals("NULL")) {

            String sql = "INSERT INTO newtable2(UserName,Record) values('김미소', rec1);";
            db.execSQL(sql);
        }



        String sql2 = "INSERT INTO newtable2(UserName,Record) values('김미소','30');";
        db.execSQL(sql2);



        Cursor c = db.query("newtable2",null,null,null,null,null,null,null);
        while(c.moveToNext()){
            System.out.println("username : "+c.getString(c.getColumnIndex("UserName"))+"record :"+c.getString(c.getColumnIndex("Record")));
            username.add(c.getString(c.getColumnIndex("UserName")));
            userrecord.add(c.getString(c.getColumnIndex("Record")));
        }




        context_main = this;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mission_menu:
                Intent missionActivity = new Intent(getApplicationContext(), MissionActivity.class);
                startActivity(missionActivity);
                break;
            case R.id.setting_menu:
                Intent settingActivity = new Intent(getApplicationContext(), AlarmSetting.class);
                startActivity(settingActivity);
                break;
        }

        return true;
        //return super.onOptionsItemSelected(item);
    }

}