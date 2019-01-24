package com.example.jsu.lab2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.*;
import java.util.Random;

public class RockPaperScissorsActivity extends AppCompatActivity {

    public enum Weapon {

        ROCK("Rock"),
        PAPER("Paper"),
        SCISSORS("Scissors");

        private String message;

        private Weapon(String msg) { message = msg; }

        @Override
        public String toString() { return message; }

    };

    private Weapon playerWeapon;
    private Weapon computerWeapon;

    private int playerScore;
    private int computerScore;

    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rock_paper_scissors);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        playerScore = computerScore = 0;
        random = new Random();

        updateScoreBoard();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rock_paper_scissors, menu);
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

    private void updateScoreBoard(){
        TextView scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        scoreTextView.setText("Player: " + playerScore + ", " + "Computer: " + computerScore);
    }

    public void playerPicksRock(View v){
        playerWeapon = Weapon.ROCK;
        updatePlayerWeaponTextView();
        playHand();
    }

    public void playerPicksPaper(View v){
        playerWeapon = Weapon.PAPER;
        updatePlayerWeaponTextView();
        playHand();
    }

    public void playerPicksScissors(View v){
        playerWeapon = Weapon.SCISSORS;
        updatePlayerWeaponTextView();
        playHand();
    }

    public void updatePlayerWeaponTextView(){
        TextView playerTextView = (TextView) findViewById(R.id.playerWeaponTextView);
        playerTextView.setText("Player's Weapon: " + playerWeapon.toString());
    }

    public void playHand(){
        generateComputerWeapon();

        if (playerWeapon == Weapon.ROCK){
            if (computerWeapon == Weapon.PAPER){
                setComputerWin("Paper Covers Rock");
            }
            else if (computerWeapon == Weapon.SCISSORS){
                setPlayerWin("Rock Smashes Scissors");
            }
            else{
                setDraw();
            }
        }
        else if (playerWeapon == Weapon.SCISSORS){
            if (computerWeapon == Weapon.PAPER){
                setPlayerWin("Scissors Cuts Paper");
            }
            else if (computerWeapon == Weapon.SCISSORS){
                setDraw();
            }
            else{
                setComputerWin("Rock Smashes Scissors");
            }
        }
        else{
            if (computerWeapon == Weapon.PAPER){
                setDraw();
            }
            else if (computerWeapon == Weapon.SCISSORS){
                setComputerWin("Scissors Cuts Paper");
            }
            else{
                setPlayerWin("Paper Covers Rock");
            }
        }

        updateScoreBoard();
    }

    public void generateComputerWeapon(){
        int weaponNumber = random.nextInt(3);
        switch(weaponNumber){
            case 0: computerWeapon = Weapon.ROCK;
                break;
            case 1: computerWeapon = Weapon.PAPER;
                break;
            case 2: computerWeapon = Weapon.SCISSORS;
                break;
            default: computerWeapon = Weapon.ROCK;
        }

        TextView computerTextView = (TextView) findViewById(R.id.computerWeaponTextView);
        computerTextView.setText("Computer's Weapon: " + computerWeapon.toString());
    }

    public void setPlayerWin(String message){
        updateGameResult("Player wins... " + message);
        playerScore++;
    }

    public void setComputerWin(String message){
        updateGameResult("Computer wins... " + message);
        computerScore++;
    }

    public void setDraw(){
        updateGameResult("It's a draw!");
    }

    public void updateGameResult(String result){
        TextView resultTextView = (TextView) findViewById(R.id.resultTextView);
        resultTextView.setText(result);
    }

}
