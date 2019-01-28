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

import java.util.HashMap;
import java.util.Map;
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

    public enum Result {

        PLAYER_WIN("Player wins..."),
        COMPUTER_WIN("Computer wins..."),
        DRAW("It's a draw...");

        private String resultMessage;

        private Result(String msg) { resultMessage = msg; }

        public String getWinnerMessage() {return resultMessage;}
    }

    private Weapon playerWeapon;
    private Weapon computerWeapon;

    private Result gameResult;
    private String weaponResultMessage;

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

        updateScoreTextView();
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

    private void updateTextViews(){
        updateScoreTextView();

        TextView playerTextView = (TextView) findViewById(R.id.playerWeaponTextView);
        String playerString = "Player's Weapon: " + playerWeapon.toString();
        playerTextView.setText(playerString);

        TextView computerTextView = (TextView) findViewById(R.id.computerWeaponTextView);
        String computerString = "Computer's Weapon: " + computerWeapon.toString();
        computerTextView.setText(computerString);

        TextView resultTextView = (TextView) findViewById(R.id.resultTextView);
        String resultString = gameResult.getWinnerMessage() + " " + weaponResultMessage;
        resultTextView.setText(resultString);
    }

    private void updateScoreTextView(){
        TextView scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        String scoreString = "Player: " + playerScore + ", " + "Computer: " + computerScore;
        scoreTextView.setText(scoreString);
    }

    public void playerPicksRock(View v){
        playerWeapon = Weapon.ROCK;
        playHand();
    }

    public void playerPicksPaper(View v){
        playerWeapon = Weapon.PAPER;
        playHand();
    }

    public void playerPicksScissors(View v){
        playerWeapon = Weapon.SCISSORS;
        playHand();
    }


    public void playHand(){
        generateComputerWeapon();

        if (playerWeapon == Weapon.ROCK){
            if (computerWeapon == Weapon.PAPER){
                setComputerWin();
                weaponResultMessage = "Paper Covers Rock";
            }
            else if (computerWeapon == Weapon.SCISSORS){
                setPlayerWin();
                weaponResultMessage = "Rock Smashes Scissors";
            }
            else{
                setDraw();
                weaponResultMessage = "";
            }
        }
        else if (playerWeapon == Weapon.SCISSORS){
            if (computerWeapon == Weapon.PAPER){
                setPlayerWin();
                weaponResultMessage = "Scissors Cuts Paper";
            }
            else if (computerWeapon == Weapon.SCISSORS){
                setDraw();
                weaponResultMessage = "";
            }
            else{
                setComputerWin();
                weaponResultMessage = "Rock Smashes Scissors";
            }
        }
        else{
            if (computerWeapon == Weapon.PAPER){
                setDraw();
                weaponResultMessage = "";
            }
            else if (computerWeapon == Weapon.SCISSORS){
                setComputerWin();
                weaponResultMessage = "Scissors Cuts Paper";
            }
            else{
                setPlayerWin();
                weaponResultMessage = "Paper Covers Rock";
            }
        }

        updateTextViews();
    }

    public void setPlayerWin(){
        playerScore++;
        gameResult = Result.PLAYER_WIN;
    }

    public void setComputerWin(){
        computerScore++;
        gameResult = Result.COMPUTER_WIN;
    }

    public void setDraw(){
        gameResult = Result.DRAW;
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
    }

}