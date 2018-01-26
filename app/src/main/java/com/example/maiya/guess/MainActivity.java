package com.example.maiya.guess;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    TextView tv;
    TextView answerTextView;
    TextView textViewScore,textViewTimer;
    int attemptedQuestion;
    int correctSelectedAnswer;
    Button button1,button2,button3,button4,playAgain;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int correctPosition;

    public void playAgain(View view){
        playAgain.setVisibility(View.INVISIBLE);
        attemptedQuestion=0;
        correctSelectedAnswer=0;
        answerTextView.setText("");
        textViewScore.setText("0/0");
        textViewTimer.setText("30s");
        generateQuestion();
        //For timer start
        new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long l) {
                textViewTimer.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                textViewTimer.setText("0s");
                answerTextView.setText("Final Score:"+Integer.toString(correctSelectedAnswer)+"/"+Integer.toString(attemptedQuestion));
                playAgain.setVisibility(View.VISIBLE);

            }
        }.start();

    }

    public void generateQuestion(){
        int wrongAnswer;
        Random rand = new Random();
        int a = rand.nextInt(30);
        int b = rand.nextInt(30);
        tv.setText(a+"+"+b);


        answers.clear();
        correctPosition = rand.nextInt(4);
        for(int i=0; i<4; i++){
            if(i == correctPosition){
                answers.add(a+b);
            }else{
                wrongAnswer = rand.nextInt(40);
                while(wrongAnswer == a+b){
                    wrongAnswer = rand.nextInt(40);
                }
                answers.add(wrongAnswer);
            }
        }
        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));
    }

    public void goTo(View view){
        goButton.setVisibility(View.INVISIBLE);
    }

    public void chooseAnswer(View view){
        attemptedQuestion++;
        if(view.getTag().toString().equals(Integer.toString(correctPosition))){
            answerTextView.setText("Correct!");
            correctSelectedAnswer++;
        }else{
            answerTextView.setText("Wrong!");
        }
            textViewScore.setText(Integer.toString(correctSelectedAnswer)+"/"+Integer.toString(attemptedQuestion));
            generateQuestion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton = (Button)findViewById(R.id.buttonGo);
         button1 = (Button)findViewById(R.id.buttonAns1);
         button2 = (Button)findViewById(R.id.buttonAns2);
         button3 = (Button)findViewById(R.id.buttonAns3);
         button4 = (Button)findViewById(R.id.buttonAns4);
         playAgain = (Button)findViewById(R.id.playAgainButton);
        tv = (TextView)findViewById(R.id.textViewQuestion);
        textViewScore = (TextView)findViewById(R.id.textViewScore);
        textViewTimer= (TextView)findViewById(R.id.textViewTimer);
        answerTextView = (TextView)findViewById(R.id.textViewAnswer);
        //generateQuestion();
        playAgain(answerTextView);//Arguement is simply added to satisfy above method

    }
}
