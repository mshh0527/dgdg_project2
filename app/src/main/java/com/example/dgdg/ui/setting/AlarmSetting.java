package com.example.dgdg.ui.setting;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.dgdg.R;

public class AlarmSetting extends PreferenceActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();

    }

    public static class MyPreferenceFragment extends PreferenceFragment {

        SharedPreferences pref;
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

            //SharedPreference객체를 참조하여 설정상태에 대한 제어 가능
            pref =PreferenceManager.getDefaultSharedPreferences(getActivity());
        }// onCreate()

        @Override
        public void onResume() {
            super.onResume();

            //설정값 변경 리스너 등록
            pref.registerOnSharedPreferenceChangeListener(listener);
        }//onResume()

        @Override
        public void onPause() {
            super.onPause();

            pref.unregisterOnSharedPreferenceChangeListener(listener);

        }

        //설정값 변경리스너 객체 맴버변수
        SharedPreferences.OnSharedPreferenceChangeListener listener= new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if(key.equals("protein")){
                    boolean isProtein= pref.getBoolean("protein", false);
                    Toast.makeText(getActivity(), "보충제 알림 : "+ isProtein, Toast.LENGTH_SHORT).show();
                    final AlarmManager alarmManager = (AlarmManager) MyPreferenceFragment.this.getActivity().getSystemService(Context.ALARM_SERVICE);
                    if(isProtein){
                        Log.d("true", "5초 보충제 알림. 운동 종료 후 30분으로 바꿔야함");
                        int time = 5000; // 5000 = 5s, 운동 종료 후 30분으로 바꿔야함
                        Intent intent= new Intent(MyPreferenceFragment.this.getActivity(), ProteinAlarmReceiver.class);
                        PendingIntent pendingIntent= PendingIntent.getBroadcast(MyPreferenceFragment.this.getActivity(),10,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                        //알람 셋팅
                        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+time,pendingIntent);
                        }else{
                            alarmManager.setExact(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+time,pendingIntent);//AlarmManager.RTC랑 ELAPSED는 동작안함.
                        }
                    }
                    else{
                        Intent intent = new Intent(MyPreferenceFragment.this.getActivity(), ProteinAlarmReceiver.class);
                        PendingIntent pIntent = PendingIntent.getBroadcast(MyPreferenceFragment.this.getActivity(), 10, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                        alarmManager.cancel(pIntent);
                    }
                }else {
                    Log.d("errr","not_thing");
                }
            }
        };

        // Preference 클릭 감지
        @Override
        public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
            String key = preference.getKey();
            Log.d("onPreferenceTreeClick","클릭된 Preference의 key는 "+key);
            if(key.equals("exercise")){
                Log.d("onPreferenceTreeClick","굿굿 "+key);
            }

            return false;
        }
    }
}