
package com.google.engedu.ghost;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;


public class GhostActivity extends AppCompatActivity {
    private static final String COMPUTER_TURN = "Computer's turn";
    private static final String USER_TURN = "Your turn";
    private GhostDictionary dictionary;
    private boolean userTurn = false;
    private Random random = new Random();
    private TextView Word, Stat;
    private SimpleDictionary simpleDictionary;
    //private FastDictionary fastDictionary;
    Button Restart,Challenge;
    Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost);
        AssetManager assetManager = getAssets();
        Restart =(Button) findViewById(R.id.restart);
        Challenge =(Button) findViewById(R.id.bluff);
        Word = (TextView) findViewById(R.id.ghostText);
        Stat = (TextView) findViewById(R.id.gameStatus);
        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/
        Restart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //restart
                Word.setText("");
                Stat.setText("");
                Stat.setTextColor(Color.BLUE);
                onStart(null);
            }
        });
        /*Challenge.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //restart

            }
        });
*/
        try {
            InputStream inputStream = assetManager.open("words.txt");
            simpleDictionary = new SimpleDictionary(inputStream);
        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Could not load dictionary", Toast.LENGTH_LONG);
            toast.show();
        }
        onStart(null);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Handler for the "Reset" button.
     * Randomly determines whether the game starts with a user turn or a computer turn.
     * @param view
     * @return true
     */
    public boolean onStart(View view) {
        userTurn = random.nextBoolean();
        //TextView text = (TextView) findViewById(R.id.ghostText);
        Word.setText("");
        //TextView Stat = (TextView) findViewById(R.id.gameStatus);
        if (userTurn) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                }
            }, 5000);
            Stat.setTextColor(Color.BLUE);
            Stat.setText(USER_TURN);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                }
            }, 5000);
            //Stat.setText(USER_TURN);
            //Log.d("TAG","fukyu");
        } else {handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 5000);
            Stat.setTextColor(Color.BLUE);
            Stat.setText(COMPUTER_TURN);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                }
            }, 2000);
            computerTurn();
        }
        return true;
    }

    private void computerTurn() {
        String fragment = Word.getText().toString();
        String nextChar;
        // Do computer turn stuff then make it the user's turn again
        if (fragment.length() >= 3 && simpleDictionary.isWord(fragment)) {
            Stat.setTextColor(Color.RED);
            Stat.setText("You Lost");
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                }
            }, 5000);
            check();
            //Toast.makeText(getApplicationContext(), "User Lost", Toast.LENGTH_SHORT).show();
        } else {
            nextChar = simpleDictionary.getAnyWordStartingWith(fragment);
            if (nextChar == null) {
                Stat.setTextColor(Color.RED);
                Stat.setText("You Lost");handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 5000);
                check();
                //Toast.makeText(getApplicationContext() ,"Computer Wins\nHuh! you bluffed!",Toast.LENGTH_SHORT).show();
                //  return;
            } else {
                addTextToWord(nextChar.charAt(fragment.length()));
                fragment = Word.getText().toString();
                if (fragment.length() >= 3 && simpleDictionary.isWord(fragment)) {
                    Stat.setTextColor(Color.GREEN);
                    Stat.setText("Computer Lost");
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                        }
                    }, 50000);

                    check();

                }

            }
            userTurn = true;
            Stat.setText(USER_TURN);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                }
            }, 5000);
        }
    }

    public void check(){
            Stat.setText("Starting new game");
            Stat.setTextColor(Color.BLACK);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                }
            },10000);
            Word.setText("");
            onStart(null);
        }

    /**
     * Handler for user key presses.
     * @param keyCode
     * @param event
     * @return whether the key stroke was handled.
     */
    @Override

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        keyCode=(char)event.getUnicodeChar();
        if ((keyCode<='z' && keyCode>='a')||(keyCode<='Z' && keyCode>'A')){
            addTextToWord((char)keyCode);
            Stat.setText(COMPUTER_TURN);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                }
            }, 2000);
            userTurn=false;
            computerTurn();
        }
        /*if (event.getUnicodeChar()==KeyEvent.KEYCODE_SPACE){
            finish();
            moveTaskToBack(true);
        }*/
        return super.onKeyUp(keyCode, event);
    }

    public void addTextToWord(char c){
        Word.setText(Word.getText().toString() + c );
    }
}
