package com.example.tictoetac;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    // 0 - x
    // 1 - o
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    // State meanings \
    // 0 - x
    // 1 - o
    // 2 - null
    int[][] winPositions = {
                            {0,1,2}, {3,4,5}, {6,7,8},
                            {0,3,6}, {1,4,7}, {2,5,8},
                            {0,4,8}, {2,4,6}
                    };

    public void playerTap(View view) {
        String winnerStr;
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if (gameState[tappedImage] == 2) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-0.01f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.final_x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("Egg's Turn - Tap to play");
            }
            else {
                activePlayer = 0;
                img.setImageResource(R.drawable.final_o);
                TextView status = findViewById(R.id.status);
                status.setText("Square's Turn - Tap to play");
            }
            img.animate().translationYBy(0.0f).setDuration(300);
        }

        // Check if any player has won
        for(int[] winPosition: winPositions){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]]!=2){
                // Somebody has won! - Find out who!
                gameActive = false;
                if(gameState[winPosition[0]] == 0){
                    winnerStr = "Square has won";
                }
                else{
                    winnerStr = "Egg has won";
                }
                // Update the status bar for winner announcement
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }

        // Check if the game ended in draw
        String gameDraw;
        String checkStatus;
        checkStatus = "Draw";
        for(int i=0; i<gameState.length; i++){
            if (gameState[i] == 2) {
                checkStatus = "inProgress";
            }
        }

        if (checkStatus == "Draw" && gameActive ) {
            gameDraw = "Game Draw";
            TextView status = findViewById(R.id.status);
            status.setText(gameDraw);
        }

    }

    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("Square's Turn - Tap to play");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
