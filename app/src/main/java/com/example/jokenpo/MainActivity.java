package com.example.jokenpo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private class Choice {
        void Choice() {}

        boolean rock = false;
        boolean paper = false;
        boolean scissor = false;
    }

    private Choice playerChoice = new Choice();

    public void rockPlay(View view) {
        playerChoice.rock = true;
        startCalc();
    }

    public void paperPlay(View view) {
        playerChoice.paper = true;
        startCalc();
    }

    public void scissorPlay(View view) {
        playerChoice.scissor = true;
        startCalc();
    }

    private void startCalc() {
        Choice botChoice = setBotResult();
        String resultMessage = calcResult(playerChoice, botChoice);

        TextView result = findViewById(R.id.result);
        result.setText(resultMessage);
    }

    //Jogada do bot
    private Choice setBotResult() {
        String[] choices = {
                "rock",
                "paper",
                "scissor"
        };

        int randomInt = new Random().nextInt(3);

        String choice = choices[randomInt];
        Choice botChoice = new Choice();

        ImageView botResultImage = findViewById(R.id.defaultPlay);

        switch (choice) {
            case "rock":
                botResultImage.setImageResource(R.drawable.pedra);
                botChoice.rock = true;
                break;

            case "paper":
                botResultImage.setImageResource(R.drawable.papel);
                botChoice.paper = true;
                break;

            case "scissor":
                botResultImage.setImageResource(R.drawable.tesoura);
                botChoice.scissor = true;
                break;
        }

        return botChoice;
    }

    //Calcular resultado da partida
    private String calcResult(Choice playerChoice, Choice botChoice) {
        //Passar resultado para String
        String playerChoiceString = choice2string(playerChoice);
        String botChoiceString = choice2string(botChoice);

        Map<String, String> results = new HashMap<>();

        //Pedra
        results.put("rock-rock", "Empate\npedra x pedra");
        results.put("rock-paper", "Derrota\npedra x papel");
        results.put("rock-scissor", "Vitória\npedra x tesoura");

        //Papel
        results.put("paper-rock", "Vitória\npapel x pedra");
        results.put("paper-paper", "Empate\npapel x papel");
        results.put("paper-scissor", "Derrota\npapel x tesoura");

        //Tesoura
        results.put("scissor-rock", "Derrota\ntesoura x pedra");
        results.put("scissor-paper", "Vitória\ntesoura x papel");
        results.put("scissor-scissor", "Empate\ntesoura x tesoura");

        String concatenatePlays = playerChoiceString + "-" + botChoiceString;
        String resultMessage = results.getOrDefault(concatenatePlays, "Erro");

        return resultMessage;
    }

    private String choice2string(Choice choice) {
        String result = "";
        if (choice.rock) {
            result = "rock";
        } else if (choice.paper) {
            result = "paper";
        } else if (choice.scissor) {
            result = "scissor";
        }

        return result;
    }
}