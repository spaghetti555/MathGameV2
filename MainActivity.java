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
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button answerButton1;
    Button answerButton2;
    Button answerButton3;
    Button answerButton4;
    TextView scoreTextView;
    TextView questionTextView;
    Handler handler;
    int finalAnswer, score = 0;
    Runnable repeatedDelayRunnable;
    ProgressBar timeProgressBar;
    int max_timeProgressBar = 20;
    int delayTimeInMiliSeconds=500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        answerButton1 = (Button) findViewById(R.id.answer1);
        answerButton2 = (Button) findViewById(R.id.answer2);
        answerButton3 = (Button) findViewById(R.id.answer3);
        answerButton4 = (Button) findViewById(R.id.answer4);

        timeProgressBar = (android.widget.ProgressBar) findViewById(R.id.ProgressBar);


        handler = new Handler();
        timeProgressBar.setProgress(max_timeProgressBar);
        repeatedDelayRunnable = new Runnable() {
            @Override
            public void run() {
                timeProgressBar.setProgress(timeProgressBar.getProgress() - 1);
                if (timeProgressBar.getProgress() == 0) {
                    handler.removeCallbacks(repeatedDelayRunnable);
                    GameOver(getString(R.string.OutOfTimeMessage));
                } else {
                    handler.postDelayed(this, delayTimeInMiliSeconds);
                }

            }
        };

        handler.postDelayed(repeatedDelayRunnable, delayTimeInMiliSeconds);


        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        questionTextView = (TextView) findViewById(R.id.questionTextView);

        SetQuestion();

        answerButton1.setOnClickListener(this);
        answerButton2.setOnClickListener(this);
        answerButton3.setOnClickListener(this);
        answerButton4.setOnClickListener(this);


    }


    private void SetQuestion() {

        Random rand = new Random();
        int num1, num2, answer;

        num1 = rand.nextInt(50);
        num2 = rand.nextInt(50);
        answer = num1 + num2;

        questionTextView.setText(num1 + " + " + num2);

        switch (GetCorrectAnswerButtonAnswer()) {
            case 1: {
                answerButton1.setText(answer + "");
                answerButton2.setText(GetRandomOtherAnswers() + "");
                answerButton3.setText(GetRandomOtherAnswers() + "");
                answerButton4.setText(GetRandomOtherAnswers() + "");
                break;
            }
            case 2: {
                answerButton1.setText(GetRandomOtherAnswers() + "");
                answerButton2.setText(answer + "");
                answerButton3.setText(GetRandomOtherAnswers() + "");
                answerButton4.setText(GetRandomOtherAnswers() + "");
                break;
            }
            case 3: {
                answerButton1.setText(GetRandomOtherAnswers() + "");
                answerButton2.setText(GetRandomOtherAnswers() + "");
                answerButton3.setText(answer + "");
                answerButton4.setText(GetRandomOtherAnswers() + "");
                break;
            }
            default: {
                answerButton1.setText(GetRandomOtherAnswers() + "");
                answerButton2.setText(GetRandomOtherAnswers() + "");
                answerButton3.setText(GetRandomOtherAnswers() + "");
                answerButton4.setText(answer + "");
                break;
            }
        }


        finalAnswer = answer;
    }

    private void GameOver(String CauseOfLosing) {
        handler.removeCallbacks(repeatedDelayRunnable);
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


    private int GetCorrectAnswerButtonAnswer() {
        Random r = new Random();
        return (r.nextInt(4) + 1);
    }

    private int GetRandomOtherAnswers() {
        Random r = new Random();
        return r.nextInt(100);
    }

    @Override
    public void onClick(View v) {


        if (Integer.parseInt(((Button)v).getText().toString()) == finalAnswer) {
            score += timeProgressBar.getProgress();
            scoreTextView.setText("Score: " + score);
            SetQuestion();
            timeProgressBar.setProgress(max_timeProgressBar);
        } else {
            GameOver(getString(R.string.WrongAnswerMessage));

        }

    }
}
