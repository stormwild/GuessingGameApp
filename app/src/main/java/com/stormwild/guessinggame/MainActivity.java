package com.stormwild.guessinggame;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText guessText;
    private Button guessBtn;
    private TextView outputLabel;
    private int num;

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void init() {
        num = (int)(Math.random() * 100 + 1);
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
                message = guess + " is correct. You win! Let's play again!";
            }

        } catch (Exception e) {
            message = "Enter a whole number between 1 and 100.";
        } finally {
            outputLabel.setText(message);
            guessText.requestFocus();
            guessText.selectAll();
        }
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

        return super.onOptionsItemSelected(item);
    }
}