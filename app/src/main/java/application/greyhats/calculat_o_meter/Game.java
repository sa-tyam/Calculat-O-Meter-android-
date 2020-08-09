package application.greyhats.calculat_o_meter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Game extends AppCompatActivity {

    TextView questionTextView;
    TextView correctTextView;
    TextView scoreTextView;
    TextView timerTextView;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button playAgainButton;

    Random rand = new Random();
    String max;

    ArrayList<Integer> answers = new ArrayList<Integer>();

    int max_value;
    int correct_answer_tag;
    int correct = 0 ;
    int total = 0 ;

    boolean game_active = true;

    public void PlayAgain (View view ){
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
        finish();
    }

    public void NewQSset () {

        button1.setY(-1000);
        button2.setY(-1000);
        button3.setX(-500);
        button4.setX(500);

        int num1 = rand.nextInt(max_value+1);
        int num2 = rand.nextInt(max_value+1);

        correct_answer_tag = rand.nextInt(4);

        String num1_str = String.valueOf(num1);
        String num2_str = String.valueOf(num2);

        questionTextView.setText(num1_str + "+" + num2_str);

        Log.i("tag" , String.valueOf(correct_answer_tag));

        answers.clear();

        for ( int i = 0; i < 4 ; i++ ) {
            if (i == correct_answer_tag) {
                int ans = num1 + num2;
                answers.add(ans);
            } else {
                int wrans = rand.nextInt(2*max_value);
                while ( wrans == num1 + num2 ){
                    wrans = rand.nextInt(2*max_value);
                }
                answers.add(wrans);
            }
        }
        button1.setText(String.valueOf(answers.get(0)));
        button2.setText(String.valueOf(answers.get(1)));
        button3.setText(String.valueOf(answers.get(2)));
        button4.setText(String.valueOf(answers.get(3)));

        button1.animate().translationY(0).setDuration(100);
        button2.animate().translationY(0).setDuration(100);
        button3.animate().translationX(0).setDuration(100);
        button4.animate().translationX(0).setDuration(100);

    }

    public void AnswerCheck (View view) {
        if (game_active == true) {
            if (Integer.parseInt(view.getTag().toString()) == correct_answer_tag + 1) {
                correct++;
                correctTextView.setText("YOU GOT IT");
            } else {
                correctTextView.setText("INCORRECT");
            }
            total++;
            scoreTextView.setText(String.valueOf(correct) + "/" + String.valueOf(total));
            NewQSset();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        max = getIntent().getStringExtra("maximum");
        max_value = Integer.parseInt(max);

        questionTextView = findViewById(R.id.questionTextView);
        correctTextView = findViewById(R.id.correctTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        button1 = findViewById(R.id.answerButton1);
        button2 = findViewById(R.id.answerButton2);
        button3 = findViewById(R.id.answerButton3);
        button4 = findViewById(R.id.answerButton4);
        playAgainButton = findViewById(R.id.playAgainButton);

        NewQSset();
        correctTextView.setText("");

        CountDownTimer timer = new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }
            @Override
            public void onFinish() {
                timerTextView.setText("0s");
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.horn);
                mediaPlayer.start();
                playAgainButton.setVisibility(View.VISIBLE);
                game_active = false;

            }
        }.start();
    }
}