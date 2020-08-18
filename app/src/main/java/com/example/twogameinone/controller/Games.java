package com.example.twogameinone.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.twogameinone.R;

public class Games extends AppCompatActivity {
    private Button mButtonTicTacToeGame;
    private Button mButtonFourInARowGame;
    FragmentManager fragmentManager;
    Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        setId();
        fragmentManager = getSupportFragmentManager();
        fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        setListener();
    }
    private void setId() {
        mButtonFourInARowGame = findViewById(R.id.btn_game_4_in_a_raw);
        mButtonTicTacToeGame = findViewById(R.id.btn_game_tic_tac_toe);
    }
    private void setListener() {
        mButtonTicTacToeGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragment == null) {
                    TicTacToeFragment ticTacToeFragment = new TicTacToeFragment();
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_container, ticTacToeFragment)
                            .commit();
                }
            }
        });

        mButtonFourInARowGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragment == null) {
                    FourInARowFragment fourInARowFragment = new FourInARowFragment();
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_container,fourInARowFragment)
                            .commit();
                }
            }
        });
    }
}