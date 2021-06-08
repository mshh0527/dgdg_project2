package com.example.dgdg.ui.setting.Recycler;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dgdg.R;
import com.example.dgdg.ui.mission.MissionActivity;
import com.example.dgdg.ui.setting.DetailActivity;
import com.example.dgdg.ui.setting.ExerciseAlarmReceiver;
import com.example.dgdg.ui.setting.Room.AppDatabase;
import com.example.dgdg.ui.setting.Room.User;
import com.example.dgdg.ui.setting.SaveAlarmActivity;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<User> userData = new ArrayList<>();

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alarmrecycler_itemview,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.onBind(userData.get(position), position);
    }

    @Override
    public int getItemCount() {
        return userData.size();
    }

    public void addItem(User user) {
        userData.add(user);
        notifyDataSetChanged();
    }

    public void addItems(ArrayList<User> users) {
        userData = users;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView key;
        private TextView time;
        private TextView day;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            key = itemView.findViewById(R.id.key);
            time = itemView.findViewById(R.id.timeTextView);
            day = itemView.findViewById(R.id.dayTextView);
        }

        public void onBind(User user, int position) {
            String s = "" + (position+1);
            key.setText(s);
            time.setText(user.getTime());
            day.setText(user.getDay());

            itemView.setOnLongClickListener(v -> {
                userData.remove(user);
                AppDatabase.getInstance(itemView.getContext()).userDao().delete(user);

                Intent intent = new Intent(itemView.getContext(), ExerciseAlarmReceiver.class);
                PendingIntent pIntent = PendingIntent.getBroadcast(itemView.getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) itemView.getContext().getSystemService(Context.ALARM_SERVICE);
                alarmManager.cancel(pIntent);

                notifyDataSetChanged();
                return false;
            });

            itemView.setOnClickListener(v -> {

                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra("data", user);
                itemView.getContext().startActivity(intent);

            });
        }
    }
}
