package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView textView1;
    Button goButton;
    int locationOfCorrectAnswer;
    TextView resultTextView;
    TextView questionTextView;
    Button selectButton0;
    Button selectButton1;
    Button selectButton2;
    Button selectButton3;
    TextView scoreTextView;
    TextView timerTextView;
    Button playAgainButton;
    ConstraintLayout newConstraint;
    int score = 0;
    int numberOfQuestions = 0;
    ArrayList<Integer> answer = new ArrayList<Integer>();

    public void playAgain(View view){
        score = 0;
        numberOfQuestions =0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        newQuestions();
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");
        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();

    }
    public void start(View view){
        textView1.setVisibility(View.INVISIBLE);
        goButton.setVisibility(View.INVISIBLE);
        newConstraint.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }
    public void newQuestions(){
        Random rand = new Random();
        int a= rand.nextInt(21);
        int b = rand.nextInt(21);

        questionTextView.setText(Integer.toString(a) +" " + " +"+ " "+Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);
        answer.clear();
        for (int i=0;i<4;i++)
        {
            if (i==locationOfCorrectAnswer)
            {
                answer.add(a+b);
            }
            else{
                int wrongAnswer = rand.nextInt(41);

                while (wrongAnswer==a+b)
                    wrongAnswer=rand.nextInt(41);

                answer.add(wrongAnswer);
            }
        }
        selectButton0.setText(Integer.toString(answer.get(0)));
        selectButton1.setText(Integer.toString(answer.get(1)));
        selectButton2.setText(Integer.toString(answer.get(2)));
        selectButton3.setText(Integer.toString(answer.get(3)));
    }
    public void choose(View view){

           if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
               resultTextView.setText("Correct :)");
               score++;
           }
           else {
               resultTextView.setText("Wrong :(");
           }
           numberOfQuestions++;
           scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
           newQuestions();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView) findViewById(R.id.textView1);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        questionTextView =(TextView) findViewById(R.id.questionTextView);
         selectButton0 = (Button) findViewById(R.id.selectButton0);
         selectButton1 = (Button) findViewById(R.id.selectButton1);
         selectButton2 = (Button) findViewById(R.id.selectButton2);
         selectButton3 = (Button) findViewById(R.id.selectButton3);
        goButton = (Button) findViewById(R.id.goButton);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
         newConstraint = findViewById(R.id.newConstraint);
         goButton.setVisibility(View.VISIBLE);
         textView1.setVisibility(View.VISIBLE);
         newConstraint.setVisibility(View.INVISIBLE);


    }
}