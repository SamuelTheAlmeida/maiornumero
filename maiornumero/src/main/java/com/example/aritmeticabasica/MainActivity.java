,,,,,,,,,,,,,,,,,,,package com.example.aritmeticabasica;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public TextView txtDigit1;
    public TextView txtDigit2;
    public TextView txtDigit3;
    public TextView txtAnswer;
    public EditText inputAnswer;
    public Button btnAnswer;
    public List<Integer> digits;
    public int answer;
    public int result;
    public double wins = 0;
    public double losses = 0;
    public double runs = 0;
    public double finalScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtDigit1 = findViewById(R.id.txtDigit1);
        txtDigit2 = findViewById(R.id.txtDigit2);
        txtDigit3 = findViewById(R.id.txtDigit3);
        txtAnswer = findViewById(R.id.txtAnswer);
        inputAnswer = findViewById(R.id.inputAnswer);

        startGame();

    }

    public void startGame() {
        // se já foram respondidas 5 rodadas, então mostra a nota final
        if (runs == 5) {
            finalScore = (wins/runs)*100;
            int finalScoreInt = (int)finalScore;
            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Fim de jogo");
            alertDialog.setMessage("Sua nota foi " + finalScoreInt + "!");

            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

        } else {
            newGame();
        }
    }

    public void newGame() {
        runs++;
        inputAnswer.setText("");
        digits = new ArrayList<Integer>();
        int drawResult;
        for (int i = 0; i < 3; i++) {
            drawResult = draw(0, 9);
            digits.add(drawResult);
        }
        txtDigit1.setText(String.valueOf(digits.get(0)));
        txtDigit2.setText(String.valueOf(digits.get(1)));
        txtDigit3.setText(String.valueOf(digits.get(2)));
        Collections.sort(digits, Collections.reverseOrder());
        String resultString = String.valueOf(digits.get(0)) + String.valueOf(digits.get(1)) + String.valueOf(digits.get(2));
        result = Integer.valueOf(resultString);
        txtAnswer.setText(String.valueOf(result));
    }


    public int draw(int min, int max) {
        Random r = new Random();
        int result = r.nextInt(max - min + 1) + min;
        return result;
    }

    public void checkAnswer(View view) {
        answer = Integer.valueOf(inputAnswer.getText().toString());
        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Resultado");
        if (answer == result) {
            wins++;
            alertDialog.setMessage("Resposta certa!");

        } else {
            losses++;
            alertDialog.setMessage("Resposta errada! A resposta correta era " + result);

        }
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startGame();
                    }
                });
        alertDialog.show();
    }
}
