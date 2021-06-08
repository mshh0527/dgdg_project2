package com.example.dgdg.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dgdg.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExercisingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExercisingFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "ex_name";
    private static final String ARG_PARAM2 = "ex_image";

    private String ex_name;
    private int ex_image;
    private TextView exercise_name;
    private ImageView exercise_image;


    private Button start;

    public ExercisingFragment() {
        // Required empty public constructor
    }

    public static ExercisingFragment newInstance(String param1, String param2) {
        ExercisingFragment fragment = new ExercisingFragment();
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
            ex_name = getArguments().getString(ARG_PARAM1);
            ex_image = getArguments().getInt(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_exercising, container, false);

        exercise_name = root.findViewById(R.id.exercise_name);
        exercise_name.setText(ex_name);
        exercise_name.setSelected(true);

        exercise_image = root.findViewById(R.id.exercise_image);
        exercise_image.setImageResource(ex_image);
        exercise_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PopupExplainActivity.class);
                intent.putExtra("ex_name", ex_name);
                startActivityForResult(intent, 1);
            }
        });

        start= root.findViewById(R.id.start);
        start.setOnClickListener(onClickListener);


        return root;
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(getActivity(),TimerActivity.class);
            startActivity(intent);

        }

    };


}