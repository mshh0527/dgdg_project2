package com.example.dgdg.ui.setting.Room;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "alarmTable")
public class User implements Parcelable {

    //Room에서 자동으로 id를 할당
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    private int id;
    @ColumnInfo(name = "user_time")
    private String time;
    @ColumnInfo(name = "user_day")
    private String day;
    @ColumnInfo(name = "user_hour")
    private int hour;
    @ColumnInfo(name = "user_minute")
    private int minute;
    @ColumnInfo(name = "user_sun")
    private boolean sun;
    @ColumnInfo(name = "user_mon")
    private boolean mon;
    @ColumnInfo(name = "user_tue")
    private boolean tue;
    @ColumnInfo(name = "user_wed")
    private boolean wed;
    @ColumnInfo(name = "user_thu")
    private boolean thu;
    @ColumnInfo(name = "user_fri")
    private boolean fri;
    @ColumnInfo(name = "user_sat")
    private boolean sat;


    public User(String time, String day, int hour, int minute, boolean sun, boolean mon,boolean tue,
                boolean wed,boolean thu,boolean fri,boolean sat) {
        this.time = time;
        this.day = day;
        this.hour =hour;
        this.minute = minute;
        this.sun = sun;
        this.mon = mon;
        this.tue = tue;
        this.wed = wed;
        this.thu = thu;
        this.fri = fri;
        this.sat = sat;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    protected User(Parcel in) {
        id = in.readInt();
        time = in.readString();
        day = in.readString();
        hour = in.readInt();
        minute = in.readInt();
        sun = in.readBoolean();
        mon = in.readBoolean();
        tue = in.readBoolean();
        wed = in.readBoolean();
        thu = in.readBoolean();
        fri = in.readBoolean();
        sat = in.readBoolean();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public Boolean getSun() {
        return sun;
    }

    public void setSun(boolean sun) {
        this.sun = sun;
    }

    public Boolean getMon() {
        return mon;
    }

    public void setMon(boolean mon) {
        this.mon = mon;
    }

    public Boolean getTue() {
        return tue;
    }

    public void setTue(boolean tue) {
        this.tue = tue;
    }

    public Boolean getWed() {
        return wed;
    }

    public void setWed(boolean wed) {
        this.wed = wed;
    }

    public Boolean getThu() {
        return thu;
    }

    public void setThu(boolean thu) { this.thu = thu; }

    public Boolean getFri() {
        return fri;
    }

    public void setFri(boolean fri) {
        this.fri = fri;
    }

    public Boolean getSat() {
        return sat;
    }

    public void setSat(boolean sat) {
        this.sat = sat;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(id);
        dest.writeString(time);
        dest.writeString(day);
        dest.writeInt(hour);
        dest.writeInt(minute);
        dest.writeBoolean(sun);
        dest.writeBoolean(mon);
        dest.writeBoolean(tue);
        dest.writeBoolean(wed);
        dest.writeBoolean(thu);
        dest.writeBoolean(fri);
        dest.writeBoolean(sat);
    }
}
