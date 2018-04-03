package com.example.android.brainpractice;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    CountDownTimer timer;
    Random random=new Random();
    TextView islemText;
    ArrayList<Integer> cevaplar=new ArrayList<Integer>();
    Button button1,button2,button3,button4;
    int dogru=0,toplamSoru=0;
    int trueLocofAnswer;
    TextView truFal;
    TextView Oran;
    Button restart;
    TextView sure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sure=findViewById(R.id.sure);
        islemText=findViewById(R.id.islem);
        restart=findViewById(R.id.restart);
        restart.setVisibility(View.INVISIBLE);

        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.button4);
        truFal=findViewById(R.id.trueFalse);
        Oran=findViewById(R.id.oran);
        islem();
        time();

    }
        public void islem()
        {
            trueLocofAnswer=random.nextInt(4);
            int a=random.nextInt(21);
            int b=random.nextInt(21);
            islemText.setText(a+"+"+b);
            for(int i=0;i<4;i++)
            {

                if(i==trueLocofAnswer){
                    cevaplar.add(a+b);
                }
                else{
                    int wrongAnswer=random.nextInt(41);
                    while (wrongAnswer==a+b){
                        wrongAnswer=random.nextInt(41);
                    }
                    cevaplar.add(wrongAnswer);
                }

            }
            button1.setText(Integer.toString(cevaplar.get(0)));
            button2.setText(Integer.toString(cevaplar.get(2)));
            button3.setText(Integer.toString(cevaplar.get(1)));
            button4.setText(Integer.toString(cevaplar.get(3)));
        }
        public void cevapVer(View view)
        {
            if(Integer.toString(trueLocofAnswer).equals(view.getTag()))
            {

                truFal.setText("TRUE");
                cevaplar.clear();
               // Log.i("bilg",Integer.toString(trueLocofAnswer)+"d");
                dogru++;
                toplamSoru++;
                Oran.setText(dogru+"/"+toplamSoru);
                Log.i("bilg", "Dogru");
                islem();

            }
            else
            {
                truFal.setText("FALSE");
                cevaplar.clear();

                toplamSoru++;
                Oran.setText(dogru+"/"+toplamSoru);
                Log.i("bilg", "Yanlis");
                islem();
            }

        }

        public void  time()
        {
            timer=new CountDownTimer(30000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    sure.setText(Long.toString(millisUntilFinished/1000));
                }

                @Override
                public void onFinish() {
                    restart.setVisibility(View.VISIBLE);
                }
            }.start();
        }
        public  void restart(View view)
        {
            restart.setVisibility(View.INVISIBLE);
            dogru=0;
            toplamSoru=0;
            Oran.setText(dogru+"/"+toplamSoru);
            time();

        }
}
