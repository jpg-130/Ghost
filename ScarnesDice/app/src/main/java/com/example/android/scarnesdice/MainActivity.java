package com.example.android.scarnesdice;

import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    Button b_roll,b_hold, b_reset;
    ImageView iv_dice;
    Random r;
    int rollednumber;
    int turn=1;
    int tries=2;
    int userturnscore=0;
    int computerturnscore=0;
    int userscore=0;
    int computerscore=0;
    public int winningscore=20;
    TextView user,computer;
    //long startTime = 0;

    private Animation animate;
    private RotateAnimation rotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*animate=new Animation() {
            @Override
            protected Animation clone() throws CloneNotSupportedException {
                rotate=new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                rotate.setDuration(2000);
                return super.clone();
            }
        };

        */

        user=(TextView)findViewById(R.id.your_score_display) ;
        computer=(TextView)findViewById(R.id.computer_score_display) ;

        b_roll=(Button) findViewById(R.id.roll);
        b_hold=(Button) findViewById(R.id.hold);
        b_reset=(Button) findViewById(R.id.reset);
        iv_dice= (ImageView) findViewById(R.id.diceimg);
        r = new Random();
        user.setText(" "+userscore);
        computer.setText(" "+computerscore);
        turn=1;
        /*while (!gameover()){
            try {
                switchturns();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        try {
            switchturns();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void switchturns() throws InterruptedException {
        if(!gameover()){
            if(turn==1){
                turn=0;
                userturn();
            }
            else {
                turn=1;
                computerturn();
            }
        }
        else {
            if (userscore>computerscore)
            {
                Toast.makeText(MainActivity.this, "YOU WIN", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(MainActivity.this, "COMPUTER WINS", Toast.LENGTH_SHORT).show();
                //delay
                //reset();
            }
        }
    }

    public void computerturn() throws InterruptedException {
        rollednumber =r.nextInt(6)+1;
        computerturnscore=rollednumber;
        Toast.makeText(MainActivity.this, "Computer Rolled!", Toast.LENGTH_SHORT).show();
        if(rollednumber == 1){
            iv_dice.setImageResource(R.drawable.dice1);
            //iv_dice.startAnimation();
            computerturnscore=0;
            //startTime = System.currentTimeMillis();
            //timerHandler.postDelayed(timerRunnable, 0);
            //Toast.makeText(MainActivity.this, "Computers Rolled "+computerturnscore, Toast.LENGTH_SHORT).show();
            //delay()
            Toast.makeText(MainActivity.this, "Computers Turn Ended!", Toast.LENGTH_SHORT).show();
            //turn=1;
            switchturns();
        }
        else if(rollednumber == 2) {
            iv_dice.setImageResource(R.drawable.dice2);
        }
        else if(rollednumber == 3) {
            iv_dice.setImageResource(R.drawable.dice3);
        }
        else if(rollednumber == 4) {
            iv_dice.setImageResource(R.drawable.dice4);
        }
        else if(rollednumber == 5) {
            iv_dice.setImageResource(R.drawable.dice5);
        }
        else if(rollednumber == 6) {
            iv_dice.setImageResource(R.drawable.dice6);
        }

        if (rollednumber!=1)
        {
            //turn=1;
            computerscore+=computerturnscore;
            computer.setText(" "+computerscore);
            switchturns();
        }

    }

    public void userturn(){
        b_roll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                rollednumber =r.nextInt(6)+1;
                userturnscore=rollednumber;

                Toast.makeText(MainActivity.this, "You Rolled!", Toast.LENGTH_SHORT).show();

                if(rollednumber == 1){
                    iv_dice.setImageResource(R.drawable.dice1);
                    Toast.makeText(MainActivity.this, "Turn Ended!", Toast.LENGTH_SHORT).show();
                    //turn=0;
                    try {
                        switchturns();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else if(rollednumber == 2) {
                    iv_dice.setImageResource(R.drawable.dice2);
                }
                else if(rollednumber == 3) {
                    iv_dice.setImageResource(R.drawable.dice3);
                }
                else if(rollednumber == 4) {
                    iv_dice.setImageResource(R.drawable.dice4);
                }
                else if(rollednumber == 5) {
                    iv_dice.setImageResource(R.drawable.dice5);
                }
                else if(rollednumber == 6) {
                    iv_dice.setImageResource(R.drawable.dice6);
                }
                if (userturnscore!=1){
                        b_hold.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                try {
                                    userscore+=userturnscore;
                                    //turn=0;
                                    user.setText(" "+userscore);
                                    switchturns();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                       });
                }
            }
        });



        b_reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //restart
                reset();
            }
        });
    }


    public void reset(){
        userscore=0;
        userturnscore=0;
        computerscore=0;
        computerturnscore=0;
        turn=1;
        user.setText(" "+userscore);
        computer.setText(" "+computerscore);
        iv_dice.setImageResource(R.drawable.dice1);
    }
    public boolean gameover(){
        if(userscore>=winningscore || computerscore>=winningscore){
            return true;
        }
        else
            return false;
    }


}
