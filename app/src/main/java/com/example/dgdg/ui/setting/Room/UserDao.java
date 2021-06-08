package com.example.dgdg.ui.setting.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Query("UPDATE alarmTable SET user_time = :t, user_day = :d, user_hour = :h, user_minute = :m, user_sun = :su" +
            ", user_mon = :mo, user_tue = :tu, user_wed = :we, user_thu = :th, user_fri = :fr, user_sat = :sa WHERE user_id =:id")
    void update(String t, String d, int h, int m, boolean su, boolean mo, boolean tu, boolean we, boolean th, boolean fr, boolean sa, int id);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM  alarmTable")
    List<User> getAll();

    @Query("DELETE FROM alarmTable")
    void deleteAll();

    @Query("SELECT COUNT(*) as cnt FROM alarmTable")
    int getDataCount();
}
