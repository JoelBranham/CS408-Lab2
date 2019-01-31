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

import java.util.*;

public class RockPaperScissorsActivity extends AppCompatActivity {

    public enum Weapon {
        ROCK("Rock", 0),
        PAPER("Paper", 1),
        SCISSORS("Scissors", 2);

        private String message;
        private int weaponNumber;

        private Weapon(String msg, int wpnNum) {
            message = msg;
            weaponNumber = wpnNum;
        }

        @Override
        public String toString() { return message; }

        public int getWeaponNumber(){
            return weaponNumber;
        }
    }

    private Weapon playerWeapon;
    private Weapon computerWeapon;

    private String weaponResultMessage;
    private String gameResultMessage;

    private Map weaponToAdjective;

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

        weaponToAdjective = new HashMap<Weapon, String>();
        weaponToAdjective.put(Weapon.ROCK, "Blunts");
        weaponToAdjective.put(Weapon.PAPER, "Covers");
        weaponToAdjective.put(Weapon.SCISSORS, "Cuts");

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

        determineWinner();

        updateScoreTextView();
        updatePlayerWeaponTextView();
        updateComputerWeaponTextView();
        updateGameResultTextView();
    }

    public void determineWinner(){
        int playerWeaponNumber = playerWeapon.getWeaponNumber();
        int computerWeaponNumber = computerWeapon.getWeaponNumber();

        if (playerWeaponNumber == computerWeaponNumber){
            gameResultMessage = "It's a draw...";
            weaponResultMessage = "";
        }
        else if ((computerWeaponNumber + 1) % 3  == playerWeaponNumber){
            gameResultMessage = "Player wins...";
            playerScore++;
            weaponResultMessage = playerWeapon + " " + weaponToAdjective.get(playerWeapon) + " " + computerWeapon;
        }
        else{
            gameResultMessage = "Computer wins...";
            computerScore++;
            weaponResultMessage = computerWeapon + " " + weaponToAdjective.get(computerWeapon) + " " + playerWeapon;
        }
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

    private void updatePlayerWeaponTextView(){
        TextView playerTextView = findViewById(R.id.playerWeaponTextView);
        String playerString = "Player's Weapon: " + playerWeapon;
        playerTextView.setText(playerString);
    }

    private void updateComputerWeaponTextView(){
        TextView computerTextView = findViewById(R.id.computerWeaponTextView);
        String computerString = "Computer's Weapon: " + computerWeapon;
        computerTextView.setText(computerString);
    }

    private void updateGameResultTextView(){
        TextView resultTextView = findViewById(R.id.resultTextView);
        String resultString = gameResultMessage + " " + weaponResultMessage;
        resultTextView.setText(resultString);
    }

    private void updateScoreTextView(){
        TextView scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        String scoreString = "Player: " + playerScore + ", " + "Computer: " + computerScore;
        scoreTextView.setText(scoreString);
    }

}