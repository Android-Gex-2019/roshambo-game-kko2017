package example.ke.roshambogame;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

//file: MainActivity.java
//name: KwangEun Oh
//date: Mar. 5, 2019
//Type: Assignment2 (Roshambo game)

public class MainActivity extends AppCompatActivity {

    private Roshambo roshambo = new Roshambo();

    /**
     * Display specified data specified by activity_main.xml
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * Event handler when clicking one of image buttons.
     * @param view
     */
    public void onImageButtonClicked(View view) {

        // Checks whether one of image buttons is clicked
        boolean clicked = view.isClickable();

        // The specified image is displayed on the image view
        // when clicking the button.
        switch(view.getId())
        {
            case R.id.rock_button:
                if(clicked)
                {
                    roshambo.playerMakesMove(Roshambo.ROCK);
                }
                break;
            case R.id.paper_button:
                if(clicked)
                {
                    roshambo.playerMakesMove(Roshambo.PAPER);
                }
                break;
            case R.id.scissor_button:
                if(clicked)
                {
                    roshambo.playerMakesMove(Roshambo.SCISSOR);
                }
                break;
            default:
                break;
        }
        // Change the image view by both player and game moves.
        changeImageView();
        // Display that player win, lose, or draw the game.
        displayResult();
    }

    /**
     * Display that player win, lose, or draw the game on the result textview.
     */
    private void displayResult() {
        int winLoseOrDraw = roshambo.winLoseOrDraw();
        TextView result = findViewById(R.id.result_label);

        if(winLoseOrDraw == Roshambo.WIN)
        {
            result.setText(R.string.win);
        }
        else if(winLoseOrDraw == Roshambo.LOSE)
        {
            result.setText(R.string.lose);
        }else if(winLoseOrDraw == Roshambo.DRAW)
        {
            result.setText(R.string.draw);
        }
    }

    /**
     * Change the image view by the player move and game move,
     * then waving those images.
     */
    public void changeImageView() {
        int playerMove = roshambo.getPlayerMove();
        int gameMove = roshambo.getGameMove();

        ImageView imgPlayerMove = findViewById(R.id.myMove_image);
        ImageView imgGameMove = findViewById(R.id.gamesMove_image);

        if(playerMove == Roshambo.ROCK)
        {
            imgPlayerMove.setImageResource(R.drawable.rock);
        }else if(playerMove == Roshambo.PAPER)
        {
            imgPlayerMove.setImageResource(R.drawable.paper);
        }else if(playerMove == Roshambo.SCISSOR)
        {
            imgPlayerMove.setImageResource(R.drawable.scissors);
        }

        if(gameMove == Roshambo.ROCK)
        {
            imgGameMove.setImageResource(R.drawable.rock);
        }else if (gameMove == Roshambo.PAPER)
        {
            imgGameMove.setImageResource(R.drawable.paper);
        }else if (gameMove == Roshambo.SCISSOR)
        {
            imgGameMove.setImageResource(R.drawable.scissors);
        }

        ObjectAnimator animatorPlayer = ObjectAnimator.ofFloat(imgPlayerMove,
                "rotationX", 0f, 360f).setDuration(500);

        ObjectAnimator animatorGame = ObjectAnimator.ofFloat(imgGameMove,
                "rotationY", 0f, 360f).setDuration(500);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animatorGame, animatorPlayer);
        set.setInterpolator(new AnticipateInterpolator());
        set.start();
    }
}
