package com.example.catchthekennyreview;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timeText;
    TextView scoreText;

    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView imageView10;
    ImageView imageView11;
    ImageView imageView12;

    ImageView[] imageArray;

    String[] stringArray;

    int score;

    Handler handler;
    Runnable runnable;
    AlertDialog.Builder alert;
    AlertDialog.Builder alert2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize
        timeText = findViewById(R.id.timeText);
        scoreText = findViewById(R.id.scoreText);
        score =0;




        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        imageView10 = findViewById(R.id.imageView10);
        imageView11 = findViewById(R.id.imageView11);
        imageView12 = findViewById(R.id.imageView12);

        /*stringArray = new  String[]{"batur","ali","osman"};
        for(String batur : stringArray){

            System.out.println("names : "+batur);

        } */

        imageArray = new ImageView[]{imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10,imageView11,imageView12};

        hideImages();

        //Timer ;

        new CountDownTimer(15000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

              timeText.setText("Time : "+millisUntilFinished/1000);  //set timer time to timetext

            }

            @Override
            public void onFinish() {

                timeText.setText("Time : 0");
                handler.removeCallbacks(runnable);

                for(ImageView image : imageArray){

                    image.setVisibility(View.INVISIBLE);

                }

                alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart ?");
                alert.setMessage("Play again ?");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);

                    }
                });

                alert.setNegativeButton("NO!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(MainActivity.this,"Game Over!",Toast.LENGTH_LONG).show();

                        for(ImageView imageStop : imageArray){

                            imageStop.setVisibility(View.INVISIBLE);

                            alert2 = new AlertDialog.Builder(MainActivity.this);
                            alert2.setTitle("SCORE");
                            alert2.setMessage("Your"+(scoreText.getText().toString()));
                            alert2.show();
                        }



                    }
                });

                alert.show();


            }
        }.start();

    }

    public void increaseScore(View view){

        score++;
        scoreText.setText("Score : "+score);

    }

    public void hideImages(){

        for(ImageView image : imageArray){

            image.setVisibility(View.INVISIBLE);

        }

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                for(ImageView image : imageArray){

                    image.setVisibility(View.INVISIBLE);

                }

                Random random = new Random();

                int i = random.nextInt(12);
                System.out.println("Random numbers : "+i);

                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(runnable,500);
            }
        };

        handler.post(runnable);




    }
}
