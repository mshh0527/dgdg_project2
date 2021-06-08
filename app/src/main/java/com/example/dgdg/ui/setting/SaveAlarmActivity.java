package com.example.dgdg.ui.setting;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dgdg.R;
import com.example.dgdg.ui.setting.Room.AppDatabase;
import com.example.dgdg.ui.setting.Room.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class SaveAlarmActivity extends AppCompatActivity {

    private final int REQUEST_CODE = 200;
    private TimePicker timePicker;
    private AlarmManager alarmManager;
    CheckBox cbSun, cbMon, cbTue, cbWed, cbThu, cbFri, cbSat;
    private AppDatabase db;

    private Button submit;

    private int id;
    private String time;
    private String day;
    private int hour, minute;
    private boolean sun, mon, tue, wed, thu, fri, sat;
    private boolean[] weeks = {false, sun, mon, tue, wed, thu, fri, sat};

    long selectTime, intervalDay;
    PendingIntent pIntent;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_alarm);

        initialized();

        // 저장
        submit.setOnClickListener(v -> {
            // db에 저장하기
            setData();
            User data = new User(time, day, hour, minute, weeks[1], weeks[2], weeks[3], weeks[4], weeks[5], weeks[6], weeks[7]);
            db.userDao().insert(data);
            Toast.makeText(getApplicationContext(),"저장되었습니다",Toast.LENGTH_SHORT).show();

            // 지정한 시간에 매일 알림
            id = data.getId(); // 왜 계속 0이니
            System.out.println("############################################### "+id);
            intent = new Intent(this, ExerciseAlarmReceiver.class);
            intent.putExtra("id",id);
            pIntent = PendingIntent.getBroadcast(this, 0,intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, selectTime,  intervalDay, pIntent);

            finish();
        });
    }

    private void initialized() {
        submit = findViewById(R.id.submit);
        timePicker = findViewById(R.id.tp_timepicker);
        alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        cbSun = findViewById(R.id.cb_sun);
        cbMon = findViewById(R.id.cb_mon);
        cbTue = findViewById(R.id.cb_tue);
        cbWed = findViewById(R.id.cb_wed);
        cbThu = findViewById(R.id.cb_thu);
        cbFri = findViewById(R.id.cb_fri);
        cbSat = findViewById(R.id.cb_sat);

        db = AppDatabase.getInstance(this);

    }


    public void setData() { // 추가 클릭 -> 운동 알림 설정(요일, 시간 선택)

        boolean[] week = { false, cbSun.isChecked(), cbMon.isChecked(), cbTue.isChecked(), cbWed.isChecked(),
                cbThu.isChecked(), cbFri.isChecked(), cbSat.isChecked() }; // cbSun을 1번부터 사용하기 위해 배열 0번은 false로 고정

        if(!cbSun.isChecked() &&  !cbMon.isChecked() &&  !cbTue.isChecked() && !cbWed.isChecked()
                &&  !cbThu.isChecked() && !cbFri.isChecked() && !cbSat.isChecked()){
            Toast.makeText(this, "요일을 선택해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hour=timePicker.getHour();
            minute=timePicker.getMinute();
        }else{
            Toast.makeText(this, "버전을 확인해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date today = new Date();
        intervalDay = 24 * 60 * 60 * 1000;// 24시간

        selectTime=calendar.getTimeInMillis();
        long currenTime=System.currentTimeMillis();

        //만약 설정한 시간이, 현재 시간보다 작다면 알람이 부정확하게 울리기 때문에 다음날 울리게 설정
        if(currenTime>selectTime){
            selectTime += intervalDay;
        }

        String strDay="";
        for(int i = 1; i <= 7; i++){
            weeks[i] = week[i];
            if(week[i]){
                switch (i){
                    case 1:
                        strDay+="일 ";break;
                    case 2:
                        strDay+="월 ";break;
                    case 3:
                        strDay+="화 ";break;
                    case 4:
                        strDay+="수 ";break;
                    case 5:
                        strDay+="목 ";break;
                    case 6:
                        strDay+="금 ";break;
                    case 7:
                        strDay+="토 ";break;
                    default:
                        strDay+="err ";break;
                }
            }
        }
        SimpleDateFormat format1 = new SimpleDateFormat ( "a hh:mm");
        time = format1.format(calendar.getTime());
        day = strDay;

    }

    public void deleteData(){
        Intent intent = new Intent(this, ExerciseAlarmReceiver.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pIntent);
    }

}