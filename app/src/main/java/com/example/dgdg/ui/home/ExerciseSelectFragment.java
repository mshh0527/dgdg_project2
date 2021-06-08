package com.example.dgdg.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dgdg.R;


public class ExerciseSelectFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Button startByRoutine;
    private Button treadmill, cycle, Test, TESTtesttest;
    private Button[] exButtons = {treadmill, cycle};
    private Button[] exButtons2={Test, TESTtesttest};
    private int[] buttonIDs = {R.id.bt_ex1, R.id.bt_ex2};
    private int[] buttonIDs2={R.id.bt_ex3, R.id.bt_ex4};
    private String[] exNames = {"트레드밀", "사이클"};
    private String[] exNames2={"Test", "TESTttttttttttttttttttttttesttest"};
    private int[] exImages = {R.drawable.image_treadmill, R.drawable.image_cycle};
    private int[] exImages2={ R.drawable.image_test, R.drawable.image_test};

    public ExerciseSelectFragment() {
        // Required empty public constructor
    }

    public static ExerciseSelectFragment newInstance(String param1, String param2) {
        ExerciseSelectFragment fragment = new ExerciseSelectFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_exercise_select, container, false);


        /* Routine Button */
        startByRoutine = root.findViewById(R.id.start_by_routine);
        startByRoutine.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Navigation.findNavController(v).navigate(R.id.action_exercise_select_to_routine);
            }
        });

        /* Individual Exercise Button */
        //유산소
        for(int i = 0; i < exButtons.length; i++){
            exButtons[i] = root.findViewById(buttonIDs[i]);
            exButtons[i].setOnClickListener(new KnowIndexOnClickListener(i){
                @Override
                public void onClick(View v){
                    Bundle bundle = new Bundle(2); // 파라미터는 전달할 데이터 개수
                    bundle.putString("ex_name", exNames[index]);
                    bundle.putInt("ex_image", exImages[index]);
                    Navigation.findNavController(v).navigate(R.id.action_exercise_select_to_exercising, bundle);

                }
            });
        }

        /* Individual Exercise Button */
        //무산소
        for(int i = 0; i < exButtons2.length; i++){
            exButtons2[i] = root.findViewById(buttonIDs2[i]);
            exButtons2[i].setOnClickListener(new KnowIndexOnClickListener(i){
                @Override
                public void onClick(View v){
                    Bundle bundle = new Bundle(2); // 파라미터는 전달할 데이터 개수
                    bundle.putString("ex_name", exNames2[index]);
                    bundle.putInt("ex_image", exImages2[index]);
                    Navigation.findNavController(v).navigate(R.id.action_exercise_select_to_exercising, bundle);

                }
            });
        }

        return root;
    }

    public abstract class KnowIndexOnClickListener implements View.OnClickListener {

        protected int index;

        public KnowIndexOnClickListener(int index) {
            this.index = index;
        }
    }
}