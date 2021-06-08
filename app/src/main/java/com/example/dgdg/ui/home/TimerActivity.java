package com.example.dgdg.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.dgdg.MainActivity;
import com.example.dgdg.R;
import com.example.dgdg.ui.ranking.RankingFragment;


public class TimerActivity extends AppCompatActivity {


    public static Context context_main; //context 변수선언
    public static String machine_record="NULL";


    //멤버변수화
    private TextView timer;
    private TextView timer2;
    private TextView timer3;

    public static long total=0;
    public static long total2=0;
    public static int count=1;
    public static long tmp;
    public static long t_m=0;
    public static long t_s=0;
    public static long t_ms=0;
    public static int CNT=1;
    //public static int msg;


    private ScrollView scrollView;
    private ScrollView scrollView2;
    private TextView record;
    private TextView record2;
    private Button bt_sta,bt_rec;
    private Button bt_sta2,bt_rec2;
    private Button bt_rec_t,bt_rec2_t;

    //상태를 표시하는 '상수' 지정
    //- 각각의 숫자는 독립적인 개별 '상태' 의미
    public static final int INIT = 0;//처음
    public static final int RUN = 1;//실행중
    public static final int PAUSE = 2;//정지

    public static final int INIT2 = 3;//처음
    public static final int RUN2 = 4;//실행중
    public static final int PAUSE2 = 5;//정지

    //상태값을 저장하는 변수
    //- INIT은 초기값임, 그걸 status 안에 넣는다.(0을 넣은거다)
    public static int status = INIT;
    public static int status_h=INIT;
    public static int status2 = INIT2;
    public static int status2_h = INIT2;

    //기록할때 순서 체크를 위한 변수
    private int cnt = 1;
    private int cnt2 = 1;

    //타이머 시간 값을 저장할 변수
    private long baseTime,baseTime2,pauseTime,pauseTime2;
    CountDownTimer countdown=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        context_main = this;

        total=0;

        //객체화
        timer = (TextView)findViewById(R.id.timer);
        // timer2 = (TextView)findViewById(R.id.timer2);
        timer3 = (TextView)findViewById(R.id.timer3);
        scrollView = (ScrollView)findViewById(R.id.scrollView);
        //scrollView2 = (ScrollView)findViewById(R.id.scrollView2);
        record = (TextView)findViewById(R.id.record);
        //record2 = (TextView)findViewById(R.id.record2);
        bt_sta = (Button)findViewById(R.id.bt_sta);
        //bt_sta2 = (Button)findViewById(R.id.bt_sta2);
        bt_rec = (Button)findViewById(R.id.bt_rec);
        // bt_rec2 = (Button)findViewById(R.id.bt_rec2);
        bt_rec.setEnabled(true);//비활성화
        //bt_rec2.setEnabled(true);//비활성화
        bt_rec_t = (Button)findViewById(R.id.bt_rec_t);
        //bt_rec2_t = (Button)findViewById(R.id.bt_rec2_t);
        bt_rec_t.setEnabled(true);//비활성화
        // bt_rec2_t.setEnabled(true);//비활성화

        bt_sta.setOnClickListener(onClickListener);
        // bt_sta2.setOnClickListener(onClickListener);
        bt_rec.setOnClickListener(onClickListener);
        // bt_rec2.setOnClickListener(onClickListener);
        bt_rec_t.setOnClickListener(onClickListener);
        //bt_rec2_t.setOnClickListener(onClickListener);

        timer3.setText(getTime3());



    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.bt_sta:
                    staButton();
                    break;
                case R.id.bt_rec:
                    recButton();
                    break;
                // case R.id.bt_sta2:
                // staButton2();
                // break;
                //case R.id.bt_rec2:
                // recButton2();
                // break;
                case R.id.bt_rec_t:
                    recButton_t();
                    break;
                //case R.id.bt_rec2_t:
                //  recButton2_t();
                //break;

            }


        }
    };



    private void staButton(){


        switch (status){
            case INIT:
                //어플리케이션이 실행되고 나서 실제로 경과된 시간...
                baseTime = SystemClock.elapsedRealtime();

                System.out.println();


                //핸들러 실행
                handler.sendEmptyMessage(0);
                bt_sta.setText("멈춤");
                // bt_rec.setEnabled(true);
                //bt_rec_t.setEnabled(true);
                //상태 변환
                status = RUN;
                break;
            case RUN:
                //핸들러 정지
                handler.removeMessages(0);

                //정지 시간 체크
                pauseTime = SystemClock.elapsedRealtime();

                bt_sta.setText("시작");
                // bt_rec.setText("30초 휴식");

                //상태변환
                status = PAUSE;


                break;




            case PAUSE:


                long reStart = SystemClock.elapsedRealtime();
                baseTime += (reStart - pauseTime);

                //String timeList = record.getText().toString();


                handler.sendEmptyMessage(0);

                bt_sta.setText("멈춤");
                //bt_rec.setText("기록");

                status = RUN;


        }

    }

    private void recButton(){

        countdown = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                bt_rec.setText("30초 휴식중");

            }

            @Override
            public void onFinish() {
                //System.out.println("stop");
                bt_rec.setText("30초 휴식");
                //status=PAUSE;
                staButton();


            }
        }.start();




    }
    private void recButton_t(){


        countdown=new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                bt_rec_t.setText("1분 휴식중");

            }

            @Override
            public void onFinish() {
                bt_rec_t.setText("1분 휴식");
                staButton();
            }
        }.start();





    }



    public String getTime(){
        //경과된 시간 체크

        long nowTime = SystemClock.elapsedRealtime();
        //시스템이 부팅된 이후의 시간?
        long overTime = nowTime - baseTime;

        long m = overTime/1000/60;
        long s = (overTime/1000)%60;
        long ms = overTime % 1000;

        //System.out.println("s : %02d"+s);

        String S=String.format("%02d",s);
        //String S2=String.format("%03d",ms);
        //System.out.println(S2);
        //System.out.println(S);

        /*
        if(S.equals("03") ){
            //System.out.println("03맞음");
            status=RUN;
            staButton();

            new AlertDialog.Builder(TimerActivity.this) // TestActivity 부분에는 현재 Activity의 이름 입력.
                    .setMessage("기록 측정 중이 맞으신가요?")     // 제목 부분 (직접 작성)
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {      // 버튼1 (직접 작성)
                        public void onClick(DialogInterface dialog, int which){
                            Toast.makeText(getApplicationContext(), "확인 누름", Toast.LENGTH_SHORT).show(); // 실행할 코드
                            status=PAUSE;
                            staButton();

                        }
                    })
                    .setNegativeButton("취소", new DialogInterface.OnClickListener() {     // 버튼2 (직접 작성)
                        public void onClick(DialogInterface dialog, int which){
                            Toast.makeText(getApplicationContext(), "취소 누름", Toast.LENGTH_SHORT).show(); // 실행할 코드
                        }
                    })
                    .show();

        }

*/
        String recTime = String.format("%02d:%02d:%03d",m,s,ms);
        machine_record=recTime;



        total2=overTime;


        return recTime;
    }
    public String getTime3(){
        //경과된 시간 체크

        total+=total2;
        long m2 = total/1000/60;
        long s2 = (total/1000)%60;
        long ms2 = total % 1000;

        String totaltime = String.format("%02d:%02d:%03d",m2,s2,ms2);

        return totaltime;


    }




    Handler handler = new Handler(){

        @Override
        public void handleMessage(@NonNull Message msg) {
            //System.out.println(getTime().substring(3));


            timer.setText(getTime());
            //timer3.setText(getTime3());


            handler.sendEmptyMessage(0);


        }
    };


}