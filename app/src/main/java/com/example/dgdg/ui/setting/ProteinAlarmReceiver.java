package com.example.dgdg.ui.setting;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.dgdg.R;

public class ProteinAlarmReceiver extends BroadcastReceiver {

    public ProteinAlarmReceiver(){ }

    NotificationManager manager;
    NotificationCompat.Builder builder;

    //오레오 이상은 반드시 채널을 설정해줘야 Notification이 작동함
    private static String CHANNEL_ID = "channel22";
    private static String CHANNEL_NAME = "Channel22";


    @Override
    public void onReceive(Context context, Intent intent) {
        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        builder = null;
        manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            manager.createNotificationChannel(
                    new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            );
            builder = new NotificationCompat.Builder(context, CHANNEL_ID);
        } else {
            builder = new NotificationCompat.Builder(context);
        }

        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setContentTitle("득근득근 보충제 알림");
        builder.setContentText("보충제 섭취 시간입니다!!(운동 후 30분?)(5초 test)");
        builder.setDefaults(Notification.DEFAULT_VIBRATE);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT); // ?
        builder.setAutoCancel(true);

        Notification notification = builder.build();
        manager.notify(0,notification);

    }
}