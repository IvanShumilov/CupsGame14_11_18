package ia.shumilov.ru.cupsgame;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CupsGame extends Activity {

    private ImageView cup_1;
    private ImageView cup_2;
    private ImageView cup_3;
    private ImageView cup_1_up;
    private ImageView cup_2_up;
    private ImageView cup_3_up;
    private ImageView ball_1;
    private ImageView ball_2;
    private ImageView ball_3;
    private View start_btn;
    private View reset_btn;
    private boolean start_state;

    private static final String SAVE_COUNT_CUP = "save_cup";
    private static final String SAVE_SHOW_STATE = "save_show_cup";

    private ImageView[] list_cup;
    private ImageView[] list_ball;
    private ImageView[] list_cup_up;

    private int randomCup = 1;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cups_game);

        cup_1 = findViewById(R.id.cup_1);
        cup_2 = findViewById(R.id.cup_2);
        cup_3 = findViewById(R.id.cup_3);
        cup_1_up = findViewById(R.id.cup_1_up);
        cup_2_up = findViewById(R.id.cup_2_up);
        cup_3_up = findViewById(R.id.cup_3_up);
        ball_1 = findViewById(R.id.ball_1);
        ball_2 = findViewById(R.id.ball_2);
        ball_3 = findViewById(R.id.ball_3);
        start_btn = findViewById(R.id.startBtn);
        reset_btn = findViewById(R.id.resetBtn);

        list_cup = new ImageView[]{cup_1, cup_2, cup_3};
        list_ball = new ImageView[]{ball_1, ball_2, ball_3};
        list_cup_up = new ImageView[]{cup_1_up, cup_2_up, cup_3_up};
        

        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_state = true;
                setInvisible();
                mixed();
            }
        });

        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_state = false;
                randomCup = 1;
                showBall();
            }
        });


        cup_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_state = false;
                showBall();
            }
        });
        cup_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_state = false;
                showBall();
            }
        });
        cup_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_state = false;
                showBall();
            }
        });

        saveState(savedInstanceState);
        showBall();
    }

    private void saveState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            randomCup = savedInstanceState.getInt(SAVE_COUNT_CUP, 1);
            start_state = savedInstanceState.getBoolean(SAVE_SHOW_STATE, false);
        }
    }

    private void setInvisible() {
        for (int  iv = 0; iv < list_cup_up.length; iv ++) {
            list_ball[iv].setVisibility(View.INVISIBLE);
            list_cup_up[iv].setVisibility(View.INVISIBLE);
            list_cup[iv].setVisibility(View.VISIBLE);
        }
    }

    private void mixed() {
        Random random = new Random();
        randomCup = random.nextInt(3);
    }

    private void showBall() {
        if (!start_state) {
            for (int b = 0; b < list_ball.length; b++) {
                if (randomCup == b) {
                    list_ball[b].setVisibility(View.VISIBLE);
                    list_cup[b].setVisibility(View.INVISIBLE);
                    list_cup_up[b].setVisibility(View.VISIBLE);
                } else {
                    list_ball[b].setVisibility(View.INVISIBLE);
                    list_cup_up[b].setVisibility(View.INVISIBLE);
                    list_cup[b].setVisibility(View.VISIBLE);
                }
            }
        } else setInvisible();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVE_COUNT_CUP, randomCup);
        outState.putBoolean(SAVE_SHOW_STATE,start_state);
    }
}
