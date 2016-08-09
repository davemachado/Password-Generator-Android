package com.davemachado.passwordgenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.CheckBox;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SeekBar seekbar;
    TextView textview;
    int passwordLength = 12;
    String lowercaseLetterString = "abcdefghijklmnopqrstuvwxyz", uppercaseLetterString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    char[] numberArray = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    char[] lowercaseArray = lowercaseLetterString.toCharArray();
    char[] uppercaseArray = uppercaseLetterString.toCharArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview = (TextView)findViewById(R.id.progress_text_view);
        seekbar = (SeekBar)findViewById(R.id.length_seekbar);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textview.setText(" " + i);
                passwordLength = i;
                Log.v("MainActivity", "Current Length: " + passwordLength);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.v("MainActivity", "Final Password Length: " + passwordLength);
            }
        });
    }

    public int getRandomNumber(int maxNumber, int minNumber) {
        int random = (int )(Math.random() * maxNumber + minNumber);
        return random;
    }

    public void generatePassword(View view) {
        char[] passwordArray = new char[passwordLength];
        int numberOfCategories = 0, currentLength = 0, randomNum = 0;
        CheckBox numberCheckBox = (CheckBox) findViewById(R.id.numbers_checkbox);
        boolean numbersChecked = numberCheckBox.isChecked();
        CheckBox lowerCaseCheckBox = (CheckBox) findViewById(R.id.lowercase_checkbox);
        boolean lowercaseChecked = lowerCaseCheckBox.isChecked();
        CheckBox upperCaseCheckBox = (CheckBox) findViewById(R.id.uppercase_checkbox);
        boolean uppercaseChecked = upperCaseCheckBox.isChecked();

        if(numbersChecked){
            Log.v("MainActivity", "numbers: " + numbersChecked);
            numberOfCategories++;
        }
        if(lowercaseChecked){
            Log.v("MainActivity", "lowercase: " + lowercaseChecked);
            numberOfCategories++;
        }
        if(uppercaseChecked){
            Log.v("MainActivity", "uppercase: " + uppercaseChecked);
            numberOfCategories++;
        }
        Log.v("MainActivity", "Number of Categories: " + numberOfCategories);
        if(numberOfCategories < 1) {
            Toast.makeText(this, "Please select at least 1 checkbox", Toast.LENGTH_SHORT).show();
            return;
        }

        while(currentLength < passwordLength) {
            randomNum = getRandomNumber(numberOfCategories, 1);

            if(randomNum == 1 && numbersChecked) {
                passwordArray[currentLength] = numberArray[getRandomNumber(8,1)];
                currentLength++;
            }
            if(randomNum == 2 && lowercaseChecked) {
                passwordArray[currentLength] = lowercaseArray[getRandomNumber(25,1)];
                currentLength++;
            }
            if(randomNum == 3 && uppercaseChecked) {
                passwordArray[currentLength] = uppercaseArray[getRandomNumber(25,1)];
                currentLength++;
            }
        }
        String passwordString = new String(passwordArray);
        TextView passwordTextView = (TextView) findViewById(R.id.password_text_view);
        passwordTextView.setText(passwordString);
    }
}