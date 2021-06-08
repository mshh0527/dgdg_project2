package com.example.dgdg.ui.ranking;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.dgdg.MainActivity;
import com.example.dgdg.R;
import com.example.dgdg.network.DBHelper;
import com.example.dgdg.ui.home.TimerActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class RankingFragment extends Fragment {

    private RankingViewModel rankingViewModel;

    HashMap<String,Integer> value=new HashMap<>();
    ArrayList<String>username=new ArrayList<>();
    ArrayList<String>userrecord=new ArrayList<>();
    ArrayList<String>total=new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_ranking, container, false);





        username = ((MainActivity)MainActivity.context_main).username;
        userrecord=((MainActivity)MainActivity.context_main).userrecord;

        //순위 변동
            /*
            for(int i=0;i<username.size();i++){
                for(int j=i+1;j<username.size();j++){
                    if(Integer.parseInt(userrecord.get(i))<Integer.parseInt(userrecord.get(j))){
                        String tmp=userrecord.get(i);
                        String tmp2=username.get(i);
                        userrecord.set(i,userrecord.get(j));
                        userrecord.set(j,tmp);
                        username.set(i,username.get(j));
                        username.set(j,tmp2);

                    }
                }
            }
*/
        for(int i=0;i<username.size();i++) {
            total.add("user name:"+username.get(i)+"    "+"user record:"+userrecord.get(i));
        }

        ArrayAdapter Adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, total) ;

        ListView listview = (ListView) view.findViewById(R.id.listview1) ;
        listview.setAdapter(Adapter) ;

        return view ;
    }

}
