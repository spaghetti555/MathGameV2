package com.example.home.mathgamev2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button answer1,answer2,answer3,answer4;
    TextView scoreTextView, questionTextView;
    Handler handler;
    int finalAnswer, score=0;
    Runnable run1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//Why cant you make the buttons and textviews here? have to do it up there so i can use them in the method

         answer1 = (Button) findViewById(R.id.answer1);
         answer2 = (Button) findViewById(R.id.answer2);
         answer3 = (Button) findViewById(R.id.answer3);
         answer4 = (Button) findViewById(R.id.answer4);

        final ProgressBar progress = (android.widget.ProgressBar) findViewById(R.id.ProgressBar);


       handler = new Handler();
        progress.setProgress(20);
        run1 = new Runnable() {
            @Override
            public void run() {
                progress.setProgress(progress.getProgress()-1);
                if (progress.getProgress()==0)
                {
                    handler.removeCallbacks(run1);
                    GameOver("You ran out of time!");
                }
                else{
                    handler.postDelayed(this,500);
                }

            }
        };

        handler.postDelayed(run1,500);






        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        questionTextView = (TextView) findViewById(R.id.questionTextView);

        SetQuestion();

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(Integer.parseInt(answer1.getText().toString())==finalAnswer)
                    {
                       score +=progress.getProgress();
                        scoreTextView.setText("Score: "+score);
                        SetQuestion();
                        progress.setProgress(20);
                    }
                    else
                        {
                            GameOver("Wrong answer!");

                        }
            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(answer2.getText().toString())==finalAnswer)
                {
                    score +=progress.getProgress();
                    scoreTextView.setText("Score: "+score);
                    SetQuestion();
                    progress.setProgress(20);
                }
                else
                {
                    GameOver("Wrong answer!");

                }
            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(answer3.getText().toString())==finalAnswer)
                {
                    score +=progress.getProgress();
                    scoreTextView.setText("Score: "+score);
                    SetQuestion();
                    progress.setProgress(20);
                }
                else
                {
                    GameOver("Wrong answer!");

                }
            }
        });
        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(answer4.getText().toString())==finalAnswer)
                {
                    score +=progress.getProgress();
                    scoreTextView.setText("Score: "+score);
                    SetQuestion();
                    progress.setProgress(20);
                }
                else
                {
                    GameOver("Wrong answer!");

                }
            }
        });
    }






    private void SetQuestion()
    {

        Random rand = new Random();
        int num1, num2, answer;

        num1 = rand.nextInt(50);
        num2 = rand.nextInt(50);
        answer=num1+num2;

        questionTextView.setText(num1 + " + " + num2);

        switch (rand.nextInt(4)+1)
        {
            case 1:
            {
                answer1.setText(answer+"");
                answer2.setText(rand.nextInt(100)+"");
                answer3.setText(rand.nextInt(100)+"");
                answer4.setText(rand.nextInt(100)+"");
            }
            case 2:
            {
                answer1.setText(rand.nextInt(100)+"");
                answer2.setText(answer+"");
                answer3.setText(rand.nextInt(100)+"");
                answer4.setText(rand.nextInt(100)+"");
            }
            case 3:
            {
                answer1.setText(rand.nextInt(100)+"");
                answer2.setText(rand.nextInt(100)+"");
                answer3.setText(answer+"");
                answer4.setText(rand.nextInt(100)+"");
            }
            default:
            {
                answer1.setText(rand.nextInt(100)+"");
                answer2.setText(rand.nextInt(100)+"");
                answer3.setText(rand.nextInt(100)+"");
                answer4.setText(answer+"");
            }
        }


        finalAnswer=answer;
    }

    private void GameOver(String CauseOfLosing)
    {
        handler.removeCallbacks(run1);
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("You lost!");
        alert.setMessage("Game Over! Your score is: " + score + " points.");
                alert.setCancelable(false);
                alert.setPositiveButton("New game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                });
               alert.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    finish();
                    }
                });

        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }



}
