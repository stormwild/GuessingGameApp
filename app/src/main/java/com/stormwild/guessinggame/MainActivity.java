package com.stormwild.guessinggame;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText guessText;
    private Button guessBtn;
    private TextView outputLabel;
    private int num;
    private int guesses = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        guessText = findViewById(R.id.guessText);
        guessBtn = findViewById(R.id.guessButton);
        outputLabel = findViewById(R.id.guessLabel);

        init();

        guessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkGuess();
            }
        });

        guessText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                checkGuess();
                return true;
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void init() {
        num = (int) (Math.random() * 100 + 1);
        outputLabel.setText("Enter a whole number between 1 and 100.");
        guessText.setText("");
        guessText.requestFocus();
        guessText.selectAll();
        Toast.makeText(MainActivity.this, "New Game Started", Toast.LENGTH_LONG).show();
    }

    public void checkGuess() {
        String guessStr = guessText.getText().toString();
        String message = "";

        try {
            int guess = Integer.parseInt(guessStr);

            if (guess < num) {
                message = guess + " is too low. Try again.";
            } else if (guess > num) {
                message = guess + " is too high. Try again.";
            } else {
                message = guess + " is correct. You win after " + guesses + " tries! Let's play again!";

                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }

            guesses++;
        } catch (Exception e) {
            message = "Enter a whole number between 1 and 100.";
        } finally {
            outputLabel.setText(message);
            guessText.requestFocus();
            guessText.selectAll();
        }
    }

    public void showAbout() {
        AlertDialog aboutDialog = new AlertDialog.Builder(MainActivity.this).create();
        aboutDialog.setTitle("About Guessing Game");
        aboutDialog.setMessage(Html.fromHtml("Copyright &copy; Guessing Game"));
        aboutDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        aboutDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        if (id == R.id.action_new_game) {
            init();
            return true;
        }

        if (id == R.id.action_game_stats) {
            return true;
        }

        if (id == R.id.action_about) {
            showAbout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
