package com.example.twogameinone.controller;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.twogameinone.R;
import com.example.twogameinone.model.Setting;
import com.example.twogameinone.model.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class FourInARowFragment extends Fragment {
    private int mIntLength;
    private User mUserGirl;
    private User mUserBoy;
    private Setting mSetting;
    private Button[] mButtons;
    private LinearLayout mLayoutButtons;
    private ImageButton mImageButtonSetting;
    private ImageButton mImageButtonActiveUserGirl;
    private ImageButton mImageButtonActiveUserBoy;
    private ImageButton mImageButtonDeactivateGirl;
    private ImageButton mImageButtonDeactivateBoy;
    private int[][] mIdButtons;
    private char[][] mCharsSituation;
    private View mLayoutMain;
    private static int sIndexButton;


    public FourInARowFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSetting = new Setting(7, "forinrow");
        mCharsSituation = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        mIntLength = mSetting.getmLength();
        mUserGirl = new User("girl", 'r', mCharsSituation, 3, "TicTacToe");
        mUserBoy = new User("boy", 'b', mCharsSituation, 3, "TicTocToe");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_four_in_a_row, container, false);
        mButtons = new Button[mIntLength * mIntLength];
        mIdButtons = new int[mIntLength][mIntLength];
        setViewId(view);
        createButtons(view, mIntLength);
        setListener();
        return view;
    }

    private void setViewId(View view) {
        mLayoutButtons = view.findViewById(R.id.layout_buttons);
        mImageButtonSetting = view.findViewById(R.id.image_btn_setting);
        mImageButtonActiveUserGirl = view.findViewById(R.id.image_btn_user_girl_active);
        mImageButtonActiveUserBoy = view.findViewById(R.id.image_btn_user_boy_active);
        mImageButtonDeactivateGirl = view.findViewById(R.id.image_btn_user_girl_deactive);
        mImageButtonDeactivateBoy = view.findViewById(R.id.image_btn_user_boy_deactive);
        mLayoutMain = view.findViewById(R.id.layout_main);
    }

    private void createButtons(View view, int intLength) {
        int id = 100;
        int widthAndHeight = 800;
        int index = 0;

        widthAndHeight = widthAndHeight / mIntLength;
        for (int i = 0; i < intLength; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, 0);
            LinearLayout mLinearLayout = new LinearLayout(getContext());
            mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            mLinearLayout.setLayoutParams(params);
            for (int j = 0; j < intLength; j++) {
                mIdButtons[i][j] = id + j;
                mButtons[index] = new Button(getContext());
                String strId = String.valueOf(i) + String.valueOf(j);
                mButtons[index].setText(strId);
                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(widthAndHeight, widthAndHeight);
                params1.setMargins(0, 0, 0, 0);
                mButtons[index].setLayoutParams(params1);
                mButtons[index].setGravity(Gravity.CENTER);
                mButtons[index].setPadding(0, 0, 0, 0);
                mButtons[index].setId(Integer.valueOf(mIdButtons[i][j]));
                mLinearLayout.addView(mButtons[index]);
                index++;
            }
            mLayoutButtons.addView(mLinearLayout);
            id += 100;
        }
    }

    private void startGame(String userSex, int row, int column) {
        if (userSex.equals("girl")) {
            mCharsSituation[row][column] = 'r';
            mImageButtonActiveUserGirl.setVisibility(View.GONE);
            mImageButtonDeactivateGirl.setVisibility(View.VISIBLE);
            mImageButtonActiveUserBoy.setVisibility(View.VISIBLE);
            mImageButtonDeactivateBoy.setVisibility(View.GONE);
        } else if (userSex.equals("boy")) {
            mCharsSituation[row][column] = 'b';
            mImageButtonActiveUserBoy.setVisibility(View.GONE);
            mImageButtonDeactivateBoy.setVisibility(View.VISIBLE);
            mImageButtonActiveUserGirl.setVisibility(View.VISIBLE);
            mImageButtonDeactivateGirl.setVisibility(View.GONE);
        }


    }

    private void setToast() {
        Toast toast = Toast.makeText(getContext(), R.string.selectable_cell, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }

    private void outWinner(String mUserSex) {
        String message = mUserSex + "won";
        for (int i = 0; i < mButtons.length; i++) {
            mButtons[i].setEnabled(false);

        }
        Snackbar snackbar = Snackbar.make(mLayoutMain, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void setListener() {

        for (int i = 0; i < mButtons.length; i++) {
            sIndexButton = i;
            if (mButtons[i].isClickable()) {
                mButtons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mImageButtonActiveUserGirl.getVisibility() == View.VISIBLE) {
                            if (mCharsSituation[0][0] == ' ') {
                                mButtons[sIndexButton].setBackgroundColor(Color.RED);
                                startGame(mUserGirl.getSexUser(), 0, 0);
                                if (mUserGirl.reviewWinner(mCharsSituation, mUserBoy.getSymbolUser(), 0, 0)) {
                                    outWinner("girl");
                                }
                            } else {
                                setToast();
                            }
                        } else if (mImageButtonActiveUserBoy.getVisibility() == View.VISIBLE) {
                            if (mCharsSituation[0][0] == ' ') {
                                mButtons[sIndexButton].setBackgroundColor(Color.BLUE);
                                startGame(mUserBoy.getSexUser(), 0, 0);
                                if (mUserBoy.reviewWinner(mCharsSituation, mUserGirl.getSymbolUser(), 0, 0)) {
                                    outWinner("boy");
                                }
                            } else {
                                setToast();
                            }
                        }
                    }
                });
            }
        }
    }

}